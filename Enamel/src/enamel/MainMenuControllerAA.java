package enamel;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MainMenuControllerAA implements Initializable {

	final KeyCombination keyCombCreateScenario = new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN);
	final KeyCombination keyCombLoadScenario = new KeyCodeCombination(KeyCode.L, KeyCombination.CONTROL_DOWN);
	final KeyCombination keyCombEditScenario = new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN);
	final KeyCombination keyCombSampleScenarios = new KeyCodeCombination(KeyCode.T, KeyCombination.CONTROL_DOWN);
	final KeyCombination keyCombUserManual = new KeyCodeCombination(KeyCode.M, KeyCombination.CONTROL_DOWN);

	@FXML
	Button csButton, lsButton, esButton, ssButton, umButton;

	private Boolean isVisuallyCapable;

	// initialize the main menu with the choice box containing the reading speed
	// options. The default is set to begginer
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// delete the temp file that was used for testing a scenario during
		// editing/creation
		File tempPreviewFile = new File("./FactoryScenarios/previewScenarioFile_3DadC6WjBKgYCbsm.txt");
		if (tempPreviewFile.exists() && !tempPreviewFile.isDirectory()) {
			tempPreviewFile.delete();
		}

	}

	public void initializeMainMenu(Scene scene) {

		scene.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler() {

			@Override
			public void handle(Event arg0) {
				if (keyCombCreateScenario.match((KeyEvent) arg0)) {
					csButton.fire();
				} else if (keyCombLoadScenario.match((KeyEvent) arg0)) {
					lsButton.fire();
				} else if (keyCombEditScenario.match((KeyEvent) arg0)) {
					esButton.fire();
				} else if (keyCombSampleScenarios.match((KeyEvent) arg0)) {
					ssButton.fire();
				} else if (keyCombUserManual.match((KeyEvent) arg0)) {
					umButton.fire();
				}
			}
		});

	}

	/*
	 * Brings the user to creation setup window
	 */
	public void createScenarioButton(ActionEvent event) throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("CreationSetupView.fxml"));
		Parent creationSetupParent = loader.load();

		Scene creationSetupScene = new Scene(creationSetupParent);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(creationSetupScene);
		window.show();

		CreationSetupController controller = loader.getController();
		controller.initializeCreationSetup(creationSetupScene);
	}

	/*
	 * Brings the user to the edit scenario introduction
	 */
	public void editScenarioButton(ActionEvent event) throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("EditScenarioIntroView.fxml"));
		Parent editScenarioSetupParent = loader.load();

		Scene editScenarioSetupScene = new Scene(editScenarioSetupParent);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(editScenarioSetupScene);
		window.show();

		EditScenarioIntroController controller = loader.getController();
		controller.initializeEditIntro(editScenarioSetupScene);
	}

	/*
	 * Brings the user to the edit scenario introduction
	 */
	public void sampleScenarioButton(ActionEvent event) throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("SampleScenarioView.fxml"));
		Parent sampleScenarioParent = loader.load();

		Scene sampleScenarioScene = new Scene(sampleScenarioParent);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(sampleScenarioScene);
		window.show();

		SampleScenarioController controller = loader.getController();
		controller.initializeSampleScenarioController(sampleScenarioScene);
	}

	/*
	 * When the user clicks the load scenario button they will be prompted with a
	 * file chooser so that they can easily select which factory scenario file that
	 * they would like to use. I set it up so that it starts in the factory
	 * scenarios directory and only shows text files.
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

		try {
			FileReader reader = new FileReader("SettingsAA/settingsAA.txt");
			BufferedReader bufferedReader = new BufferedReader(reader);

			String visualCapabilitySetting = bufferedReader.readLine();

			if (visualCapabilitySetting.equals("isVisuallyCapable = true")) {
				isVisuallyCapable = true;
			} else if (visualCapabilitySetting.equals("isVisuallyCapable = false")) {
				isVisuallyCapable = false;
			} else {
				// means error, just set to default for now
				isVisuallyCapable = true;
			}

			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// code prof gave us to run in seperate thread to aviod crashing
		Thread starterCodeThread = new Thread("Starter Code Thread") {
			public void run() {
				try {
					ScenarioParser s = new ScenarioParser(isVisuallyCapable);
					s.setScenarioFile("FactoryScenarios/" + chooser.getSelectedFile().getName());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		starterCodeThread.start();
	}
	
	public void userManualOnClick() {
		if (Desktop.isDesktopSupported()) {
		    try {
		        File myFile = new File("./Documentation/EECS 2311 User Manual.pdf");
		        Desktop.getDesktop().open(myFile);
		    } catch (IOException ex) {
		        // no application registered for PDFs
		    }
		}
	}
}