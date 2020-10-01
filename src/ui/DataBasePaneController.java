package ui;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
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
			aux = (ArrayList<Client>) Sort.bubbleSort(bank.getDatabase(), new Comparator<Client>() {

				@Override
				public int compare(Client o1, Client o2) {
					return o1.getName().compareTo(o2.getName());
				}
			});
			actualizeTV(aux);
		}
		
		else if(sortChoice.getSelectionModel().getSelectedItem().equals("ID")) {
			aux = (ArrayList<Client>)Sort.heapSort(bank.getDatabase(), new Comparator<Client>() {

				@Override
				public int compare(Client o1, Client o2) {
					int result = 0;
					if(o1.getId()-o2.getId()<0) {
						result = -1;
					}
					else if(o1.getId()-o2.getId()>0) {
						result = 1;
					}
					return result;
				}
				
			});
			actualizeTV(aux);
		}
		
		else if(sortChoice.getSelectionModel().getSelectedItem().equals("Time of vinculation")) {
			aux = (ArrayList<Client>) Sort.mergeSort(bank.getDatabase(), bank.getDatabase().size(), new Comparator<Client>() {

				@Override
				public int compare(Client o1, Client o2) {
					return o1.getRegistrationDate().compareTo(o2.getRegistrationDate());
				}
				
			});
			actualizeTV(aux);
		}
		
		else if(sortChoice.getSelectionModel().getSelectedItem().equals("Money")) {
			aux = (ArrayList<Client>) Sort.quickSort(bank.getDatabase(), 0, bank.getDatabase().size()-1, new Comparator<Client>() {

				@Override
				public int compare(Client o1, Client o2) {
					return Double.compare(o1.getTotalMoney(), o2.getTotalMoney());
				}
				
			});
			actualizeTV(aux);
		}

    }


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		sortChoice.getItems().add("Name");
		sortChoice.getItems().add("ID");
		sortChoice.getItems().add("Time of vinculation");
		sortChoice.getItems().add("Money");
		
		
		
	}
	
	public void initializeTV() {
		ObservableList<Client> data = FXCollections.observableArrayList(bank.getDatabase());

		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		timeColumn.setCellValueFactory(new PropertyValueFactory<>("registrationDate"));
		moneyColumn.setCellValueFactory(new PropertyValueFactory<>("totalMoney"));

		tableDB.getItems().setAll(data);
		
		nameColumn.setSortable(false);
		idColumn.setSortable(false);
		timeColumn.setSortable(false);
		moneyColumn.setSortable(false);
		
	}
	
	public void actualizeTV(ArrayList<Client> clients) {
		ObservableList<Client> data = FXCollections.observableArrayList(clients);
		tableDB.getItems().setAll(data);
	}

}
