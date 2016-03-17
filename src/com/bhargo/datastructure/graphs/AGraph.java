package com.bhargo.datastructure.graphs;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AGraph<T> implements IGraph<T> {
	
	public int numOfVertices=0;
	public Map<IVertex<T>,Set<IEdge<T>>> map = new HashMap<IVertex<T>, Set<IEdge<T>>>(); 

	public int getNumOfVertices() {
		return numOfVertices;
	}

	public Map<IVertex<T>, Set<IEdge<T>>> getMap() {
		return map;
	}

	public void setMap(Map<IVertex<T>, Set<IEdge<T>>> map) {
		this.map = map;
	}

	@Override
	public void addVertex(IVertex<T> vertex) {	
		numOfVertices++;
		addNode(vertex);
	}
	
	@Override
	public void addEdge(IVertex<T> from, IVertex<T> to) {	
			addConnection(from, to);
	}

	@Override
	public void addEdge(boolean directed, IVertex<T> from, IVertex<T> to) {		
		
	}

	public abstract void addNode(IVertex<T> vertex);
	
	public abstract void addConnection(IVertex<T> from, IVertex<T> to);
	
	public abstract void addConnection(IVertex<T> from, IVertex<T> to,int weight);

	
}
