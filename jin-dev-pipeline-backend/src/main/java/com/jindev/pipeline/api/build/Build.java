package com.jindev.pipeline.api.build;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;

@lombok.NoArgsConstructor(access = AccessLevel.PROTECTED)
@lombok.AllArgsConstructor(access = AccessLevel.PROTECTED)
@lombok.Getter
@lombok.Setter
@lombok.Builder
@lombok.EqualsAndHashCode
@Entity
public class Build {

  @Id @GeneratedValue private Long id;

  @NotNull
  @Column(name = "APP_NAME", length = 100, nullable = false)
  private String appName;

  @Column(name = "DESCRIPTION", length = 500)
  private String description;

  @Column(name = "BUILD_TOOL")
  private String buildTool;

  @Column(name = "GIT_ADDRESS")
  private String gitAddress;

  @Column(name = "TARGET_SERVER")
  private String targetServer;
}
