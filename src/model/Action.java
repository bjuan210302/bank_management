package model;

import java.util.Date;

public class Action {
	
	private ActionTag actionTag;
	private Date actionDate; //Date this action was performed
	
	//Values PRE action, like a backup
	private int accountBalance;
	private int cardBalance;
	
	public Action(ActionTag actionTag, Date actionDate, int accountBalance, int cardBalance) {
		this.actionTag = actionTag;
		this.actionDate = actionDate;
		this.accountBalance = accountBalance;
		this.cardBalance = cardBalance;
	}
	
	
}
