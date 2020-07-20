package com.jindev.pipeline.config;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.jindev.pipeline.properties.MessageProperties;

@Configuration
public class MessageConfig implements WebMvcConfigurer {

  private MessageProperties messageProperties;

    public MessageConfig(MessageProperties messageProperties) {
    this.messageProperties = messageProperties;
  }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
            .addMapping("/**")
            .allowedOrigins("*")
            .allowCredentials(true)
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
    }

    @Override
  public void addInterceptors(InterceptorRegistry registry) {
    // 언터셉터를 시스템 레지스트리에 등록
    registry.addInterceptor(localeChangeInterceptor());
  }

  @Bean
  public LocaleResolver localeResolver() {
    // 세션에 지역설정. default KOREAN = 'ko'
    SessionLocaleResolver resolver = new SessionLocaleResolver();
    resolver.setDefaultLocale(Locale.forLanguageTag(messageProperties.getLocale()));
    return resolver;
  }

  @Bean
  public LocaleChangeInterceptor localeChangeInterceptor() {
    // 지역설정을 변경하는 인터셉터. 요청시 파라미터에 lang 정보를 지정하면 언어가 변경됨
    LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
    interceptor.setParamName("lang");
    return interceptor;
  }

  @Bean
  public MessageSource messageSource() {
    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
    messageSource.setBasename("classpath:/com/jindev/pipeline/jenkins/jenkins-exception");
    messageSource.setDefaultEncoding(messageProperties.getEncoding());
    messageSource.setUseCodeAsDefaultMessage(true);
    messageSource.setCacheSeconds((int) TimeUnit.HOURS.toSeconds(1));
    messageSource.setFallbackToSystemLocale(false);
    return messageSource;
  }

}
