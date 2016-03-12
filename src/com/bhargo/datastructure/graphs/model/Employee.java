package com.bhargo.datastructure.graphs.model;

public class Employee {
	
	private String name;
	private PROFILE profile;
	private TEAM team;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public PROFILE getProfile() {
		return profile;
	}
	public void setProfile(PROFILE profile) {
		this.profile = profile;
	}
	public TEAM getTeam() {
		return team;
	}
	public void setTeam(TEAM team) {
		this.team = team;
	}

}
