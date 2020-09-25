package ui.attend;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Bank;

public class AttendController {
	
	private Bank bank;
	
	public AttendController(Bank bank) {
		this.bank = bank;
	}
	@FXML
    private JFXButton finishButton;
	
	@FXML
	Stage attendWindow;

    @FXML
    void finishAction(ActionEvent event) {
    	attendWindow.close();

    	
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
	}

}
