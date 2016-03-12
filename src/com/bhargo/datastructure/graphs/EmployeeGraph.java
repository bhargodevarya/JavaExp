package com.bhargo.datastructure.graphs;

import java.util.HashSet;
import java.util.Iterator;
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
				System.out.println("to edge doesnot exist");
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
		Set<IEdge<Employee>> edgeSet = map.get(from);
		if(edgeSet.size() > 0) {
			Iterator<IEdge<Employee>> itr = edgeSet.iterator();
			while(itr.hasNext()) {
				IEdge<Employee> edge = itr.next();
				if(edge.getNodes().contains(to)) {
					System.out.println("edge already exists");
				} else {
					EmployeeEdge<Employee> edge1 = new EmployeeEdge<Employee>();
					edge1.setNodes(from, to);
					edgeSet.add(edge1);
					map.put(from, edgeSet);
				}
			}
		} else {
			EmployeeEdge<Employee> edge1 = new EmployeeEdge<Employee>();
			edge1.setNodes(from, to);
			edgeSet.add(edge1);
			map.put(from, edgeSet);
		}
	
		
	}

	
}
