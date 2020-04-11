package com.jindev.pipeline.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class MessageProperties {
  @Value("${message.locale")
  private String locale;

  @Value("${message.encoding}")
  private String encoding;
}
