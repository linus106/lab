package com.linus.lab.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/7/16
 */
public class NioServer {

    private static Selector selector;
    private static Selector selector1;
    private static ServerSocketChannel serverChannel;

    public NioServer() {


    }

    public static void main(String[] args) throws IOException {
        selector = Selector.open();
        selector1 = Selector.open();
        serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        serverChannel.socket().bind(new InetSocketAddress(8080), 1024);
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);


        while (true) {
            selector.select(1000);//阻塞直到有accept事件发生
            Set<SelectionKey> keys = selector.selectedKeys();//过滤出有事件的channel
            Iterator<SelectionKey> it = keys.iterator();
            while (it.hasNext()) {
                SelectionKey selectionKey = it.next();
                it.remove();
                handle(selectionKey);
            }
            selector1.select(1000);//阻塞直到有read事件发生
            Set<SelectionKey> keys1 = selector1.selectedKeys();
            Iterator<SelectionKey> it1 = keys1.iterator();
            while(it1.hasNext()){
                SelectionKey selectionKey = it1.next();
                it1.remove();
                handle(selectionKey);
            }
        }
    }

    public static void handle(SelectionKey key) throws IOException {
        if (key.isValid()) {
            if(key.isAcceptable()){
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                SocketChannel sc = ssc.accept();
                sc.configureBlocking(false);
                sc.register(selector1, SelectionKey.OP_READ);
            }
            if (key.isReadable()) {
                SocketChannel sc = (SocketChannel) key.channel();
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                try {
                    int readBytes = sc.read(buffer);
                    if (readBytes > 0) {
                        buffer.flip();
                        byte[] bytes = new byte[buffer.remaining()];
                        buffer.get(bytes);
                        String msg = new String(bytes,"UTF-8");
                        System.out.println("服务器收到消息：" + msg);
                        response(sc, "hello client");
                    } else if (readBytes < 0){
                        key.cancel();
                        sc.close();
                    }
                } catch (Exception e) {
                    key.cancel();//取消key到channel的关系，如果再有数据发过来，也感知不到read事件了
                    sc.close();//关闭通道，连接直接断开
                }
            }
        }
    }

    public static void response(SocketChannel channel,String response) throws IOException{
        byte[] bytes = response.getBytes();
        ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
        writeBuffer.put(bytes);
        writeBuffer.flip();
        channel.write(writeBuffer);
    }
}
