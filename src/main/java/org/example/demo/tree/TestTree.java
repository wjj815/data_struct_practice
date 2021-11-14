package org.example.demo.tree;

public class TestTree {

    public static void main(String[] args) {
        TreeNode node = TreeHelper.buildTree("1,2,3,4,5,#,#,#,#,#,#");
        TreeHelper.printTree(node);

        new TraverseTree().preOrderByMorris(node);
        System.out.println();
        new TraverseTree().inOrder(node);
        System.out.println();
        new TraverseTree().inOrderByMorris(node);
    }
}
