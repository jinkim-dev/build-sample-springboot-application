package com.jindev.pipeline.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpStatusCodeException;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalControllerAdvice {

  @ExceptionHandler(value = Exception.class)
  public ResponseEntity<ApiError> handleException(Exception e) {
    GlobalException ge = convertGlobalException(e);
    return ResponseEntity.status(ge.getStatus()).body(
      ApiError.of(ge.getErrorCode(), ge.getMessage())
    );
  }

  private GlobalException convertGlobalException(Exception e) {
    GlobalException ge;
    if (e instanceof GlobalException) {
      ge = (GlobalException) e;
    } else {
      HttpStatus httpStatus;
      if (e instanceof HttpStatusCodeException) {
        httpStatus = ((HttpStatusCodeException) e).getStatusCode();
      } else {
        httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
      }
      ge = new GlobalException(CommonErrorCode.UNKNOWN, e, null);
    }
    return ge;
  }

}
