package enamel;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ToyAuthoring {

	public static void main(String[] args) {

		/*
		 * Below I have added a simple GUI for the user to easily select which factory
		 * scenario file that they would like to use. I set it up so that it starts in
		 * the factory scenarios directory and only shows text files.
		 */
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

		ScenarioParser s = new ScenarioParser(true);
		s.setScenarioFile("FactoryScenarios/" + chooser.getSelectedFile().getName());
		
		// this is the first test change
	}
}