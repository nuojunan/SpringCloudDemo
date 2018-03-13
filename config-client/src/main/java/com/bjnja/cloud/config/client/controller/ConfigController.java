package com.bjnja.cloud.config.client.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigController {

	@Value("${nja-config.appname}")
	private String foo;
	
	@GetMapping("/hi")
	public String hi() {
		return foo;
	}
}
