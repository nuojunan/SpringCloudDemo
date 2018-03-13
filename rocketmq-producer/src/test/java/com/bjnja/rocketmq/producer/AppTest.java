package com.bjnja.rocketmq.producer;

import java.io.UnsupportedEncodingException;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest{
	
	@Test
	public void test() {
		 // 创建一个Produer Group  
        DefaultMQProducer producer = new DefaultMQProducer("my-group");  
        // 指定NameServer地址  
        producer.setNamesrvAddr("192.168.99.100:9876");  
        try {  
            // 启动producer  
            producer.start();  
            // 创建一个Message ,并指定topic、Tag和消息主体  
            Message msg = new Message("test-topic-1", "TagA",  
                    ("Hello RocketMQ ").getBytes(RemotingHelper.DEFAULT_CHARSET));  
            //向broker发送一个消息  
            SendResult sendResult = producer.send(msg);  
            System.out.printf("%s%n", sendResult);  
        } catch (MQClientException | UnsupportedEncodingException e) {  
            e.printStackTrace();  
        } catch (RemotingException e) {  
            e.printStackTrace();  
        } catch (MQBrokerException e) {  
            e.printStackTrace();  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
        //当produer不再使用时,关闭produer  
        producer.shutdown();  
		
	}
}
