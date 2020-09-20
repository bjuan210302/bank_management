package model.exceptions;

public class NotEnoughMoneyException extends Exception {

	private long accountId;
	private int accountBalance;
	private int tryingToPay;
	private int result;
	
	public NotEnoughMoneyException(long accountId, int accountBalance, int tryingToPay, int result) {
		super();
		this.accountId = accountId;
		this.accountBalance = accountBalance;
		this.tryingToPay = tryingToPay;
		this.result = result;
	}

	public long getAccountId() {
		return accountId;
	}

	public int getAccountBalance() {
		return accountBalance;
	}

	public int getTryingToPay() {
		return tryingToPay;
	}

	public int getResult() {
		return result;
	}

}
