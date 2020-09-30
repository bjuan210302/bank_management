package model.util;

public class Sort {
	
	@SuppressWarnings("rawtypes")
	public static Comparable[] heapSort(Comparable[] arr) {
		int n = arr.length; 
		  
        // Build heap (rearrange array) 
        for (int i = n / 2 - 1; i >= 0; i--) 
        	heapify(arr, n, i); 
  
        // One by one extract an element from heap 
        for (int i=n-1; i>0; i--) 
        { 
            // Move current root to end 
            Comparable temp = arr[0]; 
            arr[0] = arr[i]; 
            arr[i] = temp; 
  
            // call max heapify on the reduced heap 
            heapify(arr, i, 0); 
        } 
        
		return arr;
	}
	@SuppressWarnings({ "unused", "unchecked", "rawtypes" })
	private static void heapify(Comparable arr[], int heapSize, int i) {
		int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
  
        // If left child is larger than root 
        if (left < heapSize && arr[left] != null && arr[left].compareTo(arr[largest]) > 0) {
            largest = left; 
        }
        // If right child is larger than largest
        if (right < heapSize && arr[right] != null && arr[right].compareTo(arr[largest]) > 0) {
            largest = right; 
        }
        
        // If largest is not root 
        if (largest != i) { 
        	Comparable swap = arr[i]; 
            arr[i] = arr[largest]; 
            arr[largest] = swap; 
            heapify(arr, heapSize, largest); 
        }
	}
	
	@SuppressWarnings("rawtypes")
	public static Comparable[] mergeSort(Comparable[] arr, int n) {
		if (n < 2) {
	        return arr;
	    }
	    int mid = n / 2;
	    Comparable[] l = new Comparable[mid];
	    Comparable[] r = new Comparable[n - mid];
	 
	    for (int i = 0; i < mid; i++) {
	        l[i] = arr[i];
	    }
	    for (int i = mid; i < n; i++) {
	        r[i - mid] = arr[i];
	    }
	    mergeSort(l, mid);
	    mergeSort(r, n - mid);
	 
		return merge(arr, l, r, mid, n - mid);
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static Comparable[] merge(Comparable[] a, Comparable[] l, Comparable[] r, int left, int right) {
		int i = 0, j = 0, k = 0;
		while (i < left && j < right) {
	        if (l[i].compareTo(r[j]) <= 0) {
	            a[k++] = l[i++];
	        }
	        else {
	            a[k++] = r[j++];
	        }
	    }
		
		while (i < left) {
	        a[k++] = l[i++];
	    }
	    while (j < right) {
	        a[k++] = r[j++];
	    }
	    
	    return a;
	}
	
	public static <T extends Comparable<T>>  T[] quickSort(T[] arr) {
		return arr;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Comparable[] bubbleSort(Comparable[] arr) {
		boolean sorted = false;
	    Comparable temp;
	    while (!sorted) {
	        sorted = true;
	        for (int i = 0; i < arr.length - 1; i++) {
	            if (arr[i].compareTo(arr[i+1]) > 0) {
	                temp = arr[i];
	                arr[i] = arr[i+1];
	                arr[i+1] = temp;
	                sorted = false;
	            }
	        }
	    }
		return arr;
	}
}
