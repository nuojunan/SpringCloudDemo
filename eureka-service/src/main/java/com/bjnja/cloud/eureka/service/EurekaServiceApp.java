package com.bjnja.cloud.eureka.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableEurekaServer 	// 开启eureka注册中心
public class EurekaServiceApp {
	public static void main(String[] args) {
		System.out.println("=========start EurekaServiceApp ============");
		SpringApplication.run(EurekaServiceApp.class, args);
	}
	
}
