package model;

import java.util.Date;
import java.util.NoSuchElementException;

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
			Date registrationDate, int priority) {
		super();
		this.name = name;
		this.id = id;
		this.accountBalance = accountBalance;
		this.cardBalance = cardBalance;
		this.cardPaymentDate = cardPaymentDate;
		this.registrationDate = registrationDate;
		this.priority = priority;
	}
	
	public void depositOrWithDraw(int amount) {
		if(accountBalance + amount == 0) {
			//TODO: throw NotEnoughMoney exception
		}
		
		accountBalance += amount;
		
		Action action = createAction();
		action.setActionTag(ActionTag.TAG_DEPOSIT_OR_WITHDRAW);
		addAction(action);
	}
	
	private Action createAction() {
		return new Action(this, null, null); // TODO: DATE = null
	}
	
	private void addAction(Action action) {
		clientActions.push(action);
	}
	
	public void undoLastAction() throws NoSuchElementException{
		clientActions.pop().undo();
	}
	
	//GET SET EQUALS
	
	public int getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(int accountBalance) {
		this.accountBalance = accountBalance;
	}

	public int getCardBalance() {
		return cardBalance;
	}
	public void setCardBalance(int cardBalance) {
		this.cardBalance = cardBalance;
	}

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
