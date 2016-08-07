package com.bhargo.datastructure;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import com.bhargo.datastructure.graphs.AGraph;
import com.bhargo.datastructure.graphs.AVertex;
import com.bhargo.datastructure.graphs.AdaptablePQ;
import com.bhargo.datastructure.graphs.DistanceVertex;
import com.bhargo.datastructure.graphs.EmployeeEdge;
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
		empGraph.addConnection(vertex1, vertex2, 3);
		empGraph.addConnection(vertex1, vertex3, 5);
		empGraph.addConnection(vertex2, vertex1, 6);
		empGraph.addConnection(vertex3, vertex1, 1);
		empGraph.addConnection(vertex2, vertex4, 9);
		empGraph.addConnection(vertex4, vertex2, 2);
		empGraph.addConnection(vertex3, vertex4, 5);
		empGraph.addConnection(vertex4, vertex3, 9);
		empGraph.addConnection(vertex4, vertex5, 6);
		empGraph.addConnection(vertex5, vertex4, 7);
		empGraph.addConnection(vertex5, vertex6, 1);
		empGraph.addConnection(vertex6, vertex5, 9);
		empGraph.addConnection(vertex2, vertex6, 2);
		empGraph.addConnection(vertex6, vertex2, 6);
		empGraph.addConnection(vertex5, vertex7, 1);
		empGraph.addConnection(vertex7, vertex5, 8);
		empGraph.addConnection(vertex2, vertex3, 2);
		
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
		int seeks = empGraph.getMap().size();
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
			seeks--;
			if(vertex.equals(vertexToFind)) {
				
			} else {
				if(search(graphRep, vertex, neighbour, vertexToFind, queue, visited)) {
					System.out.println("found " + vertexToFind.getT().getName());
					break;
				} else if(seeks > 0){
					System.out.println("still looking");
				} else {
					System.out.println("Node doesnot exist");
					break;
				}
			}
			
		}		
		System.out.println("Vertices traversed");
		for (IVertex<Employee> iVertex : visited) {
			System.out.print(iVertex + " ");
		}
	}
	
	public static void setupDataForDijikstra(IGraph<Employee> graph,IVertex<Employee> vertexToStart) {
		Map<IVertex<Employee>, Integer> distanceMap = new LinkedHashMap<IVertex<Employee>, Integer>();
		EmployeeGraph empGraph;
		if(graph instanceof EmployeeGraph) {
			empGraph = (EmployeeGraph)graph;
		} else {
			System.out.println("Not a valid graph instance");
			return;
		}
		Map<IVertex<Employee>, Set<IEdge<Employee>>> edgeMap = empGraph.getMap();
		
		//map that contains the vertex with the shortest path
		Map<IVertex<Employee>, IEdge<Employee>> sptEdgeMap = new HashMap<IVertex<Employee>, IEdge<Employee>>();
		
		Iterator<IVertex<Employee>> it = edgeMap.keySet().iterator();
		while(it.hasNext()) {
			IVertex<Employee> vertex = it.next();
			//edgeMapCopy.put(vertex, new HashSet<IEdge<Employee>>());
			distanceMap.put(vertex, null);
		}
		
		
		//create a PQ where the priority is decided by the edge weight
		//each item has K,V where K can be repeated, K is distance, V is vertex
		AdaptablePQ<DistanceVertex<Integer, IVertex<Employee>>> adaptablePQ = new AdaptablePQ<DistanceVertex<Integer,IVertex<Employee>>>();
		
		//populate pq with the source, it has distance as 0
		DistanceVertex<Integer, IVertex<Employee>> start = new DistanceVertex<>(0, vertexToStart);
		adaptablePQ.add(start);
		
		//
		while(!adaptablePQ.isEmpty()) {
			relax(empGraph, adaptablePQ.poll(), distanceMap, adaptablePQ, sptEdgeMap);
		}
		
		IVertex<Employee> temp = null;
		/*Iterator<IVertex<Employee>> itr = distanceMap.keySet().iterator();
		while(itr.hasNext()) {
			temp = itr.next();
			System.out.println("key " + temp  + " value " + distanceMap.get(temp));
		}*/	
		
		
		//uncomment for debugging
		Iterator<IVertex<Employee>> sptItr = sptEdgeMap.keySet().iterator();
		while(sptItr.hasNext()) {
			temp = sptItr.next();
			System.out.println(temp + " > " +sptEdgeMap.get(temp));
		}
		
		Employee emp2 = new Employee();
		emp2.setName("Khandekar");
		AVertex<Employee> from = new EmployeeVertex();
		from.setT(emp2);
		Employee emp3 = new Employee();
		emp3.setName("Gautam");
		AVertex<Employee> to = new EmployeeVertex();
		to.setT(emp3);
		
		pathTo(distanceMap,to,from,sptEdgeMap);
		
		
	}
	
	public static boolean hasPath(Map<IVertex<Employee>, Integer> distanceMap, IVertex<Employee> vertexToFind) {
		return distanceMap.get(vertexToFind) == null;
	}
	
	public static void pathTo(Map<IVertex<Employee>, Integer> distanceMap, IVertex<Employee> vertexToFind,
			IVertex<Employee> vertexToStart, Map<IVertex<Employee>, IEdge<Employee>> sptEdgeMap) {
		/*if(!hasPath(distanceMap, vertexToFind)) {
			return;
		}*/
		IVertex<Employee> vertexToFindCopy = vertexToFind;
		Stack<IEdge<Employee>> edgeStack = new Stack<IEdge<Employee>>();
		while(!vertexToFind.equals(vertexToStart)) {
			vertexToFind = getEdge(sptEdgeMap, vertexToFind, edgeStack);
		}
		
		System.out.println(" from "+ vertexToStart.getT().getName() + " to " + vertexToFindCopy.getT().getName() + " path is ");
		while(edgeStack.size() > 0) {
			System.out.print(edgeStack.pop() + " ");
		}
	}
	
	private static IVertex<Employee> getEdge(Map<IVertex<Employee>, IEdge<Employee>> sptEdgeMap, IVertex<Employee> vertexToFind,
			Stack<IEdge<Employee>> edgeStack) {
		//List<IVertex<Employee>> nodes = sptEdgeMap.get(vertexToFind).getNodes();
		edgeStack.push(sptEdgeMap.get(vertexToFind));
		
		return sptEdgeMap.get(vertexToFind).getNodes().get(0);
		
	}
	
	public static void relax(IGraph<Employee> graph,DistanceVertex<Integer, IVertex<Employee>> start,
			Map<IVertex<Employee>, Integer> distanceMap, AdaptablePQ<DistanceVertex<Integer, IVertex<Employee>>> pq,
			Map<IVertex<Employee>, IEdge<Employee>> sptEdgeMap) {
		
		//Map<IVertex<Employee>, Integer> distanceMap = new HashMap<IVertex<Employee>, Integer>();
		EmployeeGraph empGraph;
		Set<IEdge<Employee>> edgeSet;
		if(graph instanceof EmployeeGraph) {
			empGraph = (EmployeeGraph)graph;
		} else {
			System.out.println("Not a valid graph instance");
			return;
		}
		
		//graph rep
		Map<IVertex<Employee>, Set<IEdge<Employee>>> edgeMap = empGraph.getMap();
		
		//System.out.println(start.getValue().getT().getName());
		
		//get all the edges for the start vertex 
		edgeSet = edgeMap.get(start.getValue());
	 
		 Iterator<IEdge<Employee>> edgeSetItr = edgeSet.iterator();
 
		 //traverse the edges
		 while(edgeSetItr.hasNext()) {
			 IEdge<Employee> edge = edgeSetItr.next();
			 //get the to node
			 IVertex<Employee> toVertex = edge.getNodes().get(1);
			 
			 
			 Integer distanceFromMap = distanceMap.get(toVertex) == null ? Integer.MAX_VALUE : distanceMap.get(toVertex);
			 Integer sourceDistance = distanceMap.get(start.getValue()) == null ? 0:distanceMap.get(start.getValue());
			 Integer actualDistance = Integer.valueOf(((EmployeeEdge<Employee>)edge).getWeight()) == null ? 0:((EmployeeEdge<Employee>)edge).getWeight() ;
			 
			 DistanceVertex<Integer, IVertex<Employee>> dv = new DistanceVertex<Integer, IVertex<Employee>>(toVertex);
			 if(sourceDistance + actualDistance < distanceFromMap) {
				 distanceMap.put(toVertex, sourceDistance + actualDistance);
				 sptEdgeMap.put(toVertex, edge);
				 if(pq.containsVertex(dv)) {
					 //Require a DS, that allows updating on priority queues
					 //something like pq.chageKey();
					 ((AdaptablePQ)pq).updateElement(dv, sourceDistance + actualDistance);				
				 } else {
					 //this should re structure the queue
					 pq.add(new DistanceVertex<Integer, IVertex<Employee>>(sourceDistance + actualDistance, toVertex));					 
				 }
			 }
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
