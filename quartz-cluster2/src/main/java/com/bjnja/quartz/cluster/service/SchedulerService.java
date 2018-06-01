package com.bjnja.quartz.cluster.service;

import java.util.List;

import com.bjnja.quartz.cluster.bean.JobEntity;

/**
 * 调度服务接口
 *
 * @author ldy
 */
public interface SchedulerService {

	/**
	 * 获取所有任务
	 * @return List<SchedulerJob>
	 */
	List<JobEntity> getJobList();

	/**
	 * 获取正在执行任务
	 * @return List<SchedulerJob>
	 */
	List<JobEntity> getRunningJobList();

	/**
	 *  创建或更新 create
	 *  @param job 
	 */
	void createOrUpdate(JobEntity job);

	/**
	 * 立刻执行
	 * @param name
	 * @param group
	 */
	void exec(String name, String group);

	/**
	 * 暂停 pause 
	 * @param name (name 为null，默认该组所有Job)
	 * @param group
	 */
	void pause(String name, String group);

	/**
	 * 恢复 resume
	 * @param name (name 为null，默认该组所有Job)
	 * @param group
	 */
	void resume(String name, String group);

	/**
	 * 删除 remove
	 * @param name
	 * @param group
	 */
	void remove(String name, String group);
}