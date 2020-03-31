package com.jindev.pipeline.handler;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private ErrorCode errorCode;
    private Throwable cause;

    public BusinessException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public BusinessException(ErrorCode errorCode, Throwable cause) {
        this.errorCode = errorCode;
        this.cause = cause;
    }
}
