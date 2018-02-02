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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
public class CreationSetupController implements Initializable{
	
	ObservableList<String> cellsList = FXCollections.observableArrayList("6", "8");
	
	ObservableList<String> nobList = FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12");

	@FXML
	private ChoiceBox cellsBox;
	
	@FXML
	private ChoiceBox nobBox;

	/*
	 * Setup the creation window with the drop down window for Braille cells to 6 or 8
	 * as well as the number of buttons the user will use throughout the 
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cellsBox.setValue("8");
		cellsBox.setItems(cellsList);
		
		nobBox.setValue("1");
		nobBox.setItems(nobList);
		
		

	}
	
	/*
	 * clicking this button returns the user to our MainMenuViewAA
	 */
	public void mainMenuButton(ActionEvent event) throws IOException {
		Parent mmParent = FXMLLoader.load(getClass().getResource("MainMenuViewAA.fxml"));
		Scene mmScene = new Scene(mmParent);
		
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(mmScene);
		window.show();
	}
	
	/*
	 * clicking this button progresses the user to the next window to start building they're scenario
	 */
	public void continueButton() {
		
	}

}
