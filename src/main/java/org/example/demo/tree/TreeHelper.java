package org.example.demo.tree;

import java.util.Stack;

public class TreeHelper {

    public static void printTree(TreeNode root) {
        BTreePrinter.printTreeNodeNode(root);
    }

    /**
     * 从序列中生成树
     * 数据分隔符为","
     * 例: 1,2,3,4,#
     * @param treeStr
     * @return
     */
    public static TreeNode buildTree(String treeStr) {

        if(treeStr == null || "".equals(treeStr)) {
            return null;
        }

        String[] split = treeStr.split(",");

        if(split.length == 0) {
            return null;
        }

        if("#".equals(split[0])) {
            return null;
        }

        TreeNode node = new TreeNode(split[0]);

        TreeNode[] treeNodes = new TreeNode[split.length];
        treeNodes[0] = node;

        for(int i = 1; i < split.length; i++) {
            int parentIndex = (i - 1) >> 1;
            node = treeNodes[parentIndex];

            if(i == (parentIndex << 1) + 1) {
                node.left = !"#".equals(split[i]) ? new TreeNode(split[i]) : null;
                treeNodes[i] = node.left;
            }

            if(i == (parentIndex << 1) + 2) {
                node.right = !"#".equals(split[i]) ? new TreeNode(split[i]) : null;
                treeNodes[i] = node.right;
            }

        }
        return treeNodes[0];
    }
}
