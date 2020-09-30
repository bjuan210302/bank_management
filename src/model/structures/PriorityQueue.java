package model.structures;

import java.util.ArrayList;

import model.exceptions.NotEnoughSpaceException;

public class PriorityQueue<T extends Comparable<T>> implements QueueInterface<T>{

	private Heap<T> heap;
	
	@SuppressWarnings("unchecked")
	public PriorityQueue(Class somt, int size) {
		heap = new Heap<T>(somt, size);
	}
	
	@Override
	public void enqueue(T element) throws NotEnoughSpaceException {
		if(!heap.add(element)) {
			throw new NotEnoughSpaceException(heap.getMaxCapacity());
		}
	}

	@Override
	public T dequeue() {
		return heap.removeTop();
	}

	@Override
	public T peek() {
		return heap.peek();
	}

	@Override
	public boolean isEmpty() {
		return heap.isEmpty();
	}

	@Override
	public int size() {
		return heap.count();
	}
	
	
	
	
}
