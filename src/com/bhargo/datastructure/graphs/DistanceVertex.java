package com.bhargo.datastructure.graphs;

import com.bhargo.datastructure.graphs.model.Employee;

public class DistanceVertex<K,V> implements Comparable<DistanceVertex<K, V>>
{

	private K key;
	private V value;
	
	public DistanceVertex(K key, V value) {
		super();
		this.key = key;
		this.value = value;
	}
	
	public DistanceVertex(V value) {
		this.value = value;
	}
	
	public K getKey() {
		return key;
	}
	public void setKey(K key) {
		this.key = key;
	}
	public V getValue() {
		return value;
	}
	public void setValue(V value) {
		this.value = value;
	}

	@Override
	public int compareTo(DistanceVertex<K, V> o) {
		return this.getKey().toString().compareTo(o.getKey().toString());
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return getKey().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj instanceof DistanceVertex) {
			K k = getKey();
			V v =getValue();
			K objK = (K)((DistanceVertex)obj).getKey();
			V objV = (V)((DistanceVertex)obj).getValue();
			if(v.equals(objV)) {
				return k.equals(objK);
			} else {
				return false;
			}			
		} else 
			return false;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getKey().toString() + " " + getValue().toString();
	}
	
	
	
	
}
