package com.bjnja.quartz.cluster.bean;

import java.io.Serializable;

/**
 * 任务日志
 * 
 * @author ldy
 *
 */
public class JobLog implements Serializable{

	private static final long serialVersionUID = -1065576949505096310L;

	/**
	 * 执行应用名称
	 */
	private String appName;

	/**
	 * 任务名称
	 */
	private String jobName;

	/**
	 * 任务组
	 */
	private String jobGroup;

	/**
	 * 目标类
	 */
	private String targetClass;

	/**
	 * 目标方法
	 */
	private String targetMethod;

	/**
	 * 参数
	 */
	private String params;

	/**
	 * 任务状态 0：成功 1：失败
	 */
	private Integer status;

	/**
	 * 失败信息
	 */
	private String error;

	/**
	 * 耗时(单位：毫秒)
	 */
	private Integer times;

	/**
	 * 创建时间
	 */
	private Long createTime;

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
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

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public Integer getTimes() {
		return times;
	}

	public void setTimes(Integer times) {
		this.times = times;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "JobLog [appName=" + appName + ", jobName=" + jobName + ", jobGroup=" + jobGroup + ", targetClass="
				+ targetClass + ", targetMethod=" + targetMethod + ", params=" + params + ", status=" + status
				+ ", error=" + error + ", times=" + times + ", createTime=" + createTime + "]";
	}

}
