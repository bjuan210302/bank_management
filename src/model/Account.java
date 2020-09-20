package model;

import java.time.LocalDate;

import model.exceptions.DebtRelatedException;
import model.exceptions.NotEnoughMoneyException;

public class Account {  
	
	private Client owner;
	private BankAccountKey accountKey;
	private int accountBalance;
	private int cardBalance;
	private LocalDate cardPaymentDate;
	
	//In case the client cancel the account
	private String cancelReason;
	private LocalDate cancelDate;
		
	public Account(Client owner, BankAccountKey accountKey) {
		this.owner = owner;
		this.accountKey = accountKey;
		this.accountBalance = 0;
		this.cardBalance = 0;
		this.cardPaymentDate = LocalDate.now();
		this.cardPaymentDate.plusMonths(1); //Pay the card a month after creating the account
		
	}
	
	//CLIENT FUNCTIONS
	public void depositOrWithdraw(int amount) throws NotEnoughMoneyException {
		if(accountBalance + amount < 0) {
			throw new NotEnoughMoneyException(getAccountId(), accountBalance, amount, accountBalance-amount);
		}
		
		Action action = createAction(ActionTag.TAG_DEPOSIT_OR_WITHDRAW);
		owner.addAction(action);
		
		accountBalance += amount;
	}
	
	public void payCard(int amountTopay, boolean payWithAccountMoney) throws NotEnoughMoneyException, DebtRelatedException {
		if(cardBalance >= 0) {
			throw new DebtRelatedException(getAccountId(), cardBalance);
		}
		
		if(payWithAccountMoney) {
			if(accountBalance - amountTopay < 0) {
				throw new NotEnoughMoneyException(getAccountId(), accountBalance, amountTopay, accountBalance-amountTopay);
			}

			Action action = createAction(ActionTag.TAG_CARDPAYMENT);
			owner.addAction(action);
			
			accountBalance -= amountTopay;
			cardBalance = 0;
			cardPaymentDate.plusMonths(1); //The card payment is due to a month after the last payment
		}else {
			Action action = createAction(ActionTag.TAG_CARDPAYMENT);
			owner.addAction(action);
			
			cardBalance = 0;
		}
	}
	
	public void removeAccount(String cancelReason, LocalDate cancelDate) throws DebtRelatedException {
		if(cardBalance < 0) {
			throw new DebtRelatedException(getAccountId(), cardBalance);
		}
		
		this.cancelReason = cancelReason;
		this.cancelDate = cancelDate;
		this.accountBalance = 0; // The money is returned to the client
		this.cardBalance = 0;
		
		Action action = createAction(ActionTag.TAG_CARDPAYMENT);
		owner.addAction(action);
	}
	
	//AUX FUNCTIONS
	private Action createAction(ActionTag actionTag) {
		return new Action(owner, this, actionTag, LocalDate.now());
	}
	
	public boolean equals(Account otherAccount) {
		return this.accountKey.equals(otherAccount.accountKey);
	}
	public boolean equals(long otherAccountId) {
		return this.accountKey.equals(otherAccountId);
	}
	
	//GET SET
	public int getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(int accountBalance) {
		this.accountBalance = accountBalance;
	}

	public int getCardBalance() {
		return cardBalance;
	}
	public void setCardBalance(int cardBalance) {
		this.cardBalance = cardBalance;
	}

	public BankAccountKey getAccountKey() {
		return accountKey;
	}
	public long getAccountId() {
		return accountKey.getAccountId();
	}
	public LocalDate getCardPaymentDate() {
		return cardPaymentDate;
	}
	public void setCardPaymentDate(LocalDate cardPaymentDate) {
		this.cardPaymentDate = cardPaymentDate;
	}

	public String getCancelReason() {
		return cancelReason;
	}
	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}

	public LocalDate getCancelDate() {
		return cancelDate;
	}
	public void setCancelDate(LocalDate cancelDate) {
		this.cancelDate = cancelDate;
	}

}
