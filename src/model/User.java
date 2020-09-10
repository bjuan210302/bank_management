package model;

import java.util.Date;

public class User {
	
	private String name;
	private String id;
	private int accountBalance;
	private int cardBalance;
	private Date cardPaymentDate;
	private Date registrationDate;
	
	private Stack<Action> actions;

	public User(String name, String id, int accountBalance, int cardBalance, Date cardPaymentDate,
			Date registrationDate) {
		super();
		this.name = name;
		this.id = id;
		this.accountBalance = accountBalance;
		this.cardBalance = cardBalance;
		this.cardPaymentDate = cardPaymentDate;
		this.registrationDate = registrationDate;
		
		actions = new Stack<Action>();
	}
	
	
}
