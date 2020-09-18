package model;

import java.util.ArrayList;
import java.util.Date;

import model.structures.PriorityQueue;
import model.structures.Queue;
import model.structures.Stack;

public class Bank {

	private ArrayList<Client> formerClients;
	private ArrayList<Client> databaseClients;
	private Queue<Client> queueClients;
	private PriorityQueue<Client> queueSpecialAttention;
	
	private Stack<Action> clientActions;
	
	public Bank() {
		formerClients = new ArrayList<Client>();
		databaseClients = new ArrayList<Client>();
		queueClients = new Queue<Client>();
		queueSpecialAttention = new PriorityQueue<Client>();
	}
	
	public void registerClient(String name, String id, int accountBalance, int cardBalance, Date cardPaymentDate,
			Date registrationDate, int[] specialAttentionPoints) {
		
		int priority = 0;
		for(int i = 0; i < specialAttentionPoints.length; i++) {
			priority += specialAttentionPoints[i];
		}
		
		Client client = new Client(name, id, accountBalance, cardBalance, cardPaymentDate, registrationDate, priority);
		databaseClients.add(client);
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
	
	public void enqueueClient(Client client) {
		
		if(client.getPriority() > 0) {
			queueSpecialAttention.enqueue(client);
		}else {
			queueClients.enqueue(client);
		}
		
	}
	
	
	// CLIENTS FUNCTIONS
	
	public void depositOrWithdraw(Client client, int amount) {
		client.depositOrWithDraw(amount);
	}
	
	public void payCard(Client client, int amountToPay, boolean payWithAccountMoney) {
		
	}
	
	public void removeAccount(Client client) {
		
	}
	
	public void undoLastAction(Client client) {
		
	}
}
