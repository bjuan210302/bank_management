package model.structures;

import java.util.NoSuchElementException;

public interface QueueInterface<T> {

	public void enqueue(T element);
	public T dequeue() throws NoSuchElementException;
	public T peek() throws NoSuchElementException;
	public boolean isEmpty();
	public int size();
	
}
