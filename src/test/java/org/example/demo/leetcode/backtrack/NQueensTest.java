package org.example.demo.leetcode.backtrack;

import junit.framework.TestCase;

import java.util.List;

public class NQueensTest extends TestCase {

    public void testSolveNQueens() {
        NQueens nQueens = new NQueens();
        List<List<String>> lists = nQueens.solveNQueens(4);
        System.out.println(lists);
    }
}