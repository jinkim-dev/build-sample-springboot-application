package com.jindev.pipeline.api.build;

import com.offbytwo.jenkins.model.JobWithDetails;

import java.util.List;

public interface BuildService {
    List<Build> getList();
    Build get(long buildId);
    int run(long buildId);
    JobWithDetails getJob(String jobName);
}
