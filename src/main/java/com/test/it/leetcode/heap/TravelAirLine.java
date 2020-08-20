package com.test.it.leetcode.heap;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * leetcode 787
 *
 * @Author: theonecai
 * @Date: Create in 2020/8/20 20:14
 * @Description:
 */
public class TravelAirLine {

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        if (src == dst) {
            return 0;
        }
        Map<Integer, List<int[]>> cityAirLineMap = new HashMap<>();
        for (int[] edge : flights) {
            cityAirLineMap.computeIfAbsent(edge[0], e -> new ArrayList<>()).add(edge);
        }
        // levelTime[i]记录到达i城市的费用和经过的中转数
        TransferCost[] transferCost = new TransferCost[n + 1];
        for (int i = 0; i < transferCost.length; i++) {
            transferCost[i] = new TransferCost(i);
            transferCost[i].setTransfers(-1);
        }
        int[] dstCost = new int[K + 1];
        Arrays.fill(dstCost, -1);

        // int[0]=城市,int[1]=经过第几中转到达int[0]
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{src, -1});

        int[] first = null;
        while (!queue.isEmpty()) {
            first = queue.poll();

            List<int[]> nextCities = cityAirLineMap.get(first[0]);
            if (nextCities == null || nextCities.isEmpty()) {
                continue;
            }

            int[] next;
            TransferCost lt;
            for (int[] toNextCity : nextCities) {
                next = new int[]{toNextCity[1], first[1] + 1};
                lt = transferCost[next[0]];
                int nextTransferCost = toNextCity[2] + (transferCost[toNextCity[0]].getTransfers() == first[1] ?
                        transferCost[toNextCity[0]].getCost() : transferCost[toNextCity[0]].getPreCost());
                if (lt.getTransfers() == next[1]) {
                    lt.setPreCost(lt.getCost());
                    lt.setCost(Math.min(nextTransferCost, lt.getCost()));
                } else {
                    lt.setTransfers(next[1]);
                    lt.setPreCost(lt.getPreCost() == -1 ? nextTransferCost : lt.getCost());
                    lt.setCost(nextTransferCost);
                }
                dstCost[lt.getTransfers()] = lt.getCost();

                if (next[1] >= K) {
                    continue;
                }
                queue.add(next);
            }

        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i <= K; i++) {
            min = Math.min(min, dstCost[i]);
        }
        return min == 0 ? -1 : min;
    }

    static class TransferCost {
        /**
         * 城市
         */
        int city;
        /**
         * 经过transfers次中转的费用
         */
        int cost;
        /**
         * 经过transfers-1次中转的费用
         */
        int preCost = -1;
        /**
         * 中转次数
         */
        int transfers;

        public TransferCost(int city) {
            this.city = city;
        }

        public int getCity() {
            return city;
        }

        public void setCity(int city) {
            this.city = city;
        }

        public int getCost() {
            return cost;
        }

        public void setCost(int cost) {
            this.cost = cost;
        }

        public int getTransfers() {
            return transfers;
        }

        public void setTransfers(int transfers) {
            this.transfers = transfers;
        }

        public int getPreCost() {
            return preCost;
        }

        public void setPreCost(int preCost) {
            this.preCost = preCost;
        }
    }

    public static void main(String[] args) {
        TravelAirLine airLine = new TravelAirLine();

        int[][] edges = {
                {1, 2, 100},
                {1, 3, 400},
                {1, 4, 600},
                {2, 5, 300},
                {2, 6, 600},
                {3, 6, 400},
                {4, 3, 200},
                {5, 3, 100},
                {0, 1, 100},
        };
        Assert.assertEquals(-1, airLine.findCheapestPrice(6, edges, 1, 6, 0));
        Assert.assertEquals(700, airLine.findCheapestPrice(6, edges, 1, 6, 1));
        Assert.assertEquals(1200, airLine.findCheapestPrice(6, edges, 1, 6, 2));
        Assert.assertEquals(900, airLine.findCheapestPrice(6, edges, 1, 6, 3));
        Assert.assertEquals(-1, airLine.findCheapestPrice(6, edges, 1, 6, 4));
        Assert.assertEquals(-1, airLine.findCheapestPrice(6, edges, 1, 6, 5));
        Assert.assertEquals(0, airLine.findCheapestPrice(6, edges, 1, 1, 5));
        /**
         * 3
         * [[0,1,2],[1,2,1],[2,0,10]]
         * 1
         * 2
         * 1
         */
        int[][] flight = {
                {0,1,2},
                {1,2,1},
                {2,0,10},
        };
        Assert.assertEquals(1, airLine.findCheapestPrice(3, flight, 1, 2, 1));
    }
}
