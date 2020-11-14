package com.jindev.pipeline.common;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalControllerAdvice {

  @ExceptionHandler(value = Exception.class)
  public ResponseEntity<ApiError> handleException(Exception e) {
    GlobalException ge;
    if (e instanceof GlobalException) {
      ge = (GlobalException) e;
    } else {
      ge = new GlobalException(CommonErrorCode.UNKNOWN, e);
    }

    return ResponseEntity.status(ge.getStatus()).body(
      ApiError.of(ge.getErrorCode(), ge.getMessage())
    );
  }

}
