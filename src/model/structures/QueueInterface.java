package model.structures;

import java.util.NoSuchElementException;

import model.exceptions.NotEnoughSpaceException;

public interface QueueInterface<T> {

	public void enqueue(T element) throws NotEnoughSpaceException;
	public T dequeue() throws NoSuchElementException;
	public T peek() throws NoSuchElementException;
	public boolean isEmpty();
	public int size();
	
}
