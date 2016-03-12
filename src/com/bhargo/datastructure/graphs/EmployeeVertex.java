package com.bhargo.datastructure.graphs;

import com.bhargo.datastructure.graphs.model.Employee;

public class EmployeeVertex extends AVertex<Employee> {
	
	public String toString() {
		return getT().getName();
	}

}
