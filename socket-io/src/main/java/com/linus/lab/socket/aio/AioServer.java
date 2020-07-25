package com.linus.lab.socket.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/7/18
 */
public class AioServer {

    public static AtomicLong clientCount = new AtomicLong(0);
    public static CountDownLatch latch;
    public static AsynchronousServerSocketChannel channel;

    public static void main(String[] args) throws IOException {
        channel = AsynchronousServerSocketChannel.open();
        channel.bind(new InetSocketAddress(8080));
        System.out.println("aio server started");



        latch = new CountDownLatch(1);
        channel.accept(null,new AcceptHandler());
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
