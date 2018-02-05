package enamel;

import java.util.List;

public class Scenario {
	
	private List<Scene> scenario;
	private Scene currentScene;
	private int currentSceneNumber;
	
	public Scenario(List<Scene> scenario) {
		this.scenario = scenario;
		this.currentScene = scenario.get(0);
		this.currentSceneNumber = 0;
	}
	
	public Scenario(List<Scene> scenario, int currentSceneNumber) {
		this.scenario = scenario;
		this.currentScene = scenario.get(currentSceneNumber);
		this.currentSceneNumber = currentSceneNumber; 
	}
	
	public void nextScene() {
		this.currentSceneNumber++;
	}
	
	public void previousScene() throws IndexOutOfBoundsException{
		if(this.currentSceneNumber == 0) {
			throw new IndexOutOfBoundsException();
		} else {
		this.currentSceneNumber--;
		}
	}
	
	public void goToScene(int scenarioNumber) {
		this.currentScene = this.scenario.get(scenarioNumber);
	}
}
