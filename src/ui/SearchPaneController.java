package ui;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.*;
import ui.attend.AttendController;
import ui.notifications.Notification;

public class SearchPaneController {
	
	private Bank bank;
	
	private Client client = null;
	
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
    	AttendController attendController = new AttendController(bank, client);
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
    		String id = searchField.getText();
        	client =bank.searchClient(id);
        	nameField.setText(client.getName());
        	idField.setText(client.getId());
        	numberField.setText(String.valueOf(client.numberOfBankAccounts()));
        	dateField.setText(String.valueOf(client.getRegistrationDate()));
        	attendButton.setDisable(false);
        	
    	}
    	catch (NullPointerException npe) {
    		new Notification("Something went wrong!", "The id is not registered", Notification.ERROR).show();
		}
    	

    }
    
    public void initialize() {
    	attendButton.setDisable(true);
    }

}
