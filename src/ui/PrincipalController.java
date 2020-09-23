package ui;

import model.*;
import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class PrincipalController {
	
	private Bank bank;
	
	private RegistrationPaneController regPane;
	
	private DataBasePaneController dbController;

    @FXML
    private JFXButton registerButton;

    @FXML
    private JFXButton queueButton;

    @FXML
    private JFXButton dbButton;

    @FXML
    private Pane paneChange;
    
    @FXML
    private Pane firstPane;
    
    @FXML
    private Pane secondPane;
    
    public PrincipalController() {
		this.bank = new Bank();
		
	}

    @FXML
    public void dbButtonAct(ActionEvent event) {
    	
		paneChange.getChildren().clear();
		paneChange.getChildren().add(secondPane);
		dbController.initializeTV();
		

    }

    @FXML
    public void queueButtonAct(ActionEvent event) {

    }

    @FXML
    public void registerButtonAct(ActionEvent event) {
    	
		paneChange.getChildren().clear();
		paneChange.getChildren().add(firstPane);

    }
    
    public void whenInitializing() {
    	regPane = new RegistrationPaneController(bank);
		dbController = new DataBasePaneController(bank);
		//bank.loadUsers();
    	
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("registerPane.fxml"));
		fxmlLoader.setController(regPane);
		try {
			firstPane = fxmlLoader.load();
		} catch (IOException e) {
			
		}
		
		paneChange.getChildren().add(firstPane);
    	
    	fxmlLoader = new FXMLLoader(getClass().getResource("dbPane.fxml"));
		fxmlLoader.setController(dbController);
		try {
			secondPane = fxmlLoader.load();
		} catch (IOException e) {
			
		}

    	
    }
    
  

}
