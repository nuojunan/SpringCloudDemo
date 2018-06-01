package com.bjnja.quartz.cluster;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bjnja.quartz.cluster.bean.JobEntity;
//import com.bjnja.quartz.cluster.service.SchedulerService;
import com.bjnja.quartz.cluster.service.SchedulerService;

/**
 *
 */
@RestController
public class QuartzClusterController {

    @Autowired
    private SchedulerService schedulerService;

    @GetMapping("/start/{seconds}")
    public String start(@RequestParam String name,@RequestParam String method, @PathVariable String seconds){
        try {
        	JobEntity job = new JobEntity();
        	job.setCronExpression("0/" + seconds + " * * ? * * *");
        	job.setName(name + "_" + method);
        	job.setGroup("DEFAULT");
        	// 运行的
        	job.setStatus(Constant.NORMAL);
        	job.setParamsType(Class.forName("java.lang.String"));
        	job.setTargetClass(name);
        	job.setTargetMethod(method);
            schedulerService.createOrUpdate(job);
        } catch (Exception e) {
            return "Failed";
        }
        return "Successful";
    }
    
    @GetMapping("/stop")
    public String stop(@RequestParam  String name){
        try {
            schedulerService.pause(name, "DEFAULT");
        } catch (Exception e) {
            return "Failed";
        }
        return "Successful";
    }
    
    @GetMapping("/resume")
    public String resume(@RequestParam String name){
        try {
            schedulerService.resume(name, "DEFAULT");
        } catch (Exception e) {
            return "Failed";
        }
        return "Successful";
    }
    
    @GetMapping("/get")
    public List<JobEntity> get(){
        try {
            return schedulerService.getJobList();
        } catch (Exception e) {
            return null;
        }
    }
    
    @GetMapping("/remove")
    public String remove(@RequestParam String name){
    	 try {
             schedulerService.remove(name, "DEFAULT");
         } catch (Exception e) {
             return "Failed";
         }
         return "Successful";
    }
    
}