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
			Date registrationDate) {
		Client client = new Client(name, id, accountBalance, cardBalance, cardPaymentDate, registrationDate);
		databaseClients.add(client);
	}
	
	public void registerClient(String name, String id, int accountBalance, int cardBalance, Date cardPaymentDate,
			Date registrationDate, int priority) {
		Client client = new Client(name, id, accountBalance, cardBalance, cardPaymentDate, registrationDate);
		client.setPriority(priority);
		databaseClients.add(client);
	}

	public Client searchClient(Client client) {
		Client foundClient = null;
		
		//This is O(n)
		for(Client clientInDatabase: databaseClients) {
			if(clientInDatabase.equals(client)) {
				foundClient = clientInDatabase;
				break;
			}
		}
		
		return foundClient;
	}
	
	public void enqueueClient(Client client) {
		queueClients.enqueue(client);
	}

	public void enqueueSpecialAttentioClient(Client client) {
		queueSpecialAttention.enqueue(client);
	}
	
	
}
