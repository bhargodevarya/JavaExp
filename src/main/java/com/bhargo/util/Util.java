/**
 *General class for util methods 
 */
package com.bhargo.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
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

	public static List<String> readFileAsListOfString(String fileName, String splitBy) throws IOException {
		File file = new File(fileName);
		BufferedReader buffer = new BufferedReader(new FileReader(file));
		String line;
		List<String> data=new ArrayList<>();
		while ((line = buffer.readLine()) != null) {
			data = Arrays.asList(line.split(splitBy));
		}
	    return data;
	}
}
