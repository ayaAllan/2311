package enamel;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * 
 * @author Mark Savin
 *
 */

public class ScenarioAATest {

	@Test
	public void test01_getNOB() {
		int nob = 8;
		// create a scene with 8 buttons
		ScenarioAA noble = new ScenarioAA(null, 8, 0);
		// assert true that we do have 8 buttons
		assertEquals(nob, noble.getNOB());
	}

	@Test
	public void test02_getNOC() {
		ScenarioAA noc = new ScenarioAA(null, 1, 1);
		int numberOfCells = 1; // this needs to be 1 because our newly created
								// scene "noc" only has one cell
		assertEquals(numberOfCells, noc.getNOC());

		// test to make sure 2 cells is not asserted to be true because we only
		// have 1 cell
		int numberOfCells2 = 2;
		assertNotEquals(numberOfCells2, noc.getNOC());
	}

	@Test
	public void test03_getScenarioName() {
		ScenarioAA scenarioName = new ScenarioAA("Scenario Name", 1, 1);
		scenarioName.setScenarioName("Scenario Name");
		assertEquals(scenarioName.getScenarioName(), "Scenario Name");
	}

	@Test
	public void test04_getCurrentScene() {
		ScenarioAA s = new ScenarioAA("", 1, 1);
		SceneAA scene2 = new SceneAA(1, 1);
		SceneAA orginalScene = s.getCurrentScene();
		//orginalScene.setName("1");
		s.addScene(scene2);
		s.setCurrentScene(0);
		assertEquals(orginalScene, s.getCurrentScene());
		//assertEquals(s.getCurrentScene().getSceneName(), "1");

		// SceneAA scene2 = new SceneAA(1, 1);
		SceneAA scene3 = new SceneAA(1, 1);
		SceneAA scene4 = new SceneAA(1, 1);
		// s.addScene(scene2);
		s.addScene(scene3);
		s.addScene(scene4);
		s.setCurrentScene(2);
		assertEquals(scene3, s.getCurrentScene());
	}

	@Test
	public void test05_getScenario() {
		// a scenario is a collection of scenes or list of scenes
		ScenarioAA s = new ScenarioAA("", 1, 1);
		SceneAA scene1 = new SceneAA(1, 1);
		SceneAA scene2 = new SceneAA(1, 1);
		s.addScene(scene1);
		s.addScene(scene2);

		List<SceneAA> listOfScenes = new ArrayList<SceneAA>();
		listOfScenes.add(s.getCurrentScene());
		listOfScenes.add(scene1);
		listOfScenes.add(scene2);

		assertEquals(s.getScenario(), listOfScenes);
	}

	@Test
	public void test06_setNumberOfButtons() {
		ScenarioAA nob = new ScenarioAA(null, 1, 1);
		// asserting that our scene has 1 button
		int numberOfButtons = 1;
		assertEquals(numberOfButtons, nob.getNOB());

		// //Asserting that 2 buttons is incorrect because our scene only has 1
		// button. Is this done right?
		int numberOfButtons1 = 2;
		assertNotEquals(numberOfButtons1, nob.getNOB());
		//
		// //changing the number of buttons in the scene to 0
		int noButtons = 0;
		nob.setNumberOfButtons(noButtons);
		assertEquals(noButtons, nob.getNOB());
	}

	@Test
	public void test07_setNumberOfCells() {
		ScenarioAA noc = new ScenarioAA(null, 1, 1);
		int numberOfCells = 1;
		noc.setNumberOfCells(numberOfCells);
		assertEquals(numberOfCells, noc.getNOC());
	}

	@Test
	public void test08_setCurrentSceneIndex() {
		ScenarioAA s = new ScenarioAA("", 1, 1);
		s.setCurrentSceneIndex(1);
		assertEquals(1, s.getCurrentSceneIndex());
		s.setCurrentScene(0);
		assertEquals(0, s.getCurrentSceneIndex());
	}

	// Ask about the indexing
	@Test
	public void test09_findSceneIndex() {
		ScenarioAA scenario = new ScenarioAA("ScenarioName", 3, 1);
		scenario.getCurrentScene().setName("s0");
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

		assertEquals(scenario.findSceneIndex("s0"), 0);
		assertEquals(scenario.findSceneIndex("s1"), 1);
		assertEquals(scenario.findSceneIndex("s2"), 2);
		assertEquals(scenario.findSceneIndex("s3"), 3);
		assertEquals(scenario.findSceneIndex("s4"), 4);

	}

	@Test
	public void test10_newCurrentScene() {
		ScenarioAA scenario = new ScenarioAA("", 1, 1);

		SceneAA Scene1 = new SceneAA(1, 1);
		scenario.addScene(Scene1); // this doesn't seem to be needed to "pass"
									// the test but it doesn't make sense to me
									// if we don't have it.
		// scenario.setCurrentSceneIndex(0); //is this necessary

		SceneAA Scene2 = new SceneAA(1, 1);
		scenario.addScene(Scene2);
		// scenario.setCurrentSceneIndex(1);

		scenario.newCurrentScene(Scene1);
		assertEquals(scenario.getCurrentScene(), Scene1);
	}
}