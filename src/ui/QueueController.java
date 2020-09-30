package ui;

import java.net.URL;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;
import ui.attend.AttendController;
import ui.notifications.Notification;

public class QueueController implements Initializable{
	
	private Bank bank;
	
	public QueueController(Bank bank) {
		this.bank = bank;
	}
	
	@FXML
    private TableView<Client> queueTB;

    @FXML
    private TableColumn<Client, String> nameColumn;

    @FXML
    private TableColumn<Client, Long> idColumn;
    
    @FXML
    private TableView<Client> priorityQueueTB;

    @FXML
    private TableColumn<Client, String> namePQColumn;

    @FXML
    private TableColumn<Client, Long> idPQColumn;


    @FXML
    private JFXButton attendButton;

    @FXML
    public void attendAct(ActionEvent event) {
    	try {
    		AttendController attendController = new AttendController(bank, bank.getFrontQueue().getId());
    		attendController.attendWindow();
    		
    		actualizeNormalTV();
    		actualizePTV();
    	}
    	catch(NoSuchElementException e) {
    		new Notification("Something went wrong!", "There is nobody left in the queue", Notification.ERROR).show();
    	}

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}
	
	public void initializeTV() {
		ObservableList<Client> data = FXCollections.observableArrayList(bank.getQueue());
		ObservableList<Client> data2 = FXCollections.observableArrayList(bank.getClientMaxPriority());
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		
		namePQColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		idPQColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		
		
		
		queueTB.getItems().setAll(data);
		priorityQueueTB.getItems().setAll(data2);
		
		nameColumn.setSortable(false);
		idColumn.setSortable(false);
		namePQColumn.setSortable(false);
		idPQColumn.setSortable(false);
	}
	
	public void actualizeNormalTV() {
		ObservableList<Client> data = FXCollections.observableArrayList(bank.getQueue());
		queueTB.getItems().setAll(data);
	}

	public void actualizePTV() {

		ObservableList<Client> data = FXCollections.observableArrayList(bank.getClientMaxPriority());
		priorityQueueTB.getItems().setAll(data);

	}


}
