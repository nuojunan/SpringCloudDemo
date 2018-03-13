package com.bjnja.cloud.eureka.client.customer.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
// 声明此接口是fegin方式， fegin内置段容器设置
@FeignClient(name = "service-hi", fallback = HelloServiceError.class)
public interface HelloService {

	@GetMapping("/hi/{name}")
	String hi(@PathVariable("name") String name);
}
