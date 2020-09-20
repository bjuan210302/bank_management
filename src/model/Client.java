package model;

import java.util.Date;
import java.util.NoSuchElementException;

import model.structures.HashTable;
import model.structures.Stack;

public class Client {

	public static final int MAX_NUMBER_ACCOUNTS = 7; // Would a client need more than 7 bank accounts?
	private String name;
	private String id;
	private Date registrationDate;
	private Stack<Action> clientActions;
	private HashTable<BankAccountKey, Account> bankAccounts;
	private int priority;
	
	public Client(String name, String id, Date registrationDate, int priority) {
		super();
		this.name = name;
		this.id = id;
		this.registrationDate = registrationDate;
		this.priority = priority;
		
		this.clientActions = new Stack<Action>();
		this.bankAccounts = new HashTable<BankAccountKey, Account>(MAX_NUMBER_ACCOUNTS);
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
		bankAccounts.remove(new BankAccountKey(bankAccountId));
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
		return bankAccounts.getValueOf(new BankAccountKey(bankAccountId));
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
