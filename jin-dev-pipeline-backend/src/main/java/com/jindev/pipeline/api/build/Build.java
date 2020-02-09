package com.jindev.pipeline.api.build;

import com.offbytwo.jenkins.model.JobWithDetails;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@lombok.NoArgsConstructor(access = AccessLevel.PROTECTED)
@lombok.AllArgsConstructor(access = AccessLevel.PROTECTED)
@lombok.Getter
@lombok.Setter
@lombok.Builder
@Entity
public class Build {

    @Id
    @GeneratedValue
    private Long id;

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
