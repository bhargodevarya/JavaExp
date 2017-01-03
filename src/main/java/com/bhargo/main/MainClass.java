/**
 * Main Class
 */
package com.bhargo.main;

import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;

import com.bhargo.algorithms.sort.Sort;
import com.bhargo.datastructure.DSUtil;
import com.bhargo.datastructure.DSUtil.fruit;
import com.bhargo.datastructure.graphs.AVertex;
import com.bhargo.datastructure.graphs.EmployeeGraph;
import com.bhargo.datastructure.graphs.EmployeeVertex;
import com.bhargo.datastructure.graphs.IEdge;
import com.bhargo.datastructure.graphs.IVertex;
import com.bhargo.datastructure.graphs.model.Employee;
import com.bhargo.datastructure.lists.UserLinkedList;
import com.bhargo.datastructure.stack.UserStack;
import com.bhargo.datastructure.tree.BinaryTree;
import com.bhargo.domain.Person;
import com.bhargo.dynamic.DynamicDemo;
import com.bhargo.service.Creator;
import com.bhargo.service.CustomInterface;
import com.bhargo.service.impl.PersonService;
import com.bhargo.util.Util;

/**
 * @author Bhargo
 *
 */
public class MainClass {

	static Util<Person> util = new Util<Person>();

	public static void main(String args[]) throws IOException, Exception {
		// To show user count for an email service
		// traditionalWay();
		//lambdaWay();
		// streamWay();
		// JMXDemo();
		// listToMap();
		// setupGraph();
		//forkJoinDemo();
		//curryDemo(createData());
		//lambdaDemo();
		//ThreadDemo.executorServiceDemo(Callable.class);
		//ThreadDemo.executorServiceInvokeAllDemo();
		//System.out.println(Singleton.class);
		//linkedListDemo();
		//BinarySearch.doBinarySeach("/Users/barya/code/github/text.txt",99);
		//ThreadDemo.AtomicIntDemo();
        //ReflectionDemo.reflect();
		//DynamicDemo.longestSubSequenceSum(Arrays.asList(new Integer[]{5,3,-1,6,7,23,-45,12,7,-4}));
		//DynamicDemo.longestIncreasingSubsequence(Arrays.asList(new Integer[]{10, 22, 9, 33, 21, 50, 41, 60}));

        int[] arr =new int[]{865,8,45,78,62,15,77,846,254};
		/*Sort.quickSort(arr);
        //Sort.sort(arr, 0, arr.length -1);
        for (Integer in:arr) {
            System.out.println(in);
        }*/

		myBinaryTreedemo();
    }

    private static void myBinaryTreedemo() throws Exception {
        BinaryTree<Integer> myBinaryTree = new BinaryTree<>();
        myBinaryTree.add(7);
        myBinaryTree.add(3);
        myBinaryTree.add(9);
        myBinaryTree.add(2);
        myBinaryTree.add(12);

        System.out.println(myBinaryTree);
    }

    static class testObj {
        @Override
        public int hashCode() {
            return 777;
        }
    }
	
	static void linkedListDemo() {
		UserLinkedList<Integer> linkedList = new UserLinkedList<>();
		linkedList.add(5);
		linkedList.add(7);
		linkedList.add(2);
		linkedList.add(55);
		System.out.println(linkedList.get(1));
	}
	
	static void stackDemo() throws Exception {
		UserStack<Integer> stack = new UserStack<>();
		stack.push(5);
		stack.push(8);
		stack.push(15);
		stack.push(25);
		System.out.println(stack.size());
		for(int i=stack.size();i>0;i--) {
			System.out.println(stack.pop());
		}
	}
	
	static void lambdaDemo() throws Exception{
		
		//creating a lambda
		CustomInterface lambdaExp = ()  ->  System.out.println("This is a custom interface lambda");
		
		//invoking the lambda
		lambdaExp.lambdaMethod();
		
		//passing lambda as a arg
		acceptLambda(lambdaExp);
		
		List<Person> list = createData();
		
		//creating a lambda of type Predicate
		Predicate<Person> mailPredicate = n -> n.getEmail().contains("gmail");
		
		//chaining the predicate lambda inside a stream
		list.stream().filter(mailPredicate.and(n -> n.getState().equalsIgnoreCase("kerala"))).forEach(n -> System.out.println(n));
		
		//old way of sorting a list
		Collections.sort(list, new Comparator<Person>() {
			@Override
			public int compare(Person o1, Person o2) {
				return o1.getName().compareTo(o2.getName());
			}			
		});		
		
		//sorting the list using a lambda
		Collections.sort(list, (Person n1,Person n2) -> n1.getName().compareTo(n2.getName()));
		//list.forEach(System.out::println);
		
		//sorting using method reference
		Collections.sort(list, Person::comp);
		list.forEach(System.out::println);
		
		//pass method reference to a method accepting lambda
		acceptTest(System.out::println);
		
		//using lambdas to instantiate a class
		Creator<Person> creator = obj -> new Person((String)obj[0],(Integer)obj[1],(String)obj[2],(String)obj[3]);
		Person p = creator.create("Ram",25,"Bihar","ram@yahoo.com");
		System.out.println(p);
		
		//contructor reference to create an instance using custom lambda
		Creator<Person> creator2 = Person::new;
		Person person = creator2.create("Ram",25,"Bihar","ram@yahoo.com");
		System.out.println(person);
		
		//inbuilt lambda using constructor reference 
		Supplier<Person> personSupplier = Person::new;
		Person supplierPersonRef = personSupplier.get();
		System.out.println(supplierPersonRef);
	}
	
	@FunctionalInterface
	static interface test {
		void print();
	}
	
	static void acceptTest(test t) {
		
	}
	
	//method that returns lambda as a result
	static CustomInterface returnLambda() {
		return () -> {};
	}
	
	//method that accetps lambda as an argument
	static void acceptLambda(CustomInterface lambdaParam) {
		lambdaParam.lambdaMethod();
	}

	static void forkJoinDemo() {
		myRecursiveAction action = new myRecursiveAction("/home/barya/jars");
		ForkJoinPool pool = new ForkJoinPool();
		/* pool.execute(action); */
		try {
			pool.submit(action).get().stream().forEach(System.out::println);
			System.out.println(pool.getParallelism());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static class myRecursiveAction extends RecursiveTask<List<String>> {
		private String location;

		public myRecursiveAction(String location) {
			super();
			this.location = location;
		}

		@Override
		protected List<String> compute() {
			final File[] file = { new File(location) };
			List<String> fileList = new ArrayList<>();
			List<RecursiveTask<List<String>>> actionList = new ArrayList<>();
			Arrays.asList(file[0].list())
					.stream()
					.forEach(
							(n) -> {
								if (new File(n).isDirectory()) {
									myRecursiveAction recurAction = new myRecursiveAction(
											new File(n).getAbsolutePath());
									recurAction.fork();
									actionList.add(recurAction);
								} else {
									fileList.add(n);
								}
							});
			actionList.forEach(n -> fileList.addAll(n.join()));
			return fileList;
		}
	}

	public static void curryDemo(List<Person> personList) {
		
		//simpler way using just streams
		personList.stream().map(n -> n.getName() + "@google.com").collect(Collectors.toList()).forEach(n -> System.out.println(n));
		
		//lambda for the uncurried version
		BiFunction<List<Person>, String, List<Object>> biFunc =
				(list,domain) -> list.stream().map(
						n -> n.getName() + "@google.com").collect(Collectors.toList());
				
		//calling the uncurried version, passing 2 params in the same method call		
		biFunc.apply(personList, "google").forEach(MainClass::testMethod);
		
		//lambda for the somewhat curried version
		Function<List<Person>, Function<String, List<Object>>> func = n -> 
		domain -> n.stream().map(e -> e.getName() + "@" + domain + ".com").
				collect(Collectors.toList());
		
		//calling the curried version
		func.apply(personList).apply("google").forEach(MainClass::testMethod);
		
		Consumer<String> cons = System.out::println;
		cons.accept("Hello");
	}

	public static void closureDemo() {
		int i = 10;
		String[] str = { "hi", "" };

		Runnable runn = () -> {
			if (i == 10) {
				System.out.println("The value is 10");
			} else {
				System.out.println("The value is not 10");
			}
			str[0] = str[0].concat("concat");
		};

		runn.run();
		System.out.println(str[0]);
	}

	static void testMethod(Object obj) {
		System.out.println(obj);
	}

	static void setupGraph() {

		EmployeeGraph empGraph = (EmployeeGraph) DSUtil.setUpGraph();
		// Map<IVertex<Employee>, Set<IEdge<Employee>>> map = empGraph.getMap();
		Set<Map.Entry<IVertex<Employee>, Set<IEdge<Employee>>>> set = empGraph
				.getMap().entrySet();
		for (Map.Entry<IVertex<Employee>, Set<IEdge<Employee>>> entry : set) {
			// System.out.println("from " + entry.getKey());
			Set<IEdge<Employee>> empSet = entry.getValue();
			Iterator<IEdge<Employee>> itrVal = empSet.iterator();
			while (itrVal.hasNext()) {
				List<IVertex<Employee>> list = itrVal.next().getNodes();
				// System.out.println("between  " + list.get(0) + " " +
				// list.get(list.size() -1));
				/*
				 * for (IVertex<Employee> emp : list) {
				 * System.out.println(emp.getT().getName()); }
				 */
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
		// finds the node, but still traverses unnecessary nodes
		// DSUtil.performBFS(((IGraph<Employee>)empGraph),vertexToStart,
		// vertexToFind);

		DSUtil.setupDataForDijikstra(empGraph, vertexToStart);

	}

	static List<Person> createData() {
		List<Person> personList = null;
		try {
			personList = Util.createList();
			Person amar = new Person("amar", 28, "Maharastra", "amar@yahoo.com");
			Person geeta = new Person("geeta", 16, "Gujrat", "geet@yahoo.com");
			Person nik = new Person("nik", 12, "Karnataka", "nik@gmail.com");
			Person john = new Person("john", 35, "Kerala", "john@gmail.com");
			personList.add(amar);
			personList.add(geeta);
			personList.add(nik);
			personList.add(john);
		} catch (Exception e) {
			System.out
					.println("There has been an error while creating the data set");
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
		personService.votersStream = createData().stream().filter(
				n -> n.getAge() >= 18);
		try {
			mserver.registerMBean(personService, new ObjectName(
					"com.bhargo.service.impl:type=PersonService"));
		} catch (InstanceAlreadyExistsException | MBeanRegistrationException
				| NotCompliantMBeanException | MalformedObjectNameException e) {
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
		util.providers.andThen(util.countByProviders).accept(personList,
				providers);

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
				if (person.getEmail().split("@")[1].split("\\.")[0]
						.equals(provider)) {
					personSet.add(person);
				}
			}
			emailMap.put(provider, personSet);
			System.out.println(" The email provider is " + provider
					+ ", the num of users is " + personSet.size());
			personSet.clear();
		}

	}
}
