package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.NoSuchElementException;

import model.structures.Stack;

public class Client {

	private String name;
	private String id;
	private Date registrationDate;
	private Stack<Action> clientActions;
	private ArrayList<Account> bankAccounts;
	private int priority;
	
	public Client(String name, String id, int accountBalance, int cardBalance, Date cardPaymentDate,
			Date registrationDate, int priority) {
		super();
		this.name = name;
		this.id = id;
		this.registrationDate = registrationDate;
		this.priority = priority;
		
		this.clientActions = new Stack<Action>();
		this.bankAccounts = new ArrayList<Account>();
	}
	
	// CLIENTS FUNCTIONS
	public void depositOrWithdraw(int amount, long bankAccountId) {
		Account bankAccount = getBankAccount(bankAccountId);
		bankAccount.depositOrWithdraw(amount, bankAccountId);
	}
	
	public void payCard(long bankAccountId, int amountTopay, boolean payWithAccountMoney) {
		Account bankAccount = getBankAccount(bankAccountId);
		bankAccount.payCard(amountTopay, payWithAccountMoney);
	}
	
	public Account removeBankAccount(long bankAccountId, String cancelReason, Date cancelDate) {
		Account bankAccount = getBankAccount(bankAccountId);
		bankAccount.removeAccount(cancelReason, cancelDate);
		bankAccounts.remove(bankAccount);
		return bankAccount;
	}
	
	public Action undoLastAction() throws NoSuchElementException{
		return clientActions.pop();
	}
	//AUX FUNCTIONS
	public void addAction(Action action) {
		clientActions.push(action);
	}
	
	public void addBankAccount(Account bankAccount) {
		
	}
	public Account getBankAccount(long bankAccountId) {
		Account aux = null;
		
		for(Account account: bankAccounts) {
			if(account.idIsEquals(bankAccountId)) {
				aux = account;
				break;
			}
		}
		
		if(aux == null) {} //TODO: Throw UnexistingBankAccountException
		
		return aux;
	}
	
	//GET SET EQUALS
	
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	public boolean equals(Client otherClient) {
		return this.id.equals(otherClient.id);
	}
}
