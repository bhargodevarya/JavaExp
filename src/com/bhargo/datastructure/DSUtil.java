package com.bhargo.datastructure;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import com.bhargo.datastructure.graphs.AGraph;
import com.bhargo.datastructure.graphs.AVertex;
import com.bhargo.datastructure.graphs.EmployeeGraph;
import com.bhargo.datastructure.graphs.EmployeeVertex;
import com.bhargo.datastructure.graphs.IEdge;
import com.bhargo.datastructure.graphs.IGraph;
import com.bhargo.datastructure.graphs.IVertex;
import com.bhargo.datastructure.graphs.model.Employee;

public class DSUtil {
	

	public enum fruit {
		APPLE("apple"), MANGO("mango"), BANANA("banana"), ORANGE("orange"), GRAPES("grapes");

		String name;

		fruit(String name) {
			this.name = name;
		}

		public String getname() {
			return name;
		}
	}
	
	public static void listToMapConversion(List<fruit> bigList,Map<fruit,Integer> countMap) {
		Iterator<fruit> itr = bigList.iterator();
		boolean keyPresent = false;
		while(itr.hasNext()) {
			fruit fr = itr.next();
			keyPresent = countMap.containsKey(fr);
			if(keyPresent) {
				countMap.put(fr, countMap.get(fr) + 1);
			} else {
				countMap.put(fr, 0);
			}
		}		
		Set<Map.Entry<fruit, Integer>> entrySet = countMap.entrySet();
		for (Map.Entry<fruit, Integer> entry : entrySet) {
			System.out.println(entry.getKey() + " " + entry.getValue());
		}
	}
	
	@SuppressWarnings("rawtypes")
	public static AGraph setUpGraph() {
		Employee emp1 = new Employee();
		emp1.setName("Khandekar");
		Employee emp2 = new Employee();
		emp2.setName("Money");
		Employee emp3 = new Employee();
		emp3.setName("Kiran");
		Employee emp4 = new Employee();
		emp4.setName("Kishore");
		Employee emp5 = new Employee();
		emp5.setName("wall");
		Employee emp6 = new Employee();
		emp6.setName("Aditya");
		Employee emp7 = new Employee();
		emp7.setName("Gautam");

		//has to be represented as Map<node,set<edges>>
		EmployeeGraph empGraph = new EmployeeGraph();
		AVertex<Employee> vertex1 = new EmployeeVertex();
		vertex1.setT(emp1);
		AVertex<Employee> vertex2 = new EmployeeVertex();
		vertex2.setT(emp2);
		AVertex<Employee> vertex3 = new EmployeeVertex();
		vertex3.setT(emp3);
		AVertex<Employee> vertex4 = new EmployeeVertex();
		vertex4.setT(emp4);
		AVertex<Employee> vertex5 = new EmployeeVertex();
		vertex5.setT(emp5);
		AVertex<Employee> vertex6 = new EmployeeVertex();
		vertex6.setT(emp6);
		AVertex<Employee> vertex7 = new EmployeeVertex();
		vertex7.setT(emp7);
		
		empGraph.addVertex(vertex1);
		empGraph.addConnection(vertex1, vertex2);
		empGraph.addConnection(vertex1, vertex3);
		empGraph.addConnection(vertex2, vertex1);
		empGraph.addConnection(vertex3, vertex1);
		empGraph.addConnection(vertex2, vertex4);
		empGraph.addConnection(vertex4, vertex2);
		empGraph.addConnection(vertex3, vertex4);
		empGraph.addConnection(vertex4, vertex3);
		empGraph.addConnection(vertex4, vertex5);
		empGraph.addConnection(vertex5, vertex4);
		empGraph.addConnection(vertex5, vertex6);
		empGraph.addConnection(vertex6, vertex5);
		empGraph.addConnection(vertex2, vertex6);
		empGraph.addConnection(vertex6, vertex2);
		empGraph.addConnection(vertex5, vertex7);
		empGraph.addConnection(vertex7, vertex5);
		
		return empGraph;
	}
	
	public static void performBFS(IGraph<Employee> graph,IVertex<Employee> vertexToStart, IVertex<Employee> vertexToFind) {
		EmployeeGraph empGraph;
		if(graph instanceof EmployeeGraph) {
			empGraph = (EmployeeGraph)graph;
		} else {
			System.out.println("Not a valid graph instance");
			return;
		}
		
		IVertex<Employee> neighbour = null;
		//Set<IEdge<Employee>> neighbourSet = null;
		Queue<IVertex<Employee>> queue = new LinkedList<IVertex<Employee>>();
		Set<IVertex<Employee>> visited = new LinkedHashSet<IVertex<Employee>>();
		Map<IVertex<Employee>, Set<IEdge<Employee>>> graphRep = empGraph.getMap();
		if(vertexToStart != null) {
			queue.add(vertexToStart);
		} else {
			Set<Map.Entry<IVertex<Employee>, Set<IEdge<Employee>>>> entrySet = graphRep.entrySet();		
			for (Map.Entry<IVertex<Employee>, Set<IEdge<Employee>>> entry : entrySet) {
				queue.add(entry.getKey());
				break;
			}
		}
		
		
		while(!queue.isEmpty()) {
			//System.out.println("peeking " + queue.peek());
			IVertex<Employee> vertex = queue.remove();
			//System.out.println("visiting " + vertex);
			visited.add(vertex);
			if(vertex.equals(vertexToFind)) {
				
			} else {
				if(search(graphRep, vertex, neighbour, vertexToFind, queue, visited)) {
					break;
				}
			}
			
		}
		
		System.out.println("overall path");
		for (IVertex<Employee> iVertex : visited) {
			System.out.print(iVertex + " ");
		}

	}
	
	public static boolean search(Map<IVertex<Employee>, Set<IEdge<Employee>>> graphRep, IVertex<Employee> vertex,
			IVertex<Employee> neighbour, IVertex<Employee> vertexToFind, Queue<IVertex<Employee>> queue, Set<IVertex<Employee>> visited) {
		Set<IEdge<Employee>> set = getFirstHop(graphRep, vertex);
		Iterator<IEdge<Employee>> neItr = set.iterator();
		EmployeeVertex emp = null;
		while(neItr.hasNext()) {
			IEdge<Employee> edge = neItr.next();
			neighbour = edge.getNodes().get(1);
			if(neighbour instanceof EmployeeVertex) {
				emp = (EmployeeVertex)neighbour;
			}
			if(!visited.contains(neighbour) && neighbour.equals(vertexToFind)) {
				//System.out.println("found " + vertexToFind.toString() + ", path is " + edge);
				return true;
			}
			if(!queue.contains(neighbour) && !checkVisited(emp, visited)) {
				queue.add(neighbour);
			}										
		} return false;
	}
	
	static boolean checkVisited(EmployeeVertex emp, Set<IVertex<Employee>> visited) {
		for (IVertex<Employee> iVertex : visited) {
			if(iVertex instanceof EmployeeVertex) {
				return ((EmployeeVertex)iVertex).equals(emp);
			}
		}
		return false;
	}
	
	public static  Set<IEdge<Employee>> getFirstHop(Map<IVertex<Employee>, Set<IEdge<Employee>>> graphRep, IVertex<Employee> vertex) {
		Iterator<IVertex<Employee>> itr = graphRep.keySet().iterator();
		while(itr.hasNext()) {
			IVertex<Employee> node = itr.next();
			if(node.equals(vertex)) {
				return graphRep.get(node);
			}
			//System.out.println("node is " + node.getT().getName() + " vertex to find is " + vertex.getT().getName() + ". equals ?? "+ node.equals(vertex));
		}
		//System.out.println(graphRep.containsKey(vertex));
		return graphRep.get(vertex);
	}

}
