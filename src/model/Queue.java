package model;

import java.util.LinkedList;

public class Queue<T> {
	
	private LinkedList<T> elements;
	
	public Queue(){
		elements = new LinkedList<T>();
	}
	
	public void enqueue(T element) {
		elements.addFirst(element);
	}
	
	public T dequeue() {
		return elements.removeLast();
	}
	
	public boolean isEmpty() {
		return elements.isEmpty();
	}
	
	public int size() {
		return elements.size();
	}
	
}
