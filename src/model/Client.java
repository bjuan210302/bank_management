package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import model.exceptions.DebtRelatedException;
import model.exceptions.NotEnoughMoneyException;
import model.exceptions.NotEnoughSpaceException;
import model.structures.HasPriority;
import model.structures.HashTable;
import model.structures.Stack;

public class Client implements HasPriority{

	public static final int MAX_NUMBER_ACCOUNTS = 7; // Would a client need more than 7 bank accounts?
	
	private String name;
	private long id;
	private LocalDate registrationDate;
	private Stack<Action> clientActions;
	private HashTable<EntityKey, Account> bankAccounts;
	private int priority;
	private EntityKey userKey;

	public Client(String name, long id, LocalDate registrationDate, int priority) {
		super();
		this.name = name;
		this.id = id;
		this.registrationDate = registrationDate;
		this.priority = priority;
		
		this.clientActions = new Stack<Action>();
		this.bankAccounts = new HashTable<EntityKey, Account>(MAX_NUMBER_ACCOUNTS);
		userKey = new EntityKey(Long.valueOf(id));
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
		bankAccounts.remove(new EntityKey(bankAccountId));
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
			throw new NotEnoughSpaceException(MAX_NUMBER_ACCOUNTS);
		}
		
		long accountId = Math.abs(Bank.BANK_ACOUNT_ID_GENERATOR.nextLong());
		EntityKey bankAccountKey = new EntityKey(accountId);
		Account account = new Account(this, bankAccountKey);
		
		bankAccounts.add(bankAccountKey, account);
		
		return accountId;
	}
	public long addBankAccount(Account bankAccount) throws NotEnoughSpaceException {
		if(bankAccounts.isFull()) {
			throw new NotEnoughSpaceException(MAX_NUMBER_ACCOUNTS);
		}
		
		EntityKey bankAccountKey = bankAccount.getAccountKey();
		bankAccounts.add(bankAccountKey, bankAccount);
		
		return bankAccountKey.getAccountId();
	}
	
	public Account getBankAccount(long bankAccountId) {
		return bankAccounts.getValueOf(new EntityKey(bankAccountId));
	}
	
	public int numberOfBankAccounts() {
		return bankAccounts.count();
	}
	
	//GET SET EQUALS
	public String getName() {
		return name;
	}

	public long getId() {
		return id;
	}

	public LocalDate getRegistrationDate() {
		return registrationDate;
	}
	
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	public EntityKey getUserKey() {
		return userKey;
	}
	public boolean equals(Client otherClient) {
		return this.id == otherClient.id;
	}
	
	public ArrayList<Account> getBankAccounts(){
		return bankAccounts.toArrayList();
	}
	
	
	
	//TEST ONLY FUNCTIONS
	
	public String toString() {
		return "n: " + name + " | d:"  + registrationDate + " | p:" + priority + "\n";
	}
}
