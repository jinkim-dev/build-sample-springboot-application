package com.jindev.pipeline.api.build;

import lombok.Data;

import java.util.List;

@Data
public class BuildDto {
  private Long id;
  private String appName;
  private String description;
  private String buildTool;
  private String gitAddress;
  private String targetServer;
  private int latestBuildNumber;
  private String latestBuildResult;
  private List<BuildWithDetailsDto> builds;
}
