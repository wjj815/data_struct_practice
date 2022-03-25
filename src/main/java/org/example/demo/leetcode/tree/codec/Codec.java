package org.example.demo.leetcode.tree.codec;

import org.example.demo.tree.TreeNode;

public abstract class Codec {

    /**
     * 把一颗二叉树序列化成字符串
     * @param root
     * @return
     */
    public abstract String serialize(TreeNode root);

    /**
     * 把字符串反序列化成二叉树
     * @param data
     * @return
     */
    public abstract TreeNode deserialize(String data);
}
