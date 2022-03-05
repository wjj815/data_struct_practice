package org.example.demo.leetcode.array;

import junit.framework.TestCase;

import java.util.Arrays;

public class SolutionTest extends TestCase {

    public void testGetModifiedArray() {
        int length = 5;
        int[][] updates = {
                {1, 3, 2},
                {2, 4, 3},
                {0, 2, -2}
        };

        Solution solution = new Solution();
        int[] modifiedArray = solution.getModifiedArray(length, updates);
        System.out.println(Arrays.toString(modifiedArray));
    }


    public void testSubarraySum() {

        int[] nums = {3, 5, 2, -2, 4, 1};
        int k = 5;

        Solution solution = new Solution();
        int count = solution.subarraySum(nums, k);
        // 4
        // [1, 1]
        // [1, 3]
        // [4, 5]
        // [2, 5]
        System.out.println(count);
    }
}