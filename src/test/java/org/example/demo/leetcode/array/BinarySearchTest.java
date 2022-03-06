package org.example.demo.leetcode.array;

import junit.framework.TestCase;

public class BinarySearchTest extends TestCase {

    BinarySearch bs;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        bs = new BinarySearch();
    }

    public void testBinarySearch() {
        int[] arr = {1, 2 ,3, 4, 5, 11, 22};
        int target = 5;
        int i = bs.binarySearch(arr, target);
        System.out.println(i);
    }

    public void testLeftBoundOfTarget() {

        int[] arr = {1, 2 ,3, 4, 5, 8, 11,11, 22};
        int target = -1;
        int i = bs.leftBoundOfTarget(arr, target);
        System.out.println(i);
    }

    public void testRightBoundOfTarget() {
        int[] arr = {1, 2 ,3, 4, 5, 8, 11,11, 22};
        int target = 11;
        int i = bs.rightBoundOfTarget(arr, target);
        System.out.println(i);
    }

    public void testCeiling() {

        int[] arr = {1, 2 ,3, 4, 5, 8, 11,11, 22};
        int target = 1;
        int i = bs.ceiling(arr, target);
        System.out.println(i);
    }

    public void testFloor() {

        int[] arr = {1, 2 ,3, 4, 5, 8, 11,11, 22};
        int target = 8;
        int i = bs.floor(arr, target);
        System.out.println(i);
    }
}