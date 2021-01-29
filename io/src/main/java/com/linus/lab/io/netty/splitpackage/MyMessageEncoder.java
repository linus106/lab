package com.linus.lab.io.netty.splitpackage;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @Author wangxiangyu
 * @Date 2021/1/27
 * @Description TODO
 */
public class MyMessageEncoder extends MessageToByteEncoder<MyProtocol> {

    @Override
    protected void encode(ChannelHandlerContext ctx, MyProtocol msg, ByteBuf out) throws Exception {
        out.writeInt(msg.getLenth());
        out.writeBytes(msg.getData());
    }
}
