package com.test.it.algorithms;

import com.test.it.algorithms.common.Graph;

/**
 * Created by caizh on 19-1-29.
 */
public class DFS {

    public static void main(String[] args) {
        Graph graph = new Graph(10);

        //初始化tu图
        graph.addEdge(0, 1);
        graph.addEdge(0, 3);
        graph.addEdge(1, 4);
        graph.addEdge(1, 2);
        graph.addEdge(2, 5);
        graph.addEdge(3, 4);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(4, 6);
        graph.addEdge(5, 7);
        graph.addEdge(6, 7);

        dfs(graph, 0, 7);
    }

    private static void dfs(Graph graph, int start, int end) {

    }
}
