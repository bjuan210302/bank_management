package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application{
	
	PrincipalController principalController;
	
	public Main() {
		principalController = new PrincipalController();
	}

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mainPane.fxml"));

		fxmlLoader.setController(principalController);
		
		Parent root = fxmlLoader.load();
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Bank Management");
		primaryStage.setResizable(false);
		primaryStage.getIcons().add(new Image("file:assets/icon.png"));
		
		primaryStage.show();
		principalController.whenInitializing();
		
	}

}
