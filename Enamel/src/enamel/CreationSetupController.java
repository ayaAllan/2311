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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
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
	private Button continueButton;

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
		Parent mmParent = FXMLLoader.load(getClass().getResource("MainMenuViewAA.fxml"));
		Scene mmScene = new Scene(mmParent);

		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(mmScene);
		window.show();
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
		String scenarioNameString = primitiveScenarioName + ".txt";
		String nobString = Integer.toString(nobBox.getValue());

		// setup the Scenario file with the number of cells and buttons as the first two
		// lines and store it in the factory scenarios folder
		List<String> setupLines = Arrays.asList("Cell " + noc, "Button " + nobString, "");
		Path file = Paths.get("./FactoryScenarios/" + scenarioNameString);
		Files.write(file, setupLines, Charset.forName("UTF-8"));

		// setup the loader with the info needed in a scenario so that it can be
		// accessed in the scene creation window
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("ScenarioCreationView.fxml"));

		// loads the next window which will be the scenario creation
		Parent mmParent = loader.load();
		Scene mmScene = new Scene(mmParent);

		// after the scene is set, forward the number of buttons, number of braille
		// cells and scenario name to the scenario creation controller
		ScenarioCreationController controller = loader.getController();
		controller.initializeScenario(primitiveScenarioName, nobBox.getValue(), noc);

		// go to the scenario creation window
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(mmScene);
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

}
