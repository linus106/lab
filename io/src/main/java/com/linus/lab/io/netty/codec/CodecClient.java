package com.linus.lab.io.netty.codec;

import com.linus.lab.io.netty.ChatClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Scanner;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2021/1/20
 */
public class CodecClient {

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
//                    ch.pipeline().addLast(new StringEncoder());
                }
            });

            // Start the client.
            ChannelFuture f = b.connect("127.0.0.1", 9000).sync(); // (5)

            Scanner scanner = new Scanner(System.in);
            ByteBuf byteBuf = Unpooled.copiedBuffer(ProtostuffUtil.serializer(new User(1, "中文100")));
            f.channel().writeAndFlush(byteBuf);
//            while (scanner.hasNext()) {
//                f.channel().writeAndFlush(scanner.next());
//            }
//            for (int i = 0; i < 200; i++) {
//                f.channel().writeAndFlush("中文" + i);
//            }

            // Wait until the connection is closed.
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
