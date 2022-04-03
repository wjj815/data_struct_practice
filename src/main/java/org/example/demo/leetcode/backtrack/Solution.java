package org.example.demo.leetcode.backtrack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Solution {


    /**
     *给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        // 记录路径
        LinkedList<Integer> track = new LinkedList<>();
        // 记录已经访问的数
        boolean[] onPath = new boolean[nums.length];
        backTrack(nums, onPath,  track, res);
        return res;
    }

    /**
     * 路径： 记录在 track 中
     * 选择列表： num 中不存在于 track 的那些元素
     * 结束条件： nums中的元素全都在 track 中出现
     * @param nums
     * @param onPath
     * @param track
     * @param res
     */
    private void backTrack(int[] nums, boolean[] onPath, LinkedList<Integer> track, List<List<Integer>> res) {
        // 触发条件
        if(track.size() == nums.length) {
            res.add(new ArrayList<>(track));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // 如果 track 中包含了当前数，则跳过
           if(onPath[i]) {
               continue;
           }
           // 做选择
           onPath[i] = true;
           track.add(nums[i]);
           // 进入下一层
           backTrack(nums, onPath, track, res);
           // 撤销选择
           track.removeLast();
           onPath[i] = false;
        }
    }

    /**
     *
     * 698. 划分为k个相等的子集
     * 给定一个整数数组  nums 和一个正整数 k，找出是否有可能把这个数组分成 k 个非空子集，其总和都相等。
     *
     * 示例 1：
     *
     * 输入： nums = [4, 3, 2, 3, 5, 2, 1], k = 4
     * 输出： True
     * 说明： 有可能将其分成 4 个子集（5），（1,4），（2,3），（2,3）等于总和。
     *
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/partition-to-k-equal-sum-subsets
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param nums
     * @param k
     * @return
     */
    public boolean canPartitionKSubsets(int[] nums, int k) {
        // 排除一些基本情况
        if(k > nums.length) return false;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if(sum % k != 0) {
            return false;
        }

        // 备忘录， 存储 used 数组的状态
        HashMap<Integer, Boolean> memo = new HashMap<>();
        int target = sum / k;
        // k 号桶初始什么都没装， 从 nums[0] 开始做选择
        return backTrack(k, 0, nums, 0, 0, target, memo);
    }

    private boolean backTrack(int k, int bucket, int[] nums, int start, int used, int target,
                              HashMap<Integer, Boolean> memo) {
        // base case
        if(k == 0) {
            // 所有桶都被装满了， 而且 nums 一定全部用完了
            // 因为 target == sum / k
            return true;
        }

        if(bucket == target) {
            // 装满了当前桶， 递归穷举下一个桶的选择
            // 让下一个桶从 nums[0]开始选数字
            boolean res = backTrack(k - 1, 0, nums, 0, used, target, memo);
            // 缓存结果
            memo.put(used, res);
            return res;
        }

        if(memo.containsKey(used)) {
            // 避免冗余计算
            return memo.get(used);
        }

        // 从 start 开始向后探查有效的 nums[i] 装入当前桶
        for(int i = start; i < nums.length; i++) {
            // 剪枝
            if(((used >> i) & 1) == 1) {
                // nums[i] 已经被装入别的桶中
                continue;
            }

            if(nums[i] + bucket > target) {
                // 当前桶装不下 nums[i]
                continue;
            }

            // 做选择， 将 nums[i] 装入当前桶中
            used |= 1 << i; // 将第 i 位置为 1
            bucket += nums[i];
            // 递归穷举下一个数字是否装入当前桶
            if(backTrack(k, bucket, nums, i + 1, used, target, memo)) {
                return true;
            }
            // 撤销选择
            used ^= 1 << i; // 使用异或运算将第 i 位恢复 0
            bucket -= nums[i];
        }
        // 穷举了所有数字，都无法装满当前桶
        return false;
    }

}
