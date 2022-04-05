package org.example.demo.leetcode.array;

/**
 * 线段树
 */
public class NumArray {

    // 使用数组存储线段树
    private final int[] segmentTree;

    private final int n;

    public NumArray(int[] nums) {
        n = nums.length;
        segmentTree = new int[nums.length * 4];
        build(0, 0, n - 1, nums);

    }

    public void update(int index, int val) {
        change(index, val, 0, 0, n - 1);
    }

    public int sumRange(int left, int right) {
        return range(left, right, 0, 0, n - 1);
    }

    private int range(int left, int right, int node, int s, int e) {
        if(left == s && right == e) {
            return segmentTree[node];
        }
        int m = s + (e - s) / 2;
        if(right <= m) {
            return range(left, right, node * 2 + 1, s, m);
        } else if(left > m) {
            return range(left, right, node * 2 + 2, m + 1, e);
        }
        return range(left, m, node * 2 + 1, s, m) + range(m + 1, right, node * 2 + 2, m + 1, e);
    }

    private void change(int index, int val, int node, int s, int e) {
        if(s == e) {
            segmentTree[node] = val;
            return;
        }
        int m = s + (e - s) / 2;
        if(index <= m) {
            change(index, val, node * 2 + 1, s, m);
        } else {
            change(index, val, node * 2 + 2, m + 1, e);
        }
        segmentTree[node] = segmentTree[node * 2 + 1] + segmentTree[node * 2 + 2];
    }

    private void build(int node, int s, int e, int[] nums) {
        if(s == e) {
            segmentTree[node] = nums[s];
            return;
        }
        int m = s + (e - s) / 2;
        build(node * 2 + 1, s, m, nums);
        build(node * 2 + 2, m + 1, e, nums);
        segmentTree[node] = segmentTree[node * 2 + 1] + segmentTree[node * 2 + 2];
    }

}
