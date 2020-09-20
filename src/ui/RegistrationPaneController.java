package ui;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import model.*;

public class RegistrationPaneController {
	
	private Bank bank;
	
	public RegistrationPaneController(Bank bank) {
		this.bank = bank;
	}
	
	@FXML
    private CheckBox pregnantCB;

    @FXML
    private CheckBox thirdAgeCB;

    @FXML
    private CheckBox disabilityCB;

    @FXML
    private TextField nameField;

    @FXML
    private TextField idField;

    @FXML
    private Button registerButton;

    @FXML
    void registerButtonAct(ActionEvent event) {
    	System.out.println("hi");

    }

}
