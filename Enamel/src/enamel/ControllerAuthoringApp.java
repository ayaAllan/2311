package enamel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerAuthoringApp implements Initializable {

	ObservableList<String> readSpeedList = FXCollections.observableArrayList("Very Fast", "Fast" , "Medium", "Slow", "Very Slow", "Beginner");
	
	@FXML
	private ChoiceBox readSpeedBox;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("View is now loaded!");
		System.out.println("This is a test");
	 	readSpeedBox.setValue("Beginner");
		readSpeedBox.setItems(readSpeedList);
	
		
	}
	
//	@FXML
//	public void initializeTest() {
//	 	readSpeedBox.setValue("test");
//		readSpeedBox.setItems(readSpeedList);
//	}

}