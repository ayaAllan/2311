package enamel;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 * @author Mark Savin
 *
 */

public class ScenarioAATest {

	@Test
	public void test01_getNOB() {
		ScenarioAA nob = new ScenarioAA(null, 1, 1);
		// nob.setNumberOfButtons(1);
		int numberOfButtons = 1;
		assertEquals(nob.getNOB(), numberOfButtons);
	}

	@Test
	public void test02_getNOC() {
		ScenarioAA noc = new ScenarioAA(null, 1, 1);
		// noc.setNumberOfCells(1);;
		int numberOfCells = 1;
		assertEquals(noc.getNOC(), numberOfCells);
	}

	@Test
	public void test03_getScenarioName() {
		ScenarioAA scenarioName = new ScenarioAA("Scenario Name", 1, 1);
		scenarioName.setScenarioName("Scenario Name");
		assertEquals(scenarioName.getScenarioName(), "Scenario Name");
	}

	@Test
	public void test04_getCurrentScene() {
		// SceneAA currentScene = new SceneAA(1, 1);
		// currentScene.setName("Scene1");
		// assertEquals(currentScene.getSceneName(), "Scene1");
	}

	@Test
	public void test05_getScenario() {
		ScenarioAA scenario = new ScenarioAA("", 1, 1);
		scenario.setScenarioName("");
		assertEquals(scenario.getScenarioName(), "");
	}

	@Test
	public void test07_setNumberOfButtons() {
		ScenarioAA nob = new ScenarioAA(null, 1, 1);
		int numberOfButtons = 1;
		nob.setNumberOfButtons(numberOfButtons);
		assertEquals(nob.getNOB(), numberOfButtons);
	}

	@Test
	public void test08_setNumberOfCells() {
		ScenarioAA noc = new ScenarioAA(null, 1, 1);
		int numberOfCells = 1;
		noc.setNumberOfCells(numberOfCells);
		assertEquals(noc.getNOC(), numberOfCells);
	}

	@Test
	public void test09_setCurrentSceneIndex() {
		SceneAA currentSceneIndex = new SceneAA(1, 1);
	}
}