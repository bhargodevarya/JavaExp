/**
 *General class for util methods 
 */
package com.bhargo.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;

import com.bhargo.datastructure.graphs.model.Employee;
import com.bhargo.domain.Person;

/**
 * @author Bhargo
 *
 */
public class Util<T> {
	
	public BiConsumer<List<Person>, Set<String>> providers = (list,set) -> { list.forEach((n) -> {
		set.add(
				n.getEmail().split("@")[1].split("\\.")[0] );
		});
	};
		
	public BiConsumer<List<Person>, Set<String>> countByProviders= (list,set) -> {
		Set<Person> personSet = new HashSet<Person>();
		set.forEach((n) -> {
			list.forEach((s) -> {
				if(n.equals(s.getEmail().split("@")[1].split("\\.")[0])) {
					personSet.add(s);
				}
			});
			System.out.println("The email provider is " + n + ", the num of users is " + personSet.size());
			personSet.clear();
			
		});
	};
	
	public static <T> List<T> createList(){
		return new ArrayList<T>();
	}
}
