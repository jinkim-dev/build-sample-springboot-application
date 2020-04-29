package com.jindev.pipeline.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommonErrorCode implements ErrorCode {
  INVALID_INPUT_VALUE(400, "C001", "Invalid Input Value"),
  METHOD_NOT_ALLOWED(405, "C002", "Method Not Allowed"),
  HANDLE_ACCESS_DENIED(403, "C003", "Access is Denied"),
  INTERNAL_SERVER_ERROR(500, "C005", "Internal Server Error"),
  UNKNOWN(500, "C006", "Unknown Error");
  private final int status;
  private final String code;
  private final String message;
}
