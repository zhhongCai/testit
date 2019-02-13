package com.test.it.jdktest.jdk8.forkjoin;

import java.io.File;
import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

/**
 * @Author: caizhh
 * @Date: Create in 18-9-15 上午9:32
 * @Description:
 */
public class DirSizeTool {

    /**
     * 单线程方式
     * @param file
     * @return
     */
    public static long sizeOf(File file) {
        if (!file.exists()) {
            return 0;
        }
        System.out.println("Computing size of: " + file.getAbsolutePath() + "/" + file.getName());

        long size = file.length();

        // Ignore files which are not files and dirs
        if (file.isFile()) {
            size = file.length();
        } else {
            final File[] children = file.listFiles();
            if (children != null && children.length > 0) {
                for (final File child : children) {
                    size += DirSizeTool.sizeOf(child);
                }
            }
        }


        return size;
    }

    /**
     * fork/join方式
     * @param files
     * @return
     */
    public static long sizeOf2(File[] files) {
        SizeOfTask task = new SizeOfTask(files);
        return task.compute();
    }

    public static void main(String[] args) {
        System.out.println(DirSizeTool.sizeOf(new File("/opt")));
        System.out.println("============================");
        System.out.println("sizeOf2=" + DirSizeTool.sizeOf2(new File[] {new File("/opt")}));
    }

    public static class SizeOfTask extends RecursiveTask<Long> {

        private File[] file;

        public SizeOfTask(File[] file) {
            this.file = file;
        }

        @Override
        protected Long compute() {

            final File[] files = file;
            if (files == null || files.length == 0) {
                return 0L;
            }
//            System.out.println("compute: " + files.length);

            if (files.length == 1) {
                long length = files[0].length();
                if (files[0].isFile()) {
                    return length;
                } else {
                    File[] children = files[0].listFiles();
                    if (children == null || children.length == 0) {
                        return length;
                    }

                    return length + forkAndJoin(children);
                }
            } else {
                return forkAndJoin(files);
            }
        }

        private long forkAndJoin(File[] files) {
            int middle = files.length / 2;
            SizeOfTask t = new SizeOfTask(Arrays.copyOfRange(files, 0, middle));
            SizeOfTask t2 = new SizeOfTask(Arrays.copyOfRange(files, middle, files.length));
            t.fork();
            t2.fork();

            return t.join() + t2.join();
        }
    }
}


