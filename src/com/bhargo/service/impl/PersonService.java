package com.bhargo.service.impl;

import com.bhargo.domain.Person;
import com.bhargo.service.PersonServiceMXBean;

public class PersonService implements PersonServiceMXBean{

	Person person = new Person("Bhargo", 27, "Chandigarh", "amar@yahoo.com");
	
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
	

}
