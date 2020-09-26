package ui.attend;

import java.io.IOException;

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
    private ChoiceBox<String> accountChoice;

    @FXML
    private TextField moneyBox;

    @FXML
    private JFXButton moneyButton;

    @FXML
    private TextField payCardBox;

    @FXML
    private CheckBox payWithAccCheck;

    @FXML
    private JFXButton payCardButton;

    @FXML
    private JFXButton cancelAccButton;

    @FXML
    private JFXButton createAccButton;

    @FXML
    private JFXButton undoButton;


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
    void cancelAccAct(ActionEvent event) {

    }

    @FXML
    void createAccAct(ActionEvent event) {
    	try {
			client.addBankAccount();
		} catch (NotEnoughSpaceException e) {
			new Notification("Something went wrong!", "The client has reached the max of accounts", Notification.ERROR).show();
		}
    	

    }


    @FXML
    void moneyButAct(ActionEvent event) {

    }

    @FXML
    void payCardButAct(ActionEvent event) {

    }

    @FXML
    void undoAct(ActionEvent event) {

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
    	idField.setText(client.getId());
    	
	}

}
