package enamel;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class SceneAATest {

	@Test
	public void test01_getAudioNameOption1() {
		SceneAA audioName = new SceneAA(1, 1);
		// Variables written in all CAPS is a naming convention for constant
		// values
		final String AUDIO_NAME_OPTION1 = "Audio1";
		audioName.setAudioNameOption1(AUDIO_NAME_OPTION1);
		assertEquals(AUDIO_NAME_OPTION1, audioName.getAudioNameOption1());
	}

	@Test
	public void test02_getInteractionAudioNameAtIndex() {

		SceneAA buttons = new SceneAA(1, 5);
		try {
			// the following line is expected to fail and thus throw an
			// exception so we expect to go to the catch block
			buttons.getInteractionAudioNameAtIndex(6);
			// we don't want to reach here
			fail("Should have thrown IndexOutOfBOundsException");
		} catch (IndexOutOfBoundsException e) {
			// hurray test passed
		} catch (Throwable t) { // this catches any and all other errors and
								// exceptions that may be thrown
			t.printStackTrace();
			fail("Should have thrown IndexOutOfBOundsException instead got" + t.getMessage());
		}

		try {
			// a boundary case, our index starts at 1 so if we have index 0 we
			// expect an excpetion to be thrown
			buttons.getInteractionAudioNameAtIndex(0);
			fail("Should have thrown IndexOutOfBOundsException");
		} catch (IndexOutOfBoundsException e) {
			// if we reach here then the test passed!
		} catch (Throwable t) {
			t.printStackTrace();
			fail("Should have thrown IndexOutOfBOundsException instead got" + t.getMessage());
		}

		SceneAA audioNameScene = new SceneAA(1, 1);
		final String AUDIO_NAME = "Audio1";
		audioNameScene.setInteractionAudioNameAtIndex(AUDIO_NAME, 1);
		assertEquals(AUDIO_NAME, audioNameScene.getInteractionAudioNameAtIndex(1));
	}

	// REVIEW
	@Test
	public void test03_audioOption1Exists() {
		SceneAA option1 = new SceneAA(1, 1);
		Boolean option1Exists = false;
		assertEquals(option1Exists, option1.audioOption1Exists().booleanValue());

		// add aduio options after each one you've added then test they been
		// true then remove os there are no audio options and should be false
	}

	@Test
	public void test04_getSceneName() {
		SceneAA sceneName = new SceneAA(1, 1);
		final String NAME = "name";
		sceneName.setName(NAME);
		assertEquals(NAME, sceneName.getSceneName());
	}

	// REVIEW
	@Test
	public void test05_getButtons() {
		SceneAA buttons = new SceneAA(1, 1);
		List<Integer> Buttons = new ArrayList<Integer>();
		assertEquals(Buttons, buttons.getButtons());
	}

	@Test
	public void test06_getNOB() {
		int nob = 8;
		SceneAA noble = new SceneAA(0, 8);
		assertEquals(nob, noble.getNOB());
	}

	@Test
	public void test07_setPinsScene() throws InterruptedException {
		// create a pinScene
		SceneAA pinScene = new SceneAA(1, 1);
		// Set the pinScene to the letter 'a' at index 0 (The first BrailleCell)
		pinScene.setPinsScene('a', 0);
		// basically let the first BrailleCell display letter 'a'
		BrailleCell brailleCell = pinScene.getBrailleCells().get(0);
		assertTrue(brailleCell.getPinState(0)); // assert the first pin of the
												// cell to true
		assertFalse(brailleCell.getPinState(1)); // assert the second pin of the
													// cell to false
		assertFalse(brailleCell.getPinState(2)); // etc...
		assertFalse(brailleCell.getPinState(3));
		assertFalse(brailleCell.getPinState(4));
		assertFalse(brailleCell.getPinState(5));
		assertFalse(brailleCell.getPinState(6));
		assertFalse(brailleCell.getPinState(7));

		// think of boundary cases, null etc....
	}

	@Test
	public void test08_setPinsScene() {
		SceneAA pinScene = new SceneAA(1, 1);
		// listAsBoolean is size 8, with index 0 - 7
		List<Boolean> listAsBoolean = new ArrayList<Boolean>(8);
		listAsBoolean.add(true);
		listAsBoolean.add(false);
		listAsBoolean.add(false);
		listAsBoolean.add(false);
		listAsBoolean.add(false);
		listAsBoolean.add(false);
		listAsBoolean.add(false);
		listAsBoolean.add(false);

		pinScene.getBrailleCells().get(0); // get first braille cell
		// set this listAsBoolean at index 0
		pinScene.setPinsScene(listAsBoolean, 0);// pin state of the first
												// braille cell is "10000000"
	}

	// REVIEW
	// testing if the pins correctly transfer from binary to boolean
	// could do all the letters which is just copy and paste but thats way to
	// much code for no reason
	@Test
	public void test09_getPinsAsBoolean() throws InterruptedException {
		SceneAA s = new SceneAA(1, 1); // SceneAA s = new SceneAA(5, 3);
		// List<BrailleCell> list = new ArrayList<BrailleCell>(); //this was
		// oroginally in the test but seems to work wout
		BrailleCell bCell1 = new BrailleCell();
		// list.add(bCell1); //this was originally in the test but seems to work
		// wout

		bCell1.displayCharacter('a');
		List<Boolean> listAsBoolean = new ArrayList<Boolean>();
		listAsBoolean.add(true);
		listAsBoolean.add(false);
		listAsBoolean.add(false);
		listAsBoolean.add(false);
		listAsBoolean.add(false);
		listAsBoolean.add(false);
		listAsBoolean.add(false);
		listAsBoolean.add(false);
		assertEquals(listAsBoolean, s.getPinsAsBoolean(bCell1));

		bCell1.displayCharacter('b');
		List<Boolean> listAsBoolean2 = new ArrayList<Boolean>();
		listAsBoolean2.add(true);
		listAsBoolean2.add(true);
		listAsBoolean2.add(false);
		listAsBoolean2.add(false);
		listAsBoolean2.add(false);
		listAsBoolean2.add(false);
		listAsBoolean2.add(false);
		listAsBoolean2.add(false);
		assertEquals(listAsBoolean2, s.getPinsAsBoolean(bCell1));

		bCell1.displayCharacter('s');
		List<Boolean> listAsBoolean3 = new ArrayList<Boolean>();
		listAsBoolean3.add(false);
		listAsBoolean3.add(true);
		listAsBoolean3.add(true);
		listAsBoolean3.add(true);
		listAsBoolean3.add(false);
		listAsBoolean3.add(false);
		listAsBoolean3.add(false);
		listAsBoolean3.add(false);
		assertEquals(listAsBoolean3, s.getPinsAsBoolean(bCell1));
	}

	@Test
	public void test10_clearPins() {
		// create a new pin with three cells and one button for default
		// This test we are only concerned about the cells
		SceneAA scene = new SceneAA(3, 1);

		// FIRST Cell
		// set the pins to 10101010
		scene.getBrailleCells().get(0).setPins("10101010");
		// assert at each index the corresponding pin is up or down
		assertEquals(scene.getBrailleCells().get(0).getPinState(0), true); // assert
																			// the
																			// 1st
																			// pin
																			// of
																			// the
																			// 1st
																			// cell
																			// to
																			// true
		assertEquals(scene.getBrailleCells().get(0).getPinState(1), false); // assert
																			// the
																			// 2nd
																			// pin
																			// of
																			// the
																			// 1st
																			// cell
																			// to
																			// false
		assertEquals(scene.getBrailleCells().get(0).getPinState(2), true); // etc.......
		assertEquals(scene.getBrailleCells().get(0).getPinState(3), false);
		assertEquals(scene.getBrailleCells().get(0).getPinState(4), true);
		assertEquals(scene.getBrailleCells().get(0).getPinState(5), false);
		assertEquals(scene.getBrailleCells().get(0).getPinState(6), true);
		assertEquals(scene.getBrailleCells().get(0).getPinState(7), false);
		// Clear the pins of the FIRST Cell --> 00000000
		scene.clearPins(0);
		// assert all pins have been cleared
		assertEquals(scene.getBrailleCells().get(0).getPinState(0), false); // assert
																			// the
																			// 1st
																			// pin
																			// of
																			// the
																			// 1st
																			// cell
																			// is
																			// false
		assertEquals(scene.getBrailleCells().get(0).getPinState(1), false); // etc....
		assertEquals(scene.getBrailleCells().get(0).getPinState(2), false);
		assertEquals(scene.getBrailleCells().get(0).getPinState(3), false);
		assertEquals(scene.getBrailleCells().get(0).getPinState(4), false);
		assertEquals(scene.getBrailleCells().get(0).getPinState(5), false);
		assertEquals(scene.getBrailleCells().get(0).getPinState(6), false);
		assertEquals(scene.getBrailleCells().get(0).getPinState(7), false);

		// SECOND Cell
		// set all the pins to 10111011
		scene.getBrailleCells().get(1).setPins("10111011");
		assertEquals(scene.getBrailleCells().get(1).getPinState(0), true);
		assertEquals(scene.getBrailleCells().get(1).getPinState(1), false);
		assertEquals(scene.getBrailleCells().get(1).getPinState(2), true);
		assertEquals(scene.getBrailleCells().get(1).getPinState(3), true);
		assertEquals(scene.getBrailleCells().get(1).getPinState(4), true);
		assertEquals(scene.getBrailleCells().get(1).getPinState(5), false);
		assertEquals(scene.getBrailleCells().get(1).getPinState(6), true);
		assertEquals(scene.getBrailleCells().get(1).getPinState(7), true);
		// Clear the pins for the SECOND Cell --> 00000000
		scene.clearPins(1);
		assertEquals(scene.getBrailleCells().get(1).getPinState(0), false);
		assertEquals(scene.getBrailleCells().get(1).getPinState(1), false);
		assertEquals(scene.getBrailleCells().get(1).getPinState(2), false);
		assertEquals(scene.getBrailleCells().get(1).getPinState(3), false);
		assertEquals(scene.getBrailleCells().get(1).getPinState(4), false);
		assertEquals(scene.getBrailleCells().get(1).getPinState(5), false);
		assertEquals(scene.getBrailleCells().get(1).getPinState(6), false);
		assertEquals(scene.getBrailleCells().get(1).getPinState(7), false);

		// THIRD CELL
		// Set the pins to 10101111
		scene.getBrailleCells().get(2).setPins("10101111");
		assertEquals(scene.getBrailleCells().get(2).getPinState(0), true);
		assertEquals(scene.getBrailleCells().get(2).getPinState(1), false);
		assertEquals(scene.getBrailleCells().get(2).getPinState(2), true);
		assertEquals(scene.getBrailleCells().get(2).getPinState(3), false);
		assertEquals(scene.getBrailleCells().get(2).getPinState(4), true);
		assertEquals(scene.getBrailleCells().get(2).getPinState(5), true);
		assertEquals(scene.getBrailleCells().get(2).getPinState(6), true);
		assertEquals(scene.getBrailleCells().get(2).getPinState(7), true);
		// Clear the pins for the THIRD Cell --> 00000000
		scene.clearPins(2);
		assertEquals(scene.getBrailleCells().get(2).getPinState(0), false);
		assertEquals(scene.getBrailleCells().get(2).getPinState(1), false);
		assertEquals(scene.getBrailleCells().get(2).getPinState(2), false);
		assertEquals(scene.getBrailleCells().get(2).getPinState(3), false);
		assertEquals(scene.getBrailleCells().get(2).getPinState(4), false);
		assertEquals(scene.getBrailleCells().get(2).getPinState(5), false);
		assertEquals(scene.getBrailleCells().get(2).getPinState(6), false);
		assertEquals(scene.getBrailleCells().get(2).getPinState(7), false);
	}

	@Test
	public void test11_setInterationTextInput() {
		SceneAA textInput = new SceneAA(1, 1);
		// sets the first button to read "No Audio File"
		textInput.setInteractionTextInput(1, "No Audio File");
		assertEquals(textInput.getInteractionTextInput().get(1), "No Audio File");
	}

	@Test
	public void test12_setInteractionPreset() {
		SceneAA interaction = new SceneAA(1, 4);
		interaction.setInteractionPreset(1, "Play Correct Audio Clip");
		assertEquals(interaction.getInteractionPreset().get(1), "Play Correct Audio Clip");
	}

	@Test
	public void test13_setQuestion() {
		SceneAA scene = new SceneAA(1, 2);
		// test to see if a question is written
		String question = "hgh";
		scene.setQuestion(question);
		assertEquals("hgh", scene.getQuestion());

		// if the question is null
		String question2 = null;
		scene.setQuestion(question2);
		assertEquals(scene.getQuestion(), question2);
	}

}