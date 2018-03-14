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
	
	@Test
	public void findSceneIndexTest() {
		ScenarioAA scenario = new ScenarioAA("ScenarioName", 3, 1);
		SceneAA s1 = new SceneAA(1, 3);
		s1.setName("s1");
		SceneAA s2 = new SceneAA(1, 3);
		s2.setName("s2");
		SceneAA s3 = new SceneAA(1, 3);
		s3.setName("s3");
		SceneAA s4 = new SceneAA(1, 3);
		s4.setName("s4");
		scenario.addScene(s1);
		scenario.addScene(s2);
		scenario.addScene(s3);
		scenario.addScene(s4);
		
		assertEquals(scenario.findSceneIndex("s1"), 1);
		assertEquals(scenario.findSceneIndex("s2"), 2);
		assertEquals(scenario.findSceneIndex("s3"), 3);
		assertEquals(scenario.findSceneIndex("s4"), 4);
		
		
	}
}