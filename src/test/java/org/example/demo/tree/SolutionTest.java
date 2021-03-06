package org.example.demo.tree;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class SolutionTest {

    @Test
    public void buildTree() {
        int[] preOrder = {6, 2, 0, 4, 3, 5, 8, 7, 9};
        int[] inOrder = {0, 2, 3, 4, 5, 6, 7, 8, 9};
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inOrder.length; i++) {
            inMap.put(inOrder[i], i);
        }


        TreeNode treeNode = new Solution().buildTree(preOrder, 0, preOrder.length - 1,
                0, inOrder.length - 1, inMap);

        /**
         *        6
         *       / \
         *      /   \
         *     /     \
         *    /       \
         *    2       8
         *   / \     / \
         *  /   \   /   \
         *  0   4   7   9
         *     / \
         *     3 5
         */
        TreeHelper.printTree(treeNode);
    }

    @Test
    public void testMaxDepth() {
        /**
         *        6
         *       / \
         *      /   \
         *     /     \
         *    /       \
         *    2       8
         *   / \     / \
         *  /   \   /   \
         *  0   4   7   9
         *     / \
         *     3 5
         */
        TreeNode node = TreeHelper.buildTree("6,2,8,0,4,7,9,null,null,3,5");
        TreeHelper.printTree(node);

        Solution solution = new Solution();

        // 高度为 4
        Assert.assertEquals(solution.maxDepthByLevelTraverse(node), solution.maxDepthByRecur(node));
    }

    @Test
    public void testMinDepthByLevelTraverse() {

        /**
         *        6
         *       / \
         *      /   \
         *     /     \
         *    /       \
         *    2       8
         *   / \     / \
         *  /   \   /   \
         *  0   4   7   9
         *     / \
         *     3 5
         */
        TreeNode node = TreeHelper.buildTree("6,2,8,0,4,7,9,null,null,3,5");
        TreeHelper.printTree(node);

        Solution solution = new Solution();
        int depth = solution.minDepthByLevelTraverse(node);
        System.out.println(depth);

    }
}