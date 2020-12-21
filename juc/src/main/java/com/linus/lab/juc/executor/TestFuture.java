package com.linus.lab.juc.executor;

import java.util.concurrent.*;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/12/15
 */
public class TestFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {


        final ExecutorService executorService = Executors.newFixedThreadPool(3);

        final Future<Object> t1 = executorService.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                Thread.sleep(3000);
                return null;
            }
        });

        final Future<Object> t2 =executorService.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                Thread.sleep(5000);
                return null;
            }
        });


        long s = System.currentTimeMillis();
        t1.get();
        t2.get();

        System.out.println(System.currentTimeMillis() - s);

    }
}
