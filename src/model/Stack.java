package model;

import java.util.LinkedList;

public class Stack<T> {

private LinkedList<T> elements;
	
	public Stack(){
		elements = new LinkedList<T>();
	}
	
	public void push(T element) {
		elements.add(element);
	}
	
	public T pop() {
		return elements.removeLast();
	}
	
}
