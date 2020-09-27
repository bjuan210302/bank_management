package model.structures;

import model.exceptions.NotEnoughSpaceException;

public class PriorityQueue<T extends HasPriority> implements QueueInterface<T>{

	private Heap<T> heap;
	
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
