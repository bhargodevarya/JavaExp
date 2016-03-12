package com.bhargo.datastructure.graphs;

public interface IGraph<T> {
	
	void addVertex(IVertex<T> vertex);
	void addEdge(IVertex<T> from,IVertex<T> to);
	void addEdge(boolean directed, IVertex<T> from,IVertex<T> to);

}
