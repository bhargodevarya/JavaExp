package com.bhargo.datastructure.tree;

import java.util.Comparator;
import java.util.Objects;

/**
 * Created by barya on 11/26/2016.
 */
public class BinaryTree<T> {

    private Node root;
    private Comparator<T> comparator;

    public BinaryTree() {
    }

    public Node getRoot() {
        return root;
    }

    public BinaryTree(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public boolean add(T t) throws Exception {
        Node currNode = root;
        Node node = new Node(t);
        if(root == null) {
            root = node;
            //return  true;
        } else {
            addNode(currNode, node);
            //return  true;
        }
        return  true;
    }

    public BinaryTree daisyAdd(T t) throws Exception {
        add(t);
        return this;
    }

    private void addNode(Node currRoot, Node newNode) throws Exception {
        Node lastNode;
        if(root == null) {
            throw new Exception("Not initialized");
        }
        int compResult = newNode.compareTo(currRoot);

        if(compResult == 0) {
            throw new Exception("Node already present");
        } else if (compResult < 0) {
            if(currRoot.getLeft() != null) {
                 addNode(currRoot.getLeft(), newNode);
            } else {
                lastNode = currRoot;
                lastNode.setLeft(newNode);
            }
        } else {
            if(currRoot.getRight() != null) {
                 addNode(currRoot.getRight(), newNode);
            } else {
                lastNode = currRoot;
                lastNode.setRight(newNode);
            }
        }
    }

    public void postOrderTraversal() throws Exception {
        if(root == null) {
            throw new Exception("Tree not initialized");
        }
        postOrderTr(root);
    }

    private void postOrderTr(Node node) {
        if(node.getLeft() != null) {
                postOrderTr(node.getLeft());
        }
        if(node.getRight() != null) {
                postOrderTr(node.getRight());
        }
        System.out.println(node);
    }

    public void preOrderTraversal() throws Exception {
        if(root == null) {
            throw new Exception("Tree not initialized");
        }
        preOrderTr(root);
    }

    private void preOrderTr(Node node) {
        System.out.println(node);
        if(node.getLeft() != null) {
            preOrderTr(node.getLeft());
        } if(node.getRight() != null) {
            preOrderTr(node.getRight());
        }
    }

    public void inOrderTraversal() throws Exception{
        if(root == null) {
            throw new Exception("Tree not initialized");
        }
        inOrderTr(root);
    }

    private void inOrderTr(Node node) {
        if(node.getLeft() != null) {
            inOrderTr(node.getLeft());
        }
        System.out.println(node);
        if(node.getRight() != null) {
            inOrderTr(node.getRight());
        }
    }

    public boolean delete(T node) {
        //There are 3 scenarios

        /**
         * 1. Node to be deleted is a leaf Node.
         * soln: simply delete the Node
         *
         * 2. Node to be deleted has 1 leaf/child  Node
         *
         * 3. Node to be deleted has 2 child nodes
         */
        return false;
    }


    @Override
    public String toString() {
        return "BinaryTree{" +
                "root=" + root +
                '}';
    }


    protected class Node implements Comparable<Node>{
        private T value;
        private Node left;
        private Node right;

        public Node(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return Objects.equals(getValue(), node.getValue());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getValue());
        }

        @Override
        public int compareTo(Node o) {
            if(comparator != null) {
                return comparator.compare(this.getValue(), o.getValue());
            }
            return ((Comparable)this.getValue()).compareTo((Comparable)o.getValue());
            //return this.compareTo(o);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    //", left=" + left +
                    //", right=" + right +
                    '}';
        }
    }
}
