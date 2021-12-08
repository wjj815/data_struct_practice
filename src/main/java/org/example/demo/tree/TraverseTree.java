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
        System.out.println("Morris 中序遍历 ：");
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

    public void postOrder(TreeNode node) {
        if(node == null) {
            return;
        }

        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.data + " ");
    }

    public void postOrderUnRecur(TreeNode node) {
        System.out.println("非递归后序遍历 ： ");
        if(node == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> res = new Stack<>();
        stack.push(node);
        while(!stack.isEmpty()) {
            node = stack.pop();
            res.push(node);
            if(node.left != null) {
                stack.push(node.left);
            }

            if(node.right != null) {
                stack.push(node.right);
            }
        }

        while(!res.isEmpty()) {
            System.out.print(res.pop().data + " ");
        }
    }

    public void postOrderByMorris(TreeNode node) {
        System.out.println("Morris 后序遍历 ：");
        TreeNode head = node;

        while(node != null) {
            TreeNode morrisRight = node.left;
            if(morrisRight != null) {
                while(morrisRight.right != null && morrisRight.right != node) {
                    morrisRight = morrisRight.right;
                }

                if(morrisRight.right == null) {
                    morrisRight.right = node;
                    node = node.left;
                    continue;
                }else {
                    morrisRight.right = null;
                    printEdge(node.left);
                }
            }

            node = node.right;
        }

        printEdge(head);
    }

    private void printEdge(TreeNode node) {
        TreeNode temp = node;

        TreeNode treeNode = reverseList(temp);

        temp = treeNode;

        while(treeNode != null) {
            System.out.print(treeNode.data + " ");
            treeNode = treeNode.right;
        }

        reverseList(temp);
    }

    public TreeNode reverseList(TreeNode node) {

        if(node == null || node.right == null) {
            return node;
        }

        TreeNode newHead = reverseList(node.right);
        node.right.right =  node;
        node.right = null;

        return newHead;
    }
}
