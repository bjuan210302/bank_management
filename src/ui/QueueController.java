package ui;

import java.net.URL;
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
    private JFXButton attendButton;

    @FXML
    public void attendAct(ActionEvent event) {
    	AttendController attendController = new AttendController(bank, bank.getFrontQueue().getId());
    	attendController.attendWindow();
    	bank.normalDequeue();
    	actualizeTV();

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}
	
	public void initializeTV() {
		ObservableList<Client> data = FXCollections.observableArrayList(bank.getQueue());
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		
		queueTB.getItems().setAll(data);
	}
	
	public void actualizeTV() {
		ObservableList<Client> data = FXCollections.observableArrayList(bank.getQueue());
		queueTB.getItems().setAll(data);
	}


}
