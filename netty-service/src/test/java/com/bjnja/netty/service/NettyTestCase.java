package com.bjnja.netty.service;

import java.util.Scanner;

import org.junit.Test;

public class NettyTestCase {

	@Test
	public void start() {
		Scanner input = new Scanner(System.in);  
        Client bootstrap = new Client(8200, "127.0.0.1");    
          
        String infoString = "";  
        while (true){  
            infoString = input.nextLine();  
            MethodInvokeMeta meta = new MethodInvokeMeta();
            meta.setMethodName("hi");
            meta.setInterfaceClass(TestController.class);
            meta.setReturnType(String.class);
//            meta.setArgs(new Object[]{infoString});
            bootstrap.sendMessage(meta);    
             
        }   
	}
}
