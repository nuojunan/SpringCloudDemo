package com.bjnja.netty.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

	@Value("testval")
	private String testval;
	
	@GetMapping("/hi")
	public String hi() {
		System.out.println(testval);
		return "hello ====" + testval;
	}
}
