package model.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Sort {
	
	public static<T> List<T> heapSort(List<T> arr, Comparator<T> c) {
		int n = arr.size(); 
		  
        // Build heap (rearrange array) 
        for (int i = n / 2 - 1; i >= 0; i--) 
        	heapify(arr, n, i, c); 
  
        // One by one extract an element from heap 
        for (int i = n-1; i > 0; i--) 
        { 
            // Move current root to end 
            T temp = arr.get(0); 
            arr.set(0, arr.get(i));
            arr.set(i, temp);
  
            // call max heapify on the reduced heap 
            heapify(arr, i, 0, c); 
        } 
        
		return arr;
	}
	
	private static<T> void heapify(List<T> arr, int heapSize, int i, Comparator<T> c) {
		int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
  
        // If left child is larger than root 
        if (left < heapSize && arr.get(left) != null && c.compare(arr.get(left), arr.get(largest)) > 0) {
            largest = left; 
        }
        // If right child is larger than largest
        if (right < heapSize && arr.get(right) != null && c.compare(arr.get(right), arr.get(largest)) > 0) {
            largest = right; 
        }
        
        // If largest is not root 
        if (largest != i) { 
        	T swap = arr.get(i); 
        	arr.set(i, arr.get(largest));
            arr.set(largest, swap);
            heapify(arr, heapSize, largest, c); 
        }
	}
	
	public static<T> List<T> mergeSort(List<T> arr, int n, Comparator<T> c) {
		if (n < 2) {
	        return arr;
	    }
	    int mid = n / 2;
	    ArrayList<T> l = new ArrayList<T>();
	    ensureSize(l, mid);
	    ArrayList<T> r = new ArrayList<T>();
	    ensureSize(r, n-mid);
	 
	    for (int i = 0; i < mid; i++) {
	    	l.set(i, arr.get(i));
	    }
	    for (int i = mid; i < n; i++) {
	    	r.set(i-mid, arr.get(i));
	    }
	    
	    mergeSort(l, mid, c);
	    mergeSort(r, n - mid, c);
	 
		return merge(arr, l, r, mid, n - mid, c);
	}
	private static<T> List<T> merge(List<T> a, List<T> l, List<T> r, int left, int right, Comparator<T> c) {
		int i = 0, j = 0, k = 0;
		
		while (i < left && j < right) {
	        if (c.compare(l.get(i), r.get(j)) <= 0) {
	        	a.set(k++, l.get(i++));
	        }
	        else {
	        	a.set(k++, r.get(j++));
	        }
	    }
		
		while (i < left) {
			a.set(k++, l.get(i++));
	    }
	    while (j < right) {
	    	a.set(k++, r.get(j++));
	    }
	    
	    return a;
	}
	private static void ensureSize(ArrayList<?> list, int size) {
	    list.ensureCapacity(size);
	    while (list.size() < size) {
	        list.add(null);
	    }
	}
	
	public static<T>  List<T> quickSort(List<T> arr, Comparator<T> c) {
		return arr;
	}
	
	public static<T> List<T> bubbleSort(List<T> arr, Comparator<T> c) {
		boolean sorted = false;
	    T temp;
	    while (!sorted) {
	        sorted = true;
	        for (int i = 0; i < arr.size() - 1; i++) {
	            if (c.compare(arr.get(i), arr.get(i+1)) > 0) {
	                temp = arr.get(i);
	                arr.set(i, arr.get(i+1));
	                arr.set(i+1, temp);
	                sorted = false;
	            }
	        }
	    }
		return arr;
	}
}
