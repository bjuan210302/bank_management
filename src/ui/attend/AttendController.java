package ui.attend;

import java.io.IOException;
import java.util.NoSuchElementException;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.*;
import model.exceptions.DebtRelatedException;
import model.exceptions.NotEnoughMoneyException;
import model.exceptions.NotEnoughSpaceException;
import ui.notifications.Notification;

public class AttendController {
	
	private Bank bank;
	private Client client;
	
	
	
	public AttendController(Bank bank, Client client) {
		this.bank = bank;
		this.client = client;
	}
	@FXML
    private JFXButton finishButton;
	
	@FXML
	Stage attendWindow;

    @FXML
    void finishAction(ActionEvent event) {
    	attendWindow.close();

    	
    }
    
    @FXML
    private ChoiceBox<Long> accountChoice;

    @FXML
    private TextField moneyBox;

    @FXML
    private JFXButton moneyButton;

    @FXML
    private TextField payCardBox;

    @FXML
    private CheckBox payWithAccCheck;
    
    @FXML
    private JFXButton cancellAllAccButton;


    @FXML
    private JFXButton payCardButton;

    @FXML
    private JFXButton cancelAccButton;

    @FXML
    private JFXButton createAccButton;

    @FXML
    private JFXButton undoButton;
    

    @FXML
    private JFXButton searchAccButton;


    @FXML
    private Label nameField;

    @FXML
    private Label idField;

    @FXML
    private Label accIDField;

    @FXML
    private Label accBalField;

    @FXML
    private Label cardBalField;
    
    @FXML
    void cancelAllAccAct(ActionEvent event) {

    }

    @FXML
    void cancelAccAct(ActionEvent event) {
    	

    }

    @FXML
    void createAccAct(ActionEvent event) {
    	try {
			client.addBankAccount();
			actualizeChoiceBox();
		} catch (NotEnoughSpaceException e) {
			new Notification("Something went wrong!", "The client has reached the max of accounts", Notification.ERROR).show();
		}
    	

    }


    @FXML
    void moneyButAct(ActionEvent event) {
    	try {
    		Account temp = client.getBankAccount(accountChoice.getSelectionModel().getSelectedItem());
        	int newBal = Integer.parseInt(moneyBox.getText());
			client.depositOrWithdraw(newBal, temp.getAccountId());
			accBalField.setText(String.valueOf(temp.getAccountBalance()));
			moneyBox.setText("");
		} catch (NotEnoughMoneyException e) {
			new Notification("Something went wrong!", "This account has not enough money to do this action", Notification.ERROR).show();
		} catch(NullPointerException e) {
			new Notification("Something went wrong!", "Please select an account", Notification.ERROR).show();
		} catch(NumberFormatException e) {
			new Notification("Something went wrong!", "Please enter a valid value in the text box", Notification.ERROR).show();
		}

    }

    @FXML
    void payCardButAct(ActionEvent event) {

    	try {
    		Account temp = client.getBankAccount(accountChoice.getSelectionModel().getSelectedItem());
        	int newBal = Integer.parseInt(payCardBox.getText());
    		client.payCard(temp.getAccountId(), newBal, payWithAccCheck.isSelected());
    		cardBalField.setText(String.valueOf(temp.getCardBalance()));
    		payCardBox.setText("");
    	} catch (NotEnoughMoneyException e) {
    		new Notification("Something went wrong!", "This account has not enough money to do this action", Notification.ERROR).show();
    	} catch (DebtRelatedException e) {
    	 	new Notification("Something went wrong!", "This card has no debt", Notification.ERROR).show();	
    	} catch(NullPointerException e) {
			new Notification("Something went wrong!", "Please select an account", Notification.ERROR).show();
		} catch(NumberFormatException e) {
			new Notification("Something went wrong!", "Please enter a valid value in the text box", Notification.ERROR).show();
		}

    }

    @FXML
    void undoAct(ActionEvent event) {
    	try {
    		client.undoLastAction().undo();
    		Account temp = client.getBankAccount(accountChoice.getSelectionModel().getSelectedItem());
    		accIDField.setText(String.valueOf(temp.getAccountId()));
    		accBalField.setText(String.valueOf(temp.getAccountBalance()));
    		cardBalField.setText(String.valueOf(temp.getCardBalance()));
    	}
    	catch (NoSuchElementException e) {
    		new Notification("Something went wrong!", "This account has not any actions left to undo", Notification.ERROR).show();
		}
    	

    }

	
	
	public void attendWindow() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("attendPane.fxml"));
    	fxmlLoader.setController(this);
    	Pane attendPane = null;
		try {
			attendPane = fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	Scene scene = new Scene(attendPane);
    	
    	attendWindow = new Stage();
    	attendWindow.setScene(scene);
    	attendWindow.setResizable(false);
    	attendWindow.initModality(Modality.APPLICATION_MODAL);
    	attendWindow.initStyle(StageStyle.UNDECORATED);
    	
    	attendWindow.show();
    	
    	nameField.setText(client.getName());
    	idField.setText(String.valueOf(client.getId()));
    	actualizeChoiceBox();
    	accountChoice.getSelectionModel().select(0);
    	
    	
    	
	}
	

    @FXML
    public void searchAccAct(ActionEvent event) {
    	Account temp = client.getBankAccount(accountChoice.getSelectionModel().getSelectedItem());
    	accIDField.setText(String.valueOf((accountChoice.getSelectionModel().getSelectedItem())));
    	accBalField.setText(String.valueOf(temp.getAccountBalance()));
    	cardBalField.setText(String.valueOf(temp.getCardBalance()));

    }
    
    public void actualizeChoiceBox() {
    	accountChoice.getItems().setAll();
    	for (Account account : client.getBankAccounts()) {
			accountChoice.getItems().add(account.getAccountId());
		}
    	accountChoice.getSelectionModel().select(0);
    	
    }

}
