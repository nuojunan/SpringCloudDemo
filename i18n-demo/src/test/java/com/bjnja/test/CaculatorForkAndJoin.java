package com.bjnja.test;

import java.util.concurrent.RecursiveTask;

class CaculatorForkAndJoin extends RecursiveTask<Long>{

    /**
     * 创建serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private long start;
    private long end;

    CaculatorForkAndJoin(long start,long end){
        this.start = start;
        this.end = end;
    }

    //重写方法
    @Override
    protected Long compute() {
        System.out.println(Thread.currentThread().getName());
        if (end - start < 100) {
        	return 100l;
        }
            long mid = (start + end ) / 2;

            CaculatorForkAndJoin left = new CaculatorForkAndJoin(start, mid);
            left.fork();//进行拆分，同时压入现线程队列

            CaculatorForkAndJoin right = new CaculatorForkAndJoin(mid+1, end);
            right.fork();//进行拆分，同时压入现线程队列

            return left.join()+right.join();
    }    
}