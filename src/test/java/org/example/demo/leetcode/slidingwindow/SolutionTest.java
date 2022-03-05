package org.example.demo.leetcode.slidingwindow;

import junit.framework.TestCase;

import java.util.List;

public class SolutionTest extends TestCase {

    Solution solution;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        solution = new Solution();
    }

    public void testMinWindow() {

        String s = "ADOBECODEBANC", t = "ABC";
        String res = solution.minWindow(s, t);
        System.out.println(res);
    }

    public void testCheckInclusion() {
        String s1 = "ab", s2 = "eidboaoo";
        boolean b = solution.checkInclusion(s1, s2);
        System.out.println(b);
    }

    public void testFindAnagrams() {
        String s = "cbaebabacd", p = "abc";
        List<Integer> anagrams = solution.findAnagrams(s, p);
        System.out.println(anagrams);

    }

    public void testLengthOfLongestSubstring() {
        String s = "pwwkew";
        int res = solution.lengthOfLongestSubstring(s);
        System.out.println(res);
    }
}