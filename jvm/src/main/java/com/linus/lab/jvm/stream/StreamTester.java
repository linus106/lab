package com.linus.lab.jvm.stream;

import javafx.util.Pair;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author wangxiangyu
 * @Date 2020/11/2 10:40
 * @Description TODO
 */
public class StreamTester {

    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1,2,9,8,5,5);
        int total = 10;
        HashMap<Integer, AtomicInteger> cache = new HashMap<>();

        List<Pair<Integer, Integer>> pairs = list.stream().map(n->{
            if (cache.get(n) == null) {
                cache.put(n, new AtomicInteger(0));
            }

            AtomicInteger target = cache.get(total - n);
            boolean targetExists = target != null && target.intValue() > 0;
            if (targetExists) {
                target.decrementAndGet();
                return new Pair<Integer, Integer>(total - n, n);
            } else {
                cache.get(n).incrementAndGet();
                return null;
            }
        }).filter(pair->pair != null).map(p->{
            System.out.println(p);
            return p;
        }).collect(Collectors.toList());

    }
}
