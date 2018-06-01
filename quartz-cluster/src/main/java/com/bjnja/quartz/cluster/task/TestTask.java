package com.bjnja.quartz.cluster.task;

import org.springframework.beans.factory.annotation.Value;

import com.bjnja.quartz.cluster.annotation.Job;
import com.bjnja.quartz.cluster.annotation.Task;

/**
 * Task demo
 * <pre>
 *  缺省name与group，自定义参数类型
 * </pre>
 * @author ldy
 *
 */
@Job
public class TestTask{


    @Value("${spring.application.name}")
    private String appname;

	@Task(cron="0/10 * * ? * * *")
	public void exec(String params) {
		System.out.println("====    TestJob TestJob    ====" + appname);
		System.out.println(">>>>>>>>>>>> params:" + params + "==" + System.currentTimeMillis());
	}
    
}
