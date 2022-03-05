package org.example.demo.leetcode.array;

/**
 * 差分数组工具类
 */
public class Difference {

    // 差分数组
    private final int[] diff;

    /**
     * 输入一个初始数组，区间操作将在这个数组上进行
     * @param nums
     */
    public Difference(int[] nums) {
        diff = new int[nums.length];
        // 根据初始数组构造查分数组
        diff[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            diff[i] = nums[i] - nums[i - 1];
        }
    }

    /**
     * 给闭区间[i,j] 增加 val（可以是负数）
     * @param i 左边界
     * @param j 右边界
     * @param val 值
     */
    public void increment(int i, int j, int val) {
        diff[i] += val;
        if(j + 1 < diff.length) {
            diff[j + 1] -= val;
        }
    }

    /**
     *
     * @return 结果数组
     */
    public int[] result() {
        int[] res = new int[diff.length];
        // 根据差分数组构造结果数组
        res[0] = diff[0];

        for (int i = 1; i < diff.length; i++) {
            res[i] = res[i - 1] + diff[i];
        }

        return res;
    }
}

