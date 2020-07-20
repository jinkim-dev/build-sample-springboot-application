package com.jindev.pipeline.config;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.offbytwo.jenkins.JenkinsServer;

@Configuration
public class JenkinsConfig {
  @Value("${jenkins.url}")
  private String jenkinsUrl;

  @Value("${jenkins.id}")
  private String jenkinsId;

  @Value("${jenkins.password}")
  private String jenkinsPassword;

  @Bean
  public JenkinsServer jenkins() throws URISyntaxException {
    return new JenkinsServer(new URI(jenkinsUrl), jenkinsId, jenkinsPassword);
  }
}
