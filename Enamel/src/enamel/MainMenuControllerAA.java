package enamel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuControllerAA implements Initializable {

	ObservableList<String> readSpeedList = FXCollections.observableArrayList("Very Fast", "Fast", "Medium", "Slow",
			"Very Slow", "Beginner");

	@FXML
	private ChoiceBox readSpeedBox;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("View is now loaded!");
		System.out.println("This is a test");
		readSpeedBox.setValue("Beginner");
		readSpeedBox.setItems(readSpeedList);

	}

	/*
	 * Brings the user to creation setup
	 */
	public void createScenarioButton(ActionEvent event) throws IOException {
		Parent creationSetupParent = FXMLLoader.load(getClass().getResource("CreationSetupView.fxml"));
		Scene creationSetupScene = new Scene(creationSetupParent);

		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(creationSetupScene);
		window.show();

	}

}