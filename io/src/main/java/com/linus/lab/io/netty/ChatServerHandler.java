package com.linus.lab.io.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2021/1/20
 */
public class ChatServerHandler extends SimpleChannelInboundHandler<String> {

    private static List<Channel> onlineClints = new ArrayList<>();

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
        for (Channel clint : onlineClints) {
            clint.writeAndFlush(String.format("[%s]上线了", ctx.channel().remoteAddress()));
        }
        onlineClints.add(ctx.channel());
        System.out.println("channelRegistered!:" + ctx.channel().remoteAddress());
    }


    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
        onlineClints.remove(ctx.channel());
        for (Channel clint : onlineClints) {
            clint.writeAndFlush(String.format("[%s]下线了", ctx.channel().remoteAddress()));
        }
        System.out.println("channelUnregistered!:" + ctx.channel().remoteAddress());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String o) throws Exception {
        final Channel sender = channelHandlerContext.channel();
        System.out.println(String.format("[%s]说:[%s]", sender.remoteAddress(), o));
        for (Channel clint : onlineClints) {
            if (sender == clint) {
                clint.writeAndFlush(String.format("[%s]说:[%s]", "我", o) + "_");
            } else {
                clint.writeAndFlush(String.format("[%s]说:[%s]", sender.remoteAddress(), o) + "_");
            }
        }
    }
}
