package com.bhargo.datastructure.tree;

public class BinaryTreeQues {

    public static BinaryTree<Integer> createDummyTree(int ... values) {
        BinaryTree<Integer> tree = new BinaryTree<>();
        for (int value : values) {
            try {
                tree.add(value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return tree;
    }

    public static int treeHeight(BinaryTree<Integer> tree) {
        int result = calHeight(tree.getRoot(), 1);
        return result;
    }

    private static int calHeight(BinaryTree.Node node, int i) {
        boolean leftTraversed = false;
        if (node.getLeft() == null && node.getRight() == null) {
            return i;
        }
        int leftHeight = i, rightHeight = i;
        if (node.getLeft() != null) {
             leftHeight = calHeight(node.getLeft(), ++i);
            leftTraversed = true;
        }
        if (node.getRight() != null) {
            rightHeight = calHeight(node.getRight(), leftTraversed ? i : ++i);
        }
        return Math.max(leftHeight, rightHeight);
    }

    public static void levelTraversal(BinaryTree<Integer> tree) {
        BinaryTree.Node root = tree.getRoot();
        int height = treeHeight(tree);

        for (int i =height; i>=1; i--) {
            printNode(tree.getRoot(), i);
        }
    }

    private static void printNode(BinaryTree.Node root, int height) {
        if (root == null)
            return;
        if (height == 1)
            System.out.print(root.getValue() + " ");
        else if (height > 1)
        {
            printNode(root.getLeft(), height-1);
            printNode(root.getRight(), height-1);
        }
    }
}
