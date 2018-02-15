package enamel;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import javax.swing.JOptionPane;

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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class ScenarioCreationController implements Initializable {

	// number of buttons that will be used per scene, initialized in the
	// initializeScenario method
	private ObservableList<Integer> nobList, nocList;

	// all interactions that will be used for all scenes, initialized in the
	// initializeScenario method
	private ObservableList<String> interactionList;

	// all letters of the alphabet as options for the user to set pins, initialized
	// in the initializeScenario method
	private ObservableList<Character> letterList;

	@FXML
	private Label sceneNameLabel, scenarioNameLabel;

	@FXML
	private TextField sceneNameTextField;

	@FXML
	private TextArea questionTextArea, interactionTextArea;

	// all radio buttons that simulate the pins on the braille cell
	@FXML
	private RadioButton rb1, rb2, rb3, rb4, rb5, rb6, rb7, rb8;

	// number of buttons drop down box
	@FXML
	private ChoiceBox<Integer> nobBox, nocBox;

	@FXML
	private ChoiceBox<Character> letterBox;

	@FXML
	private ChoiceBox<String> interactionBox, listOfScenesBox;

	@FXML
	private ListView<String> sceneListView;

	@FXML
	private Button saveSceneButton;

	// create a scenario to keep track of all the scenes the user creates within the
	// same scenario
	private ScenarioAA scenario;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void revisitScenarioBuilder(ScenarioAA scenarioPassed) throws IOException {
		// when the user creates a new scene it should load this xml and controler but
		// with this new initializer, this way it can store all the previous attributes
		// TODO
	}

	public void initializeScenario(String name, int nob, int noc) throws IOException {

		// initialize a new scene and scenario, the scenario will be constructed with
		// the scenario name, number of buttons, and number of cells from the previous
		// window
		this.scenario = new ScenarioAA(name, nob, noc);

		// change the text in the top left corner to the scenario name
		scenarioNameLabel.setText(name);

		// create a list of buttons dependent on what the user inputed in the previous
		// window
		List<Integer> buttonsList = new ArrayList<Integer>();
		for (int i = 1; i <= nob; i++) {
			buttonsList.add(i);
		}

		// create a list of cells dependent on noc
		List<Integer> brailleCellList = new ArrayList<Integer>();
		for (int i = 1; i <= noc; i++) {
			brailleCellList.add(i);
		}

		// initialize the number of buttons drop down menu
		nobList = FXCollections.observableArrayList(buttonsList);
		nobBox.setValue(1);
		nobBox.setItems(nobList);

		// initialize the number of cells drop down menu
		nocList = FXCollections.observableArrayList(brailleCellList);
		nocBox.setValue(1);
		nocBox.setItems(nocList);

		// initialize the interactions drop down menu
		interactionList = FXCollections.observableArrayList("No Interaction", "Play Correct Audio Clip",
				"Play Wrong Audio Clip", "Repeat Question/Comment", "Skip Question/Comment");
		interactionBox.setValue("No Interaction");
		interactionBox.setItems(interactionList);

		// initialize the letters of the alphabet drop down menu
		letterList = FXCollections.observableArrayList('-', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
				'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z');
		letterBox.setValue('-');
		letterBox.setItems(letterList);

		// cant save a scene that hasnt been created yet
		saveSceneButton.setDisable(true);

	}

	public void addTextButton(ActionEvent event) throws IOException {

	}

	public void recordAudioButton(ActionEvent event) throws IOException {
		// TODO
	}

	public void setPinsButton(ActionEvent event) throws IOException, InterruptedException {

		// change the current scenes braille cells pins to the character that the user
		// enters
		BrailleCell cell = this.scenario.getCurrentScene().getBrailleCell();
		char letterChar = letterBox.getValue();

		if (letterChar == '-') {
			// do nothing to set the pins

		} else {
			cell.displayCharacter(letterChar);

			// whichever letter the user selected, display the pins as the braille cell
			// value equivalent
			List<Boolean> pinsAsBoolean = this.scenario.getCurrentScene().getPinsAsBoolean(cell);
			rb1.setSelected(pinsAsBoolean.get(0));
			rb2.setSelected(pinsAsBoolean.get(1));
			rb3.setSelected(pinsAsBoolean.get(2));
			rb4.setSelected(pinsAsBoolean.get(3));
			rb5.setSelected(pinsAsBoolean.get(4));
			rb6.setSelected(pinsAsBoolean.get(5));
			rb7.setSelected(pinsAsBoolean.get(6));
			rb8.setSelected(pinsAsBoolean.get(7));
		}

	}

	public void recordAudioInteractionButton(ActionEvent event) throws IOException {
		// TODO
	}

	public void saveInteractionButton(ActionEvent event) throws IOException {

		// get the current button number that the user wants to add interactions to
		int currentButtonNumber = this.nobBox.getValue();
		System.out.println(currentButtonNumber);

		// save the preset interaction for the current button number in the current
		// scene object
		String interactionString = interactionBox.getValue();
		this.scenario.getCurrentScene().setInteractionPreset(currentButtonNumber, interactionString);

		// save the text-to-speach input for the current button number in the current
		// scene object
		if (interactionTextArea.getText() != "") {
			String interactionTextInput = interactionTextArea.getText();
			this.scenario.getCurrentScene().setInteractionTextInput(currentButtonNumber, interactionTextInput);
		}

		// when the user clicks the button, set the drop down menu back to 'no
		// interaction'
		this.interactionBox.setValue("No Interaction");
		this.interactionTextArea.clear();

		System.out.println(currentButtonNumber);
	}

	/*
	 * clicking this button returns the user to the main menu
	 */
	public void mainMenuButton(ActionEvent event) throws IOException {
		// a prompt that tells the user they have not saved their work
		int value = JOptionPane.showConfirmDialog(null,
				"If you return to the main menu now without saving your scenario, all progress will be lost!\n Click no to stay. \n Click yes to lose all progress",
				"Please Confirm:", JOptionPane.YES_NO_OPTION);
		if (value == JOptionPane.YES_OPTION) {
			Parent mmParent = FXMLLoader.load(getClass().getResource("MainMenuViewAA.fxml"));
			Scene mmScene = new Scene(mmParent);

			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			window.setScene(mmScene);
			window.show();

		} else {
			// do nothing

		}
	}

	public void finishScenarioButton(ActionEvent event) throws IOException {
		List<String> testString = new ArrayList<String>();
		testString.add("line1");
		testString.add("line 2");

		String concatString = "";

		for (int i = 0; i < testString.size(); i++) {
			concatString = concatString + "\n" + testString.get(i);
		}

		this.sceneNameLabel.setText(concatString);
		interactionList = FXCollections.observableArrayList("No Interaction", "Play Correct Audio Clip",
				"Play Wrong Audio Clip", "Repeat Question/Comment", "Skip Question/Comment", "No Interaction",
				"Play Correct Audio Clip", "Play Wrong Audio Clip", "Repeat Question/Comment", "Skip Question/Comment",
				"No Interaction", "Play Correct Audio Clip", "Play Wrong Audio Clip", "Repeat Question/Comment",
				"Skip Question/Comment");
		this.sceneListView.setItems(interactionList);
	}

	public void saveSceneButton(ActionEvent event) throws IOException {

		// ----------------- SCENE NAME PART------------------

		// get the scenename that the user entered and create a new scene with that name
		String sceneName = sceneNameTextField.getText();

		// check to see if the scenename exists already
		Boolean sceneExists = false;
		for (int i = 0; i < this.scenario.getScenario().size(); i++) {
			if (sceneName == this.scenario.getScenario().get(i).getSceneName()) {
				sceneExists = true;
			}
		}

		if (sceneExists) {
			// popup that says choose a different name because it already exists
		} else {
			// add the scene name to the scenario and set the text in the top right corner
			// to the current scene name
			this.scenario.getCurrentScene().setName(sceneName);
			this.sceneNameLabel.setText(sceneName);
		}

		// ----------------- QUESTION/COMMENT PART ------------------

		// get the question or comment that the user inputed in the text area
		String questionText = questionTextArea.getText();

		// set the question to what the user typed, if they already typed a question or
		// comment it will be overwritten for this scene
		this.scenario.getCurrentScene().setQuestion(questionText);

		// ----------------------------------------------------------

	}

	public void newSceneButton(ActionEvent event) throws IOException {
		// REMEMBER TO INCREMENT THE SCENE IN THE SCENARIO CLASS FOR ALL NESSECARY
		// ATTRIBUTES
		// OTHERWISE IT WILL BE STUCK ON THE SAME SCENE
		// currentSceneNumber++;

		// remember to extract current radio button values (simulated pins) and set them
		// in txt file b/c it should not be done in the set pins button method
		// TODO
	}

	@FXML
	public void userTyped(KeyEvent event) {
		if (!sceneNameTextField.getText().isEmpty()) {
			saveSceneButton.setDisable(false);
		} else {
			saveSceneButton.setDisable(true);
		}
	}

}
