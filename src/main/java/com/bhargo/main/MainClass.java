/**
 * Main Class
 */
package com.bhargo.main;

import java.io.*;
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
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.lang.reflect.InvocationTargetException;
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

import com.bhargo.datastructure.DSUtil;
import com.bhargo.datastructure.DSUtil.fruit;
import com.bhargo.datastructure.graphs.AVertex;
import com.bhargo.datastructure.graphs.EmployeeGraph;
import com.bhargo.datastructure.graphs.EmployeeVertex;
import com.bhargo.datastructure.graphs.IEdge;
import com.bhargo.datastructure.graphs.IVertex;
import com.bhargo.datastructure.graphs.model.Employee;
import com.bhargo.datastructure.lists.LLQuestions.ListQues;
import com.bhargo.datastructure.lists.UserLinkedList;
import com.bhargo.datastructure.stack.UserStack;
import com.bhargo.datastructure.tree.BinaryTree;
import com.bhargo.domain.Person;
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
        //ListQues.demoLLDelete();
        //ListQues.recursiveFind(2);
        //System.out.println(ListQues.getNthNodeFromLast(2));
        //System.out.println(ListQues.detectLoop());
        ListQues.reverseLinkedList(ListQues.createDummyLL());
    }

    private static void misc() throws Exception {
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
        //Sort.doHeapSort(arr);
        //Sort.doHeapSort(arr, 0, arr.length -1);
        //threadInterrupDemo();
        //serializationDemo();
        //cloneDemo();

    }

    private static void myBinaryTreedemo() throws Exception {
        BinaryTree<Integer> myBinaryTree = new BinaryTree<>();
        myBinaryTree.add(7);
        myBinaryTree.add(3);
        myBinaryTree.add(9);
        myBinaryTree.add(2);
        myBinaryTree.add(99);
        myBinaryTree.add(8);
        myBinaryTree.add(6);

        myBinaryTree.daisyAdd(66).daisyAdd(24).daisyAdd(11);

        //myBinaryTree.daisyAdd(10).daisyAdd(9).daisyAdd(8).daisyAdd(7).daisyAdd(6);

        //System.out.println(myBinaryTree);

        //myBinaryTree.postOrderTraversal();
        //myBinaryTree.preOrderTraversal();
        //myBinaryTree.inOrderTraversal();

    }

    static void threadInterrupDemo() throws InterruptedException {
        testObj t1 = new testObj(1);
        Thread th =new Thread() {
            @Override
            public void run() {
                synchronized (t1) {
                    try {
                        while (!Thread.interrupted()) {
                            System.out.println("not interrupted");
                            Thread.sleep(1000);
                            //this.interrupt();
                        }
                        for(int i =0;i<=3;i++) {
                            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                        }
                        System.out.println("Thread has been interrupted");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        /*Thread th = new Thread(() -> {
            synchronized (t1) {
                try {
                    while (!Thread.interrupted()) {
                        System.out.println("not interrupted");
                        Thread.sleep(1000);

                    }
                    System.out.println("Thread has been interrupted");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        */
        th.start();
        Thread.sleep(5000);
        th.interrupt();
    }
    static void externalizationDemo () throws IOException, ClassNotFoundException {
        ObjectOutputStream oos2 = new ObjectOutputStream(new FileOutputStream(new File("/home/hadoop/ser2.txt")));
        oos2.writeObject(new horse());
        oos2.close();

        ObjectInputStream ois2 = new ObjectInputStream(new FileInputStream(new File("/home/hadoop/ser2.txt")));
        horse h = (horse) ois2.readObject();
        System.out.println(h.age);
        ois2.close();
    }

    /**
     * every reference variable must implement Seriablizable
     * no such restriction in the inheritance tree.
     * if a super class implements Serializable, then child doesnot have to
     * if child does, super constructor is invoked to set the super class properties
     * @throws IOException
     * @throws ClassNotFoundException
     */
    static void serializationDemo () throws IOException, ClassNotFoundException {
        cat cat = new cat(12,"kitty",new strap("kstrap"));
        File file = new File("/home/hadoop/ser.txt");
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(cat);
        //oos.flush();
        oos.close();
        //fos.flush();
        //fos.close();


        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois =new ObjectInputStream(fis);
        cat cat2 = (cat)ois.readObject();
        ois.close();
        //oos.close();
        //fis.close();
        System.out.println(cat2);
    }

    static void classloaderDemo() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ClassLoader myClassLoader = new myClassLoader();
        Class myClass = myClassLoader.loadClass("");
        myClass.getMethod("hello").invoke(null,20);
    }

    static class myClassLoader extends ClassLoader {
        @Override
        public Class<?> loadClass(String name) throws ClassNotFoundException {
            if(name.contains("ClassNotInClassPath")) {
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>");
                //write method to create a bytestream from the class file
                //pass it the defineClass() of the Parent class to get the class Object
                // invoke resolveClass(class) to link the class

            }
            return super.loadClass(name);
        }
    }

    static class horse implements Externalizable, Cloneable {
        int age=90;

        public horse() {
        }

        @Override
        public void writeExternal(ObjectOutput out) throws IOException {
            out.write(2);
        }

        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {

            this.age=in.read();
        }
    }

    static class cat implements Serializable{
        public static final long serialVersionUID = 1L;
        transient private  int age;
        private String name;
        private strap strap;
        private static int count =90;
        private int num = 77;

        public cat(int age, String name, MainClass.strap strap) {
            this.age = age;
            this.name = name;
            this.strap = strap;
        }

        public MainClass.strap getStrap() {

            return strap;
        }

        public void setStrap(MainClass.strap strap) {
            this.strap = strap;
        }

        public cat() {
        }

        public cat(int age, String name) {

            this.age = age;
            this.name = name;
        }

        @Override
        public String toString() {
            return "cat{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    ", strap=" + strap +
                    ", count=" + count +
                    ", num=" + num  +
                    '}';
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        private void writeObject(ObjectOutputStream oos) throws IOException {
            //System.out.println("writing object");
            //oos.close();
            oos.defaultWriteObject();
            oos.writeInt(this.age);
            oos.writeObject(this.name);
            oos.writeObject(this.strap);
            oos.writeObject("this is a custom string");
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        }

        private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
            ois.defaultReadObject();
            this.age = 99;

        }
    }

    static class strap implements Serializable{
        private String brand;

        @Override
        public String toString() {
            return "strap{" +
                    "brand='" + brand + '\'' +
                    '}';
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public strap() {

        }

        public strap(String brand) {

            this.brand = brand;
        }
    }

    static void swapInt(int i, int j) {
        int[] in = new int[]{i,j};
        j = in[0];i = in[1];
    }

    static void m1(String... str) {
        System.out.println("map");
    }

    static void m1(String str) {
        System.out.println("hashmap");
    }

    /**
     * class or its super class must implement Cloneable,
     * public Object clone() must be overridden
     * reference variables, dont have to
     *  -if dont, then null, if values not given in the clone method
     *  -else values given in the clone method
     *
     * @throws CloneNotSupportedException
     */
    static void cloneDemo() throws CloneNotSupportedException {
        testObj t1 = new testObj(77);
        testObj t2 = (testObj) t1.clone();
        System.out.println(t2);
    }

    static class testObj extends horse /*implements Cloneable*/{
        int i;
        cat cat;

        public testObj(int i, MainClass.cat cat) {
            this.i = i;
            this.cat = cat;
        }

        public testObj(int i) {
            this.i = i;
        }

        public testObj() {
        }

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }

        @Override
        public String toString() {
            return "testObj{" +
                    "i=" + i +
                    "cat=" + cat +
                    '}';

        }

        @Override
        public Object clone() throws CloneNotSupportedException {
            System.out.println(">>>>>>>>");
            //return new testObj(99, new cat(77,"kitty", new strap("mystrap")));
            return super.clone();
        }

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
