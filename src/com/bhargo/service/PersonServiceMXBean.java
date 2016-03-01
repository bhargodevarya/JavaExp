package com.bhargo.service;

import com.bhargo.domain.Person;

public interface PersonServiceMXBean {
	
	void setPerson(Person person);
	Person getPerson();
	
	void changeCase();
	
	void collect();
	

}
