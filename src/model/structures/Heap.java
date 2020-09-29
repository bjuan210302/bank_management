package model.structures;

import java.lang.reflect.Array;

public class Heap<T extends Comparable<T>> {

	private T[] list;
	private int heapSize;
	
	@SuppressWarnings("unchecked")
	public Heap(Class<T> c, int size) {
        this.list = (T[]) Array.newInstance(c, size);
        this.heapSize = 0;
    }
	
	public boolean add(T elem) {
		if(heapSize < list.length){
			heapSize++;
		    list[heapSize-1] = elem;
		    heapifyAdd(heapSize-1);
		    return true;
		}
		return false;
	}
	
	private void heapifyAdd (int newNodeIndex){
		int parent = (newNodeIndex - 1) / 2;
		if (parent != newNodeIndex) {
	        if (list[newNodeIndex].compareTo(list[parent]) > 0) { 
	            swap(parent, newNodeIndex); 
	            heapifyAdd(parent); 
	        }
	    } 
	 }

	public T removeTop() {
		if(heapSize > 1) {
			T top = list[0];
			T last = list[heapSize-1];
			list[heapSize-1] = null;
			list[0] = last;
			heapSize--;
			heapifyRemove(0);
			return top;
		}else if(heapSize == 1) {
			T top = list[0];
			list[0] = null;
			return top;
		}
		return null;
	}
	
	private void heapifyRemove(int i) {
		int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
  
        // If left child is larger than root 
        if (left < heapSize && list[left] != null && list[left].compareTo(list[largest]) > 0) {
            largest = left; 
        }
        // If right child is larger than largest
        if (right < heapSize && list[right] != null && list[right].compareTo(list[largest]) > 0) {
            largest = right; 
        }
        
        // If largest is not root 
        if (largest != i) { 
            swap(i, largest);
            heapifyRemove(largest); 
        }
	}
	
	private void swap(int i, int j) {
		T aux = list[i];
		list[i] = list[j];
		list[j] = aux;
	}
	
	public T peek() {
		return list[0];
	}
	public boolean isHeap() {
		return isHeap(0);
	}
	private boolean isHeap(int parent) {
		int left = parent*2 + 1;
		int right = parent*2 + 2;
		
		if(left >= heapSize && right >= heapSize) {
			return true;
		}
		
		boolean leftIsTrue = false;
		boolean rightIsTrue = false;
		
		// checking left
		if(left >= heapSize) {
			leftIsTrue = true;
		}else if(list[left].compareTo(list[parent]) <= 0) {
			leftIsTrue = true;
		}
		
		// checking right just if left is true
		if(right >= heapSize) {
			rightIsTrue = true;
		}else if(leftIsTrue && (list[right].compareTo(list[parent]) <= 0) ) {
			rightIsTrue = true;
		}
		
		return leftIsTrue && rightIsTrue && isHeap(left) && isHeap(right);
	}
	
	public boolean isEmpty() {
		return heapSize == 0;
	}
	public int count() {
		return heapSize;
	}
	public int getMaxCapacity() {
		return list.length;
	}
	public T[] getList() {
		return list;
	}
}
