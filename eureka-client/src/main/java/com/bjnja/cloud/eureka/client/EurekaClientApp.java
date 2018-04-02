package com.bjnja.cloud.eureka.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableEurekaClient		// eureka生产方
public class EurekaClientApp {
    public static void main( String[] args ){
        System.out.println( "====== start EurekaClientApp ==========");
        SpringApplication.run(EurekaClientApp.class, args);
    }
}
