package org.example.demo.leetcode.dfs;

import java.util.HashSet;

public class LandsProblemSolution {


    /**
     * 200. 岛屿数量
     * 难度
     * 中等
     * <p>
     * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
     * <p>
     * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
     * <p>
     * 此外，你可以假设该网格的四条边均被水包围。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/number-of-islands
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    infect(grid, i, j);
                    res++;
                }
            }
        }
        return res;
    }

    public void infect(char[][] grid, int i, int j) {
        // 边界问题以及已经是海水的情况返回
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length || grid[i][j] == '0') return;
        grid[i][j] = '0';
        infect(grid, i + 1, j);
        infect(grid, i, j + 1);
        infect(grid, i - 1, j);
        infect(grid, i, j - 1);
    }


    /**
     * 1254. 统计封闭岛屿的数目
     * 二维矩阵 grid 由 0 （土地）和 1 （水）组成。岛是由最大的4个方向连通的 0 组成的群，封闭岛是一个 完全 由1包围（左、上、右、下）的岛。
     * <p>
     * 请返回 封闭岛屿 的数目。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/number-of-closed-islands
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param grid
     * @return
     */
    public int closedIsland(int[][] grid) {

        // 把第一列和最后一列的岛屿淹掉
        for (int i = 0; i < grid.length; i++) {
            infect(grid, i, 0);
            infect(grid, i, grid[0].length - 1);
        }

        // 把第一行和最后一行的岛屿淹掉
        for (int i = 0; i < grid[0].length; i++) {
            infect(grid, 0, i);
            infect(grid, grid.length - 1, i);
        }

        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 0) {
                    infect(grid, i, j);
                    res++;
                }
            }
        }
        return res;
    }

    public void infect(int[][] grid, int i, int j) {
        // 边界问题以及已经是海水的情况返回
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length || grid[i][j] == 1) return;
        grid[i][j] = 1;
        infect(grid, i + 1, j);
        infect(grid, i, j + 1);
        infect(grid, i - 1, j);
        infect(grid, i, j - 1);
    }


    /**
     * 694 不同的岛屿数量
     * 0 表示海水， 1 表示陆地
     * 计算不同（distinct）岛屿数量
     * @param grid
     * @return
     */
    public int numDistinctIslands(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        // 记录所有岛屿的序列化结果
        HashSet<String> islands = new HashSet<>();
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 1) {
                    // 淹掉这个岛屿，同时存储岛屿的序列化结果
                    StringBuilder sb = new StringBuilder();
                    // 初始化的方向可以随便写，不影响正确性
                    dfsForNumDistinctIslands(grid, i, j, sb, 666);
                    islands.add(sb.toString());
                }
            }
        }
        // 不相同的岛屿数量
        return islands.size();
    }

    public void dfsForNumDistinctIslands(int[][] grid, int i, int j, StringBuilder sb, int dir) {
        int m = grid.length, n = grid[0].length;
        if(i < 0 || j < 0 || i >= m || j >= n || grid[i][j] == 0) {
            return;
        }

        // 前序遍历位置， 进入（i, j)
        grid[i][j] = 0;
        sb.append(dir).append(",");
        dfsForNumDistinctIslands(grid, i - 1, j, sb, 1); // 上
        dfsForNumDistinctIslands(grid, i + 1, j, sb, 2); // 下
        dfsForNumDistinctIslands(grid, i, j - 1, sb, 3); // 左
        dfsForNumDistinctIslands(grid, i, j + 1, sb, 4); // 右
        // 后序遍历位置， 离开（i, j)
        sb.append(-dir).append(",");
    }

}
