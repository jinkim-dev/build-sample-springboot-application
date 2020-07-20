package com.jindev.pipeline.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hubspot.jinjava.Jinjava;

@Configuration
public class WebConfig {

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }

  @Bean
  public Jinjava jinjava() {
    return new Jinjava();
  }

//  @Override
//  public void addCorsMappings(CorsRegistry registry) {
//    registry
//        .addMapping("/**")
//        .allowedOrigins("*")
//        .allowedMethods("*")
//        .allowCredentials(false)
//        .maxAge(-1);
//  }

}
