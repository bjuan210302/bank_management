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
	
	public boolean equals(EntityKey key) {
		return this.value == key.value;
	}
	public boolean equals(long otherAccountId) {
		return this.value == otherAccountId;
	}
}
