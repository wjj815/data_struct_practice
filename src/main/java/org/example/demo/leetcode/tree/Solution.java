package org.example.demo.leetcode.tree;

import org.example.demo.tree.TreeNode;

/**
 * 数的相关问题
 */
public class Solution {

    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };

    /**
     * 116. 填充每个节点的下一个右侧节点指针
     * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
     *
     * 初始状态下，所有 next 指针都被设置为 NULL。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param root
     * @return
     */
    public Node connect(Node root) {
        if(root != null) {
            connectTwoNode(root.left, root.right);
        }
        return root;
    }

    /**
     *  定义：输入两个节点，将它俩连接起来
     * @param node1
     * @param node2
     */
    public void connectTwoNode(Node node1, Node node2) {
        if(node1 == null || node2 == null) {
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
     *
     * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
     * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
     *
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param root
     */
    public void flatten(TreeNode root) {
        // base case
        if(root == null) {
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
        while(cur.right != null) {
            cur = cur.right;
        }
        cur.right = right;
    }

    }
}
