package com.bjnja.quartz.cluster.config;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.alibaba.fastjson.JSON;
import com.bjnja.quartz.cluster.Constant;
import com.bjnja.quartz.cluster.annotation.Job;
import com.bjnja.quartz.cluster.annotation.Task;
import com.bjnja.quartz.cluster.bean.JobEntity;
import com.bjnja.quartz.cluster.task.ScheduleJob;

/**
 * 定时器配置
 * 
 * <pre>
 * 分布式集群定时器，实现功能：
 * 1、相同任务，同时只能一个服务执行；
 * 2、多个任务在多个服务下均衡分配；
 * 3、一个服务断开，在此服务上运行的任务将分派给其他服务执行；
 * 4、通过注解初始化任务入库；
 * 5、任务支持在线增删改查，数据需要落地；
 * 6、任务执行需要日志记录，并提供查看
 * </pre>
 * 
 * @author ldy
 */
@Configuration
public class SchedulerConfig {

	private static ApplicationContext ctx;
	private boolean overwriteExistingJobs = false;

	@Bean(name = "scheduler")
	public SchedulerFactoryBean schedulerFactory(ApplicationContext ac, DataSource ds) {
		SchedulerFactoryBean bean = new SchedulerFactoryBean();
		// 用于quartz集群,QuartzScheduler 启动时更新己存在的Job,false不更新
		bean.setOverwriteExistingJobs(overwriteExistingJobs);
		// 延时启动，应用启动20秒后
		bean.setStartupDelay(20);
		// 不自动启动
		bean.setAutoStartup(true);
		// 加载quartz配置
		bean.setConfigLocation(new ClassPathResource("/quartz.properties"));
		// 获取所有注解配置@NjaTask
		List<Trigger> triggers = buildTrigger(ac);
		if (!triggers.isEmpty()) {
			// TODO 注册触发器
			bean.setTriggers(triggers.toArray(new Trigger[] {}));
		}
		bean.setDataSource(ds);
		bean.setApplicationContext(ac);
		
		ctx = ac;
		return bean;
	}

	public static Object get(String beanName) {
		try {
			return ctx.getBean(Class.forName(beanName));
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}

//	private List<Trigger> buildTrigger(ApplicationContext ac) {
//		// TODO 生成触发器，过滤已经存在的
//		List<Trigger> triggers = new ArrayList<>();
//		Map<String, Object> map = ac.getBeansWithAnnotation(Task.class);
//		map.forEach((k, v) -> {
//			Task task = v.getClass().getAnnotation(Task.class);
//			JobEntity job = new JobEntity();
//			// 默认name
//			String name = task.name();
//			if (Task.DEFAULT.equals(task.name())) {
//				name = v.getClass().getName();
//			}
//			job.setName(name);
//			job.setGroup(task.group());
//			job.setDescription(task.desc());
//			job.setCronExpression(task.cron());
//			job.setTargetClass(v.getClass().getName());
//			job.setTargetMethod("exec");
//			JobDetail jobDetail = JobBuilder.newJob(ScheduleJob.class).withIdentity(job.getName(), job.getGroup())
//					.storeDurably(true).build();
//			// 参数赋值
//			jobDetail.getJobDataMap().put(Constant.JOB_PARAM_KEY, JSON.toJSONString(job));
//			// 表达式调度构建器
//			CronScheduleBuilder cronBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
//			Trigger t = TriggerBuilder.newTrigger().withIdentity(job.getName(), job.getGroup())
//					.withSchedule(cronBuilder).withDescription(job.getDescription()).build();
//			// 放jobDetail
//			t.getJobDataMap().put("jobDetail", jobDetail);
//			triggers.add(t);
//		});
//		return triggers;
//	}

	/**
	 * 生成触发器，扫描类注解@Job根据方法上注解@Task添加
	 * @author ldy
	 */
	public List<Trigger> buildTrigger(ApplicationContext context) {
		
		List<Trigger> triggers = new ArrayList<>();
		try {
			// 获取所有@Job注解类
			Map<String, Object> map = context.getBeansWithAnnotation(Job.class);
			map.forEach((k, v) -> {
				Class<? extends Object> clazz = v.getClass();
				Method[] methods = clazz.getDeclaredMethods();
				for (Method method : methods) {
					// 判断该方法是否有Task注解
					if (method.isAnnotationPresent(Task.class)) {
						Task task = method.getAnnotation(Task.class);
						JobEntity job = new JobEntity();
						// 默认name
						String name = task.name();
						if (Task.DEFAULT.equals(task.name())) {
							name = clazz.getName() + "_" + method.getName();
						}
						Class<?>[] types = method.getParameterTypes();
						job.setName(name);
						job.setGroup(task.group());
						job.setDescription(task.desc());
						job.setCronExpression(task.cron());
						job.setTargetClass(clazz.getName());
						job.setTargetMethod(method.getName());
						// 参数类型
						if (null != types) {
							job.setParamsType(types[0]);
						}
						JobDetail jobDetail = JobBuilder.newJob(ScheduleJob.class)
								.withIdentity(job.getName(), job.getGroup()).storeDurably(true).build();
						// 参数赋值
						jobDetail.getJobDataMap().put(Constant.JOB_PARAM_KEY, JSON.toJSONString(job));
						// 表达式调度构建器
						CronScheduleBuilder cronBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
						Trigger t = TriggerBuilder.newTrigger().withIdentity(job.getName(), job.getGroup())
								.forJob(job.getName()).withSchedule(cronBuilder).withDescription(job.getDescription()).build();
						// 放jobDetail
						t.getJobDataMap().put("jobDetail", jobDetail);
						triggers.add(t);
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return triggers;
	}

}
