package com.jindev.pipeline.api.build;

import com.jindev.pipeline.jenkins.JenkinsAPi;
import com.offbytwo.jenkins.model.JobWithDetails;
import com.offbytwo.jenkins.model.QueueReference;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

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
  public Build get(String buildName) {
    return buildDao.findByAppName(buildName).orElse(null);
  }

  @Override
  public Build save(Build build) {
    return buildDao.save(build);
  }

  @Override
  public JobWithDetails getJob(String jobName) {
    return jenkinsAPi.getJob(jobName);
  }

  @Override
  public QueueReference build(String jobName) {
    return Optional.ofNullable(getJob(jobName)).map(jenkinsAPi::build).orElse(null);
  }
}
