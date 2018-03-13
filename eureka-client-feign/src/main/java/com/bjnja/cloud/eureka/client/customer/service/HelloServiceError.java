package com.bjnja.cloud.eureka.client.customer.service;

import org.springframework.stereotype.Component;
// 段容器实现
@Component
public class HelloServiceError implements HelloService{

	@Override
	public String hi(String name) {
		return "sorry " + name;
	}

}
