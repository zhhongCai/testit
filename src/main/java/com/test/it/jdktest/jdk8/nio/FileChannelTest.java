package com.test.it.jdktest.jdk8.nio;

import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by caizh on 2015/8/27 0027.
 */
public class FileChannelTest {
    @Test
    public void testFileChannel() {
        FileChannel fromFileChannel = null;
        FileChannel toFileChannel = null;
        try {
            File fromFile = new File("src/main/resources/fileChannelTest.txt");
            fromFileChannel = new RandomAccessFile(fromFile, "rw").getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            byteBuffer.put("hello this is a fileChannelTest sample".getBytes());
            byteBuffer.flip();
            fromFileChannel.write(byteBuffer);

            File toFile = new File("src/main/resources/fileChannelTest_dump.txt");
            toFileChannel = new FileOutputStream(toFile).getChannel();
            fromFileChannel.transferTo(0, fromFile.length(), toFileChannel);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(null != fromFileChannel) {
                try {
                    fromFileChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(null != toFileChannel) {
                try {
                    toFileChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
