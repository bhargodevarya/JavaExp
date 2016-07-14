package com.bhargo.designpattern.creational;

import java.io.Serializable;

public final class Singleton implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private static Singleton instance;
	
	private static class innerClass {
		private static final Singleton singleton = new Singleton();
	}
	
	public static Singleton getInstance() {
		return innerClass.singleton;
	}
	
	protected Object readResolve() {
		return instance;
		
	}

}
