package com.bhargo.datastructure.graphs.model;

public enum TEAM {
	EMRLD("Emerald"), DMND("Diamond"), PERL("Pearl"), SAPIR("Sapphire"), RUBY("Ruby");
	
	String name;
	
	TEAM(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

}
