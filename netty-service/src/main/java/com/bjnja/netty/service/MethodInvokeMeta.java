package com.bjnja.netty.service;

import java.io.Serializable;
import java.util.Arrays;

public class MethodInvokeMeta implements Serializable{

	private static final long serialVersionUID = 3036997132212098959L;
	//接口
    private Class<?> interfaceClass;
    //方法名
    private String methodName;
    //参数
    private Object[] args;
    //参数类型
    private Class<?>[] parameterTypes;
    //返回值类型
    private Class<?> returnType;
    
	public Class<?> getInterfaceClass() {
		return interfaceClass;
	}
	public void setInterfaceClass(Class<?> interfaceClass) {
		this.interfaceClass = interfaceClass;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public Object[] getArgs() {
		return args;
	}
	public void setArgs(Object[] args) {
		this.args = args;
	}
	public Class<?>[] getParameterTypes() {
		return parameterTypes;
	}
	public void setParameterTypes(Class<?>[] parameterTypes) {
		this.parameterTypes = parameterTypes;
	}
	public Class<?> getReturnType() {
		return returnType;
	}
	public void setReturnType(Class<?> returnType) {
		this.returnType = returnType;
	}
	@Override
	public String toString() {
		return "MethodInvokeMeta [interfaceClass=" + interfaceClass + ", methodName=" + methodName + ", args="
				+ Arrays.toString(args) + ", parameterTypes=" + Arrays.toString(parameterTypes) + ", returnType="
				+ returnType + "]";
	}
    
}
