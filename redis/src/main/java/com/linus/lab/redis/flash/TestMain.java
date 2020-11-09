package com.linus.lab.redis.flash;

import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/11/8
 */
public class TestMain {

    public static void main(String[] args) throws InterruptedException {
        final OrderService orderService = new OrderService();
        final ExecutorService executorService = Executors.newFixedThreadPool(100);

        final int productId = 12;
        final int taskNum = 1000000;



        List<Callable<Boolean>> tasks = IntStream.range(1, taskNum + 1).mapToObj(n->new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return orderService.order(productId,n);
            }
        }).collect(Collectors.toList());
        long t1 = System.currentTimeMillis();
        final List<Future<Boolean>> futures = executorService.invokeAll(tasks);

        Map<Integer, Long> map =  futures.stream().map(f->{
            try {
                if (f.get()) {
                    return 1;
                } else {
                    return 0;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return -1;
            }
        }).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        System.out.println(map);
        System.out.println("time cost : " + (System.currentTimeMillis() - t1) + "ms");


        executorService.shutdown();

    }
}
