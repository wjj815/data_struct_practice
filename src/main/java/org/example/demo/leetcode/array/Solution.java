package org.example.demo.leetcode.array;

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
}
