package ui;



import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Bank;
import ui.attend.AttendController;
import ui.notifications.Notification;

public class SearchPaneController {
	
	private Bank bank;
	
	public SearchPaneController(Bank bank) {
		this.bank = bank;
	}
	
	@FXML
    private TextField searchField;

    @FXML
    private JFXButton searchButton;

    @FXML
    private Label nameField;

    @FXML
    private Label idField;

    @FXML
    private Label numberField;

    @FXML
    private Label dateField;

    @FXML
    private JFXButton attendButton;

    @FXML
    void attendAction(ActionEvent event) {
    	AttendController attendController = new AttendController(bank, Long.parseLong(searchField.getText()));
    	attendController.attendWindow();
    	nameField.setText("");
    	idField.setText("");
    	numberField.setText("");
    	dateField.setText("");
    	searchField.setText("");
    	attendButton.setDisable(true);

    }

    @FXML
    void searchAction(ActionEvent event) {
    	try {
    		long clientId = Long.parseLong(searchField.getText());
        	nameField.setText(bank.nameOf(clientId));
        	idField.setText(String.valueOf(clientId));
        	numberField.setText(String.valueOf(bank.numberOfBankAccountsOf(clientId)));
        	dateField.setText(String.valueOf(bank.registrationDateOf(clientId)));
        	attendButton.setDisable(false);
        	
    	}
    	catch (NullPointerException npe) {
    		new Notification("Something went wrong!", "The id is not registered", Notification.ERROR).show();
    		searchField.setText("");
		} catch (NumberFormatException e) {
			new Notification("Something went wrong!", "Please enter a valid value", Notification.ERROR).show();
			searchField.setText("");
		}
    	

    }
    
    public void initialize() {
    	attendButton.setDisable(true);
    }

}
