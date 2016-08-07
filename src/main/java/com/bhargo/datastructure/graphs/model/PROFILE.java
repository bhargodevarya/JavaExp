package com.bhargo.datastructure.graphs.model;

public enum PROFILE {
	TESTER ("Tester"),DEVELOPER("Developer");
	
	PROFILE(String desc) {
		this.desc = desc;
	}
	
	public String desc;
	
	public String getDesc() {
		return desc;
	}

}
