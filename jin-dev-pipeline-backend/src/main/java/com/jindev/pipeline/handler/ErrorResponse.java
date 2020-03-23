package com.jindev.pipeline.handler;

import org.springframework.validation.BindingResult;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(staticName = "of")
@AllArgsConstructor(staticName = "of")
public class ErrorResponse {
    @NonNull
    private ErrorCode errorCode;
    private BindingResult bindingResult;
}
