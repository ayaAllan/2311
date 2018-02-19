package enamel;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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
	private ComboBox<Integer> nobBox, nocBox;

	@FXML
	private ComboBox<Character> letterBox;

	@FXML
	private ComboBox<String> interactionBox, listOfScenesBox;

	@FXML
	private ListView<String> sceneListView;

	@FXML
	private ObservableList<String> sceneList, sceneListBoxVersion;

	@FXML
	private Button saveSceneButton, newSceneButton, deleteSceneButton;

	// create a scenario to keep track of all the scenes the user creates within the
	// same scenario
	private ScenarioAA scenario;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void initializeScenario(String name, int nob, int noc) {

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
				"Play Wrong Audio Clip", "Repeat Scene", "Skip to Next Scene");
		interactionBox.setValue("No Interaction");
		interactionBox.setItems(interactionList);

		// initialize the letters of the alphabet drop down menu
		letterList = FXCollections.observableArrayList('-', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
				'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z');
		letterBox.setValue('-');
		letterBox.setItems(letterList);

		sceneList = FXCollections.observableArrayList();
		sceneListBoxVersion = FXCollections.observableArrayList("No Scene Selected");
		this.listOfScenesBox.setValue("No Scene Selected");

		// cant save a scene that hasnt been created yet
		// also set other buttons disable values based on what the user can do at this
		// point
		saveSceneButton.setDisable(true);
		this.newSceneButton.setDisable(true);
		this.deleteSceneButton.setDisable(true);

	}

	public void recordAudioButton(ActionEvent event) {
		// TODO
	}

	public void setPinsBox(ActionEvent event) throws InterruptedException {
		// change the current scenes braille cells pins to a letter
		int index = this.nocBox.getValue() - 1;

		BrailleCell cell = new BrailleCell();
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
			this.scenario.getCurrentScene().getBrailleCells().set(index, cell);
		}
	}

	public void clearPinsButton(ActionEvent event) throws InterruptedException {

		int index = this.nocBox.getValue() - 1;
		this.scenario.getCurrentScene().getBrailleCells().get(index).clear();

		rb1.setSelected(false);
		rb2.setSelected(false);
		rb3.setSelected(false);
		rb4.setSelected(false);
		rb5.setSelected(false);
		rb6.setSelected(false);
		rb7.setSelected(false);
		rb8.setSelected(false);

		// refresh letter value
		letterBox.setValue('-');
	}

	public void selectedBrailleCell(ActionEvent event) {

		int index = this.nocBox.getValue() - 1;

		// set pins to the cell numbers previous state
		BrailleCell cellAtindex = this.scenario.getCurrentScene().getBrailleCells().get(index);
		List<Boolean> pinsAsBoolean = this.scenario.getCurrentScene().getPinsAsBoolean(cellAtindex);
		rb1.setSelected(pinsAsBoolean.get(0));
		rb2.setSelected(pinsAsBoolean.get(1));
		rb3.setSelected(pinsAsBoolean.get(2));
		rb4.setSelected(pinsAsBoolean.get(3));
		rb5.setSelected(pinsAsBoolean.get(4));
		rb6.setSelected(pinsAsBoolean.get(5));
		rb7.setSelected(pinsAsBoolean.get(6));
		rb8.setSelected(pinsAsBoolean.get(7));

		// refresh letter value
		letterBox.setValue('-');
	}

	public void radioButtonClicked1(ActionEvent event) {
		int index = this.nocBox.getValue() - 1;
		if (this.scenario.getCurrentScene().getBrailleCells().get(index).getPinState(0) == true) {
			this.scenario.getCurrentScene().getBrailleCells().get(index).lowerOnePin(1);
		} else {
			this.scenario.getCurrentScene().getBrailleCells().get(index).raiseOnePin(1);
		}
		// leave this here for testing purposes in the future
		// trying to print to test
		// Boolean index11 =
		// this.scenario.getCurrentScene().getBrailleCells().get(0).getPinState(0);
		// Boolean index12 =
		// this.scenario.getCurrentScene().getBrailleCells().get(0).getPinState(1);
		// Boolean index13 =
		// this.scenario.getCurrentScene().getBrailleCells().get(0).getPinState(2);
		// Boolean index14 =
		// this.scenario.getCurrentScene().getBrailleCells().get(0).getPinState(3);
		// Boolean index15 =
		// this.scenario.getCurrentScene().getBrailleCells().get(0).getPinState(4);
		// Boolean index16 =
		// this.scenario.getCurrentScene().getBrailleCells().get(0).getPinState(5);
		// Boolean index17 =
		// this.scenario.getCurrentScene().getBrailleCells().get(0).getPinState(6);
		// Boolean index18 =
		// this.scenario.getCurrentScene().getBrailleCells().get(0).getPinState(7);
		//
		// Boolean index21 =
		// this.scenario.getCurrentScene().getBrailleCells().get(1).getPinState(0);
		// Boolean index22 =
		// this.scenario.getCurrentScene().getBrailleCells().get(1).getPinState(1);
		// Boolean index23 =
		// this.scenario.getCurrentScene().getBrailleCells().get(1).getPinState(2);
		// Boolean index24 =
		// this.scenario.getCurrentScene().getBrailleCells().get(1).getPinState(3);
		// Boolean index25 =
		// this.scenario.getCurrentScene().getBrailleCells().get(1).getPinState(4);
		// Boolean index26 =
		// this.scenario.getCurrentScene().getBrailleCells().get(1).getPinState(5);
		// Boolean index27 =
		// this.scenario.getCurrentScene().getBrailleCells().get(1).getPinState(6);
		// Boolean index28 =
		// this.scenario.getCurrentScene().getBrailleCells().get(1).getPinState(7);
		//
		// Boolean index31 =
		// this.scenario.getCurrentScene().getBrailleCells().get(2).getPinState(0);
		// Boolean index32 =
		// this.scenario.getCurrentScene().getBrailleCells().get(2).getPinState(1);
		// Boolean index33 =
		// this.scenario.getCurrentScene().getBrailleCells().get(2).getPinState(2);
		// Boolean index34 =
		// this.scenario.getCurrentScene().getBrailleCells().get(2).getPinState(3);
		// Boolean index35 =
		// this.scenario.getCurrentScene().getBrailleCells().get(2).getPinState(4);
		// Boolean index36 =
		// this.scenario.getCurrentScene().getBrailleCells().get(2).getPinState(5);
		// Boolean index37 =
		// this.scenario.getCurrentScene().getBrailleCells().get(2).getPinState(6);
		// Boolean index38 =
		// this.scenario.getCurrentScene().getBrailleCells().get(2).getPinState(7);
		//
		//
		// System.out.println("radio1 clicked");
		// System.out.println(index11);
		// System.out.println(index12);
		// System.out.println(index13);
		// System.out.println(index14);
		// System.out.println(index15);
		// System.out.println(index16);
		// System.out.println(index17);
		// System.out.println(index18);
		// System.out.println("----------------------------------");
		// System.out.println(index21);
		// System.out.println(index22);
		// System.out.println(index23);
		// System.out.println(index24);
		// System.out.println(index25);
		// System.out.println(index26);
		// System.out.println(index27);
		// System.out.println(index28);
		// System.out.println("----------------------------------");
		// System.out.println(index31);
		// System.out.println(index32);
		// System.out.println(index33);
		// System.out.println(index34);
		// System.out.println(index35);
		// System.out.println(index36);
		// System.out.println(index37);
		// System.out.println(index38);
		// System.out.println("----------------------------------");
	}

	public void radioButtonClicked2(ActionEvent event) {
		int index = this.nocBox.getValue() - 1;
		if (this.scenario.getCurrentScene().getBrailleCells().get(index).getPinState(1) == true) {
			this.scenario.getCurrentScene().getBrailleCells().get(index).lowerOnePin(2);
		} else {
			this.scenario.getCurrentScene().getBrailleCells().get(index).raiseOnePin(2);
		}
	}

	public void radioButtonClicked3(ActionEvent event) {
		int index = this.nocBox.getValue() - 1;
		if (this.scenario.getCurrentScene().getBrailleCells().get(index).getPinState(2) == true) {
			this.scenario.getCurrentScene().getBrailleCells().get(index).lowerOnePin(3);
		} else {
			this.scenario.getCurrentScene().getBrailleCells().get(index).raiseOnePin(3);
		}
	}

	public void radioButtonClicked4(ActionEvent event) {
		int index = this.nocBox.getValue() - 1;
		if (this.scenario.getCurrentScene().getBrailleCells().get(index).getPinState(3) == true) {
			this.scenario.getCurrentScene().getBrailleCells().get(index).lowerOnePin(4);
		} else {
			this.scenario.getCurrentScene().getBrailleCells().get(index).raiseOnePin(4);
		}
	}

	public void radioButtonClicked5(ActionEvent event) {
		int index = this.nocBox.getValue() - 1;
		if (this.scenario.getCurrentScene().getBrailleCells().get(index).getPinState(4) == true) {
			this.scenario.getCurrentScene().getBrailleCells().get(index).lowerOnePin(5);
		} else {
			this.scenario.getCurrentScene().getBrailleCells().get(index).raiseOnePin(5);
		}
	}

	public void radioButtonClicked6(ActionEvent event) {
		int index = this.nocBox.getValue() - 1;
		if (this.scenario.getCurrentScene().getBrailleCells().get(index).getPinState(5) == true) {
			this.scenario.getCurrentScene().getBrailleCells().get(index).lowerOnePin(6);
		} else {
			this.scenario.getCurrentScene().getBrailleCells().get(index).raiseOnePin(6);
		}
	}

	public void radioButtonClicked7(ActionEvent event) {
		int index = this.nocBox.getValue() - 1;
		if (this.scenario.getCurrentScene().getBrailleCells().get(index).getPinState(6) == true) {
			this.scenario.getCurrentScene().getBrailleCells().get(index).lowerOnePin(7);
		} else {
			this.scenario.getCurrentScene().getBrailleCells().get(index).raiseOnePin(7);
		}
	}

	public void radioButtonClicked8(ActionEvent event) {
		int index = this.nocBox.getValue() - 1;
		if (this.scenario.getCurrentScene().getBrailleCells().get(index).getPinState(7) == true) {
			this.scenario.getCurrentScene().getBrailleCells().get(index).lowerOnePin(8);
		} else {
			this.scenario.getCurrentScene().getBrailleCells().get(index).raiseOnePin(8);
		}
	}

	public void selectedButtonNumber(ActionEvent event) {
		int buttonNumber = this.nobBox.getValue();

		// get preset interaction
		String currentInteraction = this.scenario.getCurrentScene().getInteractionPreset().get(buttonNumber);
		// set preset interaction
		this.interactionBox.setValue(currentInteraction);
		// get interaction text
		String currentText = this.scenario.getCurrentScene().getInteractionTextInput().get(buttonNumber);
		// set interaction text
		this.interactionTextArea.setText(currentText);
	}

	public void presetInteractionSet(ActionEvent event) {
		int buttonNumber = this.nobBox.getValue();
		String currentInteractionValue = this.interactionBox.getValue();
		this.scenario.getCurrentScene().getInteractionPreset().put(buttonNumber, currentInteractionValue);
	}

	public void interactionTextFinished(KeyEvent event) {
		int buttonNumber = this.nobBox.getValue();
		String inputedText = this.interactionTextArea.getText();
		this.scenario.getCurrentScene().getInteractionTextInput().put(buttonNumber, inputedText);
	}

	public void recordAudioInteractionButton(ActionEvent event) {
		// TODO
	}

	public void saveInteractionButton(ActionEvent event) {

		// get the current button number that the user wants to add interactions to
		int currentButtonNumber = this.nobBox.getValue();

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
	}

	/*
	 * clicking this button returns the user to the main menu
	 */
	public void mainMenuButton(ActionEvent event) throws IOException {
		// a prompt that tells the user they have not saved their work
		// needs to be changed eventually so that it can be read by screen reader
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

	public void saveSceneButton(ActionEvent event) {

		// get the scenename that the user entered
		String sceneName = sceneNameTextField.getText();

		// need to finish implementing this part where if the scene name already exists
		// it should overwrite it not create another scene with the same name, this will
		// cause many complications with the other buttons

		// check to see if the scenename exists already
		Boolean sceneExists = false;
		for (int i = 0; i < this.scenario.getScenario().size(); i++) {
			if (sceneName.equals(this.scenario.getScenario().get(i).getSceneName())) {
				sceneExists = true;
			}
		}

		// wrong boolean it should be if it exists AND your currently at the end of the
		// index which is at a different location of what u found
		if (sceneExists) {

			// this.sceneNameLabel.setText(sceneName);
			// String questionText = questionTextArea.getText();
			// // set the question to what the user typed
			// this.scenario.getCurrentScene().setQuestion(questionText);
			// this.listOfScenesBox.setValue(sceneName);
			int index = this.scenario.findSceneIndex(sceneName);

			if (index == -1) {
				// do nothing
			} else if (this.scenario.getCurrentSceneIndex() != index) {
				this.scenario.getScenario().remove(index);
				System.out.println(index);

			} else {
				// do nothing
			}

		}
		// add the scene name to the scenario and set the text in the top right corner
		// to the current scene name
		this.scenario.getCurrentScene().setName(sceneName);
		this.sceneNameLabel.setText(sceneName);

		// get the inputed text
		String questionText = questionTextArea.getText();
		// set the question to what the user typed
		this.scenario.getCurrentScene().setQuestion(questionText);

		List<String> listOfScenesWO = new ArrayList<>();
		for (int i = 0; i < this.scenario.getScenario().size(); i++) {
			listOfScenesWO.add(this.scenario.getScenario().get(i).getSceneName());
		}
		ObservableList<String> listOfScenesOWOL = FXCollections.observableArrayList(listOfScenesWO);
		this.sceneListView.setItems(listOfScenesOWOL);

		List<String> listOfScenesLS = new ArrayList<>();
		listOfScenesLS.add("No Scene Selected");
		for (int i = 0; i < this.scenario.getScenario().size(); i++) {
			listOfScenesLS.add(this.scenario.getScenario().get(i).getSceneName());
		}
		ObservableList<String> listOfScenesOL = FXCollections.observableArrayList(listOfScenesLS);
		this.listOfScenesBox.setValue(sceneName);
		this.listOfScenesBox.setItems(listOfScenesOL);

		// need to remove all the extra created null scenes due to the user incorrectly
		// pressing new scene too many times
		// for (int i = 0; i < this.scenario.getScenario().size(); i++) {
		//
		// System.out.println(this.scenario.getScenario().get(i) == null);
		// System.out.println(this.scenario.getScenario().get(i).getSceneName());
		// System.out.println(this.scenario.getScenario().get(i).equals(null));
		// System.out.println(this.scenario.getScenario().get(i).getSceneName() ==
		// null);
		// if (this.scenario.getScenario().get(i).getSceneName() == null) {
		// this.scenario.getScenario().remove(i);
		// }
		// }

		this.newSceneButton.setDisable(false);
		this.deleteSceneButton.setDisable(false);

	}

	public void newSceneButton(ActionEvent event) {
		this.listOfScenesBox.setValue("No Scene Selected");

		// create new scene to work on but do not store it yet in the list, it should
		// only be stored in the list(Scenario attribute list of scenes) when its saved
		SceneAA s = new SceneAA(this.scenario.getNOC(), this.scenario.getNOB());

		this.scenario.newCurrentScene(s);
		this.scenario.addScene(this.scenario.getCurrentScene());
		this.scenario.setCurrentSceneIndex(this.scenario.getScenario().size() - 1);

		// clear whatever needs to be cleared
		this.sceneNameTextField.clear();
		this.questionTextArea.clear();
		this.nocBox.setValue(1);
		this.rb1.setSelected(false);
		this.rb2.setSelected(false);
		this.rb3.setSelected(false);
		this.rb4.setSelected(false);
		this.rb5.setSelected(false);
		this.rb6.setSelected(false);
		this.rb7.setSelected(false);
		this.rb8.setSelected(false);
		this.letterBox.setValue('-');
		this.nobBox.setValue(1);
		this.interactionBox.setValue("No Interaction");
		this.sceneNameLabel.setText("Unnammed New Scene");
		this.interactionTextArea.clear();
		saveSceneButton.setDisable(true);
		this.newSceneButton.setDisable(true);
	}

	public void loadSceneBox(ActionEvent event) {
		String selectedSceneName = listOfScenesBox.getValue();

		if (selectedSceneName == "No Scene Selected") {
			// do nothing
			this.sceneNameLabel.setText("Please Create a New Scene");
		} else {

			// was getting a wierd null pointer exception based on the method
			// findSceneIndex() implementation
			// this fixes it
			int indexOfValue;
			if (selectedSceneName == null) {
				indexOfValue = -1;
			} else {
				indexOfValue = this.scenario.findSceneIndex(selectedSceneName);
			}

			if (indexOfValue == -1) {
				// temporary joption pane for testing purposes
				// JOptionPane.showConfirmDialog(null, "its -1 fix it -corv");
			} else {
				this.scenario.setCurrentScene(indexOfValue);
				// update all text fields with the current scenes values
				this.sceneNameTextField.setText(this.scenario.getCurrentScene().getSceneName());
				this.questionTextArea.setText(this.scenario.getCurrentScene().getQuestion());
				this.nocBox.setValue(1);
				rb1.setSelected(this.scenario.getCurrentScene().getBrailleCells().get(0).getPinState(0));
				rb2.setSelected(this.scenario.getCurrentScene().getBrailleCells().get(0).getPinState(1));
				rb3.setSelected(this.scenario.getCurrentScene().getBrailleCells().get(0).getPinState(2));
				rb4.setSelected(this.scenario.getCurrentScene().getBrailleCells().get(0).getPinState(3));
				rb5.setSelected(this.scenario.getCurrentScene().getBrailleCells().get(0).getPinState(4));
				rb6.setSelected(this.scenario.getCurrentScene().getBrailleCells().get(0).getPinState(5));
				rb7.setSelected(this.scenario.getCurrentScene().getBrailleCells().get(0).getPinState(6));
				rb8.setSelected(this.scenario.getCurrentScene().getBrailleCells().get(0).getPinState(7));
				this.letterBox.setValue('-');
				this.nobBox.setValue(1);
				this.interactionBox.setValue(this.scenario.getCurrentScene().getInteractionPreset().get(1));
				this.interactionTextArea.setText(this.scenario.getCurrentScene().getInteractionTextInput().get(1));
				this.sceneNameLabel.setText(this.scenario.getCurrentScene().getSceneName());
				this.saveSceneButton.setDisable(false);
				this.deleteSceneButton.setDisable(false);

				// part for enabling the new scene button but also removing the new scene the
				// user createdso you dont get null references
				this.newSceneButton.setDisable(false);
				for (int i = 0; i < this.scenario.getScenario().size(); i++) {

					if (this.scenario.getScenario().get(i).getSceneName() == null) {
						this.scenario.getScenario().remove(i);
					}
				}

			}
		}
	}

	public void deleteSceneOnClick(ActionEvent event) {

		if (this.listOfScenesBox.getValue().equals("No Scene Selected")) {
			// do nothing, there is nothing to delete
		} else {
			String currentlySelectedScene = this.listOfScenesBox.getValue();

			int selectedSceneIndex = this.scenario.findSceneIndex(currentlySelectedScene);
			this.scenario.getScenario().remove(selectedSceneIndex);

			List<String> listOfScenesWO = new ArrayList<>();
			for (int i = 0; i < this.scenario.getScenario().size(); i++) {
				listOfScenesWO.add(this.scenario.getScenario().get(i).getSceneName());
			}
			ObservableList<String> listOfScenesOWOL = FXCollections.observableArrayList(listOfScenesWO);
			this.sceneListView.setItems(listOfScenesOWOL);

			List<String> listOfScenesLS = new ArrayList<>();
			listOfScenesLS.add("No Scene Selected");
			for (int i = 0; i < this.scenario.getScenario().size(); i++) {
				listOfScenesLS.add(this.scenario.getScenario().get(i).getSceneName());
			}
			ObservableList<String> listOfScenesOL = FXCollections.observableArrayList(listOfScenesLS);
			this.listOfScenesBox.setItems(listOfScenesOL);

			// repeat code from new scene to clear everything
			this.listOfScenesBox.setValue("No Scene Selected");
			this.sceneNameLabel.setText("Please Create a New Scene");

			// clear whatever needs to be cleared
			this.sceneNameTextField.clear();
			this.questionTextArea.clear();
			this.nocBox.setValue(1);
			this.rb1.setSelected(false);
			this.rb2.setSelected(false);
			this.rb3.setSelected(false);
			this.rb4.setSelected(false);
			this.rb5.setSelected(false);
			this.rb6.setSelected(false);
			this.rb7.setSelected(false);
			this.rb8.setSelected(false);
			this.letterBox.setValue('-');
			this.nobBox.setValue(1);
			this.interactionBox.setValue("No Interaction");
			this.interactionTextArea.clear();
			saveSceneButton.setDisable(true);
			newSceneButton.setDisable(false);

			// clean up any newly created scenes that arent used
			for (int i = 0; i < this.scenario.getScenario().size(); i++) {
				if (this.scenario.getScenario().get(i).getSceneName() == null) {
					this.scenario.getScenario().remove(i);
				}
			}

		}

	}

	public void moveSceneUpOnClick(ActionEvent event) {

		if (this.scenario.getCurrentSceneIndex() == 0) {
			// do nothing, reached border
		} else {
			List<SceneAA> scenarioObjectList = this.scenario.getScenario();
			// swap with the next scene in the list
			Collections.swap(scenarioObjectList, this.scenario.getCurrentSceneIndex() - 1,
					this.scenario.getCurrentSceneIndex());

			List<String> listOfScenesWO = new ArrayList<>();
			for (int i = 0; i < this.scenario.getScenario().size(); i++) {
				listOfScenesWO.add(this.scenario.getScenario().get(i).getSceneName());
			}
			ObservableList<String> listOfScenesOWOL = FXCollections.observableArrayList(listOfScenesWO);
			this.sceneListView.setItems(listOfScenesOWOL);

			List<String> listOfScenesLS = new ArrayList<>();
			listOfScenesLS.add("No Scene Selected");
			for (int i = 0; i < this.scenario.getScenario().size(); i++) {
				listOfScenesLS.add(this.scenario.getScenario().get(i).getSceneName());
			}
			ObservableList<String> listOfScenesOL = FXCollections.observableArrayList(listOfScenesLS);
			this.listOfScenesBox.setItems(listOfScenesOL);

		}

	}

	public void moveSceneDownOnClick(ActionEvent event) {

		if (this.scenario.getCurrentSceneIndex() == this.scenario.getScenario().size() - 1) {
			// do nothing, at beggining cant move down
		} else {
			List<SceneAA> scenarioObjectList = this.scenario.getScenario();
			// swap with the next scene in the list
			Collections.swap(scenarioObjectList, this.scenario.getCurrentSceneIndex() + 1,
					this.scenario.getCurrentSceneIndex());

			List<String> listOfScenesWO = new ArrayList<>();
			for (int i = 0; i < this.scenario.getScenario().size(); i++) {
				listOfScenesWO.add(this.scenario.getScenario().get(i).getSceneName());
			}
			ObservableList<String> listOfScenesOWOL = FXCollections.observableArrayList(listOfScenesWO);
			this.sceneListView.setItems(listOfScenesOWOL);

			List<String> listOfScenesLS = new ArrayList<>();
			listOfScenesLS.add("No Scene Selected");
			for (int i = 0; i < this.scenario.getScenario().size(); i++) {
				listOfScenesLS.add(this.scenario.getScenario().get(i).getSceneName());
			}
			ObservableList<String> listOfScenesOL = FXCollections.observableArrayList(listOfScenesLS);
			this.listOfScenesBox.setItems(listOfScenesOL);

		}
	}

	// user cannot click save button unless they entered a valid scene name
	@FXML
	public void userTyped(KeyEvent event) {
		if (!sceneNameTextField.getText().isEmpty()) {
			saveSceneButton.setDisable(false);
		} else {
			saveSceneButton.setDisable(true);
		}
	}

	public void finishScenarioButton(ActionEvent event) throws IOException {
		// //clean up any newly created scenes that arent used
		// for (int i = 0; i < this.scenario.getScenario().size(); i++) {
		// if (this.scenario.getScenario().get(i).getSceneName() == null) {
		// this.scenario.getScenario().remove(i);
		// }
		// }
		//
		// get the scenario name and number of buttons as a string
		String primitiveScenarioName = this.scenario.getScenarioName();
		String scenarioNameString = primitiveScenarioName + ".txt";

		PrintWriter writer = new PrintWriter("./FactoryScenarios/" + scenarioNameString, "UTF-8");
		writer.println("Cell " + this.scenario.getNOC());
		writer.println("Button " + this.scenario.getNOB());

		Boolean isFirstScene = true;

		for (int i = 0; i < this.scenario.getScenario().size(); i++) {

			SceneAAWriter sceneWriter = new SceneAAWriter(this.scenario.getScenario().get(i), isFirstScene);
			isFirstScene = false;
			List<String> sceneTxt = sceneWriter.getSceneAsTxt();
			for (int j = 0; j < sceneTxt.size(); j++) {
				writer.println(sceneTxt.get(j));
			}

		}
		writer.println(
				"Scenario " + this.scenario.getScenarioName() + " is now finished.  Please close the simulation.");
		writer.close();

		// return user to the main menu so they can load their newly created scenario
		Parent mmParent = FXMLLoader.load(getClass().getResource("MainMenuViewAA.fxml"));
		Scene mmScene = new Scene(mmParent);

		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(mmScene);
		window.show();

	}

}
