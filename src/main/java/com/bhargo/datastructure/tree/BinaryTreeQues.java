package com.bhargo.datastructure.tree;

import com.bhargo.datastructure.lists.LLQuestions.ListQues;

import java.util.*;

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

        //for reverse level traversal, reverse the loop
        for (int i =1; i<=height; i++) {
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

    public static void printDiagnolTree(BinaryTree<Integer> tree) {
        int height = treeHeight(tree);

        int diagonalDistance = 0;
        Map<Integer, List<BinaryTree.Node>> map = new LinkedHashMap<>();
        createMap(tree.getRoot(), diagonalDistance, map);
        map.entrySet().forEach(entry -> System.out.println(entry.getValue()));
    }

    private static void createMap(BinaryTree.Node root, int diagonalDistance, Map<Integer, List<BinaryTree.Node>> map) {
        if (root == null) {
            return;
        }
        if (map.containsKey(diagonalDistance)) {
            map.get(diagonalDistance).add(root);
        } else {
            List<BinaryTree.Node> list = new ArrayList<>();
            list.add(root);
            map.put(diagonalDistance, list);
        }
         createMap(root.getLeft(), diagonalDistance+1, map);
         createMap(root.getRight(), diagonalDistance, map);
    }
}
