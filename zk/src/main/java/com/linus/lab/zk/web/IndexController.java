package com.linus.lab.zk.web;

import com.linus.lab.zk.service.OrderService;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/11/14
 */
@RestController
public class IndexController {

    @Autowired
    private CuratorFramework curatorFramework;

    @Autowired
    private OrderService orderService;



    @GetMapping("/api/order-fill")
    public int fillOrder(@RequestParam Integer stock) throws Exception {
        OrderService.stock = stock;
        return OrderService.stock;
    }


    /**
     * 并发线程数：100
     * qps约为:1
     */
    @GetMapping("/api/lock/order")
    public String orderWithLock() throws Exception {
        InterProcessMutex lock = new InterProcessMutex(curatorFramework, "/lock2");
        lock.acquire();
        try {
            return orderService.doOrder();
        } finally {
            lock.release();
        }
    }


    @GetMapping("/api/order")
    public String orderWithoutLock() throws Exception {
        return orderService.doOrder();
    }

}
