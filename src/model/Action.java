package model;

import java.util.Date;

public class Action {
	
	private ActionTag actionTag;
	private Date actionDate; //Date this action was performed
	
	//Values PRE action, like a backup
	private int accountBalance;
	private int cardBalance;
	
	public Action(Client performer, ActionTag actionTag, Date actionDate) {
		this.actionTag = actionTag;
		this.actionDate = actionDate;
		this.accountBalance = performer.getAccountBalance();
		this.cardBalance = performer.getCardBalance();
	}
	
	public void undo() {
		//TODO: implement this shit
	}
	
	public void setActionTag(ActionTag actionTag) {
		this.actionTag = actionTag;
	}
	
}
