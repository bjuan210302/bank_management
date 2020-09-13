package model.structures;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.structures.Queue;

class QueueTest {
	
	private Queue<String> queue;
	
	private void setupEmptyQueue() {
		queue = new Queue<String>();
	}
	@Test
	void testDequeue() {
		setupEmptyQueue();
		String e1 = "food", e2 = "apple", e3 = "spork";
		queue.enqueue(e1);
		queue.enqueue(e2);
		queue.enqueue(e3);
		
		assertTrue(queue.dequeue().equals(e1)); //First to be added is first to be dequeued
		assertTrue(queue.dequeue().equals(e2));
		assertTrue(queue.dequeue().equals(e3)); //Last to be added is first to be dequeued
	}
	
	@Test
	void testPeek() {
		setupEmptyQueue();
		String e1 = "food", e2 = "apple";
		queue.enqueue(e1);
		queue.enqueue(e2);
		
		assertTrue(queue.peek().equals(e1));
		assertTrue(queue.dequeue().equals(e1));
		assertTrue(queue.peek().equals(e2));
	}
	
	@Test
	public void testIsEmpty() {
		setupEmptyQueue();
		assertTrue(queue.isEmpty());
		queue.enqueue("food");
		assertFalse(queue.isEmpty());
	}

	@Test
	public void testSize() {
		setupEmptyQueue();
		assertEquals(0, queue.size());
		queue.enqueue("food");
		assertEquals(1, queue.size());
		queue.enqueue("apple");
		assertEquals(2, queue.size());
		queue.enqueue("spork");
		assertEquals(3, queue.size());
	}
}
