package com.test.it.jdktest.nio;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Author: caizh
 * CreateTime: 2015/6/19 11:13
 * Version: 1.0
 */
public class BufferTest {
    public static void main(String[] args) throws IOException {
        BufferTest test = new BufferTest();
        test.testFileChannel();
    }

    public void testBuffer() throws IOException {
        ByteBuffer notDirectByteBuffer = ByteBuffer.allocate(64);
        ByteBuffer directBuffer = ByteBuffer.allocateDirect(64);

        File test = new File("test.txt");
        test.createNewFile();
        byte[] data = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        byte[] out = new byte[4096];
        System.out.println("position=" + directBuffer.position() + ", limit=" + directBuffer.limit() + ", capacity=" + directBuffer.capacity());
        directBuffer.put(data, 0, 16);
        System.out.println("position=" + directBuffer.position() + ", limit=" + directBuffer.limit() + ", capacity=" + directBuffer.capacity());
        directBuffer.flip();
        System.out.println("position=" + directBuffer.position() + ", limit=" + directBuffer.limit() + ", capacity=" + directBuffer.capacity());
        directBuffer.get(out, 0, 16);
        System.out.println("position=" + directBuffer.position() + ", limit=" + directBuffer.limit() + ", capacity=" + directBuffer.capacity());
        directBuffer.rewind();
        System.out.println("position=" + directBuffer.position() + ", limit=" + directBuffer.limit() + ", capacity=" + directBuffer.capacity());
        for(int i = 0; i < 16; i++) {
            System.out.print(directBuffer.get(i) + " ");
        }
    }

    public void testFileChannel() throws IOException {
        File f = new File("src/main/resources/test.txt");
        System.out.println(f.getAbsolutePath());
        RandomAccessFile file = new RandomAccessFile(f, "rw");
        ByteBuffer bbf = ByteBuffer.allocate(1024);
        FileChannel channel = file.getChannel();
        int byteLen = channel.read(bbf);
        while(byteLen > 0) {
            bbf.flip();
            while(bbf.hasRemaining()) {
                System.out.print((char)bbf.get());
            }
            byteLen = channel.read(bbf);
        }
        bbf.clear();
        bbf.put("hello this is a test sample".getBytes());
        bbf.flip();
        channel.write(bbf);
    }
}
