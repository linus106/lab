package com.linus.lab.io.netty.reconnect;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.concurrent.TimeUnit;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2021/1/20
 */
public class ReconnectClient {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap(); // (1)
            b.group(workerGroup); // (2)
            b.channel(NioSocketChannel.class); // (3)
            b.option(ChannelOption.SO_KEEPALIVE, true); // (4)
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new StringDecoder());
                    ch.pipeline().addLast(new StringEncoder());
                    ch.pipeline().addLast(new SimpleChannelInboundHandler() {
                        @Override
                        protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
                            System.out.println(msg);
                        }

                        @Override
                        public void channelInactive(ChannelHandlerContext ctx) throws Exception {
                            connect(b);
                        }
                    });
                }
            });

            // Start the client.

            connect(b);

        } finally {
            workerGroup.shutdownGracefully();
        }
    }

    public static void connect(Bootstrap b) throws InterruptedException {
        // Start the client.
        ChannelFuture cf = b.connect("127.0.0.1", 9000);
        cf.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (!future.isSuccess()) {
                    future.channel().eventLoop().schedule(()-> {
                        System.out.println("connecting");
                        try {
                            connect(b);
                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("connect fail!");
                        }
                    }, 3000, TimeUnit.MILLISECONDS);
                } else {
                    System.out.println("connect success");
                }
            }
        });
        cf.channel().closeFuture().sync();
    }
}
