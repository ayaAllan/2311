package enamel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;


public class SampleScenarioController implements Initializable{
	@FXML
	private CheckBox visuallyCapableCheckBox;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.visuallyCapableCheckBox.setSelected(true);
	}

	public void sampleScenarioButton1() {
		//check if user is visually capable and set the value in the scenario parser
		Boolean isVisuallyCapable;
		if(this.visuallyCapableCheckBox.isSelected()) {
			isVisuallyCapable = true;
		} else {
			isVisuallyCapable = false;
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
		//check if user is visually capable and set the value in the scenario parser
		Boolean isVisuallyCapable;
		if(this.visuallyCapableCheckBox.isSelected()) {
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
		//check if user is visually capable and set the value in the scenario parser
		Boolean isVisuallyCapable;
		if(this.visuallyCapableCheckBox.isSelected()) {
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
		Parent mmParent = FXMLLoader.load(getClass().getResource("MainMenuViewAA.fxml"));
		Scene mmScene = new Scene(mmParent);

		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(mmScene);
		window.show();
	}
}
