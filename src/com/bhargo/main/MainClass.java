/**
 * Main Class
 */
package com.bhargo.main;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.bhargo.domain.Person;
import com.bhargo.util.Util;

/**
 * @author Bhargo
 *
 */
public class MainClass {
	
	static Util<Person> util = new Util<Person>(); 
	
	static List<Person> createData() {
		List<Person> personList = null;
		try {
			personList = util.createList();
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

	public static void main(String args[]) {
		//To show user count for an email service	
		//traditionalWay();
		lambdaWay();
	}
	
	
	static void lambdaWay() {
		List<Person> personList = createData();
		Set<String> providers = new HashSet<String>();
		util.providers.andThen(util.countByProviders).accept(personList, providers);
		
	}
	
	static void traditionalWay() {

		List<Person> personList = createData();
		
		Set<Person> personSet = new HashSet<Person>();
		Map<String, Set<Person>> emailMap = new HashMap<String, Set<Person>>();
		Set<String> providers = new HashSet<String>();
		
		//1. get all distinct email providers
		for (Person person : personList) {
			String email = person.getEmail();
			String provider = email.split("@")[1].split("\\.")[0];
			providers.add(provider);
		}
		
		Iterator<String> itr = providers.iterator();
		while(itr.hasNext()) {
			String provider = itr.next();
			for (Person person : personList) {
				if(person.getEmail().split("@")[1].split("\\.")[0].equals(provider)) {
					personSet.add(person);
				}
			}
			emailMap.put(provider, personSet);
			System.out.println(" The email provider is " + provider + ", the num of users is " + personSet.size());
			personSet.clear();
		}
	
		
	}
}
