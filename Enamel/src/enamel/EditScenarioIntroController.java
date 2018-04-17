package enamel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

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
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class EditScenarioIntroController implements Initializable{

	private ScenarioAA scenario;
	private String scenarioFileName;

	@FXML
	private Button continueButton, mmButton;
	
	@FXML
	private Label selectedScenarioLabel;
	
	final KeyCombination keyCombMainMenu = new KeyCodeCombination(KeyCode.M, KeyCombination.CONTROL_DOWN);
	final KeyCombination keyCombContinue = new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN);

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.continueButton.setDisable(true);
	}
	
	public void initializeEditIntro(Scene editScenarioSetupScene) {
		
		editScenarioSetupScene.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler() {

			@Override
			public void handle(Event arg0) {
				if (keyCombMainMenu.match((KeyEvent) arg0)) {
	                mmButton.fire();
	            } else if(keyCombContinue.match((KeyEvent)arg0)) {
	            	continueButton.fire();
	            }
			}
	    });
		
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
	 * clicking this button progresses the user to the next window to start  editing their teaching scenario of choice
	 */
	public void editScenarioButton(ActionEvent event) throws IOException {
		
		
		//temp scenario for testing

		String scenarioFileName = this.selectedScenarioLabel.getText() + "_" + "storage.bin";
		String scenarioPath = "./FactoryScenarios/ScenarioStorage/" + scenarioFileName;

		// if(!scenarioPathExists)
		// should give a pop up to say the object no longer exists to edit the file
		File scenarioPathFile = new File(scenarioPath);
		if (!scenarioPathFile.exists()) {

			Thread jpaneThread = new Thread("Starter Code Thread") {
				public void run() {
					JOptionPane.showMessageDialog(null,
							"Editing teaching scenarios that were created by another application are currently not supported in version 1.0.0");
				}
			};
			jpaneThread.start();

		} else {
			try {
				ObjectInputStream is = new ObjectInputStream(new FileInputStream(scenarioPath));
				this.scenario = (ScenarioAA) is.readObject();
			} catch (Exception e) {
				e.printStackTrace();
			}

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("EditScenarioView.fxml"));

			Parent editScenarioParent = loader.load();
			Scene editScenarioScene = new Scene(editScenarioParent);


			EditScenarioController controller = loader.getController();
			controller.initializeScenario(this.scenario, editScenarioScene);


			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			window.setScene(editScenarioScene);
			window.show();
		}
		

	}

	public void chooseScenarioButton() {
		// choose the file
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
			//try catch block isnt working when the user doesnt select a file, i have no clue why
			//even when i set the exception to the super class
			// set the attribute of the scenario name
			this.scenarioFileName = chooser.getSelectedFile().getName();
			// set the text of selectedScenarioLabel without the .txt
			this.selectedScenarioLabel.setText(this.scenarioFileName.substring(0, scenarioFileName.length() - 4));

			// check if the selected scenario is valid
			if (this.selectedScenarioLabel.getText().equals("No Scenario Selected")) {
				// dont enable them to load
			} else {
				this.continueButton.setDisable(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
