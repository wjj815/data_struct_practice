package org.example.demo.array;

import java.util.*;

public class NSumSolution {


    /**
     * 注意：调用这个函数之前一定要给 nums 排序
     * 通用解决在数组中找 n 个数之和为 target 的数
     *
     * @param nums   数列
     * @param n      n
     * @param start  开始下标
     * @param target 目标数
     * @return n 个数
     */
    public List<List<Integer>> nSum(int[] nums, int n, int start, int target) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        // 至少是 2Sum, 且数组大小不应该小于 n
        if (n < 2 || len < n) return res;
        // 2Sum 是 base case
        if (n == 2) {
            // 双指针操作
            int lo = start, hi = len - 1;
            while (lo < hi) {
                int sum = nums[lo] + nums[hi];
                if (sum > target) {
                    hi--;
                } else if (sum < target) {
                    lo++;
                } else {
                    // 添加结果
                    res.add(new LinkedList<>(Arrays.asList(nums[lo], nums[hi])));
                    // 去重
                    while (lo < hi && nums[lo++] == nums[lo]);
                    while (lo < hi && nums[hi--] == nums[hi]);
                }
            }
        } else {
            // n > 2 时，递归计算(n - 1)Sum 的结果
            for(int i = start; i < len; i++) {
                List<List<Integer>> subResList = nSum(nums, n - 1, i + 1, target - nums[i]);
                for (List<Integer> subRes : subResList) {
                    // (n - 1)Sum加上 nums[i]就是 nSum
                    subRes.add(0,nums[i]);
                    res.add(subRes);
                }
                while(i < len - 1 && nums[i] == nums[i + 1]){
                    i++;
                }
            }
        }
        return res;
    }


    /**
     *
     * https://leetcode-cn.com/problems/two-sum/
     * 两数之和
     * @param nums 数列
     * @param target 目标数
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            int j = target - nums[i];
            if(map.containsKey(j) && map.get(j) != i) {
                return new int[]{i, map.get(j)};
            }
        }
        return new int[0];
    }


    /**
     * https://leetcode-cn.com/problems/3sum/
     * 三数之和为 0
     * @param nums 数列
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        return nSum(nums, 3, 0, 0);
    }

    /**
     * https://leetcode-cn.com/problems/4sum/
     * 四数之和为 target
     * @param nums 数列
     * @param target 目标数
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        return nSum(nums, 4, 0, target);
    }
}
