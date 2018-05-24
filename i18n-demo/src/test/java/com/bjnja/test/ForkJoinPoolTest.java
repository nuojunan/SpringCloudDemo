package com.bjnja.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;

import org.junit.Test;

import com.bjnja.test.ForkForeachJob.ForkTask;

public class ForkJoinPoolTest {

	@Test
	public void test() {
		 long start = System.currentTimeMillis();
        //创建 线程池
        ForkJoinPool pool = new ForkJoinPool();
        //创建任务
        CaculatorForkAndJoin task = new CaculatorForkAndJoin(0L,100000000L);

        //添加任务到线程池，获得返回值
        long sum = pool.invoke(task);

        long end = System.currentTimeMillis();
        System.out.println(sum+"spend:"+(end - start));
	}
	
	@Test
	public void ee() {
	  long start = System.currentTimeMillis();
	  List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
	  ForkJoinPool pool = new ForkJoinPool();
      //创建任务
	  List<?> invoke = pool.invoke(new ForEachTask<>(list));
	  System.out.println(invoke);
	  System.out.println(System.currentTimeMillis() - start);
	  
	}
	
	@Test
	public void eee() {
		long start = System.currentTimeMillis();
		List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		List<Integer> ll = new ArrayList<>();
		for(Integer i : list) {
			try {
				Thread.sleep(100);
				System.out.println(i);
				ll.add(i);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(ll);
		System.out.println(System.currentTimeMillis() - start);
	}
	
	@Test
	public void foreachtest() {
	  long start = System.currentTimeMillis();
	  List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
	  ForkJoinPool pool = new ForkJoinPool();
      //创建任务
	  List<?> invoke = pool.invoke(new ForkForeachJob<Integer,Integer>(list, new ForkTask<Integer,Integer>(){
			@Override
			Integer fork(Integer t) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(t);
				return t;
			}
		  }));
	  System.out.println(invoke);
	  System.out.println(System.currentTimeMillis() - start);
	  
	}
	
	@Test
	public void foreachtest2() {
	  long start = System.currentTimeMillis();
	  List<Map<String,Object>> list = new ArrayList<>();
	  for(int i = 0; i< 10; i++) {
		  Map<String, Object > map = new HashMap<>();
		  map.put("name", "N" + i);
		  map.put("age", 10 + i);
		  list.add(map);
	  }
	  ForkJoinPool pool = new ForkJoinPool();
      //创建任务
	  List<?> invoke = pool.invoke(new ForkForeachJob<Map<String,Object>,Map<String,Object>>(list, 
			  	new ForkTask<Map<String,Object>,Map<String,Object>>(){
			@Override
			Map<String,Object> fork(Map<String,Object> t) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(t);
				if (t.get("name").equals("N5")) {
					System.out.println("异常。。。。。");
					return null;
				}
				return t;
			}
		  }));
	  System.out.println(invoke);
	  System.out.println(System.currentTimeMillis() - start);
	  
	}
	
	@Test
	public void foreachtest3() {
	  long start = System.currentTimeMillis();
	  List<Map<String,Object>> list = new ArrayList<>();
	  for(int i = 0; i< 10; i++) {
		  Map<String, Object > map = new HashMap<>();
		  map.put("name", "N" + i);
		  map.put("age", 10 + i);
		  list.add(map);
	  }
	  ForkJoinPool pool = new ForkJoinPool();
      //创建任务
	  pool.execute(new ForkForeachJob<Map<String,Object>,Map<String,Object>>(list, 
			  	new ForkTask<Map<String,Object>,Map<String,Object>>(){
			@Override
			Map<String,Object> fork(Map<String,Object> t) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(t);
				return t;
			}
		  }));
	  System.out.println(System.currentTimeMillis() - start);
	  
	}
}
