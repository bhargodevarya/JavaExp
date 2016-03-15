package com.bhargo.datastructure.graphs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.bhargo.datastructure.graphs.model.Employee;

public class EmployeeGraph extends AGraph<Employee>{

	@Override
	public void addNode(IVertex<Employee> vertex) {
		// TODO Auto-generated method stub
		map.put(vertex, new HashSet<IEdge<Employee>>());
		
	}

	@Override
	public void addConnection(IVertex<Employee> from, IVertex<Employee> to) {
		// TODO Auto-generated method stub
		if(map.containsKey(from)) {
			if(map.containsKey(to)) {
				add(from, to);
			} else {
				//System.out.println("to edge doesnot exist");
				addNode(to);
				add(from, to);
			}
		} else {
			System.out.println("the from vertex doesnot exist");
			addNode(from);
			add(from, to);
		}
		
	} 
	
	private void add(IVertex<Employee> from, IVertex<Employee> to) {
		Map<IVertex<Employee>,Set<IEdge<Employee>>> tempMap = new HashMap<IVertex<Employee>, Set<IEdge<Employee>>>();
		tempMap = map;
		Set<IEdge<Employee>> tempEdgeSet = new HashSet<IEdge<Employee>>();
		Set<IEdge<Employee>> edgeSet = tempMap.get(from);
		if(edgeSet.size() > 0) {
			Iterator<IEdge<Employee>> itr = edgeSet.iterator();
			while(itr.hasNext()) {
				IEdge<Employee> edge = itr.next();
				if(edge.getNodes().contains(to)) {
					System.out.println("edge already exists");
					break;
				} else {
					EmployeeEdge<Employee> edge1 = new EmployeeEdge<Employee>();
					edge1.setNodes(from, to);
					tempEdgeSet.add(edge1);
					break;
					//tempMap.put(from, tempEdgeSet);
				}
				//map.get(from).addAll(tempEdgeSet);
			}map.get(from).addAll(tempEdgeSet);
		} else {
			EmployeeEdge<Employee> edge1 = new EmployeeEdge<Employee>();
			edge1.setNodes(from, to);
			edgeSet.add(edge1);
			map.put(from, edgeSet);
		}
	
		
	}

	
}
