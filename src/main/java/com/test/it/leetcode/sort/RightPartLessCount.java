package com.test.it.leetcode.sort;

import com.test.it.algorithms.ArrayUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: theonecai
 * @Date: Create in 2020/8/27 20:41
 * @Description:
 */
public class RightPartLessCount {

    public List<Integer> countSmaller2(int[] nums) {
        int[] count = new int[nums.length];
        Node[] nodes = new Node[nums.length];
        for (int i = 0; i < nums.length; i++) {
            nodes[i] = new Node(nums[i], i);
        }

        quickSort(nodes, 0, nodes.length - 1, count);

        return Arrays.stream(count).boxed().collect(Collectors.toList());
    }

    public void quickSort(Node[] nodes, int start, int end, int[] count) {
        if (start >= end) {
            return;
        }

        int p = partition(nodes, start, end, count);

        quickSort(nodes, start, p - 1, count);
        quickSort(nodes, p + 1, end, count);
    }

    private int partition(Node[] nodes, int start, int end, int[] count) {
        int pivotIndex = start;
        Node pivot = nodes[pivotIndex];
        int low = start;
        int high = end;

        while (low < high) {
            while (high > low && nodes[high].value >= pivot.value) {
                high--;
            }
            while (low < high && nodes[low].value <= pivot.value) {
                low++;
            }
            if (low < high) {
                swap(nodes, low, high, count);
            }
        }
        if (low != pivotIndex) {
            swap(nodes, pivotIndex, low, count);
        }
        return low;
    }

    private void swap(Node[] nodes, int low, int high, int[] count) {
        count[nodes[low].index] += high - low;
        for (int i = low + 1; i < high; i++) {
            if (nodes[i].value > nodes[high].value) {
                count[nodes[i].index] += 1;
            }
        }
        Node tmp = nodes[low];
        nodes[low] = nodes[high];
        nodes[high] = tmp;
    }

    public List<Integer> countSmaller(int[] nums) {
        Node[] nodes = new Node[nums.length];
        for (int i = 0; i < nums.length; i++) {
            nodes[i] = new Node(nums[i], i);
        }

        Arrays.sort(nodes);

        BitSet bitSet = new BitSet(nums.length + 1);
        List<Integer> count = new ArrayList<>(nodes.length);
        for (int i = 0; i < nodes.length; i++) {
            bitSet.set(nodes[i].index);
            count.add((int)sumBits(bitSet.get(nodes[i].index + 1, nodes.length)));
        }

        return count;
    }

    private long sumBits(BitSet bitSet) {
        long count = 0;
        for (long bits : bitSet.toLongArray()) {
            count += getCount(bits);
        }
        return count;
    }

    public long getCount(long i) {
        long n;
        for(n=0; i > 0; n++) {
            i &= (i - 1);
        }
        return n;
    }

    static class Node implements Comparable<Node> {
        int value;
        int index;

        public Node(int value, int index) {
            this.value = value;
            this.index = index;
        }

        @Override
        public int compareTo(Node o) {
            return this.value - o.value;
        }
    }

    public static void main(String[] args) {
        RightPartLessCount lessCount = new RightPartLessCount();
        /**
         * 5 4 3 2 1
         * 1 4 3 2 5
         * 1 2 3 4 5
         */
        int[] nums = {5,4,3,2,1};
        System.out.println(lessCount.countSmaller2(nums));
        System.out.println(lessCount.countSmaller(nums));
        int[] nums3 = {5,2,6,1};
        System.out.println(lessCount.countSmaller2(nums3));
        System.out.println(lessCount.countSmaller(nums3));
        int[] nums2 = ArrayUtil.randIntArray(100000);
        long a = System.currentTimeMillis();
        lessCount.countSmaller(nums2);
        System.out.println("cost: " + (System.currentTimeMillis() - a));
        int[] nums4 = ArrayUtil.randIntArray(100000);
        a = System.currentTimeMillis();
        lessCount.countSmaller(nums4);
        System.out.println("cost: " + (System.currentTimeMillis() - a));
    }
}
