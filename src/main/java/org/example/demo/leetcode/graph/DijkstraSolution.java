package org.example.demo.leetcode.graph;

import java.util.*;

public class DijkstraSolution {


    /**
     *
     * @param start 起点
     * @param graph 图
     * @return start 到其他节点的最短距离
     */
    public int[] dijkstra(int start, List<int[]>[] graph) {
        // 定义： distTo[i] 的值就是起点 start 到节点 i 的最短路径权重
        int[] distTo = new int[graph.length];
        Arrays.fill(distTo, Integer.MAX_VALUE);
        // base case , start 到 start 的最短距离就是 0
        distTo[start] = 0;

        // 优先级队列， distFromStart 较小的排在前面
        Queue<State> pq = new PriorityQueue<>(
                Comparator.comparingInt(value -> value.distFromStart));

        // 从起点 start 开始 BFS
        pq.offer(new State(start, 0));

        while(!pq.isEmpty()) {
            State curState = pq.poll();
            int curNodeId = curState.id;
            int curDistFromStart = curState.distFromStart;

            if(curDistFromStart > distTo[curNodeId]) {
                continue;
            }
            // 将 curNode 的相邻节点装入队列
            for(int[] neighbor : graph[curNodeId]) {
                int nextNodeId = neighbor[0];
                int distToNextNode = distTo[curNodeId] + neighbor[1];
                // 更新 dp table
                if(distTo[nextNodeId] > distToNextNode) {
                    distTo[nextNodeId] = distToNextNode;
                    pq.offer(new State(nextNodeId, distToNextNode));
                }
            }
        }
        return distTo;
    }


    /**
     *
     * 743. 网络延迟时间
     * 有 n 个网络节点，标记为 1 到 n。
     *
     * 给你一个列表 times，表示信号经过 有向 边的传递时间。 times[i] = (ui, vi, wi)，其中 ui 是源节点，vi 是目标节点， wi 是一个信号从源节点传递到目标节点的时间。
     *
     * 现在，从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1 。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/network-delay-time
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param times
     * @param n
     * @param k
     * @return
     */
    public int networkDelayTime(int[][] times, int n, int k) {
        // 节点编号是从 1 开始的，所以要一个大小为 n + 1 的邻接表
        List<int[]>[] graph = new LinkedList[n + 1];

        for(int i = 1; i <= n; i++) {
            graph[i] = new LinkedList<>();
        }

        // 构造图
        for(int[] edge : times) {
            int from = edge[0];
            int to = edge[1];
            int weight = edge[2];
            // from -> List<(to, weight)>
            // 邻接表存储图结构， 同事存储权重信息
            graph[from].add(new int[]{to, weight});
        }

        // 启动 dijkstra 算法计算以节点 k 为起点到其他节点的最低按路径
        int[] distTo = dijkstra(k, graph);

        // 找到最长的那一条最短路径
        int res = 0;
        for(int i = 1; i < distTo.length; i++) {
            if(distTo[i] == Integer.MAX_VALUE) {
                // 有节点不可达， 返回 -1
                return -1;
            }
            res = Math.max(res, distTo[i]);
        }
        return res;
    }


    /**
     * 1631. 最小体力消耗路径
     * 你准备参加一场远足活动。给你一个二维 rows x columns 的地图 heights ，其中 heights[row][col] 表示格子 (row, col) 的高度。一开始你在最左上角的格子 (0, 0) ，且你希望去最右下角的格子 (rows-1, columns-1) （注意下标从 0 开始编号）。你每次可以往 上，下，左，右 四个方向之一移动，你想要找到耗费 体力 最小的一条路径。
     *
     * 一条路径耗费的 体力值 是路径上相邻格子之间 高度差绝对值 的 最大值 决定的。
     *
     * 请你返回从左上角走到右下角的最小 体力消耗值 。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/path-with-minimum-effort
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param heights
     * @return
     */
    public int minimumEffortPath(int[][] heights) {
        int m = heights.length, n = heights[0].length;
        List<int[]>[] graph = new LinkedList[m * n];

        for (int i = 0; i < m * n; i++) {
            graph[i] = new LinkedList<>();
        }

        //构造图
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                List<int[]> adjs = adj(heights, i, j);
                // from - List<(to, weight)>
                // 邻接表存储图结构，同事存储权重信息
                for (int[] adj : adjs) {
                    graph[i * n + j].add(new int[]{adj[0] * n + adj[1], adj[2]});
                }
            }
        }
        int start = 0;
        // 定义： distTo[i] 的值就是起点 start 到节点 i 的最短路径权重
        int[] distTo = new int[graph.length];
        Arrays.fill(distTo, Integer.MAX_VALUE);
        // base case , start 到 start 的最短距离就是 0
        distTo[start] = 0;

        // 优先级队列， distFromStart 较小的排在前面
        Queue<State> pq = new PriorityQueue<>(
                Comparator.comparingInt(value -> value.distFromStart));

        // 从起点 start 开始 BFS
        pq.offer(new State(start, 0));

        while(!pq.isEmpty()) {
            State curState = pq.poll();
            int curNodeId = curState.id;
            int curDistFromStart = curState.distFromStart;

            if(curNodeId == graph.length  - 1) {
                return curDistFromStart;
            }

            if(curDistFromStart > distTo[curNodeId]) {
                continue;
            }
            // 将 curNode 的相邻节点装入队列
            for(int[] neighbor : graph[curNodeId]) {
                int nextNodeId = neighbor[0];
                int distToNextNode = Math.max(distTo[curNodeId],neighbor[1]);
                // 更新 dp table
                if(distTo[nextNodeId] > distToNextNode) {
                    distTo[nextNodeId] = distToNextNode;
                    pq.offer(new State(nextNodeId, distToNextNode));
                }
            }
        }
        return -1;
    }



    // 方向数组， 上下左右的坐标偏移量
    int[][] dirs = new int[][]{{0, 1},{1, 0}, {0, -1},{-1, 0}};

    public List<int[]> adj(int[][] matrix, int x, int y) {
        int m = matrix.length, n = matrix[0].length;
        // 存储相邻节点
        List<int[]> neighbors = new ArrayList<>();

        for (int[] dir : dirs) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            if(nx >= m || nx < 0 || ny >= n || ny < 0) {
                // 索引越界
                continue;
            }
            neighbors.add(new int[]{nx, ny, Math.abs(matrix[x][y] - matrix[nx][ny])});
        }
        return neighbors;
    }


    /**
     * 1514. 概率最大的路径
     * 给你一个由 n 个节点（下标从 0 开始）组成的无向加权图，该图由一个描述边的列表组成，其中 edges[i] = [a, b] 表示连接节点 a 和 b 的一条无向边，且该边遍历成功的概率为 succProb[i] 。
     *
     * 指定两个节点分别作为起点 start 和终点 end ，请你找出从起点到终点成功概率最大的路径，并返回其成功概率。
     *
     * 如果不存在从 start 到 end 的路径，请 返回 0 。只要答案与标准答案的误差不超过 1e-5 ，就会被视作正确答案。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/path-with-maximum-probability
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param n
     * @param edges
     * @param succProb
     * @param start
     * @param end
     * @return
     */
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        List<double[]>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        // 构造邻接表结构表示图
        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            graph[edge[0]].add(new double[]{ edge[1], succProb[i]});
            graph[edge[1]].add(new double[]{edge[0], succProb[i]});
        }

        class State {
            final int id;
            final double probFromStart;

            public State(int id, double probFromStart) {
                this.id = id;
                this.probFromStart = probFromStart;
            }
        }
        // 定义 probTo[i] 的值就是节点 start 到节点 i 的最大概率
        double[] probTo = new double[graph.length];
        // dp table 初始化为一个取不到的最小值(也可以使用默认的 0)
        Arrays.fill(probTo, -1);
        // base case : start 到 start 的概率就是 1
        probTo[start] = 1;

        Queue<State> pq = new PriorityQueue<>(
                (o1, o2) -> Double.compare(o2.probFromStart, o1.probFromStart));

        // 从起点 start 开始 bfs
        pq.offer(new State(start, 1.0));

        while(!pq.isEmpty()) {
            State curState = pq.poll();
            int curNodeId = curState.id;
            double curProbFromStart = curState.probFromStart;

            // 遇到终点提前返回
            if(curNodeId == end) {
                return curProbFromStart;
            }

            if(curProbFromStart < probTo[curNodeId]) {
                // 已经有一条概率最大的路径到达 curNode 节点了
                continue;
            }

            // 将 curNode 的相邻节点装入队列
            for (double[] neighbor : graph[curNodeId]) {
                int nexNodeId = (int) neighbor[0];
                // 看看从 curNode 达到 nextNode 的概率是否会更大
                double probeToNextNode = probTo[curNodeId] * neighbor[1];
                if(probTo[nexNodeId] < probeToNextNode) {
                    probTo[nexNodeId] = probeToNextNode;
                    pq.offer(new State(nexNodeId, probeToNextNode));
                }
            }
        }
        // 如果到达这里，说明从 start 开始无法到达 end， 返回 0
        return 0.0;
    }

}

class State {
    // 图节点的 id
    int id;
    // 从 start节点到当前节点的距离
    int distFromStart;

    State(int id, int distFromStart) {
        this.id = id;
        this.distFromStart = distFromStart;
    }
}