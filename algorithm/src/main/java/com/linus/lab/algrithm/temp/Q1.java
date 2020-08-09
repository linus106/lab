package com.linus.lab.algrithm.temp;

import java.util.Arrays;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/8/9
 */
public class Q1 {

    public static void main(String[] args) {
        String s = "Pp";

        System.out.println(makeGood(s));


    }

    public static String makeGood(String s) {
        Stack<Character> stack = new Stack<>();

        for(char c :s.toCharArray()) {
            if (stack.isEmpty()) {
                stack.push(c);
            } else {
                if (Math.abs(stack.peek() - c) == 32) {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            }
        }
        return stack.stream().map(String::valueOf).collect(Collectors.joining());

    }

}
