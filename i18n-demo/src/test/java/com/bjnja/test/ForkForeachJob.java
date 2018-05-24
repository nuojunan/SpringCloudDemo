package com.bjnja.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * 分布式执行集合
 * @author ldy
 *
 * @param <P>
 * @param <R>
 */
public class ForkForeachJob<P,R> extends RecursiveTask<List<R>>{
	private static final long serialVersionUID = 1L;
	private List<P> list;
	private ForkTask<P,R> task;
	public ForkForeachJob(List<P> list, ForkTask<P,R> task) {
		super();
		this.list = list;
		this.task = task;
	}
	
	@Override
	protected List<R> compute() {
		List<R> tt = new ArrayList<>();
		if (list.size() == 1) {
			R r = task.fork(list.get(0));
			if (null != r) {
				tt.add(r);
			}
			return tt;
		}
		List<ForkForeachJob<P,R>> tasks = new ArrayList<>();
		for (P t : list) {
			tasks.add(new ForkForeachJob<P,R>(Arrays.asList(t), task));
		}
		for ( ForkForeachJob<P,R> t : invokeAll(tasks)) {
			tt.addAll(t.join());
		}
		return tt;
	}

	static abstract class ForkTask<P,R> {
		abstract R fork(P t);
	}
}
