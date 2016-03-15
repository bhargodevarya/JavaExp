package com.bhargo.datastructure.graphs;

import com.bhargo.datastructure.graphs.model.Employee;

public class EmployeeVertex extends AVertex<Employee> {
	
	public String toString() {
		return getT().getName();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj instanceof EmployeeVertex) {
			EmployeeVertex emp = (EmployeeVertex)obj;
			return this.getT().getName().equals(emp.getT().getName());
		} else {
			return false;
		}
	}
	
	

}
