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

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

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
	 * Brings the user to creation setup window
	 */
	public void createScenarioButton(ActionEvent event) throws IOException {
		Parent creationSetupParent = FXMLLoader.load(getClass().getResource("CreationSetupView.fxml"));
		Scene creationSetupScene = new Scene(creationSetupParent);

		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(creationSetupScene);
		window.show();

	}

	/*
	 * On load scenario button click I have implemented a file chooser for the user
	 * to easily select which factory scenario file that they would like to use. I
	 * set it up so that it starts in the factory scenarios directory and only shows
	 * text files.
	 */
	public void loadScenarioButton() {

		JButton openButton = new JButton();
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("./FactoryScenarios"));
		chooser.setDialogTitle("Factory Scenarios Selection GUI");

		FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(openButton);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());
		}

		ScenarioParser s = new ScenarioParser(true);
		s.setScenarioFile("FactoryScenarios/" + chooser.getSelectedFile().getName());
	}

}