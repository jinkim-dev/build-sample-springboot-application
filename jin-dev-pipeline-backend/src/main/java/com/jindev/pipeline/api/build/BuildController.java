package com.jindev.pipeline.api.build;

import com.offbytwo.jenkins.model.JobWithDetails;
import com.offbytwo.jenkins.model.QueueReference;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/builds")
public class BuildController {

  private BuildService buildService;
  private ModelMapper modelMapper;

  public BuildController(BuildService buildService, ModelMapper modelMapper) {
    this.buildService = buildService;
    this.modelMapper = modelMapper;
  }

  @GetMapping(value = "")
  public Map<String, Object> getList() {
    Map<String, Object> map = new HashMap<>();
    List<Build> builds = buildService.getList();
    List<BuildDto> buildDtos =
        builds.stream()
            .map(
                build -> {
                  JobWithDetails details = buildService.getJob(build.getAppName());
                  return convertToDto(build, details);
                })
            .collect(Collectors.toList());
    map.put("builds", buildDtos);
    return map;
  }

  @GetMapping("/{buildName}")
  public ResponseEntity<Map<String, Object>> get(@PathVariable String buildName) {
    Map<String, Object> map = new HashMap<>();
    Build build = buildService.get(buildName);
    JobWithDetails details = buildService.getJob(buildName);
    BuildDto buildDto = Optional.ofNullable(build).map(b -> convertToDto(b, details)).orElse(null);
    map.put("build", buildDto);
    return ResponseEntity.ok(map);
  }

  @GetMapping("/{buildName}/build")
  public Map<String, Object> build(@PathVariable String buildName) {
    Map<String, Object> map = new HashMap<>();
    QueueReference queueReference = buildService.build(buildName);
    map.put("result", queueReference);
    return map;
  }

  private BuildDto convertToDto(Build build, JobWithDetails details) {
    BuildDto buildDto = modelMapper.map(build, BuildDto.class);
    try {
      if (details.getLastBuild().getNumber() > -1) {
        buildDto.setLatestBuildNumber(details.getLastBuild().getNumber());
        buildDto.setLatestBuildResult(details.getLastBuild().details().getResult().name());
      }
    } catch (IOException e) {
      log.error(e.getMessage());
    }
    return buildDto;
  }

}
