package model.structures;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.structures.HashTable;
import model.structures.Hashable;


class HashTableTest {

	class testKey implements Hashable{

		int id;
		
		public testKey(int id) {
			this.id = id;
		}
		
		@Override
		public int hashCode(int hashTableSize, int i) {
			return (id+i) % hashTableSize;
		}
		
	}
	
	HashTable<testKey, String> hashtable;
	
	public void initEmpty7Slots() {
		hashtable = new HashTable<testKey, String>(7);
	}
	public void initEmpty1Slot() {
		hashtable = new HashTable<testKey, String>(1);
	}
	
	@Test
	void testAdd() {
		initEmpty7Slots();
		testKey key1 = new testKey(1234);
		String val1 = "food";
		assertTrue(hashtable.add(key1, val1));
		assertEquals(hashtable.getValueOf(key1), val1);
		
		//Updating a value
		String val2 = "apple";
		assertTrue(hashtable.add(key1, val2));
		assertEquals(hashtable.getValueOf(key1), val2);
	}

	@Test
	void testRemove() {
		initEmpty7Slots();
		testKey key1 = new testKey(1234);
		String val1 = "food";
		hashtable.add(key1, val1);
		assertEquals(hashtable.remove(key1), val1);
		assertEquals(hashtable.remove(key1), null);//Trying to remove it again;
		
		initEmpty7Slots();
		testKey key2 = new testKey(12345);
		String val2 = "apple";
		
		hashtable.add(key1, val1);
		hashtable.add(key2, val2);
		//Remove a pair should not affect another
		hashtable.remove(key1);
		assertEquals(hashtable.getValueOf(key2), val2); //Checking if deleting the other we don't affect this one
		assertEquals(hashtable.getValueOf(key1), null); //Key1 shouldn't exist in the table
	}
	
	@Test
	void testGetValueOf() {
		initEmpty7Slots();
		testKey key1 = new testKey(1234);
		String val1 = "food";
		hashtable.add(key1, val1);
		assertEquals(hashtable.getValueOf(key1), val1);
		
		testKey key2 = new testKey(112304);//Not added key
		assertEquals(hashtable.getValueOf(key2), null);
	}
	
	@Test
	void testIsEmpty() {
		initEmpty7Slots();
		assertTrue(hashtable.isEmpty());
		testKey key1 = new testKey(1234);
		String val1 = "food";
		hashtable.add(key1, val1);
		assertFalse(hashtable.isEmpty());
	}
	
	@Test
	void testIsFull() {
		initEmpty1Slot();
		testKey key1 = new testKey(1234);
		String val1 = "food";
		hashtable.add(key1, val1);
		assertTrue(hashtable.isFull());
		hashtable.remove(key1);
		assertFalse(hashtable.isFull());
	}
}
