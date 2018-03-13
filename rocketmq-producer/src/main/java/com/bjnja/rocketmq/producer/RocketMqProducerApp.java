package com.bjnja.rocketmq.producer;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.support.MessageBuilder;

import com.bjnja.rocketmq.OrderPaidEvent;
import com.bjnja.spring.boot.starter.ExampleService;
import com.qianmi.ms.starter.rocketmq.core.RocketMQTemplate;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class RocketMqProducerApp implements CommandLineRunner{
	public static void main(String[] args) {
		System.out.println("Hello World!");
		SpringApplication.run(RocketMqProducerApp.class, args);
	}
	
	@Autowired
	private ExampleService exampleService;
	
	@Autowired
	private RocketMQTemplate rocketMQTemplate;

	@Override
	public void run(String... args) throws Exception {
		System.out.println(exampleService.wrap("林德燚"));
		System.out.println("xxxxxxxxxxxx============");
			rocketMQTemplate.getProducer().setVipChannelEnabled(false);  
		 	rocketMQTemplate.convertAndSend("test-topic-1", "Hello, World!");
	        rocketMQTemplate.send("test-topic-1", MessageBuilder.withPayload("Hello, World! I'm from spring message").build());
	        rocketMQTemplate.convertAndSend("test-topic-2", new OrderPaidEvent("T_001", new BigDecimal("88.00")));	
	}
}
