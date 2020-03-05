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
  public void createJob(Build build) {
    try {
      String pipeline = renderPipeline(build);
      String jobXml = renderJobXml(pipeline);
      jenkinsAPi.createJob(build.getAppName(), jobXml);
    } catch (IOException e) {
      log.error(e.getMessage());
    }
  }

  private String renderJobXml(String script) throws IOException {
    Map<String, Object> context = Maps.newHashMap();
    context.put("script", script);
    return render("/templates/jenkins/config.xml", context);
  }

  private String renderPipeline(Build build) throws IOException {
    Map<String, Object> context = Maps.newHashMap();
    context.put("gitAddress", build.getGitAddress());
    return render("/templates/jenkins/jenkinsfile", context);
  }

  private String render(String templatePath, Map<String, Object> context) throws IOException {
    ClassPathResource resource = new ClassPathResource(templatePath);
    Path path = Paths.get(resource.getURI());
    String template = new String(Files.readAllBytes(path));

    return jinjava.render(template, context);
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
