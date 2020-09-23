package ui;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import model.*;
import ui.notifications.Notification;

public class RegistrationPaneController {
	
	private Bank bank;
	
	public RegistrationPaneController(Bank bank) {
		this.bank = bank;
	}
	
	@FXML
    private CheckBox pregnantCB;

    @FXML
    private CheckBox thirdAgeCB;

    @FXML
    private CheckBox disabilityCB;

    @FXML
    private TextField nameField;

    @FXML
    private TextField idField;

    @FXML
    private Button registerButton;

    @FXML
    public void registerButtonAct(ActionEvent event) {
    	String name = nameField.getText();
    	String id = idField.getText();
    	int[] priority =  new int[3];
    	if(pregnantCB.isSelected()) {
    		priority[0] = 1;
    	}
    	if(thirdAgeCB.isSelected()) {
    		priority[1] = 1;
    	}
    	if(disabilityCB.isSelected()) {
    		priority[2] = 1;
    	}

    	if(name.isEmpty() || id.isEmpty() ) {
    		new Notification("Something went wrong!", "The name field and Id fill must be completed", Notification.ERROR).show();
    	}

    	else {

    		bank.registerClient(name, id, priority);
    		nameField.setText("");
    		idField.setText("");
    		pregnantCB.setSelected(false);
    		thirdAgeCB.setSelected(false);
    		disabilityCB.setSelected(false);
    		new Notification("The user was added!", "The user was successfuly added.", Notification.SUCCESS).show();

    	}


    }

}
