package com.bjnja.cloud.eureka.client.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableDiscoveryClient  // eureka消费端
@EnableHystrix			// 开启hystrix段容器
@EnableHystrixDashboard // 开启hystrix断容器仪表盘（管理端）  http://localhost:8084/hystrix/
public class EurekaClientCustomerRibbonApp {
    public static void main( String[] args ){
        System.out.println( "==== start EurekaClientCustomerApp ====" );
        SpringApplication.run(EurekaClientCustomerRibbonApp.class, args);
    }
    
    @Bean
    @LoadBalanced // Ribbon方式的負載均衡
    RestTemplate restTemplate () {
    	return new RestTemplate();
    }
}
