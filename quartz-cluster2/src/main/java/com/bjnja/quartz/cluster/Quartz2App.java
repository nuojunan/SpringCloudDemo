package com.bjnja.quartz.cluster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
//@ImportResource("quartz.xml")
public class Quartz2App {
	public static void main(String[] args) {
		System.out.println("Hello World!");
		SpringApplication.run(Quartz2App.class, args);
	}
}
