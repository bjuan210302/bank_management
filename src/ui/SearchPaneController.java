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
    	AttendController attendController = new AttendController(bank);
    	attendController.attendWindow();

    }

    @FXML
    void searchAction(ActionEvent event) {
    	try {
    		String id = searchField.getText();
        	Client temp =bank.searchClient(id);
        	nameField.setText(temp.getName());
        	idField.setText(temp.getId());
        	numberField.setText(String.valueOf(temp.numberOfBankAccounts()));
        	dateField.setText(String.valueOf(temp.getRegistrationDate()));
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
