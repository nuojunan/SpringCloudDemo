package com.bjnja.quartz.cluster.task;


import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Value;

import com.bjnja.quartz.cluster.annotation.Job;
import com.bjnja.quartz.cluster.annotation.Task;    
    
/**
 * Task demo
 * <pre>
 *  定义name和group，方法参数job上下文
 * </pre>
 * 
 * @author ldy
 *
 */
@Job
public class DemoTask {

    @Value("${spring.application.name}")
    private String appname;
    
    @Task(name="测试任务", group="测试组", cron="0/10 * * ? * * *")
    public void sayHello(JobExecutionContext context){
        System.out.println("====    ScheduleTask 123456789    ====" + appname);
    }    
}  