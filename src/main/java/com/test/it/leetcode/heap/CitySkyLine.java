package com.test.it.leetcode.heap;

import com.test.it.algorithms.ArrayUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author: theonecai
 * @Date: Create in 2020/8/7 19:28
 * @Description:
 */
public class CitySkyLine {

    public List<List<Integer>> getSkyline(int[][] buildings) {
        if (buildings == null || buildings.length == 0) {
            return Collections.emptyList();
        }

        return divideAndConquer(buildings, 0, buildings.length - 1);
    }

    private List<List<Integer>> divideAndConquer(int[][] buildings, int start, int end) {
        if (start + 1 == end || start == end) {
            return calculatePointer(buildings[start], buildings[end]);
        }

        int mid = start + (end - start) / 2;
        List<List<Integer>> leftPart = divideAndConquer(buildings, start, mid);
        List<List<Integer>> rightPart = divideAndConquer(buildings,mid + 1, end);

        return merge(leftPart, rightPart);
    }

    private List<Integer> toList(int... nums) {
        List<Integer> result = new ArrayList<>(nums.length);
        for (int num : nums) {
            result.add(num);
        }
        return result;
    }

    private List<List<Integer>> calculatePointer(int[] building, int[] building2) {
        List<List<Integer>> result = new ArrayList<>();
        // 不重叠
        if (building[1] < building2[0]) {
            result.add(toList(building[0], building[2]));
            result.add(toList(building[1], 0));
            result.add(toList(building2[0], building2[2]));
            result.add(toList(building2[1], 0));
        } else {
            // 部分重叠
            if (building[1] < building2[1]) {
                if (building[2] < building2[2]) {
                    result.add(toList(building[0], building[2]));
                    result.add(toList(building2[0], building2[2]));
                    result.add(toList(building2[1], 0));
                } else {
                    result.add(toList(building[0], building[2]));
                    if (building[2] > building2[2]) {
                        result.add(toList(building[1], building2[2]));
                    }
                    result.add(toList(building2[1], 0));
                }
            } else {
                // 完全重叠
                if (building[2] < building2[2]) {
                    result.add(toList(building[0], building[2]));
                    result.add(toList(building2[0], building2[2]));
                    result.add(toList(building2[1], building[2]));
                    result.add(toList(building[1], 0));
                } else {
                    result.add(toList(building[0], building[2]));
                    result.add(toList(building[1],0));
                }
            }
        }
        ArrayUtil.print(building);
        ArrayUtil.print(building2);
        System.out.println(result);

        return result;
    }

    private List<List<Integer>> merge(List<List<Integer>> leftPart, List<List<Integer>> rightPart) {
        System.out.println("merge:");
        System.out.println(leftPart);
        System.out.println(rightPart);

        List<List<Integer>> result = new ArrayList<>(leftPart.size() + rightPart.size());
        int leftIndex = 0;
        int rightIndex = 0;

        List<Integer>  leftPre = null;
        List<Integer>  rightPre = null;
        List<Integer> pre = null;
        while (leftIndex < leftPart.size() && rightIndex < rightPart.size()) {
            List<Integer> left = leftPart.get(leftIndex);
            List<Integer> right = rightPart.get(rightIndex);
            if (left.get(0) < right.get(0)) {
                int lh = leftPre != null && left.get(1) < leftPre.get(1) ? leftPre.get(1) : left.get(1);
                if (rightPre != null) {
                    if (isInRange(left, rightPre, right)) {
                        int rh = right.get(1) < rightPre.get(1) ? rightPre.get(1) : right.get(1);
                        if (lh < rh) {
                            leftIndex++;
                            leftPre = left;
                            continue;
                        } else {
                            leftIndex++;
                            leftPre = left;
                            left.set(1, left.get(1) < leftPre.get(0) ? lh : rh);
                            pre = left;
                            result.add(left);
                            continue;
                        }
                    } else {
                        pre.set(1, 0);
                    }
                }
                if (pre != null && pre.get(1).equals(lh)) {
                    pre.set(0, left.get(0));
                    leftPre = left;
                    pre = left;
                    leftIndex++;
                    continue;
                }

                leftPre = left;
                pre = left;
                result.add(left);
                leftIndex++;
            } else {
                int rh = rightPre != null && right.get(1) < rightPre.get(1) ? rightPre.get(1) : right.get(1);
                if (leftPre != null) {
                    if (isInRange(right, leftPre, left)) {
                        int lh = left.get(1) < leftPre.get(1) ? leftPre.get(1) : left.get(1);
                        if (rh < lh) {
                            rightIndex++;
                            rightPre = right;
                            continue;
                        } else {
                            rightIndex++;
                            rightPre = right;
                            right.set(1, right.get(1) <rightPre.get(0) ? rh : lh);
                            pre = right;
                            result.add(right);
                            continue;
                        }
                    } else {
                        pre.set(1, 0);
                    }
                }
                if (pre != null && pre.get(1).equals(rh)) {
                    pre.set(0, right.get(0));
                    rightPre = right;
                    pre = right;
                    rightIndex++;
                    continue;
                }

                rightPre = right;
                pre = right;
                result.add(right);
                rightIndex++;
            }
        }

        List<Integer> tmp;
        while (leftIndex < leftPart.size()) {
            tmp = leftPart.get(leftIndex++);
            if (pre != null && pre.get(0).equals(tmp.get(0))) {
                pre.set(1, tmp.get(1));
                leftIndex++;
                continue;
            }
            int lh = leftPre != null && tmp.get(1) < leftPre.get(1) ? rightPre.get(1) : tmp.get(1);
            if (pre.get(1) == lh) {
                pre.set(0, tmp.get(0));
                leftPre = tmp;
                continue;
            }
            result.add(tmp);
            pre = tmp;
        }
        while (rightIndex < rightPart.size()) {
            tmp = rightPart.get(rightIndex++);
            if (pre != null && pre.get(0).equals(tmp.get(0))) {
                pre.set(1, tmp.get(1));
                continue;
            }
            int rh = rightPre != null && tmp.get(1) < rightPre.get(1) ? rightPre.get(1) : tmp.get(1);
            if (pre.get(1) == rh) {
                pre.set(0, tmp.get(0));
                rightPre = tmp;
                continue;
            }
            result.add(tmp);
            pre = tmp;
        }

        pre.set(1, 0);

        System.out.println(result);
        System.out.println();
        return result;
    }

    private boolean isInRange(List<Integer> p, List<Integer> start, List<Integer> end) {
        return start.get(0) <= p.get(0) && p.get(0) <= end.get(0);
    }

    public static void main(String[] args) {
        CitySkyLine citySkyLine = new CitySkyLine();
        /**
         * (2,10) (3,15) (7,0) ;  (5,12) (12,0) (15,10) (20,0); (19,8) (24,0);
         * (2,10) (3,15) (7,12) (12,0) (15,10) (20,0); (19,8) (24,0);
         *
         * [[2,10],[3,15],[7,15],[9,12],[12,0],[15,10],[20,8],[24,0]]
         * [[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
         */
        int[][] buildings = {{2,9,10},{3,7,15},{5,12,12},{15,20,10},{19,24,8}};
        System.out.println(citySkyLine.getSkyline(buildings));
        System.out.println();

        int[][] b2 = {{2, 6, 11}, {5, 10, 12}, {7, 9, 14}};
        System.out.println(citySkyLine.getSkyline(b2));
        System.out.println();

        int[][] buildings2 = {{2,4,10}, {3,7,15}, {5,12,12}, {11, 16, 11}, {15,20,10},{19,24,8}};
        System.out.println(citySkyLine.getSkyline(buildings2));
        System.out.println();

        int[][] buildings3 = {{1, 2, 3}, {2, 4, 6}, {3, 8, 5}};
        System.out.println(citySkyLine.getSkyline(buildings3));
        System.out.println();

        int[][] buildings4 = {{1, 10, 5}};
        System.out.println(citySkyLine.getSkyline(buildings4));
        System.out.println();

        int[][] buildings5 = {{1, 10, 10}, {2, 4, 6}, {3, 5, 7}, {4, 10, 9}};
        System.out.println(citySkyLine.getSkyline(buildings5));
        System.out.println();


        int[][] buildings6 = {{0, 2, 3}, {2, 4, 3}, {4, 6, 3}};
        System.out.println(citySkyLine.getSkyline(buildings6));
        System.out.println();

    }
}
