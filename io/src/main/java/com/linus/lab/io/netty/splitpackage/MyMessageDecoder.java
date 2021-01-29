package com.linus.lab.io.netty.splitpackage;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;

import java.util.List;

/**
 * @Author wangxiangyu
 * @Date 2021/1/27
 * @Description TODO
 */
public class MyMessageDecoder extends ByteToMessageDecoder {

    private int length = -1;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println(in);
        if (length == -1 && in.readableBytes() >= 4) {
            this.length = in.readInt();
        }
        if (length != -1 && in.readableBytes() >= length) {
            byte[] data = new byte[length];
            in.readBytes(data);
            MyProtocol myProtocol = new MyProtocol(length, data);
            out.add(myProtocol);
            length = -1;// reset
        }
    }
}
