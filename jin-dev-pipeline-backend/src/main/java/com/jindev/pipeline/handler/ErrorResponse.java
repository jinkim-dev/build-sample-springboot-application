package com.jindev.pipeline.handler;

import org.springframework.validation.BindingResult;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(staticName = "of")
public class ErrorResponse {
    ErrorCode errorCode;
    BindingResult bindingResult;
}
