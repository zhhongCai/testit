package com.test.it.jdktest.jdk8.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;
import java.util.List;

/**
 * <<Java NIO>> examples
 * Created by caizh on 2015/9/8 0008.
 */
public class SelectorSocketsThreadPool extends SelectorSockets {
    private static int MAX_THREADS = 5;

    private ThreadPool pool = new ThreadPool(MAX_THREADS);

    public static void main(String[] args) {
        new SelectorSocketsThreadPool().serverStart();
    }

    @Override
    protected void readData(SelectionKey selectorKey) throws IOException {
        WorkerThread worker = pool.getWorker();
        if(worker == null) {
            return;
        }
        worker.serviceChannel(selectorKey);
    }

    private class ThreadPool {
        List idle = new LinkedList();

        ThreadPool(int poolSize) {
            if(poolSize < 1) {
                throw new IllegalArgumentException("thread pool size illegal");
            }
            for (int i = 0; i < poolSize; i++) {
                WorkerThread worker = new WorkerThread(this);
                worker.setName("Worker" + (i+1));
                worker.start();
                idle.add(worker);
            }
        }

        public WorkerThread getWorker() {
            WorkerThread worker = null;
            synchronized (idle) {
                if(idle.size() > 0) {
                    worker = (WorkerThread) idle.remove(0);
                }
            }
            return worker;
        }

        public void returnWorker(WorkerThread worker) {
            synchronized (idle) {
                idle.add(worker);
            }
        }
    }

    private class WorkerThread extends Thread {
        private ThreadPool threadPool;
        private SelectionKey key;

        private ByteBuffer buffer = ByteBuffer.allocate(1024);

        public WorkerThread(ThreadPool threadPool) {
            this.threadPool = threadPool;
        }

        @Override
        public synchronized void run() {
            System.out.println(this.getName() + " is ready");
            while(true) {
                try {
                    this.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                    Thread.interrupted();
                }
                if(key == null) {
                    System.out.println("key is null!!!");
                    continue;
                }
                System.out.println(this.getName() + " has been awakened");
                try {
                    drainChannel(key);
                } catch (Exception e) {
                    System.out.println("Caught '" + e + "' closing channel");
                    try {
                        key.channel().close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    key.selector().wakeup();
                }
                key = null;

                this.threadPool.returnWorker(this);
            }
        }

        private void drainChannel(SelectionKey key) {
            SocketChannel socketChannel = (SocketChannel) key.channel();
            int count = -1;
            buffer.clear();
            try {
                while((count = socketChannel.read(buffer)) > 0) {
                    buffer.flip();
                    while(buffer.hasRemaining()) {
                        socketChannel.write(buffer);
                    }
                    buffer.clear();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("count=" + count);
            if(count < 0) {
                try {
                    socketChannel.close();
                    return;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            key.interestOps(key.interestOps() | SelectionKey.OP_READ);
            System.out.println("drainChannel: key.interestOps()=" + key.interestOps());

            key.selector().wakeup();
        }

        public synchronized void serviceChannel(SelectionKey selectorKey) {
            System.out.println("serviceChannel");
            System.out.println("serviceChannel: selectorKey.interestOps()=" + selectorKey.interestOps());
            this.key = selectorKey;
            key.interestOps(key.interestOps() & (~SelectionKey.OP_READ));
            System.out.println("serviceChannel: key.interestOps()=" + key.interestOps());
            this.notify();
        }
    }

}
