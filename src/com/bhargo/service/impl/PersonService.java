package com.bhargo.service.impl;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.bhargo.domain.Person;
import com.bhargo.service.PersonServiceMXBean;

public class PersonService implements PersonServiceMXBean{

	Person person = new Person("Bhargo", 27, "Chandigarh", "amar@yahoo.com");
	
	public Stream<Person> votersStream = null;
	public static boolean collected = false;
	
	@Override
	public void setPerson(Person person) {
		// TODO Auto-generated method stub
		this.person = person;
		
	}

	@Override
	public Person getPerson() {
		// TODO Auto-generated method stub
		return person;
	}

	@Override
	public void changeCase() {
		// TODO Auto-generated method stub
		String name = person.getName().toUpperCase();
		person.setName(name);
	}

	@Override
	public void collect() {
		// TODO Auto-generated method stub
		System.out.println("called");
		if(votersStream != null) {
			collected = true;
			votersStream.collect(Collectors.toList()).forEach(n -> System.out.println(n.getName()));
		} else {
			System.out.println("Stream is null");
		}
		
	}
	

}
