package com.jindev.pipeline.api.build;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.google.common.collect.Maps;
import com.hubspot.jinjava.Jinjava;
import com.jindev.pipeline.common.GlobalException;
import com.jindev.pipeline.jenkins.JenkinsAPi;
import com.jindev.pipeline.jenkins.JenkinsError;
import com.offbytwo.jenkins.model.JobWithDetails;
import com.offbytwo.jenkins.model.QueueReference;

import lombok.extern.slf4j.Slf4j;

@Service
@Validated
@Slf4j
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
  public Optional<Build> get(long id) {
    return buildDao.findById(id);
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
  public void createJob(Build build) {
    String pipeline = renderPipeline(build);
    String jobXml = renderJobXml(pipeline);
    jenkinsAPi.createJob(build.getAppName(), jobXml);
  }

  private String renderJobXml(String script) {
    Map<String, Object> context = Maps.newHashMap();
    context.put("script", script);
    return render("/templates/jenkins/config.xml", context);
  }

  private String renderPipeline(Build build) {
    Map<String, Object> context = Maps.newHashMap();
    context.put("gitAddress", build.getGitAddress());
    return render("/templates/jenkins/jenkinsfile", context);
  }

  private String render(String templatePath, Map<String, Object> context) {
    try {
      ClassPathResource resource = new ClassPathResource(templatePath);
      Path path = Paths.get(resource.getURI());
      String template = new String(Files.readAllBytes(path));
      return jinjava.render(template, context);
    } catch (IOException ie) {
     throw new GlobalException(JenkinsError.RESOURCE_FILE_ACCESS_FAIL, ie.getCause());
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
