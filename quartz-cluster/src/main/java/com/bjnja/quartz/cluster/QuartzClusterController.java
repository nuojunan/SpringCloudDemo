package com.bjnja.quartz.cluster;


import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bjnja.quartz.cluster.bean.JobEntity;
import com.bjnja.quartz.cluster.service.SchedulerManager;

/**
 * 定时任务
 */
@RestController
@CrossOrigin
@RequestMapping("/schedule")
public class QuartzClusterController {

    @Autowired
    private SchedulerManager schedulerService;
    

    @PostMapping("/save.json")
    public Response save(@RequestBody JobEntity job) {
    	try {
    		schedulerService.createOrUpdate(job);
    		return Response.ok();
    	} catch (Exception e) {
    		return Response.fail(e.getMessage());
		}
    }
    
    @PostMapping("/start.json")
    public Response start(String name, String group) {
    	try {
    		schedulerService.resume(name, group);
    		return Response.ok();
    	} catch (Exception e) {
    		return Response.fail(e.getMessage());
		}
    }
    
    @PostMapping("/stop.json")
    public Response stop(String name, String group) {
    	try {
    		schedulerService.pause(name, group);
    		return Response.ok();
    	} catch (Exception e) {
    		return Response.fail(e.getMessage());
		}
    }
    
    @PostMapping("/exec.json")
    public Response exec(String name, String group) {
    	try {
    		schedulerService.exec(name, group);
    		return Response.ok();
    	} catch (Exception e) {
    		return Response.fail(e.getMessage());
		}
    }
    
    @PostMapping("/delete.json")
    public Response delete(String name, String group) {
    	try {
    		schedulerService.remove(name, group);
    		return Response.ok();
    	} catch (Exception e) {
    		return Response.fail(e.getMessage());
		}
    }
    
    @GetMapping("/fetch.json")
    public Response fetch() {
    	try {
    		return Response.ok(schedulerService.getJobList());
    	} catch (Exception e) {
    		return Response.fail(e.getMessage());
		}
    }
  
    static class Response implements Serializable{

		private static final long serialVersionUID = 1L;
    	
		private int status;
		
		private Object data;
		
		private int total;
		
		public static Response ok() {
			return ok(null);
		}
		
		public static Response ok(Object data) {
			return ok(data, 0);
		}
		public static Response ok(Object data, int total) {
			Response res = new Response();
			res.status = 0;
			res.data = data;
			res.total = total;
			return res;
		}
		public static Response fail(Object data) {
			Response res = new Response();
			res.status = -1;
			res.data = data;
			return res;
		}

		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

		public Object getData() {
			return data;
		}

		public void setData(Object data) {
			this.data = data;
		}

		public int getTotal() {
			return total;
		}

		public void setTotal(int total) {
			this.total = total;
		}
    }
    
}