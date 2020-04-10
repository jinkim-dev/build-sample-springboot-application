package com.jindev.pipeline.handler;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  private MessageSource messageSource;

  public GlobalExceptionHandler(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  /**
   * javax.validation.Valid or @Validated으로 binding error 발생 시 발생한다. HttpMessageConverter에서 등록한
   * HttpMessageConverter binding 못할 경우 발생한다. 주로 @RequestBody, @RequestBody 어노테이션에서 발생
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  protected ResponseEntity<ApiError> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException e) {
    log.error(
        "handleMethodArgumentNotValidException : {}, {}", e.getMessage(), e.getBindingResult());
    ErrorCode errorCode = CommonErrorCode.INVALID_INPUT_VALUE;
    BindingResult bindingResult = e.getBindingResult();
    List<FieldErrorDto> fieldErrorDto =
        bindingResult.getFieldErrors().stream()
            .map(FieldErrorDto::new)
            .collect(Collectors.toList());
    final ApiError response = ApiError.of(errorCode, e.getMessage(), fieldErrorDto);
    return ResponseEntity.badRequest().body(response);
  }

  /**
   * 지원하지 않는 HTTP method 호출 할 경우 발생
   *
   * @return
   */
  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  protected ResponseEntity<ApiError> handleHttpRequestMethodNotSupportedException(
      HttpRequestMethodNotSupportedException e) {
    log.error("handleHttpRequestMethodNotSupportedException", e);
    ErrorCode errorCode = CommonErrorCode.METHOD_NOT_ALLOWED;
    final ApiError response = ApiError.of(errorCode, e.getMessage());
    return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(response);
  }

  @ExceptionHandler(BusinessException.class)
  protected ResponseEntity<ApiError> handleBusinessException(final BusinessException e) {
    log.error("handleBusinessException", e);
    final ErrorCode errorCode = e.getErrorCode();
    final ApiError response = ApiError.of(errorCode, resolveExceptionToMessage(e));
    return ResponseEntity.status(errorCode.getStatus()).body(response);
  }

  @ExceptionHandler(Exception.class)
  protected ResponseEntity<ApiError> handleException(Exception e) {
    log.error("handleEntityNotFoundException", e);
    final ErrorCode errorCode = CommonErrorCode.INTERNAL_SERVER_ERROR;
    final ApiError response = ApiError.of(errorCode, e.getMessage());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
  }

  private String resolveExceptionToMessage(BusinessException be) {
    Locale locale = LocaleContextHolder.getLocale();
    String errorKind = be.getErrorCode().getClass().getSimpleName().replace("Error", "");
    String key = String.format("%s.%s", errorKind, be.getErrorCode().toString()).toLowerCase();
    String message = messageSource
      .getMessage(key, null, locale);
    return message;
  }
}
