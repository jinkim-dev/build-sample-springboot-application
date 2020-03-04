package com.jindev.pipeline.api.build;

import com.google.common.collect.Maps;
import com.hubspot.jinjava.Jinjava;
import com.jindev.pipeline.jenkins.JenkinsAPi;
import com.offbytwo.jenkins.model.JobWithDetails;
import com.offbytwo.jenkins.model.QueueReference;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Validated
public class BuildServiceImpl implements BuildService {

  private JenkinsAPi jenkinsAPi;
  private BuildDao buildDao;
  private Jinjava jinjava;

  public BuildServiceImpl(JenkinsAPi jenkinsAPi, BuildDao buildDao, Jinjava jinjava) {
    this.jenkinsAPi = jenkinsAPi;
    this.buildDao = buildDao;
    this.jinjava = jinjava;
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
  public Build modify(Build build) {
    return buildDao.save(build);
  }

  @Override
  public void createJob(String jobName) {
    ClassPathResource configResource = new ClassPathResource("/templates/jenkins/config.xml");
    ClassPathResource pipelineResource = new ClassPathResource("/templates/jenkins/jenkinsfile");
    try {
      Path pipelinePath = Paths.get(pipelineResource.getURI());
      String pipeline = new String(Files.readAllBytes(pipelinePath));
      // TODO: Render pipeline
      Path configPath = Paths.get(configResource.getURI());
      String jobTemplateXml = new String(Files.readAllBytes(configPath));
      Map<String, Object> context = Maps.newHashMap();
      context.put("script", pipeline);
      String jobXml = jinjava.render(jobTemplateXml, context);
      jenkinsAPi.createJob(jobName, jobXml);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void deleteById(long id) {
    buildDao.deleteById(id);
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
