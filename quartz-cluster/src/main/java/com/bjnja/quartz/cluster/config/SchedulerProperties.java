package com.bjnja.quartz.cluster.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
/**
 * scheduler配置
 * @author ldy
 *
 */
@ConfigurationProperties("scheduler")
public class SchedulerProperties {

	/** 是否启动Scheduler */
	private Boolean enabled;
	/** 是否重新加载初始化Job，默认重新加载 */
	private Boolean overwriteExistingJobs = true;
	/** 应用实例名 集群中每一个实例都必须使用相同的名称 */
	private String instanceName;
	/** 数据库表面前缀 */
	private String tablePrefix;
	/** 是否加入集群 */
	private Boolean isClustered;
	/** 线程数 */
	private Integer threadCount;
	/** 集群校验时间 */
	private Integer clusterCheckinInterval;
	/** 启动延迟时间 s; 默认20s*/
	private Integer startupDelay = 20;
	
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public Boolean getOverwriteExistingJobs() {
		return overwriteExistingJobs;
	}
	public void setOverwriteExistingJobs(Boolean overwriteExistingJobs) {
		this.overwriteExistingJobs = overwriteExistingJobs;
	}
	public String getInstanceName() {
		return instanceName;
	}
	public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
	}
	public String getTablePrefix() {
		return tablePrefix;
	}
	public void setTablePrefix(String tablePrefix) {
		this.tablePrefix = tablePrefix;
	}
	public Boolean getIsClustered() {
		return isClustered;
	}
	public void setIsClustered(Boolean isClustered) {
		this.isClustered = isClustered;
	}
	public Integer getThreadCount() {
		return threadCount;
	}
	public void setThreadCount(Integer threadCount) {
		this.threadCount = threadCount;
	}
	public Integer getClusterCheckinInterval() {
		return clusterCheckinInterval;
	}
	public void setClusterCheckinInterval(Integer clusterCheckinInterval) {
		this.clusterCheckinInterval = clusterCheckinInterval;
	}
	public Integer getStartupDelay() {
		return startupDelay;
	}
	public void setStartupDelay(Integer startupDelay) {
		this.startupDelay = startupDelay;
	}
}
