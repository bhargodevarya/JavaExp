package com.bhargo.datastructure.tree;

import java.util.Objects;

/**
 * Created by barya on 11/26/2016.
 */
public class BinaryTree<T> {

    private node root;

    public boolean add(T t) throws Exception {
        node currNode = root;
        node node = new node(t);
        if(root == null) {
            root = node;
            //return  true;
        } else {
            addNode(currNode, node);
            //return  true;
        }
        return  true;
    }

    private void addNode(node currRoot, node newNode) throws Exception {
        node lastNode;
        if(root == null) {
            throw new Exception("Not initialized");
        }
        int compResult = newNode.compareTo(currRoot);

        if(compResult == 0) {
            throw new Exception("node already present");
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

    private class node implements Comparable<node>{
        private T value;
        private node left;
        private node right;

        public node(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public node getLeft() {
            return left;
        }

        public void setLeft(node left) {
            this.left = left;
        }

        public node getRight() {
            return right;
        }

        public void setRight(node right) {
            this.right = right;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            node node = (node) o;
            return Objects.equals(getValue(), node.getValue());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getValue());
        }

        @Override
        public int compareTo(node o) {
            return ((Comparable)this.getValue()).compareTo((Comparable)o.getValue());
            //return this.compareTo(o);
        }

        @Override
        public String toString() {
            return "node{" +
                    "value=" + value +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "BinaryTree{" +
                "root=" + root +
                '}';
    }
}
