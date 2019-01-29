package com.test.it.algorithms;

import com.test.it.algorithms.common.Graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: caizhh
 * @Date: Create in 19-1-29 下午6:54
 * @Description:
 */
public class BFS {

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


        bfs(graph, 0, 7);
    }

    private static void bfs(Graph graph, int startVertex, int endVertex) {
        //已访问的端点,visitedVertex[i] = true,表示端点i已访问
        boolean[]  visitedVertex = new boolean[graph.getVertex()];

        //存储已经被访问、但相连的顶点还没有被访问的顶点
        Queue<Integer> toBeVisitedQueue = new LinkedList<>();

        //搜索路径,通过顶点i的邻接表访问到顶点j，那prev[j]就等于i
        int[] path = new int[graph.getVertex()];

        for (int i = 0; i < visitedVertex.length; i++) {
            visitedVertex[i] = false;
            path[i] = -1;
        }

        visitedVertex[startVertex] = true;
        //起点
        toBeVisitedQueue.add(startVertex);

        while(toBeVisitedQueue.size() > 0) {
            //当前端点
            int currentVertex = toBeVisitedQueue.poll();
            for (Integer relatedVertex : graph.releatedVertexs(currentVertex)) {
                //未访问端点
                if (!visitedVertex[relatedVertex]) {
                    path[relatedVertex] = currentVertex;
                    visitedVertex[relatedVertex] =true;

                    if (relatedVertex == endVertex) {
                        printPrev(path, startVertex, endVertex);
                        return;
                    }
                    toBeVisitedQueue.add(relatedVertex);
                }
            }
        }
    }

    private static void printPrev(int[] prev, int startVertex, int endVertex) {
        if (prev[endVertex] != -1 && endVertex != startVertex) {
            printPrev(prev, startVertex, prev[endVertex]);
        }
        System.out.print(endVertex + " ");
    }
}
