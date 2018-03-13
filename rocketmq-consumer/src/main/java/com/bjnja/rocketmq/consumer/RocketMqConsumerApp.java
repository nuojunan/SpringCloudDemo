package com.bjnja.rocketmq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

import com.bjnja.rocketmq.OrderPaidEvent;
import com.qianmi.ms.starter.rocketmq.annotation.RocketMQMessageListener;
import com.qianmi.ms.starter.rocketmq.core.RocketMQListener;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class RocketMqConsumerApp {
	static Logger log = LoggerFactory.getLogger(RocketMqConsumerApp.class);
	public static void main(String[] args) {
		System.out.println("Hello World!");
		SpringApplication.run(RocketMqConsumerApp.class, args);
	}
	    @Service
	    @RocketMQMessageListener(topic = "test-topic-1", consumerGroup = "my-consumer_test-topic-1")
	    public class MyConsumer1 implements RocketMQListener<String>{
	        public void onMessage(String message) {
	            log.info("received message: {}", message);
	        }
	    }
	    
	    @Service
	    @RocketMQMessageListener(topic = "test-topic-2", consumerGroup = "my-consumer_test-topic-2")
	    public class MyConsumer2 implements RocketMQListener<OrderPaidEvent>{
	        public void onMessage(OrderPaidEvent orderPaidEvent) {
	            log.info("received orderPaidEvent: {}", orderPaidEvent);
	        }
	    }
	    
}
