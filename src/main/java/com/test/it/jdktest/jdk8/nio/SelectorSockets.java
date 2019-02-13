package com.test.it.jdktest.jdk8.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * <<Java NIO>> book's example
 * Created by caizh on 2015/9/8 0008.
 */
public class SelectorSockets {
    private int port = 8088;

    public static void main(String[] args) {
        new SelectorSockets().serverStart();
    }

    public void serverStart() {
        System.out.println("server starting...");
        ServerSocketChannel serverSocketChannel = null;
        try {
            serverSocketChannel = ServerSocketChannel.open();
            ServerSocket serverSocket = serverSocketChannel.socket();
            serverSocket.bind(new InetSocketAddress(port));

            serverSocketChannel.configureBlocking(false);

            Selector selector = Selector.open();

            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("server started");

            while(true) {
                int count = selector.select();
                if(count == 0) {
                    continue;
                }
                Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                while(it.hasNext()) {
                    SelectionKey selectorKey  = it.next();
                    it.remove();
                    System.out.println("selectorKey.interestOps()=" + selectorKey.interestOps());
                    if(selectorKey.isAcceptable()) {
                        ServerSocketChannel serverChannel = (ServerSocketChannel) selectorKey.channel();
                        SocketChannel socketChannel = serverChannel.accept();


                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);

                        sayHi(socketChannel);
                    } else if(selectorKey.isReadable()) {
                        readData(selectorKey);
                    } else if(selectorKey.isWritable()) {
                        writeData(selectorKey);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(serverSocketChannel != null) {
                try {
                    serverSocketChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    private void writeData(SelectionKey selectorKey) throws IOException {
        System.out.println("server writeData begin");
        SocketChannel socketChannel = (SocketChannel) selectorKey.channel();
        writeBuffer.clear();
        writeBuffer.put("A haha this is a very long long long long string, just for test........".getBytes());
        writeBuffer.flip();
        socketChannel.write(writeBuffer);
        System.out.println("\nserver writeData done");
    }

    private ByteBuffer readBuffer = ByteBuffer.allocate(512);
    private ByteBuffer writeBuffer = ByteBuffer.allocate(512);

    protected void readData(SelectionKey selectorKey) throws IOException {
        System.out.println("server readData begin");
        SocketChannel socketChannel = (SocketChannel) selectorKey.channel();
        readBuffer.clear();
        int length = -1;
        while((length = socketChannel.read(readBuffer)) > 0) {
            readBuffer.flip();
            while(readBuffer.hasRemaining()) {
                socketChannel.write(readBuffer);
            }
            readBuffer.clear();
        }
        if(length < 0) {
            socketChannel.close();
        }
        System.out.println("\nserver readData done!");
    }

    private void sayHi(SocketChannel socketChannel) throws IOException {
        ByteBuffer bf = ByteBuffer.allocate(64);
        bf.put("Hi there, this is server speaking...\n".getBytes());
        bf.flip();
        socketChannel.write(bf);
    }
}
