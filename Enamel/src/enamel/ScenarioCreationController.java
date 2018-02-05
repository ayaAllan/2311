package enamel;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ScenarioCreationController implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {

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
