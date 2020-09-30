package ui;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Bank;
import model.Client;
import model.util.Array2ArrayList;
import model.util.Sort;

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
    
    @FXML
    private ChoiceBox<String> sortChoice;

    @FXML
    private JFXButton sortButton;

    

	
	public DataBasePaneController(Bank bank) {
		this.bank = bank;
	}
	
	@FXML
    void sortAct(ActionEvent event) {
		ArrayList<Client> aux = new ArrayList<>();
		if(sortChoice.getSelectionModel().getSelectedItem().equals("Name")) {
			Comparable [] temp = Array2ArrayList.toArray(bank.getDatabase());
			temp = Sort.bubbleSort(temp);
			aux = Array2ArrayList.toArraylist(temp);
			actualizeTV(aux);
		}
		
		else if(sortChoice.getSelectionModel().getSelectedItem().equals("ID")) {
			Comparable [] temp = Array2ArrayList.toArray(bank.getDatabase());
			temp = Sort.heapSort(temp);
			aux = Array2ArrayList.toArraylist(temp);
			actualizeTV(aux);
		}
		
		else if(sortChoice.getSelectionModel().getSelectedItem().equals("Time of vinculation")) {
			Comparable [] temp = Array2ArrayList.toArray(bank.getDatabase());
			temp = Sort.mergeSort(temp, temp.length);
			aux = Array2ArrayList.toArraylist(temp);
			actualizeTV(aux);
		}
		
		else if(sortChoice.getSelectionModel().getSelectedItem().equals("Money")) {
			
		}

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
		
		nameColumn.setSortable(false);
		idColumn.setSortable(false);
		timeColumn.setSortable(false);
		moneyColumn.setSortable(false);
		sortChoice.getItems().add("Name");
		sortChoice.getItems().add("ID");
		sortChoice.getItems().add("Time of vinculation");
		sortChoice.getItems().add("Money");
		
	}
	
	public void actualizeTV(ArrayList<Client> clients) {
		ObservableList<Client> data = FXCollections.observableArrayList(clients);
		tableDB.getItems().setAll(data);
	}

}
