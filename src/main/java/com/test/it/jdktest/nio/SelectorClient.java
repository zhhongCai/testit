package com.test.it.jdktest.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Created by caizh on 2015/9/10 0010.
 */
public class SelectorClient {
    public static void main(String[] args) {
        SocketChannel socketChannel = null;
        Selector selector = null;
        BufferedReader reader = null;
        try {
            socketChannel = SocketChannel.open();
            //要在connect之前调用
            socketChannel.configureBlocking(false);
            selector = Selector.open();

            socketChannel.connect(new InetSocketAddress(8088));
            socketChannel.register(selector, SelectionKey.OP_CONNECT);

            while(true) {
                selector.select();
                Iterator<SelectionKey> it = selector.selectedKeys() == null ? null : selector.selectedKeys().iterator();
                if(it == null || !it.hasNext()) {
                    System.out.println("null");
                    continue;
                }
                while(it.hasNext()) {
                    SelectionKey key = it.next();
                    it.remove();
                    process(key);
                }
                Thread.sleep(1000L);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if(reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(selector != null) {
                try {
                    selector.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(socketChannel != null) {
                try {
                    socketChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void process(SelectionKey key) throws IOException {
        if(key.isConnectable())  {
            SocketChannel socket = (SocketChannel) key.channel();
            if(socket.isConnectionPending()) {
                socket.finishConnect();
            }

            socket.configureBlocking(false);
//            socket.register(key.selector(), SelectionKey.OP_READ);
            key.interestOps(key.interestOps() | SelectionKey.OP_READ | SelectionKey.OP_WRITE);
            System.out.println("drainChannel: key.interestOps()=" + key.interestOps());
            key.selector().wakeup();

            ByteBuffer buffer = ByteBuffer.allocate(512);
            buffer.put("testtttttttttttttt".getBytes());
            buffer.flip();
            socket.write(buffer);

        } else if(key.isReadable()) {
            ByteBuffer buffer = ByteBuffer.allocate(512);
            SocketChannel socket = (SocketChannel) key.channel();
            byte[] str = new byte[512];
            int count = -1;
            System.out.print("read from server: ");
            while((count = socket.read(buffer)) > 0) {
                buffer.flip();
                while(buffer.hasRemaining()) {
                    buffer.get(str, 0, count);
                    System.out.print(new String(str, 0, count));
                }
                buffer.clear();
                System.out.println("--done");
            }
            if(count < 0) {
                socket.close();
            }
        } else if(key.isWritable()) {
            ByteBuffer buffer = ByteBuffer.allocate(512);
            SocketChannel socket = (SocketChannel) key.channel();
            buffer.put("this is client: ahhhhhhhh".getBytes());
            buffer.flip();
            socket.write(buffer);
        }
    }
}
