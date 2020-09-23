package ui;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;

public class DataBasePaneController implements Initializable{
	private Bank bank;
	
    @FXML
    private TableView<Client> tableDB;

    @FXML
    private TableColumn<Client, String> nameColumn;

    @FXML
    private TableColumn<Client, String> idColumn;

    @FXML
    private TableColumn<Client, LocalDate> timeColumn;

    @FXML
    private TableColumn<Client, Double> moneyColumn;

	
	public DataBasePaneController(Bank bank) {
		this.bank = bank;
	}
	
	


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		
	}
	
	public void initializeTV() {
		ObservableList<Client> data = FXCollections.observableArrayList(bank.getDatabase());

		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		timeColumn.setCellValueFactory(new PropertyValueFactory<>("registrationDate"));

		tableDB.getItems().setAll(data);
	}

}
