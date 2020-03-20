package com.jindev.pipeline.api.build;

import com.offbytwo.jenkins.model.JobWithDetails;
import com.offbytwo.jenkins.model.QueueReference;

import java.util.List;
import java.util.Optional;

public interface BuildService {
    List<Build> getList();
    Optional<Build> get(long id);
    Build save(Build build);
    Build modify(Build build);
    void deleteById(long id);
    void createJob(Build build);
    JobWithDetails getJob(String jobName);
    QueueReference build(String jobName);
}
