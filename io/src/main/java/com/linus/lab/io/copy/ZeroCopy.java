package com.linus.lab.io.copy;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author wangxiangyu
 * @Date 2020/8/21 11:11
 * @Description TODO
 */
public class ZeroCopy {

    private static final String SOURCE_PATH = "D:\\Temp\\s.log";
    private static final String TARGET_PATH = "D:\\Temp\\t.log";

    public static void main(String[] args) throws IOException {


//        FileChannel sfc = new RandomAccessFile(SOURCE_PATH, "rw").getChannel();
//        FileChannel tfc = new RandomAccessFile(TARGET_PATH, "rw").getChannel();
//
//        long start = System.currentTimeMillis();
//        MappedByteBuffer mappedByteBuffer = tfc.map(FileChannel.MapMode.READ_WRITE, 0, sfc.size());
//        sfc.write(mappedByteBuffer);
//        mappedByteBuffer.flip();
//        System.out.println(System.currentTimeMillis() - start);


        try {
            FileChannel sourceChannel = new RandomAccessFile(SOURCE_PATH, "rw").getChannel();
            FileChannel destChannel = new RandomAccessFile(TARGET_PATH, "rw").getChannel();
            long start = System.currentTimeMillis();
            MappedByteBuffer map = sourceChannel.map(FileChannel.MapMode.READ_WRITE, 0, sourceChannel.size());
            destChannel.write(map);
            map.flip();
            System.out.println("耗时：" + (System.currentTimeMillis() - start));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(1);
    }
}
