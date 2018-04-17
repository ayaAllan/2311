package enamel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class CreationSetupController implements Initializable {

	// elements that the 'number of buttons' choice box has. Twelve is the maximum
	// buttons allowed by the ScenarioParser Class
	private ObservableList<Integer> nobList = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
	private ObservableList<Integer> nocList = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);

	// number of buttons, number of cells
	@FXML
	private ChoiceBox<Integer> nobBox, nocBox;
	@FXML
	private TextField scenarioNameTextField;
	@FXML
	private Button continueButton, mmButton;
	
	final KeyCombination keyCombMainMenu = new KeyCodeCombination(KeyCode.M, KeyCombination.CONTROL_DOWN);
	final KeyCombination keyCombContinue = new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN);

	/*
	 * Setup the creation window with the drop down window for the number of buttons
	 * the user will use throughout the teaching scenario. The default is set to 1.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		nocBox.setValue(1);
		nocBox.setItems(nocList);
		nobBox.setValue(1);
		nobBox.setItems(nobList);

		this.continueButton.setDisable(true);
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

	/*
	 * clicking this button progresses the user to the next window to start building
	 * a teaching scenario
	 */
	public void continueButton(ActionEvent event) throws IOException {

		// get the number of cells that the user inputs and check if it is an integer,
		// if it is not go straight to catch block and a pop up error will occur
		int noc = nocBox.getValue();

		// get the scenario name and number of buttons as a string
		String primitiveScenarioName = scenarioNameTextField.getText();

		// setup the loader with the info needed in a scenario so that it can be
		// accessed in the scene creation window
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("ScenarioCreationView.fxml"));

		// loads the next window which will be the scenario creation
		Parent createScenarioParent = loader.load();
		Scene createScenarioScene = new Scene(createScenarioParent);

		// after the scene is set, forward the number of buttons, number of braille
		// cells and scenario name to the scenario creation controller
		ScenarioCreationController controller = loader.getController();
		controller.initializeScenario(primitiveScenarioName, nobBox.getValue(), noc, createScenarioScene);

		// go to the scenario creation window
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(createScenarioScene);
		window.show();

	}

	@FXML
	public void userTyped(KeyEvent event) {
		if (!scenarioNameTextField.getText().isEmpty()) {
			continueButton.setDisable(false);
		} else {
			continueButton.setDisable(true);
		}
	}

	public void initializeCreationSetup(Scene creationSetupScene) {
		creationSetupScene.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler() {

			@Override
			public void handle(Event arg0) {
				if (keyCombContinue.match((KeyEvent) arg0)) {
	                continueButton.fire();
	            } else if(keyCombMainMenu.match((KeyEvent)arg0)) {
	            	mmButton.fire();
	            }
			}
	    });
		
	}

}
