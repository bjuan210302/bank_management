package model;

import java.util.Date;

public class Action {
	
	private ActionTag actionTag;
	private Date actionDate; //Date this action was performed
	private Account bankAccount;
	private Client performer;
	
	//Values PRE action, like a backup
	private int accountBalance;
	private int cardBalance;
	private Date cardPaymentDate;
	
	public Action(Client performer, Account bankAccount, ActionTag actionTag, Date actionDate) {
		this.performer = performer;
		this.bankAccount = bankAccount;
		this.actionTag = actionTag;
		this.actionDate = actionDate;
		this.accountBalance = bankAccount.getAccountBalance();
		this.cardBalance = bankAccount.getCardBalance();
		this.cardPaymentDate = bankAccount.getCardPaymentDate();
	}
	
	public void undo() {
		bankAccount.setAccountBalance(accountBalance);
		bankAccount.setCardBalance(cardBalance);
		bankAccount.setCardPaymentDate(cardPaymentDate);
		
		if(actionTag.equals(ActionTag.TAG_REMOVE_ACC)) {
			bankAccount.setCancelReason(null);
			bankAccount.setCancelDate(null);
		}
	}
	
	public Account getBankAccount() {
		return bankAccount;
	}
	
	public ActionTag getTag() {
		return actionTag;
	}
	public void setActionTag(ActionTag actionTag) {
		this.actionTag = actionTag;
	}
	
}
