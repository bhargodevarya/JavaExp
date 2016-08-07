package com.bhargo.service;

@FunctionalInterface
public interface CustomInterface {

	void lambdaMethod();
	
	static void staticMethod() {
		System.out.println("This is a static method");
	}
	
	default void defaultMethod() {	
		System.out.println("This is a default method");
	}
}
