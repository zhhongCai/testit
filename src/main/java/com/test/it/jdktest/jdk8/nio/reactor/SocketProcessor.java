package com.test.it.jdktest.jdk8.nio.reactor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

/**
 * @Author: zhenghong.cai
 * @Date: Create in 2020/3/13 15:45
 * @Description:
 */
public class SocketProcessor implements Runnable {
    private SelectionKey key;

    public SocketProcessor(SelectionKey key) {
        this.key = key;
    }

    @Override
    public void run() {
        SocketChannel socket = (SocketChannel) key.channel();
        if (!socket.isOpen() || !key.isValid()) {
            System.out.println("socket is not open!");
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        if (key.isReadable()) {
            read(socket);
        } else if (key.isWritable()) {
            write(socket, "hello there");
        } else {
            try {
                socket.close();
                System.out.println("socket closed");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void write(SocketChannel socket, String data) {
        ByteBuffer writeBuffer = ByteBuffer.allocate(256);;
        try {
            System.out.println("server writeData begin: " + data);
            writeBuffer.clear();
            writeBuffer.put(data.getBytes());
            writeBuffer.flip();
            socket.write(writeBuffer);
            System.out.println("server writeData done");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writeBuffer.clear();
            writeBuffer = null;
        }
    }

    private void read(SocketChannel socket) {
        ByteBuffer buffer = ByteBuffer.allocate(256);
        try {
            socket.read(buffer);
            String output = new String(buffer.array()).trim();
            System.out.println("收到消息: " + output + "$");


            write(socket, "message received success!" + Math.random());

            // 继续读
            key.interestOps(SelectionKey.OP_READ);

            if (output.equals("quit")) {
                socket.close();
                System.out.println("连接退出了");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            buffer.clear();
            buffer = null;
        }
    }
}
