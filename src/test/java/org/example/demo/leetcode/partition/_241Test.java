package org.example.demo.leetcode.partition;

import junit.framework.TestCase;

import java.util.List;

public class _241Test extends TestCase {

    public void testDiffWaysToCompute() {
        _241 s = new _241();
        String expression = "2*3-4*5";
        List<Integer> integers = s.diffWaysToCompute(expression);
        System.out.println(integers);
    }
}