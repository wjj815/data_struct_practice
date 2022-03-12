package org.example.demo.leetcode.queue.monotonous;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public int[] maxSlidingWindow(int[] nums, int k) {
        MonotonousQueue window = new MonotonousQueue();
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i < nums.length;i++) {
            //先填满窗口的前 k - 1
            if(i < k - 1) {
                window.push(nums[i]);
            } else {
                // 窗口向前滑动，加入新数字
                window.push(nums[i]);
                // 记录当前窗口的最大值
                res.add(window.max());
                // 移出旧数字
                window.pop(nums[i - k + 1]);

            }
        }
        // 需要转成 int[] 数组再返回
        return res.stream().mapToInt(Integer::intValue).toArray();
    }
}
