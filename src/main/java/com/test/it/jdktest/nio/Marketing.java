package com.test.it.jdktest.nio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.GatheringByteChannel;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * <<Java NIO> chap3-2 example
 * Created by caizh on 2015/9/7 0007.
 */
public class Marketing {
    private static final String TMP_FILE = "tmp.txt";

    public static void main(String[] args) throws IOException {
        int reps = 10;
        if(args.length > 0) {
            reps = Integer.parseInt(args[0]);
        }
        FileOutputStream fos = new FileOutputStream(TMP_FILE);
        GatheringByteChannel gatherChannel = fos.getChannel();
        ByteBuffer[] bs = getBs(reps);
        while(gatherChannel.write(bs) > 0) {
            // do nothing here
        }

        System.out.println("copy to " + TMP_FILE);
        fos.close();

    }

    private static String[] col1 = {
        "aaaaaaa", "bbbb", "ccccc", "ddddd"
    };

    private static String[] col2 = {
        "eeeee", "fffffff", "hhhhh", "jjjjjjj"
    };

    private static ByteBuffer[] getBs(int reps) {
        List list = new LinkedList();
        for(int i = 0; i < reps; i++) {
            list.add(pickRandom(col1, " "));
            list.add(pickRandom(col2, " "));
        }
        ByteBuffer[] bfs = new ByteBuffer[list.size()];
        list.toArray(bfs);
        return bfs;
    }

    private static Random rand = new Random();

    private static ByteBuffer pickRandom(String[] strs, String suffix) {
        String str = strs[rand.nextInt(strs.length)];
        ByteBuffer bf = ByteBuffer.allocate(str.length() + suffix.length());
        bf.put(str.getBytes());
        bf.put(suffix.getBytes());
        bf.flip();
        return bf;
    }
}
