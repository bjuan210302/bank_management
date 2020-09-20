package model;

import model.structures.Hashable;

public class BankAccountKey implements Hashable{

	private long accountId;
	
	
	public BankAccountKey(long accountId) {
		this.accountId = accountId;
	}

	@Override
	public int hashCode(int hashTableSize, int i) {
		return (int) ((accountId+i) % hashTableSize);
	}
	
	public long getAccountId() {
		return accountId;
	}
	
	public boolean equals(BankAccountKey key) {
		return this.accountId == key.accountId;
	}
	public boolean equals(long otherAccountId) {
		return this.accountId == otherAccountId;
	}
}
