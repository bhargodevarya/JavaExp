package com.bhargo.datastructure.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class AEdge<T> implements IEdge<T>{

	List<IVertex<T>> nodes = new LinkedList<>();
	private int weight;	

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public List<IVertex<T>> getNodes() {
		return nodes;
	}

	public void setNodes(IVertex<T> from,IVertex<T> to) {
		nodes.add(from);
		nodes.add(to);
		
	}
	
}
