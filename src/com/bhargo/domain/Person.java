package com.bhargo.domain;

public class Person {
	
	private String name;
	private Integer age;
	private String state;
	private String email;
	
	public Person() {
		super();
	}
	
	public Person(String name, Integer age, String state, String email) {
		super();
		this.name = name;
		this.age = age;
		this.state = state;
		this.email = email;
	}
	
	public Person(Object ...objects) throws InstantiationException {
		if(objects[0] instanceof String) {
			this.name = (String)objects[0];
			if(objects[1] instanceof Integer) {
				this.age = (Integer)objects[1];
				if(objects[2] instanceof String) {
					this.state = (String)objects[2];
					if(objects[3] instanceof String) {
						this.email = (String)objects[3];
					}
				}
			}
		}
		if(name == null || age == null || state == null || email == null) {
			throw new InstantiationException();
		}
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String toString() {
		return name + " " + state + " " + email + " " + age;
	}
	
	
	public int comp(Person p) {
		return this.getAge().compareTo(p.getAge());
	}
	

}
