package com.bjnja.quartz.cluster.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 任务注解
 * @author ldy
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Task {
	// 默认字符串
	static final String DEFAULT = "DEFAULT";

	/** 名称; 默认DEFAULT：类名_方法名 */
	String name() default DEFAULT;

	/** 组; 默认 DEFAULT */
	String group() default DEFAULT;

	/** cron表达式 */
	String cron();
	
	/** description 描述*/
	String desc() default "";
}
