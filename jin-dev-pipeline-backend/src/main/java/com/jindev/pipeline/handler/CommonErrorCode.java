package com.jindev.pipeline.handler;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CommonErrorCode implements ErrorCode {
    INVALID_INPUT_VALUE(400, "C001", "Invalid Input Value"),
    METHOD_NOT_ALLOWED(405, "C002", "Method Not Allowed"),
    HANDLE_ACCESS_DENIED(403, "C003", "Access is Denied")
    ;
    private final int status;
    private final String code;
    private final String message;
}
