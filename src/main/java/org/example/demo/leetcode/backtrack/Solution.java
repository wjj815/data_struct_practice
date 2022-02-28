package org.example.demo.leetcode.backtrack;

import java.util.ArrayList;
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

}
