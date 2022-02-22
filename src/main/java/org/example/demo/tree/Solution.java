package org.example.demo.tree;

import java.util.Map;

public class Solution {


    /**
     * @param preorder 先序数列
     * @param preStart 先序 开始下标
     * @param preEnd   先序 结束下标
     * @param inorder  中序数列
     * @param inStart  中序开始下标
     * @param inEnd    中序 结束下标
     * @param inMap    中序下标映射
     * @return
     */
    public TreeNode buildTree(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd, Map<Integer, Integer> inMap) {

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
                inorder, inStart, inRoot - 1, inMap);
        // 创建数右子节点，先序序列的区间为[preStart + numsLeft + 1, preEnd]
        root.right = buildTree(preorder, preStart + numsLeft + 1, preEnd,
                inorder, inRoot + 1, inEnd, inMap);

        return root;
    }
}
