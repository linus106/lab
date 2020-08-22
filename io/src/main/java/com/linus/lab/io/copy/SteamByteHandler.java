package com.linus.lab.io.copy;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Author wangxiangyu
 * @Date 2020/8/21 14:44
 * @Description 按照一个一个字节去读写
 */
public class SteamByteHandler implements IFileCopyHandler {


    @Override
    public void copy(String source, String target) throws IOException {
        FileInputStream fis = new FileInputStream(source);
        FileOutputStream fos = new FileOutputStream(target);

        int c;
        while((c = fis.read()) != -1) {
            fos.write(c);
        }
        fis.close();
        fos.close();
    }

    @Override
    public String getName() {
        return "SteamByteHandler";
    }
}
