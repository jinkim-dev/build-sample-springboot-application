package com.jindev.pipeline.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration extends WebMvcConfigurationSupport {


  @Bean
  public Docket swaggerApi() {
    return new Docket(DocumentationType.SWAGGER_2).apiInfo(swaggerInfo()).select()
      .apis(RequestHandlerSelectors.basePackage("com.jindev.pipeline.api"))
      .paths(PathSelectors.any())
      .build()
      .useDefaultResponseMessages(false);
      // 기본으로 세팅되는 200, 401, 403, 404 메시지를 표시하지 않음
  }

  private ApiInfo swaggerInfo() {
    return new ApiInfoBuilder().title("Jindev Build API Documentation")
      .description("Jindev Build Spring boot API Swagger Documentation")
      .license("jindev")
      .licenseUrl("https://github.com/jinkim-dev/build-sample-springboot-application")
      .version("1.0")
      .build();
  }

  @Override
  protected void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("swagger-ui.html")
      .addResourceLocations("classpath:/META-INF/resources/");

    registry.addResourceHandler("/webjars/**")
      .addResourceLocations("classpath:/META-INF/resources/webjars/");
  }
}
