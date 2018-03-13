package com.bjnja.cloud.eureka.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiController {

	@Value("${server.port}")
	private String port;
	@GetMapping("/hi/{name}")
	public String hi(@PathVariable("name") String name) {
		return " hello:  " + name + " in [" +port + "]";
	}
}
