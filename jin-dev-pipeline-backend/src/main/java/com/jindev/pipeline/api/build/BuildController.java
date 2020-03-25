package com.jindev.pipeline.api.build;

import com.offbytwo.jenkins.model.BuildWithDetails;
import com.offbytwo.jenkins.model.JobWithDetails;
import com.offbytwo.jenkins.model.QueueReference;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

import javax.xml.ws.Response;

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

  @GetMapping("/{buildId}")
  public ResponseEntity<Map<String, Object>> get(@PathVariable long buildId) {
    Map<String, Object> map = new HashMap<>();
    Optional<Build> build = buildService.get(buildId);
    build
        .map(Build::getAppName)
        .ifPresent(
            appName -> {
              JobWithDetails details = buildService.getJob(appName);
              BuildDto buildDto = build.map(b -> convertToDto(b, details)).orElse(null);
              map.put("build", buildDto);
            });
    return ResponseEntity.ok(map);
  }

  @PostMapping
  public ResponseEntity save(@RequestBody Build build) {
    build = buildService.save(build);
    buildService.createJob(build);
    return ResponseEntity.created(
            URI.create(System.getenv("server.servlet.context-path") + build.getAppName()))
        .build();
  }

  @PutMapping("/{buildId}")
  public ResponseEntity update(@PathVariable long buildId, @RequestBody Build build) {
    Map<String, Object> map = new HashMap<>();
    build.setId(buildId);
    build = buildService.modify(build);
    map.put("build", build);
    return ResponseEntity.ok(map);
  }

  @DeleteMapping("/{buildId}")
  public ResponseEntity delete(@PathVariable long buildId) {
    buildService.deleteById(buildId);
    return ResponseEntity.ok().build();
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
    if (details.getLastBuild() != null && details.getLastBuild().getNumber() > -1) {
      buildDto.setLatestBuildNumber(details.getLastBuild().getNumber());
      List<BuildWithDetailsDto> detailsDtos =
          details.getBuilds().stream()
              .map(
                  b -> {
                    try {
                      return b.details();
                    } catch (IOException e) {
                      log.error(e.getMessage());
                    }
                    return null;
                  })
              .filter(Objects::nonNull)
              .map(this::convertToDetailDto)
              .collect(Collectors.toList());

      Optional.of(detailsDtos)
          .filter(dtos -> !ObjectUtils.isEmpty(dtos))
          .ifPresent(
              dtos -> {
                buildDto.setLatestBuildResult(dtos.get(dtos.size() - 1).getResult().name());
              });

      buildDto.setBuilds(detailsDtos);
    }
    return buildDto;
  }

  private BuildWithDetailsDto convertToDetailDto(BuildWithDetails details) {
    return modelMapper.map(details, BuildWithDetailsDto.class);
  }
}
