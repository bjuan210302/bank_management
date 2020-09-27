package model;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

import model.exceptions.DebtRelatedException;
import model.exceptions.NotEnoughMoneyException;
import model.exceptions.NotEnoughSpaceException;
import model.structures.PriorityQueue;
import model.structures.Queue;
import model.util.LoadClientsFromFile;

public class Bank {

	public static final Random BANK_ACOUNT_ID_GENERATOR = new Random();
	
	private ArrayList<Account> formerBankAccounts;
	private ArrayList<Client> databaseClients;
	private Queue<Client> queueClients;
	private PriorityQueue<Client> queueSpecialAttention;
	
	public Bank() {
		formerBankAccounts = new ArrayList<Account>();
		databaseClients = new ArrayList<Client>();
		queueClients = new Queue<Client>();
		queueSpecialAttention = new PriorityQueue<Client>();
	}
	
	public void registerClient(String name, String id, int[] specialAttentionPoints) {
		
		int priority = 0;
		for(int i = 0; i < specialAttentionPoints.length; i++) {
			priority += specialAttentionPoints[i];
		}
		
		Client client = new Client(name, id, LocalDate.now(), priority);
		databaseClients.add(client);
	}
	
	public Client searchClient(String id) {
		Client foundClient = null;

		//This is O(n) at worst
		for(Client clientInDatabase: databaseClients) {
			if(clientInDatabase.getId().equals(id)) {
				foundClient = clientInDatabase;
				break;
			}
		}
		
		return foundClient;
	}

	public Client searchClient(Client client) {
		Client foundClient = null;
		
		//This is O(n) at worst
		for(Client clientInDatabase: databaseClients) {
			if(clientInDatabase.equals(client)) {
				foundClient = clientInDatabase;
				break;
			}
		}
		
		return foundClient;
	}
	
	public void enqueueClient(Client client) throws NotEnoughSpaceException {
		
		if(client.getPriority() > 0) {
			queueSpecialAttention.enqueue(client);
		}else {
			queueClients.enqueue(client);
		}
		
	}
	
	// CLIENTS FUNCTIONS
	
	public void depositOrWithdraw(Client client, long bankAccountId, int amount) throws NotEnoughMoneyException {
		client.depositOrWithdraw(amount, bankAccountId);
	}
	
	public void payCard(Client client, long bankAccountId, int amountToPay, boolean payWithAccountMoney) throws NotEnoughMoneyException, DebtRelatedException {
		client.payCard(bankAccountId, amountToPay, payWithAccountMoney);
	}
	
	public void removeAccount(Client client, long bankAccountId, String cancelReason, LocalDate cancelDate) throws DebtRelatedException {
		Account removedBankAccount = client.removeBankAccount(bankAccountId, cancelReason, cancelDate);
		formerBankAccounts.add(removedBankAccount);
	}
	
	public void undoLastAction(Client client) throws NotEnoughSpaceException {
		Action action = client.undoLastAction();
		action.undo();
		if(action.getTag().equals(ActionTag.TAG_REMOVE_ACC)) {
			client.addBankAccount(action.getBankAccount());
			formerBankAccounts.remove(action.getBankAccount());
		}
	}
	
	public void addBankAccount(Client client) throws NotEnoughSpaceException {
		client.addBankAccount();
	}
	public int numberOfBankAccounts(Client client) {
		return client.numberOfBankAccounts();
	}
	
	//GET SET
	
	public ArrayList<Client> getDatabase(){
		return databaseClients;
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
