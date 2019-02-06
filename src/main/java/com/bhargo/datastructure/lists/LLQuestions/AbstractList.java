package com.bhargo.datastructure.lists.LLQuestions;

import com.bhargo.datastructure.lists.IList;

public abstract class AbstractList<T> implements IList<T> {

    public class Node {
        protected T value;
        protected Node next;

        public Node(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}
