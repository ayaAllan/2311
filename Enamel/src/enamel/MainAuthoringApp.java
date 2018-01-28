package enamel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainAuthoringApp extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		Parent root = FXMLLoader.load(getClass().getResource("ViewAuthoringApp.fxml"));
		primaryStage.setTitle("AuthoringApp v1.0.0");
		primaryStage.setScene(new Scene(root, 1000, 800));
		primaryStage.show();
	}

}