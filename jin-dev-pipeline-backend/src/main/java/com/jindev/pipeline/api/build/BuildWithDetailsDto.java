package com.jindev.pipeline.api.build;

import com.offbytwo.jenkins.model.BuildResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BuildWithDetailsDto {
    private int id;
    private String displayName;
    private String url;
    private boolean building;
    private long duration;
    private long estimatedDuration;
    private BuildResult result;
}
