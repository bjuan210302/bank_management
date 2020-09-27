package model.structures;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

class HeapTest {

	class Node implements HasPriority{

		int val;
		
		public Node(int val) {
			this.val = val;
		}
		
		@Override
		public int getPriority() {
			return val;
		}
		
		public String toString() {
			return String.valueOf(val);
		}
	}
	
	Heap<Node> heap;
	Node[] aux;
	
	void septup7Empty() {
		heap = new Heap<Node>(Node.class, 7);
		aux = new Node[] {new Node(1), new Node(4), new Node(3), new Node(7), new Node(8), new Node(9), new Node(10)};
	}
	
	@Test
	void testAddAndIsHeap() {
		septup7Empty();
		Node[] post = new Node[] {new Node(10), new Node(7), new Node(9), new Node(1), new Node(4), new Node(3), new Node(8)};
		
		for(Node node: aux) {
			assertTrue(heap.add(node));
		}
		
		for(int i = 0; i < heap.getList().length; i++) {
			assertEquals(post[i].getPriority(), heap.getList()[i].getPriority());
		}
		
		assertTrue(heap.isHeap()); //If the assertEquals is right, this should be too
		
		//Now that the heap is full lets try to add something more
		assertFalse(heap.add(new Node(12)));
	}
	
	@Test
	void testRemove() {
		septup7Empty();
		for(Node node: aux) {
			heap.add(node);
		}
		
		//Top should be 10, then 9, then 8, 7, 4, 3, 1
		int[] temp = new int[] {10,9,8,7,4,3,1};
		
		for(int n: temp) {
			assertEquals(n, heap.removeTop().val);
			assertTrue(heap.isHeap());
		}
		
		//At this point the heap is empty, so the next remove should be null
		
		assertEquals(null, heap.removeTop());
	}

	
}
