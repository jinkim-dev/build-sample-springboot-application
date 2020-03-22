package com.jindev.pipeline.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * javax.validation.Valid or @Validated으로  binding error 발생 시 발생한다.
     * HttpMessageConverter에서 등록한 HttpMessageConverter binding 못할 경우 발생한다.
     * 주로 @RequestBody, @RequestBody 어노테이션에서 발생
     */
     @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotvalidException(MethodArgumentNotValidException e) {
         log.error("handleMethodArgumentNotValidException", e);
         final ErrorResponse response = ErrorResponse.of(CommonErrorCode.INVALID_METHOD_VALUE, e.getBindingResult());
         return ResponseEntity.badRequest().body(response);
     }
}
