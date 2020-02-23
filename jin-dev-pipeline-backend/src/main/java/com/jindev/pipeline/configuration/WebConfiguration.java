package com.jindev.pipeline.configuration;

import com.hubspot.jinjava.Jinjava;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
}
