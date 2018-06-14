package com.bjnja.cloud.eureka.client.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * Hello world!
 *
 */
@EnableTurbine
@SpringBootApplication
@EnableDiscoveryClient		// eureka消费端
@EnableFeignClients			// 开启feign通信方式
@EnableHystrix	
@EnableHystrixDashboard		// 打开段容器仪表盘  http://localhost:8085/hystrix/
public class EurekaClientCustomerFeignApp {
    public static void main( String[] args ){
        System.out.println( "===== start EurekaClientCustomerFeignApp =========" );
        SpringApplication.run(EurekaClientCustomerFeignApp.class, args);
    }
}
