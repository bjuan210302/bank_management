package model.structures;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class Stack<T> {

private LinkedList<T> elements;
	
	public Stack(){
		elements = new LinkedList<T>();
	}
	
	public void push(T element) {
		elements.add(element);
	}
	
	public T pop() throws NoSuchElementException{
		return elements.removeLast();
	}
	
}
