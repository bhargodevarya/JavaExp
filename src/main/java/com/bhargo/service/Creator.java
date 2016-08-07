package com.bhargo.service;

public interface Creator<T> {
	
	T create(Object ...obj) throws Exception;

}
