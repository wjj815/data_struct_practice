package org.example.demo.leetcode.tree.codec;

import org.example.demo.tree.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class PreOrderCodec extends Codec{

    // 代表分割符
    String SEP = ",";
    // 代表 null 空指针字符
    String NULL = "#";
    // 用于拼接字符串


    @Override
    public String serialize(TreeNode root) {
        List<String> strings = new ArrayList<>();
        traverse(root, strings);
        return String.join(SEP, strings);
    }

    public void traverse(TreeNode root,List<String> strings) {
        if(root == null) {
            strings.add(NULL);
            return;
        }

        // 前序遍历
        strings.add(String.valueOf(root.val));

        traverse(root.left, strings);
        traverse(root.right, strings);
    }

    @Override
    public TreeNode deserialize(String data) {
        LinkedList<String> nodes = new LinkedList<>(Arrays.asList(data.split(SEP)));

        return deserialize(nodes);
    }

    public TreeNode deserialize(LinkedList<String> nodes) {
        if(nodes.isEmpty()) return null;

        // 前序遍历位置
        // 列表最左侧就是根节点
        String first = nodes.removeFirst();

        if(first.equals(NULL)) return null;

        TreeNode root = new TreeNode(Integer.parseInt(first));

        root.left = deserialize(nodes);
        root.right = deserialize(nodes);
        return root;
    }
}
