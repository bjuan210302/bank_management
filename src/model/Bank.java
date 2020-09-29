package model;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

import model.exceptions.DebtRelatedException;
import model.exceptions.NotEnoughMoneyException;
import model.exceptions.NotEnoughSpaceException;
import model.structures.HashTable;
import model.structures.PriorityQueue;
import model.structures.Queue;
import model.util.LoadClientsFromFile;

public class Bank {

	public static final Random BANK_ACOUNT_ID_GENERATOR = new Random();
	public static final int MAX_NUMBER_CLIENTS = 401; // Far from 2^8=256 and 2^9=512
	
	private ArrayList<Account> formerBankAccounts;
	private HashTable<EntityKey, Client> databaseClients;
	private Queue<Client> queueClients;
	private PriorityQueue<Client> queueSpecialAttention;
	
	public Bank() {
		formerBankAccounts = new ArrayList<Account>();
		databaseClients = new HashTable<EntityKey, Client>(MAX_NUMBER_CLIENTS);
		queueClients = new Queue<Client>();
		queueSpecialAttention = new PriorityQueue<Client>();
	}
	
	public void registerClient(String name, long id, int[] specialAttentionPoints) {
		
		int priority = 0;
		for(int i = 0; i < specialAttentionPoints.length; i++) {
			priority += specialAttentionPoints[i];
		}
		
		Client client = new Client(name, id, LocalDate.now(), priority);
		databaseClients.add(client.getUserKey(), client);
	}
	
	public Client searchClient(long id) {
		return databaseClients.getValueOf(new EntityKey(id));
	}
	
	public void enqueueClient(Client client) throws NotEnoughSpaceException {
		
		if(client.getPriority() > 0) {
			queueSpecialAttention.enqueue(client);
		}else {
			queueClients.enqueue(client);
		}
		
	}
	
	// CLIENTS FUNCTIONS
	
	public void depositOrWithdraw(long clientId, long bankAccountId, int amount) throws NotEnoughMoneyException {
		Client client = searchClient(clientId);
		client.depositOrWithdraw(amount, bankAccountId);
	}
	
	public void payCard(long clientId, long bankAccountId, int amountToPay, boolean payWithAccountMoney) throws NotEnoughMoneyException, DebtRelatedException {
		Client client = searchClient(clientId);
		client.payCard(bankAccountId, amountToPay, payWithAccountMoney);
	}
	
	public void removeAccount(long clientId, long bankAccountId, String cancelReason) throws DebtRelatedException {
		Client client = searchClient(clientId);
		Account removedBankAccount = client.removeBankAccount(bankAccountId, cancelReason, LocalDate.now());
		formerBankAccounts.add(removedBankAccount);
	}
	
	public void undoLastAction(long clientId) throws NotEnoughSpaceException {
		Client client = searchClient(clientId);
		Action action = client.undoLastAction();
		action.undo();
		if(action.getTag().equals(ActionTag.TAG_REMOVE_ACC)) {
			client.addBankAccount(action.getBankAccount());
			formerBankAccounts.remove(action.getBankAccount());
		}
	}
	
	public void addBankAccount(long clientId) throws NotEnoughSpaceException {
		Client client = searchClient(clientId);
		client.addBankAccount();
	}
	
	//AUX Functions
	public int numberOfBankAccountsOf(long clientId) {
		return searchClient(clientId).numberOfBankAccounts();
	}
	public int balanceOf(long clientId, long bankAccountId) {
		Client client = searchClient(clientId);
		return client.getBankAccount(bankAccountId).getAccountBalance();
	}
	public int cardBalanceOf(long clientId, long bankAccountId) {
		Client client = searchClient(clientId);
		return client.getBankAccount(bankAccountId).getCardBalance();
	}
	public String nameOf(long clientId) {
		return searchClient(clientId).getName();
	}
	public LocalDate registrationDateOf(long clientId) {
		return searchClient(clientId).getRegistrationDate();
	}
	public ArrayList<Account> bankAccountsOf(long clientId){
		return searchClient(clientId).getBankAccounts();
	}
	//GET SET
	
	public ArrayList<Client> getDatabase(){
		return databaseClients.toArrayList();
	}
	//TEST ONLY FUNCTIONS
	public void loadUsers() {
		try {
			LoadClientsFromFile.loadUsersTo(databaseClients);
		} catch (IOException e) {
			System.out.println("EXCEPTION FROM LOADUSERS() in Bank.java");
			System.out.println(e.getMessage());
		}
	}
}
