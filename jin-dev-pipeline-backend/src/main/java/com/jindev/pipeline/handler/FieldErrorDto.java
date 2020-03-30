package com.jindev.pipeline.handler;

import org.springframework.validation.FieldError;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FieldErrorDto {
  private String fieldName;
  private String message;

  public FieldErrorDto(FieldError fieldError) {
      fieldName = fieldError.getField();
      message = fieldError.getDefaultMessage();
  }
}
