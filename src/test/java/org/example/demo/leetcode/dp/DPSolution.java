package org.example.demo.leetcode.dp;

public class DPSolution {


    /**
     * 518. 零钱兑换 II
     * 给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
     *
     * 请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。
     *
     * 假设每一种面额的硬币有无限个。 
     *
     * 题目数据保证结果符合 32 位带符号整数。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/coin-change-2
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param amount
     * @param coins
     * @return
     */
    public int change(int amount, int[] coins) {
        // base case
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for(int coin: coins) {
            for(int i = coin;i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }
}
