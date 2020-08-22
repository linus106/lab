package com.linus.lab.io.copy;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author wangxiangyu
 * @Date 2020/8/21 14:44
 * @Description 零拷贝,CPU不参与拷贝工作，需要硬件支持
 */
public class ZeroCopyHandler implements IFileCopyHandler {


    @Override
    public void copy(String source, String target) throws IOException {
        FileChannel sourceChannel = new RandomAccessFile(source, "rw").getChannel();
        FileChannel targetChannel = new RandomAccessFile(target, "rw").getChannel();
        sourceChannel.transferTo(0, sourceChannel.size(), targetChannel);
    }

    @Override
    public String getName() {
        return "ZeroCopyHandler";
    }
}
