package com.bjnja.cloud.eureka.client.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class HelloService {

	// 通过restTemplate实现htttp通信
	@Autowired
	RestTemplate restTemplate;
	
	// 断容器错误回调定义
	@HystrixCommand(fallbackMethod = "hiError")
	public String hi(String name) {
		return restTemplate.getForObject("http://SERVICE-HI/hi/" + name, String.class);
	}
	
	// 定义错误函数
	public String hiError(String name) {
		return "hi,"+name+",sorry,error!";
	}
}
