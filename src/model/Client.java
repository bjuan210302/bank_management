package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import model.exceptions.DebtRelatedException;
import model.exceptions.NotEnoughMoneyException;
import model.exceptions.NotEnoughSpaceException;
import model.structures.HashTable;
import model.structures.Stack;

public class Client {

	public static final int MAX_NUMBER_ACCOUNTS = 7; // Would a client need more than 7 bank accounts?
	
	private String name;
	private String id;
	private LocalDate registrationDate;
	private Stack<Action> clientActions;
	private HashTable<BankAccountKey, Account> bankAccounts;
	private int priority;
	
	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public LocalDate getRegistrationDate() {
		return registrationDate;
	}

	public Client(String name, String id, LocalDate registrationDate, int priority) {
		super();
		this.name = name;
		this.id = id;
		this.registrationDate = registrationDate;
		this.priority = priority;
		
		this.clientActions = new Stack<Action>();
		this.bankAccounts = new HashTable<BankAccountKey, Account>(MAX_NUMBER_ACCOUNTS);
	}
	
	// CLIENTS FUNCTIONS
	public void depositOrWithdraw(int amount, long bankAccountId) throws NotEnoughMoneyException {
		Account bankAccount = getBankAccount(bankAccountId);
		bankAccount.depositOrWithdraw(amount);
	}
	
	public void payCard(long bankAccountId, int amountTopay, boolean payWithAccountMoney) throws NotEnoughMoneyException, DebtRelatedException {
		Account bankAccount = getBankAccount(bankAccountId);
		bankAccount.payCard(amountTopay, payWithAccountMoney);
	}
	
	public Account removeBankAccount(long bankAccountId, String cancelReason, LocalDate cancelDate) throws DebtRelatedException {
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
	
	public long addBankAccount() throws NotEnoughSpaceException {
		if(bankAccounts.isFull()) {
			throw new NotEnoughSpaceException(MAX_NUMBER_ACCOUNTS, bankAccounts.count());
		}
		
		long accountId = Bank.BANK_ACOUNT_ID_GENERATOR.nextLong();
		BankAccountKey bankAccountKey = new BankAccountKey(accountId);
		Account account = new Account(this, bankAccountKey);
		
		bankAccounts.add(bankAccountKey, account);
		
		return accountId;
	}
	public long addBankAccount(Account bankAccount) throws NotEnoughSpaceException {
		if(bankAccounts.isFull()) {
			throw new NotEnoughSpaceException(MAX_NUMBER_ACCOUNTS, bankAccounts.count());
		}
		
		BankAccountKey bankAccountKey = bankAccount.getAccountKey();
		bankAccounts.add(bankAccountKey, bankAccount);
		
		return bankAccountKey.getAccountId();
	}
	
	public Account getBankAccount(long bankAccountId) {
		return bankAccounts.getValueOf(new BankAccountKey(bankAccountId));
	}
	
	public int numberOfBankAccounts() {
		return bankAccounts.count();
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
	
	
	
	//TEST ONLY FUNCTIONS
	
	public String toString() {
		return "n: " + name + " | d:"  + registrationDate + " | p:" + priority + "\n";
	}
}
