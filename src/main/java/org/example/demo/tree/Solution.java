package org.example.demo.tree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public class Solution {


    /**
     * @param preorder 先序数列
     * @param preStart 先序 开始下标
     * @param preEnd   先序 结束下标
     * @param inStart  中序开始下标
     * @param inEnd    中序 结束下标
     * @param inMap    中序下标映射
     * @return
     */
    public TreeNode buildTree(int[] preorder, int preStart, int preEnd, int inStart, int inEnd, Map<Integer, Integer> inMap) {

        /**
         * 边界处理
         */
        if (preStart > preEnd || inStart > inEnd) return null;

        /**
         * 创建树节点
         */
        TreeNode root = new TreeNode(preorder[preStart]);
        // 获取该树节点在中序遍历中的位置
        int inRoot = inMap.get(root.val);
        // 根据中序序列获取左侧子树节点的数量
        int numsLeft = inRoot - inStart;
        // 创建树左子节点,先序序列的区间为[preStart + 1, preStart + numsLeft]
        root.left = buildTree(preorder, preStart + 1, preStart + numsLeft,
                inStart, inRoot - 1, inMap);
        // 创建数右子节点，先序序列的区间为[preStart + numsLeft + 1, preEnd]
        root.right = buildTree(preorder, preStart + numsLeft + 1, preEnd,
                inRoot + 1, inEnd, inMap);

        return root;
    }


    /**
     * 递归方式
     * @param root 根节点
     * @return 这棵二叉树的最大深度
     */
    public int maxDepthByRecur(TreeNode root) {
        if(root == null) return 0;
        // 递归计算左右子树的最大深度
        int leftMax = maxDepthByRecur(root.left);
        int rightMax = maxDepthByRecur(root.right);
        // 整颗数的最大深度
        return Math.max(leftMax, rightMax) + 1;
    }


    /**
     *  层序遍历的方式
     * @param root 根节点
     * @return 最大高度
     */
    public int maxDepthByLevelTraverse(TreeNode root) {
        if(root == null) return 0;
        int depth = 0;
        Deque<TreeNode> dq = new ArrayDeque<>();
        dq.offer(root);
        // 从上到下遍历二叉树的每一层
        while(!dq.isEmpty()) {
            int size = dq.size();
            // 从左到右遍历每一层的每个节点
            for (int i = 0; i < size; i++) {
                TreeNode node = dq.poll();
                if(node.left != null) {
                    dq.offer(node.left);
                }
                if(node.right != null) {
                    dq.offer(node.right);
                }
            }
            depth++;
        }
        return depth;
    }
}
