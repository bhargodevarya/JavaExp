package com.bhargo.datastructure.tree;

public class BinaryTreeQues {

    public static BinaryTree<Integer> createDummyTree() {
        BinaryTree<Integer> tree = new BinaryTree<>();
        try {
            tree.daisyAdd(20).daisyAdd(10).daisyAdd(45).daisyAdd(12)
            .daisyAdd(30).daisyAdd(60);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tree;
    }

    public static void levelTraversal(BinaryTree<Integer> tree) {
        BinaryTree.Node root = tree.getRoot();
        System.out.println(root.getValue());
        recLevelTraversal(root);
    }

    private static void recLevelTraversal(BinaryTree.Node root) {
        if (root == null) {
            return;
        }
        if (root.getLeft() == null && root.getRight() == null) {
            return;
        }
        if (root.getLeft() != null) {
            System.out.println(root.getLeft().getValue());
        }
        if (root.getRight() != null) {
            System.out.println(root.getRight().getValue());
        }
        recLevelTraversal(root.getLeft());
        recLevelTraversal(root.getRight());
    }


}
