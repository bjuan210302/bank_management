package model.structures;

import java.util.ArrayList;
import java.util.LinkedList;

public class Queue<T> implements QueueInterface<T>{
	
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
	
	public T peek() {
		return elements.getLast();
	}
	
	public boolean isEmpty() {
		return elements.isEmpty();
	}
	
	public int size() {
		return elements.size();
	}
	
	public ArrayList<T> toArrayList(){
		return new ArrayList<T>(elements);
	}
}
