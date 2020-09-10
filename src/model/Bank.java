package model;

import java.util.ArrayList;

public class Bank {

	private ArrayList<User> formerClients;
	private ArrayList<User> databaseClients;
	private Queue<User> queueClients;
	private Queue<User> queueSpecialAttention;
	
	public Bank() {
		formerClients = new ArrayList<User>();
		databaseClients = new ArrayList<User>();
		queueClients = new Queue<User>();
		queueSpecialAttention = new Queue<User>();
	}
	
}
