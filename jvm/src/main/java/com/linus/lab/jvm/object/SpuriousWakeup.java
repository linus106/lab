package com.linus.lab.jvm.object;

/**
 * @Author wangxiangyu
 * @Date 2020/11/19 13:48
 * @Description TODO
 */
public class SpuriousWakeup {

    private static int stock = 0;

    public static void main(String[] args) throws InterruptedException {


        Object sync = new Object();

        new Thread(new Runnable() {
            @Override
            public void run() {

                synchronized (sync) {
                    try {
                        if (stock <= 0) {
                            sync.wait();
                        }
                        stock -= 1;
                        System.out.println("comuse stock! stock is :" + stock);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {

                synchronized (sync) {
                    try {
                        if (stock <= 0) {
                            sync.wait();
                        }
                        stock -= 1;
                        System.out.println("comuse stock! stock is :" + stock);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();

        Thread.sleep(3000);

        synchronized (sync) {
            stock += 1;
            sync.notifyAll();
        }

        Thread.sleep(1000);
    }
}
