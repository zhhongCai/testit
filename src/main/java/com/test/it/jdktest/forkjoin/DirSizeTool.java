package com.test.it.jdktest.forkjoin;

import com.google.common.collect.Lists;

import java.io.File;
import java.util.List;
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

        long size = 0;

        // Ignore files which are not files and dirs
        if (file.isFile()) {
            size = file.length();
        } else {
            final File[] children = file.listFiles();
            if (children != null) {
                for (final File child : children) {
                    size += DirSizeTool.sizeOf(child);
                }
            }
        }


        return size;
    }

    /**
     * fork/join方式
     * @param file
     * @return
     */
    public static long sizeOf2(File file) {
        SizeOfTask task = new SizeOfTask(file);
        return task.compute();
    }

    public static void main(String[] args) {
        System.out.println(DirSizeTool.sizeOf(new File("/home/caizh/tmp")));
        System.out.println("============================");
        System.out.println("sizeOf2=" + DirSizeTool.sizeOf2(new File("/home/caizh/Downloads")));
    }

    public static class SizeOfTask extends RecursiveTask<Long> {

        private final File file;

        public SizeOfTask(File file) {
            this.file = file;
        }

        @Override
        protected Long compute() {

            if (file == null || !file.exists()) {
                return 0L;
            }
            System.out.println("comput size of " + file.getAbsolutePath() + "/" + file.getName());

            if (file.isFile()) {
                return file.length();
            }

            final File[] children = file.listFiles();
            if (children != null) {

                Long size = 0L;
                List<SizeOfTask> tasks = Lists.newArrayListWithCapacity(children.length);
                for (final File child : children) {
                    SizeOfTask t = new SizeOfTask(child);
                    t.fork();
                    tasks.add(t);
                }

                for (SizeOfTask t : tasks) {
                    size += t.join();
                }
                return size;
            }

            return file.length();
        }
    }
}


