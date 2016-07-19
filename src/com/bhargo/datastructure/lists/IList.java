package com.bhargo.datastructure.lists;

public interface IList<T> {
	
	void add(T t);
	T get(int index);
	void remove(int index);
	T get();
}
