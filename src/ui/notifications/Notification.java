package ui.notifications;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import com.jfoenix.controls.JFXTextArea;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Notification {

	public static final int SUCCESS = 1;
	public static final int ERROR = 2;
	
	@FXML
	private Label titleLabel;
	@FXML
	private JFXTextArea messageArea;
	@FXML
	private Rectangle banner;
	@FXML
	private ImageView icon;
	@FXML
    private Rectangle line;

	    
	Stage notificationStage;
	Dimension dimension;
	
	public Notification(String title, String message, int type) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("notificationGUI.fxml"));
    	fxmlLoader.setController(this);
    	AnchorPane notification = null;
		try {
			notification = fxmlLoader.load();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
    	Scene scene = new Scene(notification);

		File file = new File("");
		Paint color = new Color(0, 0, 0, 1);
		
		switch (type) {
		case SUCCESS:
			file = new File("assets/success.png");
			color = Color.rgb(183, 225, 131);
			break;
		case ERROR:
			file = new File("assets/error.png");
			color = Color.rgb(255, 121, 118);
			break;
		}

		titleLabel.setText(title);
		messageArea.setText(message);
		Image image = new Image(file.toURI().toString());
		icon.setImage(image);
		banner.setFill(color);
		line.setFill(color);
		
		notificationStage = new Stage();
		notificationStage.setScene(scene);
		notificationStage.setResizable(false);
		notificationStage.initStyle(StageStyle.UNDECORATED);
		notificationStage.setAlwaysOnTop(true);
		
		dimension = Toolkit.getDefaultToolkit().getScreenSize();
		
		double x = dimension.getWidth();
        double y = dimension.getHeight() - notification.getPrefHeight() - 40;
		notificationStage.setX(x);
		notificationStage.setY(y);
	}
	
	public void show() {
		long timeToSleep = 18;
		double delta = 10;
		
		notificationStage.show();
		new Thread(()-> {
			while(notificationStage.getX() > (dimension.getWidth() - ((AnchorPane)banner.getParent()).getPrefWidth() - 10)) {
				notificationStage.setX(notificationStage.getX() - delta);
				try {
					Thread.sleep(timeToSleep);
				} catch (InterruptedException e) {
				}
			}
			
			try {
				Thread.sleep(2500);
			} catch (InterruptedException e) {
			}
			
			while(notificationStage.getX() < dimension.getWidth()) {
				notificationStage.setX(notificationStage.getX() + delta);
				try {
					Thread.sleep(timeToSleep);
				} catch (InterruptedException e) {
				}
			}
			
			Platform.runLater(() -> notificationStage.close());
		}).start();
	}
	
}
