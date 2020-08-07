package com.linus.lab.jvm.oom;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/7/25
 */
public class StackOverFlowTest {

    static int count = 0;

    static void redo () {
        count++;
        redo();
    }


    public static void main(String[] args) throws InterruptedException {

        try {
            redo();
        } catch (Throwable t) {
            t.printStackTrace();
            System.out.println(count);
            Thread.sleep(10000);
        }

    }
}
