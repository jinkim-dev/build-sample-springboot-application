package com.jindev.pipeline.common;

import org.springframework.http.HttpStatus;

import com.jindev.pipeline.util.MessageUtils;

import lombok.Getter;

@Getter
public class GlobalException extends RuntimeException {
  private ErrorCode errorCode;
  private Throwable cause;
  private Object[] args;
  private HttpStatus status;

  public GlobalException(ErrorCode errorCode) {
    this(errorCode, null);
  }

  public GlobalException(ErrorCode errorCode, Throwable cause) {
    this(errorCode, cause, null);
  }

  public GlobalException(ErrorCode errorCode, Throwable cause, Object[] args) {
    this(errorCode, cause, args, null);
  }

  public GlobalException(ErrorCode errorCode, Throwable cause, Object[] args, HttpStatus status) {
    this.errorCode = errorCode;
    this.cause = cause;
    this.args = args;
    this.status = status;
  }

  @Override
  public String getMessage() {
    return MessageUtils.getMessage(getMessageCode(), this.args);
  }

  private String getMessageCode() {
    return this.errorCode.toString().toLowerCase().replaceAll("_", ".");
  }
}
