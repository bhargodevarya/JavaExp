package com.bhargo.datastructure.lists;

public class UserLinkedList<T> implements IList<T> {
	private int size = 0;
	private Node<T> first;
	
	private class Node<T> {

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
	}
	
	private Node<T> createNode(T t) {
		return new Node<T>(t,null);
	}
	
	private Node getLast() throws Exception {
		Node result = first;
		if(size == 0)
			throw new Exception("not initilized");
		Node current = first.getNext();
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
		if(first != null) {
			try {
				getLast().setNext(n);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			first = new Node<T>(t, null);
			//first.setNext(n);
		}
		size++;
	}

	@Override
	public T get(int index) {
		// TODO Auto-generated method stub
		T result = null;
		if(!(index <= size))
			throw new IllegalArgumentException("invalid size");
		if(size == 0)
			return first.getValue();
		Node current = first.getNext();
		for(int i =1;i<=index;i++) {
			result = (T)current.getValue();
			current = current.getNext();
		}
		return result;
	}

	@Override
	public void remove(int index) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public T get() {
		// TODO Auto-generated method stub
		return first.getValue();
	}

}
