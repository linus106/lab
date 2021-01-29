package com.linus.lab.io.netty.splitpackage;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.UnsupportedEncodingException;
import java.util.Scanner;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2021/1/20
 */
public class MyClient {

    public static void main(String[] args) throws InterruptedException, UnsupportedEncodingException {
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap(); // (1)
            b.group(workerGroup); // (2)
            b.channel(NioSocketChannel.class); // (3)
            b.option(ChannelOption.SO_KEEPALIVE, true); // (4)
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new MyMessageEncoder());
//                    ch.pipeline().addLast(new StringEncoder());
                }
            });

            // Start the client.
            ChannelFuture f = b.connect("127.0.0.1", 9000).sync(); // (5)

            Scanner scanner = new Scanner(System.in);

//            while (scanner.hasNext()) {
//                f.channel().writeAndFlush(scanner.next());
//            }
            for (int i = 0; i < 200; i++) {
                byte[] bytes = "来来来来，嘻嘻嘻喜爱，123123123".getBytes("UTF-8");
                f.channel().writeAndFlush(new MyProtocol(bytes.length, bytes));
            }

            // Wait until the connection is closed.
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
