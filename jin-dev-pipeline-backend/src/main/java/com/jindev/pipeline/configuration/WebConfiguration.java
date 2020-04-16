package com.jindev.pipeline.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.http.MediaType;
import org.springframework.plugin.core.OrderAwarePluginRegistry;
import org.springframework.plugin.core.PluginRegistry;

import com.hubspot.jinjava.Jinjava;

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
  public PluginRegistry<LinkDiscoverer, MediaType> discoverers(
    OrderAwarePluginRegistry<LinkDiscoverer, MediaType> relProviderPluginRegistry) {
    return relProviderPluginRegistry;
  }
}
