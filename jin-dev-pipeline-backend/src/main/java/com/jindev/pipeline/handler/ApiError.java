package com.jindev.pipeline.handler;

import java.io.Serializable;
import java.util.List;

import org.springframework.validation.BindingResult;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@JsonInclude(Include.NON_NULL)
@RequiredArgsConstructor(staticName = "of")
@AllArgsConstructor(staticName = "of")
public class ApiError implements Serializable {
    @NonNull
    @JsonProperty("error_code")
    private ErrorCode errorCode;

    @NonNull
    private String message;

    @JsonProperty("field_error")
    private List<FieldErrorDto> fieldErrorDtos;
}
