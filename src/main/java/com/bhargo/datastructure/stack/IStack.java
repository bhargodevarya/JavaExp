package com.bhargo.datastructure.stack;

public interface IStack<T> {
	
	void push(T t);
	T pop() throws Exception;

}
