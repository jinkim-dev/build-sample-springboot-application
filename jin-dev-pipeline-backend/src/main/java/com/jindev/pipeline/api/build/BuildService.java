package com.jindev.pipeline.api.build;

import com.offbytwo.jenkins.model.JobWithDetails;
import com.offbytwo.jenkins.model.QueueReference;

import java.util.List;

public interface BuildService {
    List<Build> getList();
    Build get(String buildName);
    Build save(Build build);
    Build modify(Build build);
    void deleteById(long id);
    void createJob(Build build);
    JobWithDetails getJob(String jobName);
    QueueReference build(String jobName);
}
