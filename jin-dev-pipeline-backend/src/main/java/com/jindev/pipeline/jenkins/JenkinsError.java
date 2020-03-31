package com.jindev.pipeline.jenkins;

import com.jindev.pipeline.handler.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum JenkinsError implements ErrorCode {
  CONNECTION_FAIL(500, "J001", "Jenkins Server Connection Fail"),
  UNKNOWN(500, "J002", "Jenkins Server Unknown Error");
  private final int status;
  private final String code;
  private final String message;
}
