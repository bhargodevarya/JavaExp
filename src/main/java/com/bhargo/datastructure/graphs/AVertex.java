package com.bhargo.datastructure.graphs;

public abstract class AVertex<T> implements IVertex<T> {
	
	T obj;
	
	public T getT() {
		return obj; 
	}
	
	public void setT(T t) {
		 this.obj = t;
	}

}
