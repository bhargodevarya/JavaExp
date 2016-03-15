package com.bhargo.datastructure.graphs;

public class EmployeeEdge<T> extends AEdge<T>{
	
	public String toString() {
		return getNodes().get(0) + " " + getNodes().get(1);
	}

}
