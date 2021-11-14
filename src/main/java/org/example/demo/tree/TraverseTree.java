package org.example.demo.tree;

import java.util.Stack;

/**
 * 二叉树遍历
 *
 * @author wangjunjie
 */
public class TraverseTree {


    public void preOrder(TreeNode node) {

        if (node == null) {
            return;
        }
        System.out.print(node.data + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    public void preOrderUnRecur(TreeNode node) {
        if (node == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();

        stack.push(node);

        while (!stack.isEmpty()) {
            node = stack.pop();
            System.out.print(node.data + " ");

            if (node.right != null) {
                stack.push(node.right);
            }

            if (node.left != null) {
                stack.push(node.left);
            }

        }
    }

    public void preOrderByMorris(TreeNode node) {

        System.out.println("Morris 先序遍历 ：");
        TreeNode mostRight;
        while (node != null) {
            mostRight = node.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != node) {
                    mostRight = mostRight.right;
                }

                if (mostRight.right == null) {
                    System.out.print(node.data + " ");
                    mostRight.right = node;
                    node = node.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            } else {
                System.out.print(node.data + " ");
            }
            node = node.right;
        }
    }

    public void inOrder(TreeNode node) {

        if (node == null) {
            return;
        }

        inOrder(node.left);
        System.out.print(node.data + " ");
        inOrder(node.right);
    }

    public void inOrderUnRecur(TreeNode node) {
        if (node == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();

        while (node != null || !stack.isEmpty()) {

            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                System.out.println(node.data + " ");
                node = node.right;
            }
        }
    }

    public void inOrderByMorris(TreeNode node) {
        if (node == null) {
            return;
        }
        TreeNode mostRight;
        while (node != null) {
            mostRight = node.left;
            if (mostRight != null) {

                while (mostRight.right != null && mostRight.right != node) {
                    mostRight = mostRight.right;
                }

                if (mostRight.right == null) {
                    mostRight.right = node;
                    node = node.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            System.out.print(node.data + " ");
            node = node.right;
        }
    }

}
