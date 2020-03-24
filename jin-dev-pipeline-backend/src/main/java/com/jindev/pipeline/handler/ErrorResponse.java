package com.jindev.pipeline.handler;

import org.springframework.validation.BindingResult;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@JsonInclude(Include.NON_NULL)
@RequiredArgsConstructor(staticName = "of")
@AllArgsConstructor(staticName = "of")
public class ErrorResponse {
    @NonNull
    private ErrorCode errorCode;
    private BindingResult bindingResult;
}
