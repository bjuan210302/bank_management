package model;

import model.structures.Hashable;

public class EntityKey implements Hashable{

	private long value;
	
	
	public EntityKey(long value) {
		this.value = value;
	}

	@Override
	public int hashCode(int hashTableSize, int i) {
		return (int) ((value+i) % hashTableSize);
	}
	
	public long getAccountId() {
		return value;
	}
	
	public boolean equals(Object key) {
		if(!(key instanceof EntityKey)) {
			return false;
		}
		EntityKey key2 = (EntityKey) key;
		return this.value == key2.value;
	}
	public String toString() {
		return String.valueOf(value);
	}
}
