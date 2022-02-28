package org.example.demo.leetcode.backtrack;

import junit.framework.TestCase;

import java.util.List;

public class SolutionTest extends TestCase {

    public void testPermute() {

        Solution solution = new Solution();
        int[] nums = {1, 2, 3};
        List<List<Integer>> permute = solution.permute(nums);
        System.out.println(permute);
    }
}