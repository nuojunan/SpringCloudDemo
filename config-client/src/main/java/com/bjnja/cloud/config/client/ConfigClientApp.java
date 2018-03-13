package com.bjnja.cloud.config.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class ConfigClientApp {
    public static void main( String[] args ){
        System.out.println( "=== start ConfigClientApp =====" );
        SpringApplication.run(ConfigClientApp.class, args);
    }
}
