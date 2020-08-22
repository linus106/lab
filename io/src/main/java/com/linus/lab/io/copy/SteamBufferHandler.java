package com.linus.lab.io.copy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Author wangxiangyu
 * @Date 2020/8/21 14:44
 * @Description 一次读写1K数据,耗费CPU
 */
public class SteamBufferHandler implements IFileCopyHandler {


    @Override
    public void copy(String source, String target) throws IOException {
        FileInputStream fis = new FileInputStream(source);
        FileOutputStream fos = new FileOutputStream(target);

        int c;
        byte[] buffer = new byte[1024];
        while((c = fis.read(buffer)) != -1) {
            fos.write(buffer, 0 , c);
        }
        fis.close();
        fos.close();
    }

    @Override
    public String getName() {
        return "SteamBufferHandler";
    }
}
