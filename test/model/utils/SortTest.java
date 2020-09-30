package model.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import javafx.scene.control.Separator;
import model.util.Sort;

class SortTest {

	class Node implements Comparable<Node>{
		int val;
		public Node(int val) {
			this.val = val;
		}
		@Override
		public int compareTo(Node n) {
			return val - n.val;
		}
		public boolean equals(Node n) {
			return this.val == n.val;
		}
	}
	
	Node[] actual;
	Node[] expected;
	
	void setup(){
		actual = new Node[]{
				new Node(1), new Node(6), new Node(17), new Node(0), new Node(10), new Node(2), new Node(2)
				};
		expected  = new Node[]{
				new Node(0), new Node(1), new Node(2), new Node(2), new Node(6), new Node(10), new Node(17)
				};
	}
	@Test
	void mergeSortTest() {
		setup();
		actual = (Node[]) Sort.mergeSort(actual, actual.length);
		
		for(int i = 0; i < actual.length; i++) {
			assertTrue(actual[i].equals(expected[i]));
		}
	}
	
	@Test
	void bubbleSortTest() {
		setup();
		actual = (Node[]) Sort.bubbleSort(actual);
		
		for(int i = 0; i < actual.length; i++) {
			assertTrue(actual[i].equals(expected[i]));
		}
	}
	
	@Test
	void heapSortTest() {
		setup();
		actual = (Node[]) Sort.heapSort(actual);
		
		for(int i = 0; i < actual.length; i++) {
			assertTrue(actual[i].equals(expected[i]));
		}
	}

}
