package com.jindev.pipeline.api.build;

import com.offbytwo.jenkins.model.JobWithDetails;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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

  @GetMapping
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

  @GetMapping("/{buildId}")
  public ResponseEntity<Map<String, Object>> get(@PathVariable int buildId) {
    Map<String, Object> map = new HashMap<>();
    Build build = buildService.get(buildId);
    JobWithDetails details = buildService.getJob(build.getAppName());
    BuildDto buildDto = Optional.ofNullable(build).map(b -> convertToDto(b, details)).orElse(null);
    map.put("build", buildDto);
    return ResponseEntity.ok(map);
  }

  private BuildDto convertToDto(Build build, JobWithDetails details) {
    BuildDto buildDto = modelMapper.map(build, BuildDto.class);
    buildDto.setLatestBuildNumber(details.getLastBuild().getNumber());
    try {
      buildDto.setLatestBuildResult(details.getLastBuild().details().getResult().name());
    } catch (IOException e) {
      log.error(e.getMessage());
    }
    return buildDto;
  }

  // test
  //    @GetMapping("/{build}/run")
  //    public Map<String, Object> run() {
  //        Map<String, Object> map = new HashMap<>();
  //        map.put("test", "/{build}");
  //        return map;
  //    }

  // test
  //    @GetMapping("/{build}/jobs")
  //    public Map<String, Object> getJobs() {
  //        Map<String, Object> map = new HashMap<>();
  //        map.put("test", "/{build}/jobs");
  //        return map;
  //    }
}
