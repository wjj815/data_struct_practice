package org.example.demo.leetcode.unionFind;

import java.util.*;

public class Solution {


    /**
     * 130. 被围绕的区域
     * 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
     * @param board
     */
    public void solve(char[][] board) {
        if(board.length == 0) {
            return;
        }

        int m = board.length;;
        int n = board[0].length;
        // 给 dummy 留一个额外位置
        UnionFindSet unionFindSet = new UnionFindSet(m * n + 1);
        int dummy = m * n;
        
        // 将首列和末列的 O 与 dummy 连通
        for(int i = 0; i < m; i++) {
            if(board[i][0] == 'O') {
                unionFindSet.union(i * n, dummy);
            }
            
            if(board[i][n - 1] == 'O') {
                unionFindSet.union(i * n + n - 1, dummy);
            }
        }

        // 首行和末行的 O 与 dummy 连通
        for(int j = 0; j < n; j++) {
            if(board[0][j] == 'O') {
                unionFindSet.union(j, dummy);
            }

            if(board[m - 1][j] == 'O') {
                unionFindSet.union((m - 1) * n + j, dummy);
            }
        }
        
        // 方向数组 d 是上下左右搜索的常用手法
        int[][] d = new int[][]{{1, 0}, {0, 1}, {0, -1}, {-1, 0}};
        for(int i = 1; i < m - 1; i++) {
            for(int j = 1; j < n - 1; j++) {
                if(board[i][j] == 'O') {
                    // 将此 O 与上下左右的 O 连通
                    for(int k = 0; k < 4; k++) {
                        int x = i + d[k][0];
                        int y = j + d[k][1];
                        if(board[x][y] == 'O') {
                            unionFindSet.union(x * n + y, i * n + j);
                        }
                    }
                }
            }
        }

        // 所有不和 dummy 连通的 O， 都要被替换
        for(int i = 1; i < m - 1; i++) {
            for(int j = 1; j < n - 1; j++) {
                if(!unionFindSet.connected(dummy, i * n + j)) {
                    board[i][j] = 'X';
                }
            }
        }
    }


    /**
     * 990. 等式方程的可满足性
     * 给定一个由表示变量之间关系的字符串方程组成的数组，每个字符串方程 equations[i] 的长度为 4，并采用两种不同的形式之一："a==b" 或 "a!=b"。在这里，a 和 b 是小写字母（不一定不同），表示单字母变量名。
     *
     * 只有当可以将整数分配给变量名，以便满足所有给定的方程时才返回 true，否则返回 false。 
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/satisfiability-of-equality-equations
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param equations
     * @return
     */
    public boolean equationsPossible(String[] equations) {
        UnionFindSet unionFindSet = new UnionFindSet(26);
        // 先让相等字母形成连通分量
        for (String equation : equations) {

            if(equation.charAt(1) == '=') {
                char x = equation.charAt(0);
                char y = equation.charAt(3);
                unionFindSet.union(x - 'a', y - 'a');
            }
        }

        // 检查不等关系是否打破相等关系的连通性
        for (String equation : equations) {
            if(equation.charAt(1) == '!') {
                char x = equation.charAt(0);
                char y = equation.charAt(3);
                // 如果相等关系成立，就是逻辑冲突
                if(unionFindSet.connected(x - 'a', y - 'a')) {
                    return false;
                }
            }
        }

        return true;
    }


    /**
     * 力扣 261 以图判树
     *
     * 给你输入编号从 0 到 n - 1 的 n 个 节点， 和一个无向边列表 edges(每条边用节点二元组表示）， 请你判断输入的这些边
     * 组成的结构是否是一颗树
     *
     * n = 5
     * edges = [[0, 1], [0, 2], [0, 3], [1, 4]]
     *
     * true
     *
     *  判断输入的若干条边是否能构造出一棵树结构
     * @param n
     * @param edges
     * @return
     */
    public boolean validTree(int n, int[][] edges) {
        // 初始化 0 .. n - 1 共 n 个节点
        UnionFindSet unionFindSet = new UnionFindSet(n);
        // 遍历所有边， 将组成边的两个节点进行连接
        for(int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            // 若两个节点已经在同一连通分量中， 会产生环
            if(unionFindSet.connected(u, v)) {
                return false;
            }
            // 这条边不会产生环， 可以是树的一部分
            unionFindSet.union(u, v);
        }
        // 要保证最后只形成了一棵树， 即只有一个连通分量
        return unionFindSet.count() == 1;
    }


    /**
     * 1135. 最低成本连通所有城市
     *
     * 最小生成树， 就是图中若干边的集合(mst)
     * 1. 包含图中的所有节点
     * 2. 形成的结构是树结构（即不存在环）
     * 3. 权重和最小
     * 贪心思路：
     * 将所有边按照权重从小到大排序，从权重最小的边开始遍历， 如果这条边和 mst 中的其他边不会形成环，则这条边是最小生成树的一部分，
     * 将它加入 mst 集合，否则，这条边不是最小生成树的一部分，不要把它加入 mst 集合。
     *
     * N 座城市， 1 到 N 次序编号， 给一些可连接的选项 connections
     *
     * 计算 连通所有城市最小成本，如果无法连通所有城市，请返回 -1
     * @param n
     * @param connections
     * @return
     */
    public int minimumCost(int n, int[][] connections) {

        // 城市编号为 1.. n, 所以初始化大小为 n + 1
        UnionFindSet unionFindSet = new UnionFindSet(n + 1);

        // 对所有边按照权重从小到大排序
        Arrays.sort(connections, Comparator.comparingInt(a -> a[2]));
        // 记录最小生成树的权重之和
        int mst = 0;
        for (int[] connection : connections) {
            int u = connection[0];
            int v = connection[1];
            int weight = connection[2];
            // 若这条边会产生环，则不能加入 mst
            if(unionFindSet.connected(u, v)) {
                continue;
            }
            // 若这条边不会产生环， 则属于最小生成树
            mst += weight;
            unionFindSet.union(u, v);
        }

        // 保证所有节点都被连通
        // 按理说 unionFindSet.count() == 1, 说明所有节点被连通
        // 但因为节点 0 没有被使用， 所以 0 会额外占用一个连通分量
        return unionFindSet.count() == 2 ? mst : -1;
    }


    /**
     * 1584. 连接所有点的最小费用
     * 给你一个points 数组，表示 2D 平面上的一些点，其中 points[i] = [xi, yi] 。
     *
     * 连接点 [xi, yi] 和点 [xj, yj] 的费用为它们之间的 曼哈顿距离 ：|xi - xj| + |yi - yj| ，其中 |val| 表示 val 的绝对值。
     *
     * 请你返回将所有点连接的最小总费用。只有任意两点之间 有且仅有 一条简单路径时，才认为所有点都已连接。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/min-cost-to-connect-all-points
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param points
     * @return
     */
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        // 生成所有边及权重
        List<int[]> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                int xi = points[i][0], yi = points[i][1];
                int xj = points[j][0], yj = points[j][1];
                // 用坐标在 points 中的索引表示坐标点
                edges.add(new int[]{
                        i, j, Math.abs(xi - xj) + Math.abs(yi - yj)
                });
            }
        }

        edges.sort(Comparator.comparingInt(a -> a[2]));

        // 执行 Kruskal 算法
        int mst = 0;
        UnionFindSet unionFindSet = new UnionFindSet(n);

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int weight = edge[2];
            // 若这条边会产生环， 则不能加入 mst
            if(unionFindSet.connected(u, v)) {
                continue;
            }
            // 若这条边不会产生环，则属于最小生成树
            mst += weight;
            unionFindSet.union(u, v);
        }
        return mst;
    }
}
