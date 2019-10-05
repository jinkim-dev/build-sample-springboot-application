package com.sample.build.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.build.model.Build;
import com.sample.build.repository.BuildRepository;

@Service
public class BuildServiceImpl implements BuildService {
	
	private BuildRepository buildRepository;
	
	@Autowired
	public BuildServiceImpl(BuildRepository buildRepository) {
		this.buildRepository = buildRepository;
	}

	@Override
	public List<Build> list() {
		return buildRepository.findAll();
	}
	
}
