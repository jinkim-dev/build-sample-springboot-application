package com.jindev.pipeline.handler;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private ErrorCode errorCode;

    public BusinessException(Throwable cause) {
        super(cause);
    }
}
