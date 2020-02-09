package com.jindev.pipeline.api.build;

import com.jindev.pipeline.jenkins.JenkinsAPi;
import com.offbytwo.jenkins.model.Job;
import com.offbytwo.jenkins.model.JobWithDetails;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;

@Service
@Validated
public class BuildServiceImpl implements BuildService {

  private JenkinsAPi jenkinsAPi;

  private BuildDao buildDao;

  public BuildServiceImpl(JenkinsAPi jenkinsAPi, BuildDao buildDao) {
    this.jenkinsAPi = jenkinsAPi;
    this.buildDao = buildDao;
  }

  @Override
  public List<Build> getList() {
    return buildDao.findAll();
  }

  @Override
  public Build get(long buildId) {
    return buildDao.findById(buildId).orElse(null);
  }

  @Override
  public int run(long buildId) {
    return 0;
  }

  @Override
  public JobWithDetails getJob(String jobName) {
    return jenkinsAPi.getJob(jobName);
  }
}
