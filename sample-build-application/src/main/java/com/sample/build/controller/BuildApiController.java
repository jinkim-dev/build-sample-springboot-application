package com.sample.build.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.build.model.Build;
import com.sample.build.service.BuildService;

@RestController
@RequestMapping("/api/builds")
public class BuildApiController {
	
	private BuildService buildService;

	@Autowired
	public BuildApiController(BuildService buildService) {
		this.buildService = buildService;
	}

	@GetMapping("")
	public HttpEntity<List<Build>> list() {
		var builds = buildService.list();
		System.out.println("api builds");
		return new ResponseEntity<>(builds, HttpStatus.OK);
	}
}
