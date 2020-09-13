package model;

import java.util.Date;

import model.structures.Stack;

public class Client {

	private String name;
	private String id;
	private int accountBalance;
	private int cardBalance;
	private Date cardPaymentDate;
	private Date registrationDate;
	private Stack<Action> clientActions;
	
	//In case the client has special attention
	private int priority;
	
	//In case the client cancel their account
	private String cancelReason;
	private Date cancelDate;
	
	public Client(String name, String id, int accountBalance, int cardBalance, Date cardPaymentDate,
			Date registrationDate) {
		super();
		this.name = name;
		this.id = id;
		this.accountBalance = accountBalance;
		this.cardBalance = cardBalance;
		this.cardPaymentDate = cardPaymentDate;
		this.registrationDate = registrationDate;
	}
	
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	public boolean equals(Client otherClient) {
		//TODO: implement 
		return false;
	}
}
