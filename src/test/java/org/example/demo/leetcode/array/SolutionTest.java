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
}