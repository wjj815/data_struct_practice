package org.example.demo.leetcode.tree;

import org.example.demo.tree.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数的相关问题
 */
public class Solution {

    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    ;

    /**
     * 116. 填充每个节点的下一个右侧节点指针
     * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
     * <p>
     * 初始状态下，所有 next 指针都被设置为 NULL。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param root
     * @return
     */
    public Node connect(Node root) {
        if (root != null) {
            connectTwoNode(root.left, root.right);
        }
        return root;
    }

    /**
     * 定义：输入两个节点，将它俩连接起来
     *
     * @param node1
     * @param node2
     */
    public void connectTwoNode(Node node1, Node node2) {
        if (node1 == null || node2 == null) {
            return;
        }
        /**** 前序遍历位置 ****/
        // 将传入的两个节点连接
        node1.next = node2;
        // 连接相同父节点的两个子节点
        connectTwoNode(node1.left, node1.right);
        connectTwoNode(node2.left, node2.right);
        // 连接跨越父节点的两个子节点
        connectTwoNode(node1.right, node2.left);

    }

    /**
     * 114. 二叉树展开为链表
     * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
     * <p>
     * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
     * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
     * <p>
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param root
     */
    public void flatten(TreeNode root) {
        // base case
        if (root == null) {
            return;
        }

        flatten(root.left);
        flatten(root.right);
        // 后序遍历逻辑
        // 临时存储右子树节点
        TreeNode right = root.right;
        // 将左子树赋给右子树
        root.right = root.left;
        // 将左子树置空
        root.left = null;
        TreeNode cur = root;
        // 将原右子树拼接到后面
        while (cur.right != null) {
            cur = cur.right;
        }
        cur.right = right;
    }


    /**
     * 654. 最大二叉树
     * 给定一个不重复的整数数组 nums 。 最大二叉树 可以用下面的算法从 nums 递归地构建:
     * <p>
     * 创建一个根节点，其值为 nums 中的最大值。
     * 递归地在最大值 左边 的 子数组前缀上 构建左子树。
     * 递归地在最大值 右边 的 子数组后缀上 构建右子树。
     * 返回 nums 构建的 最大二叉树 。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/maximum-binary-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param nums
     * @return
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }

    /**
     * 将 nums[l..h] 构造成符合条件的树， 返回根节点
     *
     * @param nums
     * @param l
     * @param r
     * @return
     */
    public TreeNode build(int[] nums, int l, int r) {
        // base case
        if (l > r) {
            return null;
        }

        // 找到数组中的最大值
        int maxIndex = l;

        for (int i = l; i <= r; i++) {
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }

        TreeNode node = new TreeNode(nums[maxIndex]);
        // 递归调用构建左右子树
        node.left = build(nums, l, maxIndex - 1);
        node.right = build(nums, maxIndex + 1, r);
        return node;
    }


    /**
     * 106. 从中序与后序遍历序列构造二叉树
     * 给定两个整数数组 inorder 和 postorder ，其中 inorder 是二叉树的中序遍历， postorder 是同一棵树的后序遍历，请你构造并返回这颗 二叉树 。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }
        return buildNode(postorder, 0, inorder.length - 1, 0, postorder.length - 1, inMap);
    }

    public TreeNode buildNode(int[] postorder, int inL, int inR, int postL, int postR, Map<Integer, Integer> map) {
        // base case
        if (inL > inR) {
            return null;
        }

        TreeNode node = new TreeNode(postorder[postR]);
        int inIndex = map.get(postorder[postR]);
        int leftSize = inIndex - inL;

        node.left = buildNode(postorder, inL, inIndex - 1, postL, postL + leftSize - 1, map);
        node.right = buildNode(postorder, inIndex + 1, inR, postL + leftSize, postR - 1, map);
        return node;
    }


    /**
     * 652. 寻找重复的子树
     * 给定一棵二叉树 root，返回所有重复的子树。
     *
     * 对于同一类的重复子树，你只需要返回其中任意一棵的根结点即可。
     *
     * 如果两棵树具有相同的结构和相同的结点值，则它们是重复的。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/find-duplicate-subtrees
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param root
     * @return
     */
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> res = new ArrayList<>();
        HashMap<String, Integer> memo = new HashMap<>();
        getStringByTraverse(root, memo, res);
        return res;
    }

    public String getStringByTraverse(TreeNode root, HashMap<String, Integer> memo, List<TreeNode> res) {
        if(root == null) {
            return "#";

        }

        String left = getStringByTraverse(root.left, memo, res);
        String right = getStringByTraverse(root.right, memo, res);
        String subTree = String.format("%s,%s,%s", left, right, root.val);

        int freq = memo.getOrDefault(subTree, 0);
        // 多次重复也只会被加入结果集一次
        if(freq == 1) {
            res.add(root);
        }
        // 给子树对应的出现次数加一
        memo.put(subTree, freq + 1);
        return subTree;

    }

    // 记录最大直径的长度
    int maxDiameter = 0;

    /**
     * 543. 二叉树的直径
     * 难度
     * 简单
     * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
     * @param root
     * @return
     */
    public int diameterOfBinaryTree(TreeNode root) {
        maxDepthForMaxDiameter(root);
        return maxDiameter;
    }

    int maxDepthForMaxDiameter(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int leftMax = maxDepthForMaxDiameter(root.left);
        int rightMax = maxDepthForMaxDiameter(root.right);
        // 后序位置顺便计算最大直径
        int myDiameter = leftMax + rightMax;
        maxDiameter = Math.max(maxDiameter, myDiameter);
        return 1 + Math.max(leftMax, rightMax);
    }


}
