package com.linus.lab.io.copy;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author wangxiangyu
 * @Date 2020/8/21 14:44
 * @Description 建立对source文件的内存映射，使程序可以直接读写内核空间。数据在内核空间的缓冲区移动，减少了一次拷贝
 */
public class MemoryMappingHandler implements IFileCopyHandler {


    @Override
    public void copy(String source, String target) throws IOException {
        FileChannel sourceChannel = new RandomAccessFile(source, "rw").getChannel();
        FileChannel targetChannel = new RandomAccessFile(target, "rw").getChannel();
        MappedByteBuffer map = sourceChannel.map(FileChannel.MapMode.READ_WRITE, 0, sourceChannel.size());
        targetChannel.write(map);
    }




    @Override
    public String getName() {
        return "MemoryMappingHandler";
    }
}
