package com.bjnja.quartz.cluster.bean;

import java.io.Serializable;

/**
 * 任务类
 * @author ldy
 *
 */
public class JobEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 组
	 */
	private String group;

	/**
	 * 目标类
	 */
	private String targetClass;

	/**
	 * 目标方法
	 */
	private String targetMethod;
	
	/**
	 * 方法参数类型
	 */
	private Class<?> paramsType;

	/**
	 * 参数
	 */
	private Object params;

	/**
	 * cron表达式
	 */
	private String cronExpression;
	

	 /**
     * 任务状态； PAUSED：暂停 ；NORMAL：正常
     */
	private String status;
	
	/**
	 * 最近执行时间
	 */
	private Long lastExecTime;
	/**
	 * 最近执行状态 0：成功 1：失败
	 */
	private Integer lastExecStatus;
	
	/**
	 * 描述\备注
	 */
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getTargetClass() {
		return targetClass;
	}

	public void setTargetClass(String targetClass) {
		this.targetClass = targetClass;
	}

	public String getTargetMethod() {
		return targetMethod;
	}

	public void setTargetMethod(String targetMethod) {
		this.targetMethod = targetMethod;
	}


	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getLastExecTime() {
		return lastExecTime;
	}

	public void setLastExecTime(Long lastExecTime) {
		this.lastExecTime = lastExecTime;
	}

	public Integer getLastExecStatus() {
		return lastExecStatus;
	}

	public void setLastExecStatus(Integer lastExecStatus) {
		this.lastExecStatus = lastExecStatus;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Class<?> getParamsType() {
		return paramsType;
	}

	public void setParamsType(Class<?> paramsType) {
		this.paramsType = paramsType;
	}

	public Object getParams() {
		return params;
	}

	public void setParams(Object params) {
		this.params = params;
	}

}
