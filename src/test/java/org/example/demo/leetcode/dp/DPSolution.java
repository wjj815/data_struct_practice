package org.example.demo.leetcode.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * 下降路径
     * 可以从第一行中的任何元素开始，并从每一行中选择一个元素。在下一行选择的元素和当前行所选元素最多相隔一列（即位于正下方或者沿对角线向左或者向右的第一个元素）。具体来说，位置
     * (row, col) 的下一个元素应当是 (row + 1, col - 1)、(row + 1, col) 或者 (row + 1, col + 1)
     * 。
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
                                dp(matrix, i - 1, j + 1)));
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

    /**
     * 514. 自由之路
     * 电子游戏“辐射4”中，任务 “通向自由” 要求玩家到达名为 “Freedom Trail Ring” 的金属表盘，并使用表盘拼写特定关键词才能开门。
     * 
     * 给定一个字符串 ring ，表示刻在外环上的编码；给定另一个字符串 key ，表示需要拼写的关键词。您需要算出能够拼写关键词中所有字符的最少步数。
     * 
     * 最初，ring 的第一个字符与 12:00 方向对齐。您需要顺时针或逆时针旋转 ring 以使 key 的一个字符在 12:00
     * 方向对齐，然后按下中心按钮，以此逐个拼写完 key 中的所有字符。
     * 
     * 旋转 ring 拼出 key 字符 key[i] 的阶段中：
     * 
     * 您可以将 ring 顺时针或逆时针旋转 一个位置 ，计为1步。旋转的最终目的是将字符串 ring 的一个字符与 12:00
     * 方向对齐，并且这个字符必须等于字符 key[i] 。
     * 如果字符 key[i] 已经对齐到12:00方向，您需要按下中心按钮进行拼写，这也将算作 1
     * 步。按完之后，您可以开始拼写 key 的下一个字符（下一阶段）, 直至完成所有拼写。
     * 
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/freedom-trail
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * 
     * @param ring
     * @param key
     * @return
     */
    public int findRotateSteps(String ring, String key) {

        Map<Character, List<Integer>> map = new HashMap<>();

        class DP {
            // 字符 -> 索引列表

            // 备忘录
            final int[][] memo;

            public DP(int[][] memo) {
                this.memo = memo;
            }

            // 计算圆盘指针在 ring[i],输入 key[j...] 的最少操作数
            public int dp(String ring, int i, String key, int j) {
                // base case
                if (j == key.length()) {
                    return 0;
                }
                // 查找备忘录，避免重叠子问题
                if (memo[i][j] != 0) {
                    return memo[i][j];
                }

                int n = ring.length();
                // 做选择
                int res = Integer.MAX_VALUE;
                // ring 上可能有多个字符 key[j]
                for (int k : map.get(key.charAt(j))) {
                    // 拨动指针的次数
                    int delta = Math.abs(k - i);
                    // 选择顺时针还是逆时针
                    delta = Math.min(delta, n - delta);
                    // 将指针拨到 ring[k], 继续输入 key[j + 1..]
                    int subProblem = dp(ring, k, key, j + 1);
                    // 选择【整体】操作次数最少的
                    res = Math.min(res, 1 + delta + subProblem);
                    // PS: 加一是因为拨动按钮也是一次操作
                }
                // 将结果存入备忘录
                return memo[i][j] = res;
            }
        }

        int[][] memo = new int[ring.length()][key.length()];

        DP dp = new DP(memo);
        for (int i = 0; i < ring.length(); i++) {
            map.putIfAbsent(ring.charAt(i), new ArrayList<>());
            map.get(ring.charAt(i)).add(i);
        }
        // 圆盘指针最初指向 12 点钟方向
        // 从第一个字符开始输入 key
        return dp.dp(ring, 0, key, 0);
    }

    public int findRotateStepsByDP(String ring, String key) {

        Map<Character, List<Integer>> map = new HashMap<>();

        class DP {
            // 字符 -> 索引列表

            // 备忘录
            final int[][] memo;

            public DP(int[][] memo) {
                this.memo = memo;
            }

            // 计算圆盘指针在 ring[i],输入 key[j...] 的最少操作数
            public int dp(String ring, int i, String key, int j) {
                // base case
                if (j == key.length()) {
                    return 0;
                }
                // 查找备忘录，避免重叠子问题
                if (memo[i][j] != 0) {
                    return memo[i][j];
                }

                int n = ring.length();
                // 做选择
                int res = Integer.MAX_VALUE;
                // ring 上可能有多个字符 key[j]
                for (int k : map.get(key.charAt(j))) {
                    // 拨动指针的次数
                    int delta = Math.abs(k - i);
                    // 选择顺时针还是逆时针
                    delta = Math.min(delta, n - delta);
                    // 将指针拨到 ring[k], 继续输入 key[j + 1..]
                    int subProblem = dp(ring, k, key, j + 1);
                    // 选择【整体】操作次数最少的
                    res = Math.min(res, 1 + delta + subProblem);
                    // PS: 加一是因为拨动按钮也是一次操作
                }
                // 将结果存入备忘录
                return memo[i][j] = res;
            }
        }

        int[][] memo = new int[ring.length()][key.length()];

        DP dp = new DP(memo);
        for (int i = 0; i < ring.length(); i++) {
            map.putIfAbsent(ring.charAt(i), new ArrayList<>());
            map.get(ring.charAt(i)).add(i);
        }
        // 圆盘指针最初指向 12 点钟方向
        // 从第一个字符开始输入 key
        return dp.dp(ring, 0, key, 0);
    }

    /**
     * 300. 最长递增子序列
     * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
     * 
     * 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7]
     * 的子序列。
     * 
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/longest-increasing-subsequence
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * 
     * 输入：nums = [10,9,2,5,3,7,101,18]
     * 输出：4
     * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
     * 
     * 
     * 
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        int[] top = new int[nums.length];
        // 牌堆数初始化为 0
        int piles = 0;
        for (int i = 0; i < nums.length; i++) {
            // 要处理的扑克牌
            int poker = nums[i];
            // 搜索左侧边界的二分查找
            int left = 0, right = piles - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (top[mid] > poker) {
                    right = mid - 1;
                } else if (top[mid] < poker) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            // 没有找到合适的牌堆，新建一堆
            if (left >= piles) {
                piles++;
            }
            // 吧这张牌放在牌堆顶
            top[left] = poker;
            /**
             * 放牌堆的过程
             * [10, 0, 0, 0, 0, 0, 0, 0]
             * [9, 0, 0, 0, 0, 0, 0, 0]
             * [2, 0, 0, 0, 0, 0, 0, 0]
             * [2, 5, 0, 0, 0, 0, 0, 0]
             * [2, 3, 0, 0, 0, 0, 0, 0]
             * [2, 3, 7, 0, 0, 0, 0, 0]
             * [2, 3, 7, 101, 0, 0, 0, 0]
             * [2, 3, 7, 18, 0, 0, 0, 0]
             * 
             */
            // System.out.println(Arrays.toString(top));
        }

        return piles;
    }

    /**
     * dp 版
     * 
     * @param nums
     * @return
     */
    public int lengthOfLISByDP(int[] nums) {

        if (nums == null || nums.length == 0) {
            return 0;
        }
        // dp[i]表示以 i 下标结束的LIS
        int[] dp = new int[nums.length];
        // base case
        Arrays.fill(dp, 1);
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            res = Math.max(res, dp[i]);
        }

        return res;
    }

}
