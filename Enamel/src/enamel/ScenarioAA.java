package enamel;

import java.util.ArrayList;
import java.util.List;

public class ScenarioAA {

	// INCOMPLETE CLASS
	// It works and can be tested but alot of methods that are needed dont exist as
	// well as the implementation of certain methods need to be changed in the
	// future

	private List<SceneAA> listOfScenes;
	private List<Integer> buttons;
	private String scenarioName;
	private SceneAA currentScene;

	// number of buttons and number of cells
	private int currentSceneNumber, nob, noc;

	public ScenarioAA(String name, int nob, int noc) {
		this.scenarioName = name;
		this.nob = nob;
		this.noc = noc;
		this.listOfScenes = new ArrayList<SceneAA>();
		this.buttons = new ArrayList<Integer>();
		this.currentScene = new SceneAA();
		this.currentSceneNumber = 0;

	}

	public int getNOB() {
		return this.nob;
	}

	public SceneAA getCurrentScene() {
		return this.currentScene;
	}

	public List<SceneAA> getScenario() {
		return this.listOfScenes;
	}

	public int getCurrentSceneNumber() {
		return this.currentSceneNumber;
	}

	public void setNumberOfButtons(int nob) {
		this.nob = nob;
	}

	public void setNumberOfCells(int noc) {
		this.noc = noc;
	}

	public void setScenarioName(String name) {
		this.scenarioName = name;
	}

	public void nextScene() {
		this.currentSceneNumber++;
		this.currentScene = listOfScenes.get(currentSceneNumber);
	}

	public void previousScene() throws IndexOutOfBoundsException {
		if (this.currentSceneNumber == 0) {
			throw new IndexOutOfBoundsException();
		} else {
			this.currentSceneNumber--;
			this.currentScene = listOfScenes.get(currentSceneNumber);
		}
	}

	public void goToScene(int scenarioNumber) {
		this.currentScene = this.listOfScenes.get(scenarioNumber);
	}

	// this method is for when the user has already entered a scene name for the
	// current scene but wants to change it
	public void addSceneOverride(SceneAA scene) {

	}

	public void removeScene(SceneAA scene) {
		// TODO
	}

	// TODO
	// remember to implement the list of scenes properly later when the user saves a
	// scene and goes to the next one.
	// Also increment the appropriate attributes in both classes
}
