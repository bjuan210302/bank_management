package model;

import java.util.Date;

import model.structures.Hashable;

public class Account {  
	
	private Client owner;
	private BankAccountKey accountKey;
	private int accountBalance;
	private int cardBalance;
	private Date cardPaymentDate;
	
	//In case the client cancel the account
	private String cancelReason;
	private Date cancelDate;
		
	public Account(Client owner, long accountId) {
		this.owner = owner;
		this.accountKey = new BankAccountKey(accountId);
		this.accountBalance = 0;
		this.cardBalance = 0;
		//TODO: set cardPaymentDate
	}
	
	//CLIENT FUNCTIONS
	public void depositOrWithdraw(int amount, long accountId) {
		if(accountBalance + amount < 0) {
			//TODO: throw NotEnoughMoney exception
		}
		
		Action action = createAction(ActionTag.TAG_DEPOSIT_OR_WITHDRAW);
		owner.addAction(action);
		
		accountBalance += amount;
	}
	
	public void payCard(int amountTopay, boolean payWithAccountMoney) {
		if(cardBalance >= 0) {
			//TODO: throw DebtRelatedException
		}
		
		if(payWithAccountMoney) {
			if(accountBalance - amountTopay < 0) {
				//TODO: throw NotEnoughMoney exception
			}

			Action action = createAction(ActionTag.TAG_CARDPAYMENT);
			owner.addAction(action);
			
			accountBalance -= amountTopay;
			cardBalance = 0;
			//Modify cardPaymentDate
		}else {
			Action action = createAction(ActionTag.TAG_CARDPAYMENT);
			owner.addAction(action);
			
			cardBalance = 0;
		}
	}
	
	public void removeAccount(String cancelReason, Date cancelDate) {
		if(cardBalance < 0) {
			//TODO: throw DebtRelatedException
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
		return new Action(owner, this, actionTag, null); // TODO: DATE = null
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

	public Date getCardPaymentDate() {
		return cardPaymentDate;
	}
	public void setCardPaymentDate(Date cardPaymentDate) {
		this.cardPaymentDate = cardPaymentDate;
	}

	public String getCancelReason() {
		return cancelReason;
	}
	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}

	public Date getCancelDate() {
		return cancelDate;
	}
	public void setCancelDate(Date cancelDate) {
		this.cancelDate = cancelDate;
	}

}
