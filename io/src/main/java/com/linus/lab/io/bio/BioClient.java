package com.linus.lab.io.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/7/14
 *
 *
 * client:                      1connect           3write               read&write
 *
 * server: 0bind and listen               2accept         4read&wrtie             read&write
 *
 */
public class BioClient {

    private static int PORT = 8080;

    private static String IP = "127.0.0.1";

    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket(IP, PORT);

        for (int i = 0; i< 1000;i++) {
            socket.getOutputStream().write("hello server".getBytes());
            socket.getOutputStream().flush();
            Thread.sleep(1000);
        }

        InputStream inputStream = socket.getInputStream();
        byte[] rev = new byte[1024];
        inputStream.read(rev);
        System.out.println("client receive: "+new String(rev));
    }
}
