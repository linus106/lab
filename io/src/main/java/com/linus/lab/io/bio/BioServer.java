package com.linus.lab.io.bio;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/7/14
 */
public class BioServer {

    private static ServerSocket server;

    private static int PORT = 8080;

    public static void main(String[] args) throws IOException {
        try {
            server = new ServerSocket(PORT);
            System.out.println("bio server started");
            while (true) {
                Socket socket = server.accept();
                new SocketHandler(socket).run();
            }
        } finally {
            if (server != null) {
                server.close();
                server = null;
                System.out.println("bio server closed");
            }
        }
    }

}

class SocketHandler implements Runnable {

    private Socket socket;
    public SocketHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            byte[] buffer = new byte[1024];
            socket.getInputStream().read(buffer);
            System.out.println(new String (buffer));
            socket.getOutputStream().write("hello client".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                IOUtils.closeQuietly(socket.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
