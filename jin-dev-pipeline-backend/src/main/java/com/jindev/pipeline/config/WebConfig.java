package com.jindev.pipeline.config;

import java.util.Set;
import java.util.regex.Pattern;

import org.modelmapper.ModelMapper;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

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

  @Bean
  public MessageSource messageSource() {
    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
    messageSource.setBasenames(getBasenames());
    messageSource.setDefaultEncoding("UTF-8");
    return messageSource;
  }

  private String[] getBasenames() {
    final String parentPackage = "com.jindev.pipeline";
    final String propPattern = "\\w*-exception_\\w*.properties";
    Reflections reflections = new Reflections(parentPackage, new ResourcesScanner());
    Set<String> properties = reflections.getResources(Pattern.compile(propPattern));
    return properties.stream()
      .map(prop -> prop.replaceAll(String.format("([\\w/]*)/%s", propPattern), "$1"))
      .toArray(size -> new String[size]);
  }
}
