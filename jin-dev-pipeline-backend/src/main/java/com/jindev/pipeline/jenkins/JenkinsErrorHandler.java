package com.jindev.pipeline.jenkins;

import java.nio.channels.ConnectionPendingException;

import com.jindev.pipeline.common.GlobalErrorHandler;
import com.jindev.pipeline.common.GlobalException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JenkinsErrorHandler {

  public static GlobalErrorHandler DEFAULT_JENKINS_ERROR_HANDLER = e -> {
    log.error(e.getMessage());
    final GlobalException ge;
    if (e instanceof ConnectionPendingException) {
      ge = new GlobalException(JenkinsError.CONNECTION_FAIL, e);
    } else {
      ge = new GlobalException(JenkinsError.UNKNOWN, e);
    }
    throw ge;
  };

}
