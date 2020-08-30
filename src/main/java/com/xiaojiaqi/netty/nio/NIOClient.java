package com.xiaojiaqi.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

/**
 * @Author: liangjiaqi
 * @Date: 2020/8/29 4:57 PM
 */
public class NIOClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();

        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("localhost",9009));


    }
}
