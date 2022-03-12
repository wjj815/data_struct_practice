package org.example.demo.leetcode.queue.monotonous;

import junit.framework.TestCase;

import java.util.Arrays;

public class SolutionTest extends TestCase {

    public void testMaxSlidingWindow() {
        int[] arr = {-7,-8,7,5,7,1,6,0};
        int n = 4;
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.maxSlidingWindow(arr, n)));
    }
}