package org.example.demo.tree;

import java.util.TreeSet;

public class TestTree {

    public static void main(String[] args) {
        TreeNode node = TreeHelper.buildTree("6,2,8,0,4,7,9,null,null,3,5");
        TreeHelper.printTree(node);

        new TraverseTree().preOrderByMorris(node);
        System.out.println();
        new TraverseTree().preOrderUnRecur2(node);
        System.out.println();
        new TraverseTree().inOrder(node);
        System.out.println();
        new TraverseTree().inOrderByMorris(node);
        System.out.println();
        new TraverseTree().postOrderByMorris(node);
        System.out.println();
        new TraverseTree().postOrderUnRecur(node);
        System.out.println();
        TreeSet<Integer> set = new TreeSet<>();
        set.add(1);
        set.add(3);
        set.add(3);
        set.add(4);
        System.out.println(set.ceiling(6));

    }
}
