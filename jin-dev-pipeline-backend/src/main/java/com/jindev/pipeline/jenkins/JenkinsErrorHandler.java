package com.jindev.pipeline.jenkins;

import java.nio.channels.ConnectionPendingException;

import org.apache.http.client.HttpResponseException;
import org.springframework.http.HttpStatus;

import com.jindev.pipeline.common.GlobalErrorHandler;
import com.jindev.pipeline.common.GlobalException;

public class JenkinsErrorHandler {

  public static GlobalErrorHandler DEFAULT_JENKINS_ERROR_HANDLER = e -> {
    final GlobalException ge;
    if (e instanceof ConnectionPendingException) {
      ge = new GlobalException(JenkinsError.CONNECTION_FAIL, e);
    } else if (e instanceof HttpResponseException) {
      ge = new GlobalException(JenkinsError.UNKNOWN, e, null,
        HttpStatus.valueOf(((HttpResponseException) e).getStatusCode()));
    } else {
      ge = new GlobalException(JenkinsError.UNKNOWN, e);
    }
    throw ge;
  };

}
