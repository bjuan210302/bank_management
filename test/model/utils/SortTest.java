package model.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Comparator;

import org.junit.jupiter.api.Test;
import model.util.Sort;

class SortTest {

	ArrayList<Integer> actual;
	ArrayList<Integer> expected;
	Comparator<Integer> c;
	
	void setup(){
		
		actual = new ArrayList<Integer>();
		expected = new ArrayList<Integer>();
		
		for(Integer i: new Integer[]{1, 6, 17, 0, 10, 2, 2}) {
			actual.add(i);
		}
		for(Integer i: new Integer[]{0, 1, 2, 2, 6, 10, 17}) {
			expected.add(i);
		}
		
		c = new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				if(o1 < o2) {
					return -1;
				}else if(o1 == o2) {
					return 0;
				}else {
					return 1;
				}
			}
		};
	}
	@Test
	void mergeSortTest() {
		setup();
		actual = (ArrayList<Integer>) Sort.mergeSort(actual, actual.size(), c);
		
		for(int i = 0; i < actual.size(); i++) {
			assertTrue(actual.get(i) == expected.get(i));
		}
	}
	
	@Test
	void bubbleSortTest() {
		setup();
		actual = (ArrayList<Integer>) Sort.bubbleSort(actual, c);
		
		for(int i = 0; i < actual.size(); i++) {
			assertTrue(actual.get(i) == expected.get(i));
		}
	}
	
	@Test
	void heapSortTest() {
		setup();
		actual = (ArrayList<Integer>) Sort.heapSort(actual, c);
		
		for(int i = 0; i < actual.size(); i++) {
			assertTrue(actual.get(i) == expected.get(i));
		}
	}
	
	@Test
	void quickSortTest() {
		setup();
		actual = (ArrayList<Integer>) Sort.quickSort(actual, 0, actual.size()-1, c);
		
		for(int i = 0; i < actual.size(); i++) {
			assertTrue(actual.get(i) == expected.get(i));
		}
	}
	

}
