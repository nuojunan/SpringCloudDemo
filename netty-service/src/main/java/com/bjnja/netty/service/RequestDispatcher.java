package com.bjnja.netty.service;

import java.lang.reflect.Method;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import io.netty.channel.ChannelHandlerContext;

@Component
public class RequestDispatcher implements ApplicationContextAware{
	static ApplicationContext ac;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ac = applicationContext;
	}
	
	public  static void dispatcher(ChannelHandlerContext ctx, MethodInvokeMeta meta) {
		System.out.println("meta: " + meta);
		try{
			Class<?> interfaceClass = meta.getInterfaceClass();
	        String name = meta.getMethodName();
	        Object[] args = meta.getArgs();
	        Class<?>[] parameterTypes = meta.getParameterTypes();
	        Object targetObject = ac.getBean(interfaceClass);
	        Method method = targetObject.getClass().getMethod(name, parameterTypes);
	        Object obj = method.invoke(targetObject, args);
        	ctx.writeAndFlush(obj);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
