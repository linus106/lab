package com.linus.lab.io.copy;

import java.io.IOException;
import java.util.Arrays;

/**
 * @Author wangxiangyu
 * @Date 2020/8/21 14:48
 * @Description TODO
 */
public class Tester {


    private static final String BASE_PATH = "D:\\Temp\\";
    private static final String SOURCE_FILE = "The.Grand.Budapest.Hotel.2014.HDRip.X264-PLAYNOW.mp4";
//    private static final String SOURCE_FILE = "10_kbqa-ss_micro_app_kbqa_lt_all.log";
    private static final String SUFFIX = SOURCE_FILE.substring(SOURCE_FILE.lastIndexOf('.'));

    public static void main(String[] args) {
        Arrays.asList(
                new SteamBufferHandler(), new MemoryMappingHandler(), new ZeroCopyHandler()).forEach(h->{
            String source = BASE_PATH + SOURCE_FILE;
            String target = BASE_PATH + h.getName() + SUFFIX;
            try {
                long start = System.currentTimeMillis();
                h.copy(source, target);
                System.out.println(String.format("%s:%dms", h.getName(), System.currentTimeMillis() - start));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
