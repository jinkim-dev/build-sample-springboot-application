package com.jindev.pipeline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.jindev.pipeline"})
public class JinDevPipelineApplication {

	public static void main(String[] args) {
		SpringApplication.run(JinDevPipelineApplication.class, args);
	}

}
