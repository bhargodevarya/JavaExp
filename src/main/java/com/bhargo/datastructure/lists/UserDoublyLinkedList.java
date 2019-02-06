package com.bhargo.datastructure.lists;

import com.bhargo.datastructure.lists.LLQuestions.AbstractList;

public class UserDoublyLinkedList<T> extends AbstractList<T> {

    public class Node extends AbstractList.Node {
        private Node previous;

        public Node(T value, Node previous) {
            super(value);
            this.previous = previous;
        }

        public Node getPrevious() {
            return previous;
        }

        public void setPrevious(Node previous) {
            this.previous = previous;
        }
    }

    @Override
    public void add(T t) {

    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public void remove(int index) {

    }

    @Override
    public T get() {
        return null;
    }
}
