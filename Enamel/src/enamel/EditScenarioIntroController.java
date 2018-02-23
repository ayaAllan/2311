package enamel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class EditScenarioIntroController implements Initializable{

	private ScenarioAA scenario;
	private String scenarioFileName;

	@FXML
	private Button editScenarioButton;
	
	@FXML
	private Label selectedScenarioLabel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.editScenarioButton.setDisable(true);
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
	 * clicking this button progresses the user to the next window to start  editing their teaching scenario of choice
	 */
	public void editScenarioButton(ActionEvent event) throws IOException {
		
		
		//temp scenario for testing

		String scenarioFileName = this.selectedScenarioLabel.getText() + "_" + "storage.bin";
		String scenarioPath = "./FactoryScenarios/ScenarioStorage/" + scenarioFileName;
		
		//if(!scenarioPathExists)
		//should give a pop up to say the object no longer exists to edit the file
		try {
			ObjectInputStream is = new ObjectInputStream(new FileInputStream(scenarioPath));
			this.scenario = (ScenarioAA) is.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("EditScenarioView.fxml"));

		Parent mmParent = loader.load();
		Scene mmScene = new Scene(mmParent);


		EditScenarioController controller = loader.getController();
		controller.initializeScenario(this.scenario);


		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(mmScene);
		window.show();

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
				this.editScenarioButton.setDisable(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
