package com.bjnja.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class ForEachTask<T, TT> extends RecursiveTask<List<?>>{
	private static final long serialVersionUID = 1L;
	private static final int NUM = 10;
	private List<T> list;
	public ForEachTask(List<T> list) {
		super();
		this.list = list;
	}

	@Override
	protected List<Object> compute() {
		List<Object> tt = new ArrayList<>();
		if (list.size() == 1) {
			System.out.println("=========" + Thread.currentThread().getName()  + "===" + list.get(0));
			tt.addAll(list);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return tt;
		}
		List<ForEachTask> tasks = new ArrayList<>();
		for (T t : list) {
			tasks.add(new ForEachTask(Arrays.asList(t)));
		}
		for ( ForEachTask t : invokeAll(tasks)) {
			Collection<Object> join = (Collection<Object>) t.join();
			tt.addAll(join);
		}
		return tt;
	}

}
