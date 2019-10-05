package com.sample.build.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/build")
public class BuildController {
	@GetMapping("")
	public String list() {
		System.out.println("list ");
		return "index";
	}
}
