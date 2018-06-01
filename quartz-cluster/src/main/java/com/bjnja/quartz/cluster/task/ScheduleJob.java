package com.bjnja.quartz.cluster.task;

import java.lang.reflect.Method;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.alibaba.fastjson.JSON;
import com.bjnja.quartz.cluster.Constant;
import com.bjnja.quartz.cluster.bean.JobEntity;
import com.bjnja.quartz.cluster.bean.JobLog;
import com.bjnja.quartz.cluster.config.SchedulerConfig;

/**
 * 定时任务作业类。
 * 
 * @author ldy
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class ScheduleJob extends QuartzJobBean {

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		long now = System.currentTimeMillis();
		JobEntity job = null;
		try {
			String jsonParams = context.getJobDetail().getJobDataMap().getString(Constant.JOB_PARAM_KEY);
			job = JSON.parseObject(jsonParams, JobEntity.class);
			job.setLastExecTime(now);
			Object otargetObject = SchedulerConfig.get(job.getTargetClass());
			// 判断方式是否有参数
			if (null == job.getParamsType()) {
				// 无参数
				Method m = otargetObject.getClass().getMethod(job.getTargetMethod(), new Class[] {});
				m.invoke(otargetObject);
			} else {
				// 有参数
				Method m = otargetObject.getClass().getMethod(job.getTargetMethod(),
						new Class[] { job.getParamsType() });
				if (job.getParamsType().equals(JobExecutionContext.class)) {
					// 上下文参数
					m.invoke(otargetObject, new Object[] { context });
				} else {
					// 普通参数
					Object params = job.getParams();
					m.invoke(otargetObject, new Object[] { params });
				}
			}
			job.setLastExecStatus(Constant.SUCCESS);
			// 添加日志
			log(job, null);
		} catch (Exception e) {
			// TODO 添加日志
			job.setLastExecStatus(Constant.FAIL);
			log(job, e.getMessage());
			throw new JobExecutionException(e);
		} finally {
			// 放回上下文
			context.getJobDetail().getJobDataMap().put(Constant.JOB_PARAM_KEY, JSON.toJSONString(job));
		}
	}

	/**
	 * 记录日志
	 */
	private void log(JobEntity job, String errorMsg) {
		JobLog log = new JobLog();
		log.setAppName("xxx");
		log.setCreateTime(job.getLastExecTime());
		log.setJobGroup(job.getGroup());
		log.setJobName(job.getName());
		// 参数
		log.setParams(JSON.toJSONString(job.getParams()));
		log.setTargetClass(job.getTargetClass());
		log.setTargetMethod(job.getTargetMethod());
		log.setStatus(job.getLastExecStatus());
		log.setError(errorMsg);
		long t = System.currentTimeMillis() - job.getLastExecTime();
		log.setTimes((int) t);
		System.out.println(log);
	}

}