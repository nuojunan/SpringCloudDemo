package com.bjnja.cloud.config.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableConfigServer
@EnableEurekaClient
public class ConfigServiceApp {
    public static void main( String[] args ){
        System.out.println( "=== start ConfigServiceApp =========" );
        SpringApplication.run(ConfigServiceApp.class, args);
    }
}
