package enamel;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class MainAuthoringApp extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage mainMenuStage) throws Exception {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("MainMenuViewAA.fxml"));

		Parent root = loader.load();
		mainMenuStage.setTitle("AuthoringApp v1.0.0");
		Scene scene = new Scene(root, 1000, 800);
		mainMenuStage.setScene(scene);
		mainMenuStage.show();

		MainMenuControllerAA controller = loader.getController();
		controller.initializeMainMenu(scene);

		visualCapabilityAlert();
	}

	private void visualCapabilityAlert() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Visual Capability Settings");
		alert.setHeaderText(
				"Please indicate whether you are visually capable or visually impaired by selecting one of the options below.");
		alert.setContentText("This setting will impact the way you view your teaching scenario simulations.");

		ButtonType vcButton = new ButtonType("Visually Capable");
		ButtonType viButton = new ButtonType("Visually Impaired");

		alert.getButtonTypes().setAll(vcButton, viButton);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == vcButton) {
			try {
				FileWriter writer = new FileWriter("SettingsAA/settingsAA.txt", false);
				writer.write("isVisuallyCapable = true");
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (result.get() == viButton) {
			try {
				FileWriter writer = new FileWriter("SettingsAA/settingsAA.txt", false);
				writer.write("isVisuallyCapable = false");
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {

		}
	}
}