package com.jindev.pipeline.handler;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CommonErrorCode implements ErrorCode {
    INVALID_INPUT_VALUE(400, "C001", "Invalid Input Value"),
    INVALID_METHOD_VALUE(405, "C002", "Invalid Method"),
    HANDLE_ACCESS_DENIED(403, "C003", "Access is Denied")
    ;
    private final int status;
    private final String code;
    private final String message;
}
