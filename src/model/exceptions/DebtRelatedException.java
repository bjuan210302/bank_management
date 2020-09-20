package model.exceptions;

public class DebtRelatedException extends Exception {

	private long accountId;
	private int cardBalance;
	
	public DebtRelatedException(long accountId, int cardBalance) {
		super();
		this.accountId = accountId;
		this.cardBalance = cardBalance;
	}

	public long getAccountId() {
		return accountId;
	}

	public int getCardBalance() {
		return cardBalance;
	}
	
}
