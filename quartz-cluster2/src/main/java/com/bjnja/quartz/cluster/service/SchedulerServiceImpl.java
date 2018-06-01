package com.bjnja.quartz.cluster.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.quartz.CronExpression;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.bjnja.quartz.cluster.Constant;
import com.bjnja.quartz.cluster.bean.JobEntity;
import com.bjnja.quartz.cluster.task.ScheduleJob;

@Service
public class SchedulerServiceImpl implements SchedulerService {

	private static Logger logger = LoggerFactory.getLogger(SchedulerServiceImpl.class);

	@Autowired
	private Scheduler scheduler;

	@Override
	public List<JobEntity> getJobList() {
		try {
			GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
			Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
			List<JobEntity> jobList = new ArrayList<JobEntity>();
			for (JobKey jobKey : jobKeys) {
				JobDetail detail = scheduler.getJobDetail(jobKey);
				TriggerKey triggerKey = TriggerKey.triggerKey(jobKey.getName(), jobKey.getGroup());
				if (scheduler.checkExists(triggerKey)) {
					String json = detail.getJobDataMap().getString(Constant.JOB_PARAM_KEY);
					JobEntity job = JSON.parseObject(json, JobEntity.class);
					// 状态
					Trigger.TriggerState triggerState = scheduler.getTriggerState(triggerKey);
					job.setStatus(triggerState.name());
					jobList.add(job);
				}
				// List<? extends Trigger> triggers =
				// scheduler.getTriggersOfJob(jobKey);
				// for (Trigger trigger : triggers) {
				// JobEntity job = new JobEntity();
				// job.setName(jobKey.getName());
				// job.setGroup(jobKey.getGroup());
				// job.setDescription(trigger.getDescription());
				// Trigger.TriggerState triggerState =
				// scheduler.getTriggerState(trigger.getKey());
				// job.setStatus(triggerState.name());
				// if (trigger instanceof CronTrigger) {
				// CronTrigger cronTrigger = (CronTrigger) trigger;
				// String cronExpression = cronTrigger.getCronExpression();
				// job.setCronExpression(cronExpression);
				// }
				// job.setTargetClass(detail.getJobDataMap().getString(Constant.JOB_TARGET_CLASS));
				// job.setTargetMethod(detail.getJobDataMap().getString(Constant.JOB_TARGET_METHOD));
				// job.setParams(detail.getJobDataMap().getString(Constant.JOB_PARAM_KEY));
				// Object lastExecTime =
				// detail.getJobDataMap().get(Constant.JOB_LAST_EXEC_TIME);
				// job.setLastExecTime(null != lastExecTime ?
				// Long.valueOf(lastExecTime.toString()) : null);
				// Object lastExecStatus =
				// detail.getJobDataMap().get(Constant.JOB_LAST_EXEC_STATUS);
				// job.setLastExecStatus(null != lastExecStatus ?
				// Integer.valueOf(lastExecStatus.toString()) : 0);
				// jobList.add(job);
				// }
			}
			return jobList;
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	@Override
	public List<JobEntity> getRunningJobList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createOrUpdate(JobEntity job) {
		try {
			CronExpression cronExpression = new CronExpression(job.getCronExpression());
			if (isValidExpression(cronExpression)) {
				// 获取TriggerKey
				TriggerKey triggerKey = TriggerKey.triggerKey(job.getName(), job.getGroup());
				// 从数据库中查询触发器
				CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
				// 不存在则新建一个触发器
				if (null == trigger) {
					JobDetail jobDetail = JobBuilder.newJob(ScheduleJob.class)
							.withIdentity(job.getName(), job.getGroup()).storeDurably(true).build();
					// 参数赋值
					jobDetail.getJobDataMap().put(Constant.JOB_PARAM_KEY, JSON.toJSONString(job));
					// 表达式调度构建器
					CronScheduleBuilder cronBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
					// 按新的cronExpression表达式构建一个新的trigger
					trigger = TriggerBuilder.newTrigger().withIdentity(job.getName(), job.getGroup())
							.forJob(job.getName()).withSchedule(cronBuilder).withDescription(job.getDescription())
							.build();
					scheduler.addJob(jobDetail, true);
					scheduler.scheduleJob(trigger);
				} else {
					// Trigger已存在，那么更新相应的定时设置
					// 表达式调度构建器
					CronScheduleBuilder cronBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
					JobDetail jobDetail = scheduler.getJobDetail(JobKey.jobKey(job.getName(), job.getGroup()));
					jobDetail.getJobDataMap().put(Constant.JOB_PARAM_KEY, JSON.toJSONString(job));

					// 按新的cronExpression表达式重新构建trigger
					trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(cronBuilder)
							.withDescription(job.getDescription()).build();
					// 按新的trigger重新设置job执行
					scheduler.addJob(jobDetail, true);
					scheduler.rescheduleJob(triggerKey, trigger);
				}
				// 暂停
				if (Constant.PAUSED.equals(job.getStatus())) {
					pause(job.getName(), job.getGroup());
				}

			}
		} catch (Exception e) {
			logger.error("", e);
			throw new IllegalArgumentException(e);
		}
	}

	@Override
	public void exec(String name, String group) {
		try {
			scheduler.triggerJob(JobKey.jobKey(name, group));
		} catch (SchedulerException e) {
			logger.error("", e);
			throw new IllegalArgumentException(e);
		}
	}

	@Override
	public void pause(String name, String group) {
		try {
			scheduler.pauseJob(JobKey.jobKey(name, group));
		} catch (SchedulerException e) {
			logger.error("", e);
			throw new IllegalArgumentException(e);
		}
	}

	@Override
	public void resume(String name, String group) {
		try {
			scheduler.resumeJob(JobKey.jobKey(name, group));
		} catch (SchedulerException e) {
			logger.error("", e);
			throw new IllegalArgumentException(e);
		}
	}

	@Override
	public void remove(String name, String group) {
		// 删除只删除触发器
		TriggerKey triggerKey = new TriggerKey(name, group);
		try {
			// 停止触发器
			scheduler.pauseTrigger(triggerKey);
			// 移除触发器
			if (!scheduler.unscheduleJob(triggerKey)) {
				throw new IllegalArgumentException("Remove Task fail!");
			}
		} catch (SchedulerException e) {
			logger.error("", e);
			throw new RuntimeException(e);
		}
	}

	private boolean isValidExpression(final CronExpression cronExpression) {

		CronTriggerImpl trigger = new CronTriggerImpl();
		trigger.setCronExpression(cronExpression);

		Date date = trigger.computeFirstFireTime(null);

		return date != null && date.after(new Date());
	}

}
