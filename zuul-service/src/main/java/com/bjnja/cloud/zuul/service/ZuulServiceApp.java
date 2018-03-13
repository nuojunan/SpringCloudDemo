package com.bjnja.cloud.zuul.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableEurekaClient		// eureka客户端
@EnableZuulProxy		// zuul网管代理
public class ZuulServiceApp {
    public static void main( String[] args ){
        System.out.println( "===== start ZuulServiceApp =========" );
        SpringApplication.run(ZuulServiceApp.class, args);
    }
}
