package org.example.demo.tree;

import java.util.ArrayDeque;
import java.util.Deque;

public class TreeHelper {

    public static void printTree(TreeNode root) {
        BTreePrinter.printTreeNodeNode(root);
    }

    /**
     * 从序列中生成树
     * 数据分隔符为","
     * 例: 1,2,3,4,#
     *
     * @param treeArrStr
     * @return
     */
    public static TreeNode buildTree(String treeArrStr) {
        String[] treeArr = treeArrStr.split(",");
        int len = treeArr.length;
        if (treeArr.length == 0) {
            return null;
        }
        Deque<TreeNode> dq = new ArrayDeque<>();
        TreeNode root = getTreeNode(treeArr[0]);

        if (root == null) {
            return null;
        }

        dq.offer(root);
        int i = 1;
        outer: while (!dq.isEmpty()) {
            int size = dq.size();
            for (int i1 = 0; i1 < size; i1++) {
                TreeNode node = dq.poll();

                if (node.left != null) {
                    dq.offer(node.left);
                } else {
                    node.left = getTreeNode(treeArr[i]);
                    if (node.left != null) {
                        dq.offer(node.left);
                    }
                    i++;
                    if(i == len) {
                        break outer;
                    }
                }

                if (node.right != null) {
                    dq.offer(node.right);
                } else {
                    node.right = getTreeNode(treeArr[i]);
                    if (node.right != null) {
                        dq.offer(node.right);
                    }
                    i++;
                    if(i == len) {
                        break outer;
                    }
                }
            }
        }
        return root;
    }

    public static TreeNode getTreeNode(String s) {
        if ("null".equals(s)) {
            return null;
        }
        return new TreeNode(s);
    }

}
