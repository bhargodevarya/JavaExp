/**
 * Main Class
 */
package com.bhargo.main;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Executors;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;

import com.bhargo.datastructure.DSUtil;
import com.bhargo.datastructure.DSUtil.fruit;
import com.bhargo.datastructure.graphs.AVertex;
import com.bhargo.datastructure.graphs.EmployeeGraph;
import com.bhargo.datastructure.graphs.EmployeeVertex;
import com.bhargo.datastructure.graphs.IEdge;
import com.bhargo.datastructure.graphs.IGraph;
import com.bhargo.datastructure.graphs.IVertex;
import com.bhargo.datastructure.graphs.model.Employee;
import com.bhargo.domain.Person;
import com.bhargo.service.impl.PersonService;
import com.bhargo.util.Util;

/**
 * @author Bhargo
 *
 */
public class MainClass {

	static Util<Person> util = new Util<Person>();

	public static void main(String args[]) throws IOException {
		// To show user count for an email service
		// traditionalWay();
		// lambdaWay();
		// streamWay();
		// JMXDemo();
		// listToMap();
		setupGraph();
	}

	static void setupGraph() {

		EmployeeGraph empGraph = (EmployeeGraph)DSUtil.setUpGraph();
		//Map<IVertex<Employee>, Set<IEdge<Employee>>> map = empGraph.getMap();
		Set<Map.Entry<IVertex<Employee>, Set<IEdge<Employee>>>> set = empGraph.getMap().entrySet();
		for (Map.Entry<IVertex<Employee>, Set<IEdge<Employee>>> entry : set) {
			//System.out.println("from " + entry.getKey());
			Set<IEdge<Employee>> empSet = entry.getValue();
			Iterator<IEdge<Employee>> itrVal = empSet.iterator();
			while (itrVal.hasNext()) {
				List<IVertex<Employee>> list = itrVal.next().getNodes();
				//System.out.println("between  " +  list.get(0) + " " + list.get(list.size() -1));
				/*for (IVertex<Employee> emp : list) {
					System.out.println(emp.getT().getName());
				}*/
			}
		}
		Employee emp2 = new Employee();
		emp2.setName("Gautam");
		AVertex<Employee> vertexToFind = new EmployeeVertex();
		vertexToFind.setT(emp2);
		Employee emp3 = new Employee();
		emp3.setName("Khandekar");
		AVertex<Employee> vertexToStart = new EmployeeVertex();
		vertexToStart.setT(emp3);
		//finds the node, but still traverses unnecessary nodes
		//DSUtil.performBFS(((IGraph<Employee>)empGraph),vertexToStart, vertexToFind);
		
		DSUtil.setupDataForDijikstra(empGraph, vertexToStart);

	}
	
	static List<Person> createData() {
		List<Person> personList = null;
		try {
			personList = Util.createList();
			Person amar = new Person("amar", 28, "Chandigarh", "amar@yahoo.com");
			Person geeta = new Person("geeta", 16, "Gujrat", "geet@yahoo.com");
			Person nik = new Person("nik", 12, "Kerala", "nik@gmail.com");
			Person john = new Person("john", 35, "Kerala", "john@gmail.com");
			personList.add(amar);
			personList.add(geeta);
			personList.add(nik);
			personList.add(john);
		} catch (Exception e) {
			System.out.println("There has been an error while creating the data set");
		}
		return personList;
	}

	static List<fruit> createABigList() {
		Random random = new Random();
		List<fruit> list = new ArrayList<>();
		int randomNum = 0;
		for (int i = 0; i < 50000000; i++) {
			randomNum = random.nextInt(6);
			switch (randomNum) {
			case 1:
				list.add(fruit.APPLE);
				break;
			case 2:
				list.add(fruit.MANGO);
				break;
			case 3:
				list.add(fruit.BANANA);
				break;
			case 4:
				list.add(fruit.GRAPES);
				break;
			case 5:
				list.add(fruit.ORANGE);
				break;
			default:
				list.add(fruit.APPLE);
				break;
			}
		}
		return list;
	}

	static void listToMap() {
		List<fruit> bigList = createABigList();
		System.out.println(bigList.size());
		Map<fruit, Integer> countMap = new HashMap<>();
		DSUtil.listToMapConversion(bigList, countMap);
	}

	static void JMXDemo() {
		MBeanServer mserver = ManagementFactory.getPlatformMBeanServer();
		PersonService personService = new PersonService();
		personService.votersStream = createData().stream().filter(n -> n.getAge() >= 18);
		try {
			mserver.registerMBean(personService, new ObjectName("com.bhargo.service.impl:type=PersonService"));
		} catch (InstanceAlreadyExistsException | MBeanRegistrationException | NotCompliantMBeanException
				| MalformedObjectNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(PersonService.collected);
		while (!PersonService.collected) {
			// System.out.println(personService.getPerson().getName());
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	static void lambdaWay() {
		List<Person> personList = createData();
		Set<String> providers = new HashSet<String>();
		util.providers.andThen(util.countByProviders).accept(personList, providers);

	}

	/*
	 * static void streamWay() { List<Person> personList = createData();
	 * Function<Person, String> split = (person) -> { return
	 * person.getEmail().split("@")[1].split("\\.")[0]; };
	 * personList.stream().map(split).collect(Collectors.toSet());
	 * Map<Object,List<Object>> mapObj = personList.stream().collect(
	 * Collectors.groupingBy( (Person p) -> p.getAge(),
	 * Collectors.mapping((Person p) -> p.getName(), Collectors.toList()) ) );
	 * List<String> pList =
	 * personList.stream().collect(Collectors.mapping((Person p) -> p.getName(),
	 * Collectors.toList())); pList.forEach(System.out::println); }
	 */

	static void traditionalWay() {

		List<Person> personList = createData();

		Set<Person> personSet = new HashSet<Person>();
		Map<String, Set<Person>> emailMap = new HashMap<String, Set<Person>>();
		Set<String> providers = new HashSet<String>();

		// 1. get all distinct email providers
		for (Person person : personList) {
			String email = person.getEmail();
			String provider = email.split("@")[1].split("\\.")[0];
			providers.add(provider);
		}

		Iterator<String> itr = providers.iterator();
		while (itr.hasNext()) {
			String provider = itr.next();
			for (Person person : personList) {
				if (person.getEmail().split("@")[1].split("\\.")[0].equals(provider)) {
					personSet.add(person);
				}
			}
			emailMap.put(provider, personSet);
			System.out.println(" The email provider is " + provider + ", the num of users is " + personSet.size());
			personSet.clear();
		}

	}
}
