package org.example.demo.leetcode.array;

import java.util.HashMap;

public class Solution {


    /**
     * 区间加法
     * @param length 初始数组长度
     * @param updates // 变化配置
     * @return // 改变后的数组
     */
    public int[] getModifiedArray(int length, int[][] updates) {
        // nums 初始化为 全 0
        int[] nums = new int[length];
        // 构造差分解法
        Difference df = new Difference(nums);

        for (int[] update : updates) {
            int i = update[0];
            int j = update[1];
            int val = update[2];
            df.increment(i, j, val);
        }

        return df.result();
    }

    /**
     * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回该数组中和为 k 的连续子数组的个数。
     * @param nums 整数数组
     * @param k 整数
     * @return 和为 k 的连续子数组的个数
     */
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        // 构造前缀和
        /// 原解法 O(n^2)---------------
//        int[] preSum = new int[n + 1];
//        preSum[0] = 0;
//        for (int i = 0; i < n; i++) {
//            preSum[i + 1] = preSum[i] + nums[i];
//        }
//
//        int res = 0;
//        // 穷举所有子数组
//        for (int i = 1; i <= n; i++) {
//            for (int j = 0; j < i; j++) {
//                // 子数组 nums[j .. i - 1]的元素和
//                if(preSum[i] - preSum[j] == k) {
//                    res++;
//                }
//            }
//        }

        // 优化解法O(n)----

        // map: 前缀和 -> 该前缀和出现的次数
        HashMap<Integer, Integer> preSum = new HashMap<>();
        // baseCase
        preSum.put(0, 1);

        int res = 0, sum0_i = 0;
        for (int i = 0; i < n; i++) {
            sum0_i += nums[i];
            // 这里我们想找的前缀和 num[0 .. j];
            int sum0_j = sum0_i - k;
            // 如果前面有这个前缀和，则直接更新答案
            if(preSum.containsKey(sum0_j)) {
                res += preSum.get(sum0_j);
            }
            // 把前缀和 nums[0 .. i] 加入并记录出现次数
            preSum.put(sum0_i, preSum.getOrDefault(sum0_i, 0) + 1);
        }
        return res;
    }
}
