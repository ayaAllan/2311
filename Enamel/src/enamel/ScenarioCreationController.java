package enamel;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ScenarioCreationController implements Initializable {

	// nob that will be used per scene, initialized in the initializeScenario method
	private ObservableList<Integer> nobList;

	// all interactions that will be used for all scenes, also initialized in the
	// initializeScenario method
	private ObservableList<String> interactionList;

	@FXML
	private Label sceneNameLabel, scenarioNameLabel;

	@FXML
	private TextField sceneNameTextField, letterTextField;

	@FXML
	private TextArea questionTextArea, interactionButtonTextArea;

	// all radio buttons that simulate the pins on the braille cell
	@FXML
	private RadioButton rb1, rb2, rb3, rb4, rb5, rb6, rb7, rb8;

	// number of buttons drop down box
	@FXML
	private ChoiceBox<Integer> nobBox;

	@FXML
	private ChoiceBox<String> interactionBox;

	// create a scenario to keep track of all the scenes the user creates within the
	// same scenario
	private ScenarioAA scenario;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

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

		// initialize the number of buttons drop down menu
		nobList = FXCollections.observableArrayList(buttonsList);
		nobBox.setValue(1);
		nobBox.setItems(nobList);

		// initialize the interactions drop down menu
		interactionList = FXCollections.observableArrayList("No Interaction", "Play Correct Audio Clip",
				"Play Wrong Audio Clip", "Repeat Question/Comment", "Skip Question/Comment");
		interactionBox.setValue("No Interaction");
		interactionBox.setItems(interactionList);

	}

	public void sceneNameButton(ActionEvent event) throws IOException {

		// check to see if the user has entered a name for the scene yet, this boolean
		// flag will come in handy for the JPane below
		Boolean noCurrentSceneName = this.scenario.getCurrentScene().getSceneName() == null;

		// get the scenename that the user entered and create a new scene with that name
		String sceneName = sceneNameTextField.getText();

		// dont want to allow the user to be able to enter a blank scene name, it will
		// mess with editing
		if (sceneName.equals("")) {
			JOptionPane.showMessageDialog(null, "ERROR: Please enter a scene name with at least one character.");
		} else {

			// When the user creates another scene we have to be able to make sure they dont
			// create a scene with the same name as an already existing scene
			// TODO

			// add the scene name to the scenario and set the text in the top right corner
			// to the current scene name
			this.scenario.getCurrentScene().setName(sceneName);
			this.sceneNameLabel.setText(sceneName);

			// clear the text field after the user pushes the button
			this.sceneNameTextField.clear();

			// pop up to give the user feedback that they named the scene, different
			// messages based on boolean flag mentioned above
			if (noCurrentSceneName) {

				JOptionPane.showMessageDialog(null,
						"Scene name " + "'" + sceneName + "'" + " has successfully been created.");
			} else {

				JOptionPane.showMessageDialog(null,
						"The current scene name has been overwritten to: " + "'" + sceneName + "'");
			}

		}

	}

	public void addTextButton(ActionEvent event) throws IOException {
		// check to see if they already recorded a question for the JPane override
		// window
		Boolean noCurrentQuestion = this.scenario.getCurrentScene().getQuestion() == null;

		// get the question or comment that the user inputed in the text area
		String questionText = questionTextArea.getText();

		// dont want to allow the user to be able to enter a blank scene name, it will
		// mess with editing
		if (questionText.equals("")) {
			JOptionPane.showMessageDialog(null, "ERROR: Please enter a valid text input.");
		} else {

			// set the question to what the user typed, if they already typed a question or
			// comment it will be overwritten for this scene
			this.scenario.getCurrentScene().setQuestion(questionText);

			// QUESTION FOR PROF: Does the teacher need the option to add multiple
			// questions/comments for a single scene. Would have to change the
			// implementation of this button if so.
			// TODO

			// clear text area after the user pushes the button
			questionTextArea.clear();

			// pop up to give the user feedback on the text that they saved
			if (noCurrentQuestion) {
				JOptionPane.showMessageDialog(null, "Question/Statement successfully created.");
			} else {
				JOptionPane.showMessageDialog(null,
						"The previous Question/Statement has been successfully overwritten.");
			}
		}

	}

	public void recordAudioButton(ActionEvent event) throws IOException {
		// TODO
	}

	public void setPinsButton(ActionEvent event) throws IOException {
		// TODO
	}

	public void saveInteractionButton(ActionEvent event) throws IOException {
		// TODO
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

	public void finishScenarioButton(ActionEvent event) throws IOException {
		// TODO
	}

	public void finishSceneButton(ActionEvent event) throws IOException {
		// REMEMBER TO INCREMENT THE SCENE IN THE SCENARIO CLASS FOR ALL NESSECARY
		// ATTRIBUTES
		// OTHERWISE IT WILL BE STUCK ON THE SAME SCENE
		// currentSceneNumber++;
	}

	public void selectedSceneButton(ActionEvent event) throws IOException {
		// TODO
	}

}
