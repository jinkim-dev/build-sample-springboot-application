package com.jindev.pipeline.util;

import java.util.Locale;

import org.springframework.context.MessageSource;

import com.jindev.pipeline.common.StaticContextAccessor;

public class MessageUtils {

  private static MessageSource messageSource;

  static {
    messageSource = StaticContextAccessor
      .getBean(MessageSource.class);
  }

  public static String getMessage(String code, Object[] args) {
    return messageSource.getMessage(code, args, code, Locale.getDefault());
  }
}
