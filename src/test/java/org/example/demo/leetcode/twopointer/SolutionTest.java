package org.example.demo.leetcode.twopointer;

import junit.framework.TestCase;

public class SolutionTest extends TestCase {

    Solution solution;


    @Override
    protected void setUp() throws Exception {
        super.setUp();
        solution = new Solution();
    }

    public void testRemoveDuplicates() {
        int[] nums = {1, 1, 2}; // 输入数组
        int[] expectedNums = {1,2}; // 长度正确的期望答案

        int k = solution.removeDuplicates(nums, 1); // 调用

        assert k == expectedNums.length;
        for (int i = 0; i < k; i++) {
            assert nums[i] == expectedNums[i];
        }

    }
}