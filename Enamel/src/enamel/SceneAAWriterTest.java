package enamel;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class SceneAAWriterTest {

	@Test
	public void test01_writeQuestion() {
		SceneAA scene = new SceneAA(1, 1);
		Boolean isFirstScene = true; //idk if this is okay to put, nothing changes if initialized to false
		SceneAAWriter sceneWriter = new SceneAAWriter(scene, isFirstScene);

		String Question = "Is this a question?";
		scene.setQuestion(Question);
		sceneWriter.getSceneAsTxt().add(Question);
		assertEquals(Question, scene.getQuestion());

		// Null Question
		String NullQuestion = null;
		scene.setQuestion(NullQuestion);
		sceneWriter.getSceneAsTxt().add(NullQuestion);
		assertEquals(NullQuestion, scene.getQuestion());

		// Empty (NO) Question
		String Empty = "";
		scene.setQuestion(Empty);
		sceneWriter.getSceneAsTxt().add(Empty);
		assertEquals(Empty, scene.getQuestion());
	}

	@Test
	public void test02_writePins() {
		// Create a sceneAAWriter Object, in order to do this must also create
		// scene and boolean object for the sceneAAWriter parameters
		SceneAA scene = new SceneAA(4, 1); // number of braille cells is 1
		Boolean isFirstScene = true;
		SceneAAWriter sceneWriter = new SceneAAWriter(scene, isFirstScene);

		// get the first brailleCell of the scene
		BrailleCell Cell1 = scene.getBrailleCells().get(0);
		// declare the pin state of the braille cell to be "10010000"
		Cell1.setPins("10010000");

		sceneWriter.getSceneAsTxt().add("1");
		sceneWriter.getSceneAsTxt().add("0");
		sceneWriter.getSceneAsTxt().add("0");
		sceneWriter.getSceneAsTxt().add("1");
		sceneWriter.getSceneAsTxt().add("0");
		sceneWriter.getSceneAsTxt().add("0");
		sceneWriter.getSceneAsTxt().add("0");
		sceneWriter.getSceneAsTxt().add("0");
		assertTrue(Cell1.getPinState(0));
		assertFalse(Cell1.getPinState(1));
		assertFalse(Cell1.getPinState(2));
		assertTrue(Cell1.getPinState(3));
		assertFalse(Cell1.getPinState(4));
		assertFalse(Cell1.getPinState(5));
		assertFalse(Cell1.getPinState(6));
		assertFalse(Cell1.getPinState(7));

		// get the last brailleCell of the scene
		BrailleCell Cell4 = scene.getBrailleCells().get(3);
		// declare the pin state of the braille cell to be "10010000"
		Cell4.setPins("10010101");

		sceneWriter.getSceneAsTxt().add("1");
		sceneWriter.getSceneAsTxt().add("0");
		sceneWriter.getSceneAsTxt().add("0");
		sceneWriter.getSceneAsTxt().add("1");
		sceneWriter.getSceneAsTxt().add("0");
		sceneWriter.getSceneAsTxt().add("1");
		sceneWriter.getSceneAsTxt().add("0");
		sceneWriter.getSceneAsTxt().add("1");
		assertTrue(Cell4.getPinState(0));
		assertFalse(Cell4.getPinState(1));
		assertFalse(Cell4.getPinState(2));
		assertTrue(Cell4.getPinState(3));
		assertFalse(Cell4.getPinState(4));
		assertTrue(Cell4.getPinState(5));
		assertFalse(Cell4.getPinState(6));
		assertTrue(Cell4.getPinState(7));

		// Handling a boundary condition, when we try to get button 5 but there are only 4 buttons
		try {
			//the following line is expected to fail and thus throw an exception so we expect to go to the catch block
			scene.getBrailleCells().get(4); //there is no 5th cell
			//we don't want to reach here
			fail("Should have thrown IndexOutOfBOundsException");
		} catch(IndexOutOfBoundsException e) {
			//hurray test passed
		} catch(Throwable t) { //this catches any and all other errors and exceptions that may be thrown
			t.printStackTrace();
			fail("Should have thrown IndexOutOfBOundsException instead got" + t.getMessage());
		}

	}
	
	//this method will always provide the default values contained in the getSceneAsText 
	//Useful for all the remaining tests down below 
	private List<String> getExpectedSceneBeginning(boolean first) {
		List<String> expected = new ArrayList<>();
		expected.add("");
		expected.add("/~BEGINNING-OF-SCENE=" + null);
		expected.add("/~disp-clearAll");
		if (first) {//if this is the first and only scene then just pause
			expected.add("/~pause:1");
		} else {//else this is not the only scene so when we get to a new 
			    //scene we will need to clear the pins to prepare for the next question
			expected.add("/~reset-buttons");
			expected.add("/~pause:1");
		}
		expected.add("/~disp-cell-pins:0 00000000");
		return expected;
	}
	
	//This method allows me to find what piece of content I'm missing. 
	//This has nothing to do with testing, just makes output more readable
	//Useful for all the remaining tests down below
	private void assertArrayEquals(List<String> expected, List<String> sceneAsTxt) {
		for (int i=0; i < sceneAsTxt.size(); i++) {
			if (i> expected.size()) {
				String extra = sceneAsTxt.subList(i, sceneAsTxt.size()).toString();
				fail("Did not expect these items "+extra);
			}
			assertEquals(String.format("\nExpected: %s\nReceived: %s\nExpected List %s\nReceived List %s ", 
					       expected.get(i), sceneAsTxt.get(i), expected.toString(), sceneAsTxt.toString()), 
					     expected.get(i),sceneAsTxt.get(i));
		}
		if (expected.size() > sceneAsTxt.size()) {
			String missing = expected.subList(sceneAsTxt.size(), expected.size()).toString();
			fail("Expect these missing items: "+missing);
		}
	}
	
	@Test
	public void test03_writeAudioOption1() {
		SceneAA scene = new SceneAA(1, 1);
		Boolean isFirstScene = true; 
		SceneAAWriter sceneWriter = new SceneAAWriter(scene, isFirstScene);
	
		String fileName = "filename";
		scene.setAudioNameOption1(fileName); //setting the name to filename
		List<String> expected = getExpectedSceneBeginning(isFirstScene);
		expected.add("/~pause:1");
		if (fileName != null) { 
			expected.add("/~sound:" + fileName); //setting the name to filename
		}
		expected.add("/~user-input");
		expected.add("/~END-OF-SCENE=" + null);
		assertArrayEquals(expected, sceneWriter.getSceneAsTxt());
		
		
		//Be able to write a scene (LIst of Strings, guestSceneAstTxt. simiulates the notepad file) to the attribute and be assert what ive written using he scene writer should be the same as what i expect

	}
	
	@Test
	public void test04_writeInteractions() {//"Play Correct Audio Clip"
		SceneAA scene = new SceneAA(1, 4); // number of buttons = 4
		Boolean isFirstScene = true;
		SceneAAWriter sceneWriter = new SceneAAWriter(scene, isFirstScene);
		
		String interaction = "Play Correct Audio Clip";
		scene.setInteractionPreset(1, interaction);
		assertEquals(interaction, scene.getInteractionPreset().get(1));
		
		List<String> expected = getExpectedSceneBeginning(isFirstScene);
		expected.add("/~pause:1");
		expected.add("/~skip-button:0 GOTO:1");
		expected.add("/~user-input");
		expected.add("/~GOTO:1");
		expected.add("/~sound:correct.wav");
		expected.add("/~disp-clearAll");
		expected.add("/~skip:END-OF-SCENE=" + null);
		expected.add("/~END-OF-SCENE=" + null);
		assertArrayEquals(expected,sceneWriter.getSceneAsTxt());	
		
	}
	
	@Test
	public void test05_writeInteractions() {//Interaction Text Input for "Play Correct Audio Clip"
		SceneAA scene = new SceneAA(1, 4); // number of buttons = 4
		Boolean isFirstScene = true;
		SceneAAWriter sceneWriter = new SceneAAWriter(scene, isFirstScene);
		
		String interaction = "Play Correct Audio Clip";
		scene.setInteractionPreset(1, interaction);
		assertEquals(interaction, scene.getInteractionPreset().get(1));
		
		String text = "What letter is the pin displaying";
		scene.setInteractionTextInput(1, text);
		assertEquals(text, scene.getInteractionTextInput().get(1));
		
		List<String> expected1 = new ArrayList<>();
		expected1.add("");
		expected1.add("/~BEGINNING-OF-SCENE=" + null);
		expected1.add("/~disp-clearAll");
		expected1.add("/~pause:1");
		expected1.add("/~disp-cell-pins:0 00000000");
		expected1.add("/~pause:1");
		expected1.add("/~skip-button:0 GOTO:1");
		expected1.add("/~user-input");
		expected1.add("/~GOTO:1");
		expected1.add("/~sound:correct.wav");
		expected1.add(text);
		expected1.add("/~disp-clearAll");
		expected1.add("/~skip:END-OF-SCENE=" + null);
		expected1.add("/~END-OF-SCENE=" + null);
		assertArrayEquals(expected1, sceneWriter.getSceneAsTxt());	
	}
	
	@Test
	public void test06_writeInteractions() {//Interaction Audio Name for "Play Correct Audio Clip"
		SceneAA scene = new SceneAA(1, 4); // number of buttons = 4
		Boolean isFirstScene = true;
		SceneAAWriter sceneWriter = new SceneAAWriter(scene, isFirstScene);
		
		String interaction = "Play Correct Audio Clip";
		scene.setInteractionPreset(1, interaction);
		assertEquals(interaction, scene.getInteractionPreset().get(1));
		
		String audioName = "Hakuna Matata";
		scene.setInteractionAudioNameAtIndex(audioName, 1);
		assertEquals(audioName, scene.getInteractionAudioNameAtIndex(1));
		
		List<String> expected1 = new ArrayList<>();
		expected1.add("");
		expected1.add("/~BEGINNING-OF-SCENE=" + null);
		expected1.add("/~disp-clearAll");
		expected1.add("/~pause:1");
		expected1.add("/~disp-cell-pins:0 00000000");
		expected1.add("/~pause:1");
		expected1.add("/~skip-button:0 GOTO:1");
		expected1.add("/~user-input");
		expected1.add("/~GOTO:1");
		expected1.add("/~sound:correct.wav");
		//expected1.add(text);
		expected1.add("/~pause:1");
		expected1.add("/~sound:Hakuna Matata");
		expected1.add("/~disp-clearAll");
		expected1.add("/~skip:END-OF-SCENE=" + null);
		expected1.add("/~END-OF-SCENE=" + null);
		assertArrayEquals(expected1, sceneWriter.getSceneAsTxt());	
	}
	
	@Test
	public void test07_writeInteractions() {//"Play Wrong Audio Clip"
		SceneAA scene = new SceneAA(1, 4); // number of buttons = 4
		Boolean isFirstScene = true;
		SceneAAWriter sceneWriter = new SceneAAWriter(scene, isFirstScene);
		
		String interaction = "Play Wrong Audio Clip";
		scene.setInteractionPreset(1, interaction);
		assertEquals(interaction, scene.getInteractionPreset().get(1));
		
		List<String> expected = getExpectedSceneBeginning(isFirstScene);
		expected.add("/~pause:1");
		expected.add("/~skip-button:0 GOTO:1");
		expected.add("/~user-input");
		expected.add("/~GOTO:1");
		expected.add("/~sound:wrong.wav");
		expected.add("/~disp-clearAll");
		expected.add("/~skip:END-OF-SCENE=" + null);
		expected.add("/~END-OF-SCENE=" + null);
		assertArrayEquals(expected,sceneWriter.getSceneAsTxt());
		
	}
	
	@Test
	public void test08_writeInteractions() {//Interaction Text Input for Play wrong Audio
		SceneAA scene = new SceneAA(1, 4); // number of buttons = 4
		Boolean isFirstScene = true;
		SceneAAWriter sceneWriter = new SceneAAWriter(scene, isFirstScene);
		
		String interaction = "Play Wrong Audio Clip";
		scene.setInteractionPreset(1, interaction);
		assertEquals(interaction, scene.getInteractionPreset().get(1));
		
		String text = "What letter is the pin displaying";
		scene.setInteractionTextInput(1, text);
		assertEquals(text, scene.getInteractionTextInput().get(1));
		
		List<String> expected1 = new ArrayList<>();
		expected1.add("");
		expected1.add("/~BEGINNING-OF-SCENE=" + null);
		expected1.add("/~disp-clearAll");
		expected1.add("/~pause:1");
		expected1.add("/~disp-cell-pins:0 00000000");
		expected1.add("/~pause:1");
		expected1.add("/~skip-button:0 GOTO:1");
		expected1.add("/~user-input");
		expected1.add("/~GOTO:1");
		expected1.add("/~sound:wrong.wav");
		expected1.add(text);
		expected1.add("/~disp-clearAll");
		expected1.add("/~skip:END-OF-SCENE=" + null);
		expected1.add("/~END-OF-SCENE=" + null);
		assertArrayEquals(expected1, sceneWriter.getSceneAsTxt());	
	}
	
	@Test
	public void test09_writeInteractions() {//Interaction Audio Name for Play wrong Audio
		SceneAA scene = new SceneAA(1, 4); // number of buttons = 4
		Boolean isFirstScene = true;
		SceneAAWriter sceneWriter = new SceneAAWriter(scene, isFirstScene);
		
		String interaction = "Play Wrong Audio Clip";
		scene.setInteractionPreset(1, interaction);
		assertEquals(interaction, scene.getInteractionPreset().get(1));
		
		String audioName = "Hakuna Matata";
		scene.setInteractionAudioNameAtIndex(audioName, 1);
		assertEquals(audioName, scene.getInteractionAudioNameAtIndex(1));
		
		List<String> expected1 = new ArrayList<>();
		expected1.add("");
		expected1.add("/~BEGINNING-OF-SCENE=" + null);
		expected1.add("/~disp-clearAll");
		expected1.add("/~pause:1");
		expected1.add("/~disp-cell-pins:0 00000000");
		expected1.add("/~pause:1");
		expected1.add("/~skip-button:0 GOTO:1");
		expected1.add("/~user-input");
		expected1.add("/~GOTO:1");
		expected1.add("/~sound:wrong.wav");
		expected1.add("/~pause:1");
		expected1.add("/~sound:Hakuna Matata");
		expected1.add("/~disp-clearAll");
		expected1.add("/~skip:END-OF-SCENE=" + null);
		expected1.add("/~END-OF-SCENE=" + null);
		assertArrayEquals(expected1, sceneWriter.getSceneAsTxt());	
	}
	
	@Test
	public void test10_writeInteractions() {//"Skip to Next Scene"
		SceneAA scene = new SceneAA(1, 4); // number of buttons = 4
		Boolean isFirstScene = true;
		SceneAAWriter sceneWriter = new SceneAAWriter(scene, isFirstScene);
		
		String interaction = "Skip to Next Scene";
		scene.setInteractionPreset(1, interaction);
		assertEquals(interaction, scene.getInteractionPreset().get(1));
		
		List<String> expected = getExpectedSceneBeginning(isFirstScene);
		expected.add("/~pause:1");
		expected.add("/~skip-button:0 GOTO:1");
		expected.add("/~user-input");
		expected.add("/~GOTO:1");
		expected.add("/~skip:END-OF-SCENE=" + null);
		expected.add("/~END-OF-SCENE=" + null);
		assertArrayEquals(expected,sceneWriter.getSceneAsTxt());	
	}
	
	@Test
	public void test11_getSceneAsTxt() {//test to check if this scene has a repeat interaction
		SceneAA scene = new SceneAA(1, 4); // number of buttons = 4
		Boolean isFirstScene = true;
		SceneAAWriter sceneWriter = new SceneAAWriter(scene, isFirstScene);
		
		String interaction = "Repeat Question Text";
		scene.setInteractionPreset(1, interaction);
		assertEquals(interaction,scene.getInteractionPreset().get(1));
		
		List<String> expected = getExpectedSceneBeginning(isFirstScene);
		expected.add("/~repeat");
		expected.add("/~endrepeat");
		expected.add("/~pause:1");
		expected.add("/~repeat-button:0");
		expected.add("/~user-input");
		expected.add("/~END-OF-SCENE=" + null);
		assertArrayEquals(expected,sceneWriter.getSceneAsTxt());	
	}
	
	@Test
	public void test12_getSceneAsTxt() {//test to see what happens when there is more than one Scene in the Scenario List
		SceneAA scene = new SceneAA(1, 4); // number of buttons = 4
		Boolean isFirstScene = false;
		SceneAAWriter sceneWriter = new SceneAAWriter(scene, isFirstScene);
		
		String interaction = "Repeat Question Text";
		scene.setInteractionPreset(1, interaction);
		assertEquals(interaction,scene.getInteractionPreset().get(1));
		
		List<String> expected = getExpectedSceneBeginning(isFirstScene);
		expected.add("/~repeat");
		expected.add("/~endrepeat");
		expected.add("/~pause:1");
		expected.add("/~repeat-button:0");
		expected.add("/~user-input");
		expected.add("/~END-OF-SCENE=" + null);
		assertArrayEquals(expected, sceneWriter.getSceneAsTxt());	
	}
	
	@Test
	public void test13_getSceneAsTxt() {//if interaction preset != "Repeat Question Text" Line 64 and 67
		SceneAA scene = new SceneAA(1, 4); // number of buttons = 4
		Boolean isFirstScene = true;
		SceneAAWriter sceneWriter = new SceneAAWriter(scene, isFirstScene);
		
		String interaction = "Repeat Question Tex";
		scene.setInteractionPreset(1, interaction);
		assertEquals(interaction,scene.getInteractionPreset().get(1));
		
		List<String> expected = getExpectedSceneBeginning(isFirstScene);
		expected.add("/~pause:1");
		expected.add("/~skip-button:0" + " " + "GOTO:1");
		expected.add("/~user-input");
		expected.add("/~END-OF-SCENE=" + null);
		assertArrayEquals(expected,sceneWriter.getSceneAsTxt());
	}

}