package model.structures;

import java.util.ArrayList;

public class HashTable<K extends Hashable, V> {

	class Node<K2, V2>{
		private K2 key;
		private V2 value;
		
		public Node(K2 key, V2 value) {
			this.key = key;
			this.value = value;
		}

		public K2 getKey() {
			return key;
		}

		public V2 getValue() {
			return value;
		}
		
		public boolean equalsKey(K2 key) {
			return this.key.equals(key);
		}
	}
	
	private Node<K, V>[] bucket;
	private int size;
	
	public HashTable(int size){
		this.size = size;
		bucket = new Node[size];
	}
	
	public boolean add(K key, V value) {
		if(isFull()) {
			return false;
		}
		
		boolean added = false;
		Node<K, V> node = new Node<K, V>(key, value);
		int i = 0;
		do {
			int index = key.hashCode(size, i);
			if(bucket[index] == null || bucket[index].equalsKey(key)) {
				//If the spot is null the new node is introduced here. If is an old version of the same node, the value is updated.
				bucket[index] = node;
				added = true;
			}else {
				i++;
			}
		}while(!added);
		
		return added;
	}
	
	public V remove(K key) {
		V removed = null;
		boolean allSpotsChecked = false;
		ArrayList<Integer> visitedIndex = new ArrayList<Integer>();
		int i = 0;
		int index = key.hashCode(size, i);
		do {
			Node<K, V> nodeInTable = bucket[index];
			visitedIndex.add(index);
			if(nodeInTable != null && nodeInTable.equalsKey(key)) {
				removed = bucket[index].getValue();
				bucket[index] = null;
			}else {
				i++;
				index = key.hashCode(size, i);
				allSpotsChecked = visitedIndex.contains(index);
			}
			
		}while(!allSpotsChecked && removed == null);
		
		return removed;
	}
	
	public V getValueOf(K key) {
		V value = null;
		boolean found = false;
		boolean allSpotsChecked = false;
		ArrayList<Integer> visitedIndex = new ArrayList<Integer>();
		int i = 0;
		int index = key.hashCode(size, i);
		do {
			
			Node<K, V> nodeInTable = bucket[index];
			visitedIndex.add(index);
			
			if(nodeInTable != null && nodeInTable.equalsKey(key)) {
				value = nodeInTable.getValue();
				found = true;
			}else {
				i++;
				index = key.hashCode(size, i);
				allSpotsChecked = visitedIndex.contains(index);
			}
			
		}while(!allSpotsChecked && !found);
		
		return value;
	}
	
	//SIZE AND CONTENT FUN
	public boolean isEmpty() {
		boolean isEmpty = true;
		
		for(int i = 0; i < size; i++) {
			if(bucket[i] != null) {
				isEmpty = false;
				break;
			}
		}
		
		return isEmpty;
	}
	
	public boolean isFull() {
		boolean isFull = true;
		
		for(int i = 0; i < size; i++) {
			if(bucket[i] == null) {
				isFull = false;
				break;
			}
		}
		
		return isFull;
	}
	
	public int getSize() {
		return size;
	}
	
	public int count() {
		int counter = 0;
		for(int i = 0; i < size; i++) {
			if(bucket[i] != null) {
				counter++;
			}
		}
		
		return counter;
	}
}
