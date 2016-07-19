package com.bhargo.datastructure.stack;

import java.util.ArrayList;
import java.util.List;

public class UserStack<T> implements IStack<T>{
	
	private List<T> stackList = new ArrayList<>();
	
	@Override
	public void push(T t) {
		stackList.add(t);
	}

	@Override
	public T pop() throws Exception {
		if(stackList.size() == 0) {
			throw new Exception("Empty stack");
		}
		T result =stackList.get(stackList.size()-1);
		stackList.remove(stackList.size() -1);
		return result;
	}
	
	public int size() {
		return stackList.size();
	}

}
