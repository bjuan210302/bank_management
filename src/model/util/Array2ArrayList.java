package model.util;

import java.util.ArrayList;
import model.*;

public class Array2ArrayList  {
	
	@SuppressWarnings("rawtypes")
	public static ArrayList<Client> toArraylist(Comparable[] arr){
		ArrayList<Client> arr2 = new ArrayList<>();
		
		for (Comparable comparable : arr) {
			arr2.add((Client) comparable);
		}
		
		return arr2;
		
	}
	
	public static Comparable[] toArray(ArrayList<Client> arr1) {
		Object[] aux = arr1.toArray();
		Comparable[] aux2 = new Comparable[aux.length];
		for (int i = 0; i < aux2.length; i++) {
			aux2[i] = (Comparable) aux[i];
		}
		return aux2;
	}

}
