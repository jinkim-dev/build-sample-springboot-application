package com.jindev.pipeline.jenkins;

import com.jindev.pipeline.common.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum JenkinsError implements ErrorCode {
  CONNECTION_FAIL(500, "J001"),
  UNKNOWN(500, "J002");
  private final int status;
  private final String code;
}
