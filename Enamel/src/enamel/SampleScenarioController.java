package enamel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
import javafx.scene.control.CheckBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class SampleScenarioController implements Initializable {
	@FXML
	private CheckBox visuallyCapableCheckBox;

	@FXML
	private Button s1Button, s2Button, s3Button, mmButton;

	private Boolean isVisuallyCapable;

	final KeyCombination keyCombSample1 = new KeyCodeCombination(KeyCode.DIGIT1, KeyCombination.CONTROL_DOWN);
	final KeyCombination keyCombSample2 = new KeyCodeCombination(KeyCode.DIGIT2, KeyCombination.CONTROL_DOWN);
	final KeyCombination keyCombSample3 = new KeyCodeCombination(KeyCode.DIGIT3, KeyCombination.CONTROL_DOWN);
	final KeyCombination keyCombMainMenu = new KeyCodeCombination(KeyCode.M, KeyCombination.CONTROL_DOWN);

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void initializeSampleScenarioController(Scene sampleScenarioScene) {

		sampleScenarioScene.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler() {

			@Override
			public void handle(Event arg0) {
				if (keyCombSample1.match((KeyEvent) arg0)) {
					s1Button.fire();
				} else if (keyCombSample2.match((KeyEvent) arg0)) {
					s2Button.fire();
				} else if (keyCombSample3.match((KeyEvent) arg0)) {
					s3Button.fire();
				} else if (keyCombMainMenu.match((KeyEvent) arg0)) {
					mmButton.fire();
				}
			}
		});

	}

	public void sampleScenarioButton1() {
		// check if user is visually capable and set the value in the scenario parser
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

		Thread starterCodeThread = new Thread("Starter Code Thread") {
			public void run() {
				try {
					ScenarioParser s = new ScenarioParser(isVisuallyCapable);
					s.setScenarioFile("./FactoryScenarios/SampleScenarios/Scenario_1.txt");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		starterCodeThread.start();
	}

	public void sampleScenarioButton2() {
		// check if user is visually capable and set the value in the scenario parser
		Boolean isVisuallyCapable;
		if (this.visuallyCapableCheckBox.isSelected()) {
			isVisuallyCapable = true;
		} else {
			isVisuallyCapable = false;
		}

		Thread starterCodeThread = new Thread("Starter Code Thread") {
			public void run() {
				try {
					ScenarioParser s = new ScenarioParser(isVisuallyCapable);
					s.setScenarioFile("./FactoryScenarios/SampleScenarios/Scenario_2.txt");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		starterCodeThread.start();
	}

	public void sampleScenarioButton3() {
		// check if user is visually capable and set the value in the scenario parser
		Boolean isVisuallyCapable;
		if (this.visuallyCapableCheckBox.isSelected()) {
			isVisuallyCapable = true;
		} else {
			isVisuallyCapable = false;
		}

		Thread starterCodeThread = new Thread("Starter Code Thread") {
			public void run() {
				try {
					ScenarioParser s = new ScenarioParser(isVisuallyCapable);
					s.setScenarioFile("./FactoryScenarios/SampleScenarios/Scenario_3.txt");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		starterCodeThread.start();

	}

	/*
	 * clicking this button returns the user to the main menu
	 */
	public void mainMenuButton(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("MainMenuViewAA.fxml"));
		Parent mmParent = loader.load();

		Scene mmScene = new Scene(mmParent);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(mmScene);
		window.show();

		MainMenuControllerAA controller = loader.getController();
		controller.initializeMainMenu(mmScene);
	}
}
