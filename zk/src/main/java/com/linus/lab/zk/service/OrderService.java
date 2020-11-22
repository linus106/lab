package com.linus.lab.zk.service;

import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/11/21
 */
@Service
public class OrderService {



    public static int stock = 1000;

    public String doOrder() throws InterruptedException {
        if (stock <= 0) {
            return "fail";
        } else {
            int curr = stock;
            Thread.sleep(new Random().nextInt(100));//do bus
            System.out.println("consume:" + curr);
            stock = curr - 1;
            return "ok";
        }
    }


}
