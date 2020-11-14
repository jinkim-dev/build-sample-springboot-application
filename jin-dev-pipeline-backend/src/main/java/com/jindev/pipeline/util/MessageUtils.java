package com.jindev.pipeline.util;

import java.util.Locale;

import org.springframework.context.support.MessageSourceAccessor;

import com.jindev.pipeline.common.StaticContextAccessor;

public class MessageUtils {

  private static final MessageSourceAccessor messageSourceAccessor;

  static {
    messageSourceAccessor = StaticContextAccessor
      .getBean(MessageSourceAccessor.class);
  }

  public static String getMessage(String code, Object[] args) {
    return messageSourceAccessor.getMessage(code, args, code, Locale.getDefault());
  }
}
