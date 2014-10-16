package group_project;

import java.util.LinkedList;

public class Queue<E> {
	
	FlizbazArrayList<E> linkedList = new FlizbazArrayList<E>();
	
	public E remove(){
		return linkedList.remove(0);
	}
	
	public E element(){
		return linkedList.get(0);		
	}
	
	public boolean add(E element){
		linkedList.add(element);
		return true;
	}
}
