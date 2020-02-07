package com.jindev.pipeline.configuration;

import com.offbytwo.jenkins.JenkinsServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class JenkinsConfiguration {
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
