package com.bhargo.datastructure.lists;

public class UserLinkedList<T> implements IList<T> {
    private int size = 0;
    private Node<T> HEAD;
    
    public class Node<T> {

        private T value;
        private Node next;
        public Node(T value,Node next) {
            this.value = value;
            this.next = next;
        }
        public Node getNext() {
            return next;
        }
        public T getValue() {
            return value;
        }
        public void setNext(Node next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }
    }
    
    private Node<T> createNode(T t) {
        return new Node<T>(t,null);
    }
    
    private Node getLast() throws Exception {
        Node result = HEAD;
        if(size == 0)
            throw new Exception("not initilized");
        Node current = HEAD.getNext();
        while(current != null) {
            result = current;
            current = current.getNext();
        }
        return result;
    }

    @Override
    public void add(T t) {
        // TODO Auto-generated method stub
        Node<T> n = createNode(t);
        if(size > 0) {
            try {
                getLast().setNext(n);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            HEAD = new Node(t, null);
        }
        size++;
    }

    public UserLinkedList daisyAdd(T t) {
        add(t);
        return this;
    }

    @Override
    public T get(int index) {
        // TODO Auto-generated method stub
        T result = null;
        if(!(index < size))
            throw new IllegalArgumentException("invalid size");
        if(size == 0)
            throw new IllegalStateException("No nodes in the linked list");
        Node current = HEAD.getNext();
        for(int i =0;i<=index;i++) {
            current = current.getNext();
        }
        return (T)current.getValue();
    }

    @Override
    public void remove(int index) {
        // TODO Auto-generated method stub
        // index-1 node's next
        // index node's next
        if(!validIndex(index)) {
            throw  new IllegalArgumentException("Invalid index");
        }
        Node current = HEAD;
        Node prev = null;
        while (index >= 0) {
            prev = current;
            current = current.getNext();
            index--;
        }

        System.out.printf("prev is " + prev.getValue() + " curr is " + current.getValue());
        prev.setNext(current.getNext());
        current = null;

        System.out.println(this);
    }

    @Override
    public T get() {
        // TODO Auto-generated method stub
        return (T) HEAD.getNext().getValue();
    }

    private boolean validIndex(int index) {
        return index >=0 && index < size;
    }

    public Node getHead() {
        return HEAD;
    }

    public void printList() {
        Node curr = HEAD.getNext();
        while (curr != null) {
            System.out.println(curr.getValue());
            curr = curr.getNext();
        }
    }

    public Integer getSize() {
        return size;
    }

    public Node getNode(int index) {
        if (index > size)
            throw new IllegalArgumentException("greater than size");
        Node result = getHead();
        while (index > 1) {
            result = result.getNext();
            index--;
        }
        return result;
    }



}
