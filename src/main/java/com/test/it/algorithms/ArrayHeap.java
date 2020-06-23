package com.test.it.algorithms;

/**
 * @Author: theonecai
 * @Date: Create in 2020/6/17 14:21
 * @Description:
 */
public class ArrayHeap<T extends Comparable<? super T>> {
    /**
     * 基于数组
     */
    private Object[] heap;

    /**
     * 堆最大容量
     */
    private int capacity;

    /**
     * 当前元素个数
     */
    private int size;

    /**
     * 是否最大堆
     */
    private boolean maxHeap;

    public boolean isMaxHeap() {
        return maxHeap;
    }

    public ArrayHeap(int capacity) {
        this(capacity, true);
    }

    public ArrayHeap(int capacity, boolean maxHeap) {
        this.capacity = capacity;
        heap = new Object[capacity + 1];
        this.size = 0;
        this.maxHeap = maxHeap;
    }

    /**
     * 堆化
     * @param arr
     */
    public void heapify(T[] arr, int len) {
        if (arr == null) {
            return;
        }
        int i = len / 2;
        for (int j = 1; j <= len; j++) {
            heap[j] = arr[j - 1];
            size++;
        }
        for ( ; i >= 1; i--) {
            fixDown(i);
        }
    }

    public T top() {
        if (size == 0) {
            return null;
        }
        return (T) heap[1];
    }

    public void updateTop(T newData) {
        if (size == 0) {
            push(newData);
            return;
        }

        heap[1] = newData;

        fixDown(1);
    }

    public void push(T data) {
        heap[++size] = data;
        fixUp(size);
    }

    public T poll() {
        if (size <= 0) {
            return null;
        }
        Object data = heap[1];
        heap[1] = heap[size];
        heap[size--] = null;
        fixDown(1);
        return (T)data;
    }

    private int fixUp(int index) {
        int upIndex = index/2;
        int current = index;
        while (upIndex > 0) {
            if ((maxHeap && compare(heap[current], heap[upIndex]) > 0) ||
                    (!maxHeap && compare(heap[current], heap[upIndex]) < 0)) {
                swap(current, upIndex);
            } else {
                break;
            }
            current = upIndex;
            upIndex = current / 2;
        }

        return current;
    }

    private int compare(Object left, Object right) {
        return ((Comparable)left).compareTo(right);
    }

    private void swap(int from, int to) {
        Object tmp = heap[from];
        heap[from] = heap[to];
        heap[to] = tmp;
    }

    private int fixDown(int index) {
        int downIndex = index * 2;

        int current = index;
        while (downIndex <= size) {
            int leftIdx = downIndex;
            int rightIdx = downIndex + 1;
            if (rightIdx <=size && ((maxHeap && compare(heap[leftIdx], heap[rightIdx]) < 0) ||
                    (!maxHeap && compare(heap[leftIdx], heap[rightIdx]) > 0))) {
                downIndex = rightIdx;
            }
            if ((maxHeap && compare(heap[current], heap[downIndex]) < 0) ||
                    (!maxHeap && compare(heap[current], heap[downIndex]) > 0)) {
                swap(current, downIndex);
            } else {
                break;
            }
            current = downIndex;
            downIndex = current * 2;
        }
        return current;
    }

    public static void main(String[] args) {
        int len = 100;
        ArrayHeap<Integer> maxHeap = new ArrayHeap<>(len);
        Integer[] a = ArrayUtil.randArray(len);
        long start = System.currentTimeMillis();
        for (int i = 0; i < len; i++) {
            maxHeap.push(a[i]);
        }
//        System.out.println("origin:");
//        ArrayUtil.print(a);

        int pre = Integer.MAX_VALUE;
        int current;
        for (int i = 0; i < len; i++) {
            current = maxHeap.poll();
            if (pre < current) {
                System.out.println("maxHeap: pre = " + pre + ", current = " + current);
                return;
            }
            pre = current;
        }

        maxHeap = new ArrayHeap<>(len);
        maxHeap.heapify(a, a.length);

        pre = Integer.MAX_VALUE;
        for (int i = 0; i < len; i++) {
            current = maxHeap.poll();
            if (pre < current) {
                System.out.println("maxHeap: pre = " + pre + ", current = " + current);
                return;
            }
            pre = current;
        }

        ArrayHeap<Integer> minHeap = new ArrayHeap<>(len, false);
        for (int i = 0; i < len; i++) {
            minHeap.push(a[i]);
        }

        pre = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            current = minHeap.poll();
            if (pre > current) {
                System.out.println("minHeap: pre = " + pre + ", current = " + current);
                return;
            }
            pre = current;
        }

        minHeap = new ArrayHeap<>(len, false);
        minHeap.heapify(a, a.length);

        pre = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            current = minHeap.poll();
            if (pre > current) {
                System.out.println("minHeap: pre = " + pre + ", current = " + current);
                return;
            }
            pre = current;
        }
        System.out.println("done,costtime=" + (System.currentTimeMillis() - start));


    }
}
