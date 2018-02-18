package enamel;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ScenarioAA {

	// INCOMPLETE CLASS

	private List<SceneAA> listOfScenes;
	private List<Integer> buttons;
	private String scenarioName;
	private SceneAA currentScene;

	// number of buttons and number of cells
	private int currentSceneIndex, nob, noc;

	public ScenarioAA(String name, int nob, int noc) {
		this.scenarioName = name;
		this.nob = nob;
		this.noc = noc;
		this.listOfScenes = new ArrayList<SceneAA>();
		this.buttons = new ArrayList<Integer>();
		SceneAA s = new SceneAA(noc, nob);
		this.currentScene = s;
		this.listOfScenes.add(s);
		this.currentSceneIndex = 0;

	}

	public int getNOB() {
		return this.nob;
	}

	public int getNOC() {
		return this.noc;
	}

	public String getScenarioName() {
		return this.scenarioName;
	}

	public SceneAA getCurrentScene() {
		return this.currentScene;
	}

	public List<SceneAA> getScenario() {
		return this.listOfScenes;
	}

	public int getCurrentSceneIndex() {
		return this.currentSceneIndex;
	}

	public void setNumberOfButtons(int nob) {
		this.nob = nob;
	}

	public void setNumberOfCells(int noc) {
		this.noc = noc;
	}
	
	public void setCurrentSceneIndex(int index) {
		this.currentSceneIndex = index;
	}

	public void setScenarioName(String name) {
		this.scenarioName = name;
	}

	public int findSceneIndex(String sceneName) {
		int index = -1;
		System.out.println("-----------START ----------------- ");
		for (int i = 0; i < this.listOfScenes.size(); i++) {
			System.out.println(this.listOfScenes.get(i).getSceneName());
			System.out.println(sceneName);
			System.out.println(sceneName.equals(this.listOfScenes.get(i).getSceneName()));
			System.out.println("---end of line --- ");
			if (sceneName.equals(this.listOfScenes.get(i).getSceneName())) {
				index = i;
			}
		}
		System.out.println("-----------END ----------------- ");
		return index;

	}

	public void setCurrentScene(int index) {
		this.currentScene = this.listOfScenes.get(index);
		this.currentSceneIndex = index;
	}

	public void newCurrentScene(SceneAA scene) {
		this.currentScene = scene;
	}

	// not used, keeping incase something changes
	public void nextScene() {
		this.currentSceneIndex++;
		SceneAA s = new SceneAA(noc, nob);
		this.currentScene = s;
		this.listOfScenes.add(s);
	}

	// not used, keeping incase something changes
	public void previousScene() throws IndexOutOfBoundsException {
		if (this.currentSceneIndex == 0) {
			throw new IndexOutOfBoundsException();
		} else {
			this.currentSceneIndex--;
			this.currentScene = listOfScenes.get(currentSceneIndex);
		}
	}

	public void goToScene(int scenarioNumber) {
		this.currentScene = this.listOfScenes.get(scenarioNumber);
	}

	public void addScene(SceneAA scene) {
		this.listOfScenes.add(scene);
	}
}
