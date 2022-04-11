package org.example.demo.leetcode.dp;

import java.util.Arrays;

public class DPSolution {


    /**
     * 518. 零钱兑换 II
     * 给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
     * <p>
     * 请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。
     * <p>
     * 假设每一种面额的硬币有无限个。 
     * <p>
     * 题目数据保证结果符合 32 位带符号整数。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/coin-change-2
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param amount
     * @param coins
     * @return
     */
    public int change(int amount, int[] coins) {
        // base case
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }


    /**
     * 931. 下降路径最小和
     * 难度
     * 中等
     * <p>
     * 给你一个 n x n 的 方形 整数数组 matrix ，请你找出并返回通过 matrix 的下降路径 的 最小和 。
     * <p>
     * 下降路径 可以从第一行中的任何元素开始，并从每一行中选择一个元素。在下一行选择的元素和当前行所选元素最多相隔一列（即位于正下方或者沿对角线向左或者向右的第一个元素）。具体来说，位置 (row, col) 的下一个元素应当是 (row + 1, col - 1)、(row + 1, col) 或者 (row + 1, col + 1) 。
     * <p>
     * 输入：matrix = [[2,1,3],[6,5,4],[7,8,9]]
     * 输出：13
     * 解释：如图所示，为和最小的两条下降路径
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/minimum-falling-path-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param matrix
     * @return
     */
    public int minFallingPathSum(int[][] matrix) {
        class DP {
            final int[][] memo;

            public DP(int[][] memo) {
                this.memo = memo;
                for (int i = 0; i < memo.length; i++) {
                    Arrays.fill(memo[i], 99999);
                }
            }

            public int dp(int[][] matrix, int i, int j) {
                if (j < 0 || j == matrix[i].length) {
                    return 666666;
                }
                // base case
                if (i == 0) {
                    return matrix[i][j];
                }

                if (memo[i][j] != 99999) {
                    return memo[i][j];
                }

                return memo[i][j] = matrix[i][j] + Math.min(
                        dp(matrix, i - 1, j - 1),
                        Math.min(
                                dp(matrix, i - 1, j),
                                dp(matrix, i - 1, j + 1))
                );
            }
        }
        int m = matrix.length;
        int n = matrix[0].length;
        DP dp = new DP(new int[m][n]);

        int res = 999999;

        for (int i = 0; i < n; i++) {
            res = Math.min(res, dp.dp(matrix, m - 1, i));
        }
        return res;
    }

    /**
     * 动态规划版，竟然比备忘录版的递归慢，离谱
     *
     * @param matrix
     * @return
     */
    public int minFallingPathSum2(int[][] matrix) {
        int n = matrix.length;
        int[][] dp = new int[n][n + 2];
        int INFINITY = 666666;
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], INFINITY);
        }

        int res = INFINITY;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (i == 0) {
                    // 第一行的情况
                    dp[i][j] = matrix[i][j - 1];
                } else {

                    dp[i][j] = matrix[i][j - 1] + Math.min(dp[i - 1][j + 1],
                            Math.min(dp[i - 1][j - 1], dp[i - 1][j]));
                }
                // 到达最后一行统计结果
                if (i == n - 1) {
                    res = Math.min(res, dp[i][j]);
                }
            }
        }

        return res;
    }
}
