package com.jindev.pipeline.configuration;

import java.util.concurrent.TimeUnit;

import com.hubspot.jinjava.Jinjava;

import org.apache.commons.lang.CharEncoding;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class WebConfiguration {

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }

  @Bean
  public Jinjava jinjava() {
    return new Jinjava();
  }

  @Bean
  public MessageSource messageSource() {
    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
    messageSource.setBasename("classpath:/com/jindev/pipeline/jenkins");
    messageSource.setDefaultEncoding(CharEncoding.UTF_8);
    messageSource.setUseCodeAsDefaultMessage(true);
    messageSource.setCacheSeconds((int) TimeUnit.HOURS.toSeconds(1));
    messageSource.setFallbackToSystemLocale(false);
    return messageSource;
  }
}
