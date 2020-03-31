package com.jindev.pipeline.handler;

import com.jindev.pipeline.jenkins.JenkinsError;
import java.nio.channels.ConnectionPendingException;
import org.apache.http.client.HttpResponseException;

public class JenkinsErrorHandler {

  public static BusinessException handle(Exception e) {
    if (e instanceof ConnectionPendingException) {
      return new BusinessException(JenkinsError.CONNECTION_FAIL);
    } else if(e instanceof HttpResponseException) {
      return new BusinessException(JenkinsError.UNKNOWN);
    }

    return new BusinessException(JenkinsError.UNKNOWN);
  }
}
