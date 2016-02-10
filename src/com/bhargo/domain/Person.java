package com.bhargo.domain;

public class Person {
	
	private String name;
	private Integer age;
	private String state;
	private String email;
	
	public Person(String name, Integer age, String state, String email) {
		super();
		this.name = name;
		this.age = age;
		this.state = state;
		this.email = email;
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
	
	

}