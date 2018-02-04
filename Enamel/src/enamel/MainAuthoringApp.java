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
	public void start(Stage mainMenuStage) throws Exception {

		/*
		 * The main menu which is linked to
		 */
		Parent root = FXMLLoader.load(getClass().getResource("MainMenuViewAA.fxml"));
		mainMenuStage.setTitle("AuthoringApp v1.0.0");
		mainMenuStage.setScene(new Scene(root, 1000, 800));
		mainMenuStage.show();

	}

}