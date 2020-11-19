package com.linus.lab.algorithm.greedy;

import java.util.Stack;
import java.util.concurrent.CountDownLatch;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/11/17
 */
public class RemoveKDigits {

    public String removeKdigits(String num, int k) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < num.length(); i++) {
            final char c = num.charAt(i);
            while (!stack.empty() && c < stack.peek() && 0 < k) {
                stack.pop();
                k--;
            }
            if (!stack.isEmpty() || c != '0') {
                stack.push(c);
            }
        }
        while (k-- > 0 && !stack.isEmpty()) stack.pop();
        if (stack.isEmpty()) return "0";


        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        RemoveKDigits o = new RemoveKDigits();
        String res = o.removeKdigits("10", 2);

        System.out.println(res);
    }
}
