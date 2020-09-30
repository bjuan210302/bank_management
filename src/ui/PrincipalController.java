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
	
	private SearchPaneController searchController;
	
	private QueueController qController;

    @FXML
    private JFXButton registerButton;

    @FXML
    private JFXButton queueButton;

    @FXML
    private JFXButton dbButton;
    
    @FXML
    private JFXButton searchButton;

    @FXML
    private Pane paneChange;
    
    @FXML
    private Pane firstPane;
    
    @FXML
    private Pane secondPane;
    
    @FXML
    private Pane thirdPane;
    
    @FXML
    private Pane fourthPane;
    
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
    	paneChange.getChildren().clear();
    	paneChange.getChildren().add(thirdPane);
    	qController.initializeTV();

    }

    @FXML
    public void registerButtonAct(ActionEvent event) {
    	
		paneChange.getChildren().clear();
		paneChange.getChildren().add(firstPane);

    }
    
    @FXML
    public void searchButtonAct(ActionEvent event) {
    	paneChange.getChildren().clear();
    	paneChange.getChildren().add(fourthPane);
    	searchController.initialize();

    }
    
    public void whenInitializing() {
    	regPane = new RegistrationPaneController(bank);
		dbController = new DataBasePaneController(bank);
		searchController = new SearchPaneController(bank);
		qController = new QueueController(bank);
		
		bank.loadUsers();
    	
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
		
		 fxmlLoader = new FXMLLoader(getClass().getResource("searchPane.fxml"));
		fxmlLoader.setController(searchController);
		try {
			fourthPane = fxmlLoader.load();
		} catch (IOException e) {
			
		}
		
		fxmlLoader = new FXMLLoader(getClass().getResource("queuePane.fxml"));
		fxmlLoader.setController(qController);
		try {
			thirdPane = fxmlLoader.load();
		} catch (IOException e) {
			
		}

    	
    }
    
  

}
