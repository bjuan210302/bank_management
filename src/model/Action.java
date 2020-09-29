package model;

import java.time.LocalDate;

import model.exceptions.NotEnoughSpaceException;

public class Action {
	
	private ActionTag actionTag;
	private LocalDate actionDate; //Date this action was performed
	private Account bankAccount;
	private Client performer;
	
	//Values PRE action, like a backup
	private int accountBalance;
	private int cardBalance;
	private LocalDate cardPaymentDate;
	
	public Action(Client performer, Account bankAccount, ActionTag actionTag, LocalDate actionDate) {
		this.performer = performer;
		this.bankAccount = bankAccount;
		this.actionTag = actionTag;
		this.actionDate = actionDate;
		this.accountBalance = bankAccount.getAccountBalance();
		this.cardBalance = bankAccount.getCardBalance();
		this.cardPaymentDate = bankAccount.getCardPaymentDate();
	}
	
	public void undo() throws NotEnoughSpaceException {
		bankAccount.setAccountBalance(accountBalance);
		bankAccount.setCardBalance(cardBalance);
		bankAccount.setCardPaymentDate(cardPaymentDate);
		
		if(actionTag.equals(ActionTag.TAG_REMOVE_ACC)) {
			bankAccount.setCancelReason(null);
			bankAccount.setCancelDate(null);
			performer.addBankAccount(bankAccount);
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
