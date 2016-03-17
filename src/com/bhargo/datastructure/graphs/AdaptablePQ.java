package com.bhargo.datastructure.graphs;

import java.util.PriorityQueue;

import com.bhargo.datastructure.graphs.model.Employee;

public class AdaptablePQ<E> extends PriorityQueue<E> {

	private E entity;

	public E getEntity() {
		return entity;
	}

	public void setEntity(E entity) {
		this.entity = entity;
	}

	/*public void delete(E element) {
		System.out.println("size is " + this.size());
		if (findElement(element) != null) {
			this.remove(element);
			System.out.println("deleted, size is " + this.size());
		} else {
			System.out.println("Element " + element + " doesnot exist");
		}
	}*/

	public E findElement(E element) {
		E tempEntity;
		// List<E> tempList = new ArrayList<E>();
		int maxSeeks = this.size();
		while (maxSeeks > 0) {
			tempEntity = this.poll();
			maxSeeks--;
			if (tempEntity != null) {
				if (tempEntity.equals(element)) {
					this.add(tempEntity);
					return tempEntity;
				} else {
					this.add(tempEntity);
				}
			} else {
				System.out.println("Q is empty");
			}
		}
		return null;
	}
	
	public void updateElement(E element, Integer in) {
		E elementReturned = findElement(element);
		if(elementReturned != null) {
			this.remove(element);
			((DistanceVertex<Integer, IVertex<Employee>>)element).setKey(in);
		} else {
			System.out.println("Element " + element + " does not exist");
		}
	}
	
	public boolean containsVertex(E element) {

		E tempEntity;
		// List<E> tempList = new ArrayList<E>();
		int maxSeeks = this.size();
		while (maxSeeks > 0) {
			tempEntity = this.poll();
			maxSeeks--;
			if (tempEntity != null) {
				if (tempEntity.equals(element)) {
					this.add(tempEntity);
					return true;
				} else {
					this.add(tempEntity);
				}
			} else {
				System.out.println("Q is empty");
			}
		}
		return false;
	}

/*	public void updatePQ(E element) {
		DistanceVertex<?, ?> distanceVertex = null;
		if (element instanceof DistanceVertex<?, ?>) {
			distanceVertex = (DistanceVertex<?, ?>) element;
		} else {
			System.out.println("invalid object, expecting EMployeeVertex");
			return;
		}

		E elementFound = findElement(element);
		if (elementFound != null) {
			
		} else {

		}

	}*/

}
