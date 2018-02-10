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
import javafx.scene.control.TextField;
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

import javax.swing.JOptionPane;

public class CreationSetupController implements Initializable {

	// elements that the 'number of buttons' choice box has. Twelve is the maximum
	// buttons allowed by the ScenarioParser Class
	private ObservableList<Integer> nobList = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);

	// number of buttons choicebox
	@FXML
	private ChoiceBox<Integer> nobBox;

	@FXML
	private TextField scenarioNameTextField;

	// number of cells textbox
	@FXML
	private TextField nocTextField;

	/*
	 * Setup the creation window with the drop down window for the number of buttons
	 * the user will use throughout the teaching scenario. The default is set to 1.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		nobBox.setValue(1);
		nobBox.setItems(nobList);

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

		try {

			// get the number of cells that the user inputs and check if it is an integer,
			// if it is not go straight to catch block and a pop up error will occur
			String nocString = nocTextField.getText();
			Integer.parseInt(nocString);

			// get the scenario name and number of buttons as a string
			String primitiveScenarioName = scenarioNameTextField.getText();
			String scenarioNameString = primitiveScenarioName + ".txt";
			String nobString = Integer.toString(nobBox.getValue());

			// pop up error if the user does not type in a file name
			if (primitiveScenarioName.equals("")) {
				JOptionPane.showMessageDialog(null, "ERROR: Please enter a scenario name with at least one character.");
			} else {

				// setup the Scenario file with the number of cells and buttons as the first two
				// lines and store it in the factory scenarios folder
				List<String> setupLines = Arrays.asList("Cell " + nocString, "Button " + nobString, "");
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
				controller.initializeScenario(primitiveScenarioName, nobBox.getValue(), Integer.parseInt(nocString));

				// go to the scenario creation window
				Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
				window.setScene(mmScene);
				window.show();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
		}

	}

}
