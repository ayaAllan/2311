package enamel;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class SceneAATest {

	// @Mark im sorry but I changed the constructor for the app so all the test
	// cases were failing, methods can be tested now as well as the constructor.
	// Try
	// to group the 'one line of code' getters together and the 'one line of
	// code'
	// setters also in a seperate test case together. All the other methods
	// should
	// have their own test cases (at least one)

	@Test
	public void test02_getSceneName() {
		// final String sceneName = "Scene1";
	}

	@Test
	public void test03_setNametest() {
		// final String sceneName = "Lesson 1";
	}

	@Test
	public void test07_getNOB() {
		int nob = 8;
		SceneAA noble = new SceneAA(0, 8);
		assertEquals(nob, noble.getNOB());
	}

	@Test
	public void test03_getSceneName() {
		final String sceneName = "Scene 1";

	}

	@Test
	public void test04_getButtonsNull() {
		List<Integer> buttons = new ArrayList<>();
	}

	@Test
	public void test05_getButtonsEmpty() {
		List<Integer> buttons = new ArrayList<>();
	}

	@Test
	public void test06_getButtonsNonEmpty() {
		List<Integer> buttons = Arrays.asList(1, 2, 3, 4, 5);
	}

	@Test
	public void test09_getSceneName() {
		SceneAA sceneName = new SceneAA(1, 1);
		String name = "name";
		sceneName.setName(name);
		assertEquals(name, sceneName.getSceneName());
	}

	@Test
	public void test08_setQuestion() {
		SceneAA scene = new SceneAA(1, 2);
		String question = "hgh";
		scene.setQuestion(question);
		assertEquals(question, scene.getQuestion());
	}

	@Test
	public void setPinsScene() {
		SceneAA pin = new SceneAA(1, 1);
		pin.getBrailleCells().get(0).setPins("10000001");
		assertEquals(pin.getBrailleCells().get(0).getPinState(0), true);
		assertEquals(pin.getBrailleCells().get(0).getPinState(1), false);
		assertEquals(pin.getBrailleCells().get(0).getPinState(2), false);
		assertEquals(pin.getBrailleCells().get(0).getPinState(3), false);
		assertEquals(pin.getBrailleCells().get(0).getPinState(4), false);
		assertEquals(pin.getBrailleCells().get(0).getPinState(5), false);
		assertEquals(pin.getBrailleCells().get(0).getPinState(6), false);
		assertEquals(pin.getBrailleCells().get(0).getPinState(7), true);
	}

	@Test
	public void test10_clearPins() {
		SceneAA scene = new SceneAA(3, 1);
		scene.getBrailleCells().get(0).setPins("10101010");
		assertEquals(scene.getBrailleCells().get(0).getPinState(0), true);
		assertEquals(scene.getBrailleCells().get(0).getPinState(1), false);
		assertEquals(scene.getBrailleCells().get(0).getPinState(2), true);
		assertEquals(scene.getBrailleCells().get(0).getPinState(3), false);
		assertEquals(scene.getBrailleCells().get(0).getPinState(4), true);
		assertEquals(scene.getBrailleCells().get(0).getPinState(5), false);
		assertEquals(scene.getBrailleCells().get(0).getPinState(6), true);
		assertEquals(scene.getBrailleCells().get(0).getPinState(7), false);
		scene.clearPins(0);
		assertEquals(scene.getBrailleCells().get(0).getPinState(0), false);
		assertEquals(scene.getBrailleCells().get(0).getPinState(1), false);
		assertEquals(scene.getBrailleCells().get(0).getPinState(2), false);
		assertEquals(scene.getBrailleCells().get(0).getPinState(3), false);
		assertEquals(scene.getBrailleCells().get(0).getPinState(4), false);
		assertEquals(scene.getBrailleCells().get(0).getPinState(5), false);
		assertEquals(scene.getBrailleCells().get(0).getPinState(6), false);
		assertEquals(scene.getBrailleCells().get(0).getPinState(7), false);
		scene.getBrailleCells().get(1).setPins("10111011");
		scene.clearPins(1);
		assertEquals(scene.getBrailleCells().get(1).getPinState(0), false);
		assertEquals(scene.getBrailleCells().get(1).getPinState(1), false);
		assertEquals(scene.getBrailleCells().get(1).getPinState(2), false);
		assertEquals(scene.getBrailleCells().get(1).getPinState(3), false);
		assertEquals(scene.getBrailleCells().get(1).getPinState(4), false);
		assertEquals(scene.getBrailleCells().get(1).getPinState(5), false);
		assertEquals(scene.getBrailleCells().get(1).getPinState(6), false);
		assertEquals(scene.getBrailleCells().get(1).getPinState(7), false);
		scene.getBrailleCells().get(2).setPins("10101111");
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

	// testing if the pins correctly transfer from binary to boolean
	// could do all the letters which is just copy and paste but thats way to
	// much
	// code for no reason
	@Test
	public void getPinsAsBooleanTest() throws InterruptedException {
		List<BrailleCell> list = new ArrayList<BrailleCell>();
		BrailleCell bCell1 = new BrailleCell();
		bCell1.displayCharacter('a');
		list.add(bCell1);

		List<Boolean> listAsBoolean = new ArrayList<Boolean>();
		listAsBoolean.add(true);
		listAsBoolean.add(false);
		listAsBoolean.add(false);
		listAsBoolean.add(false);
		listAsBoolean.add(false);
		listAsBoolean.add(false);
		listAsBoolean.add(false);
		listAsBoolean.add(false);

		SceneAA s = new SceneAA(5, 3);
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
	public void setInteractionPreset() {
		SceneAA interaction = new SceneAA(1, 4);
		interaction.setInteractionPreset(1, "Play Correct Audio Clip");
		assertEquals(interaction.getInteractionPreset().get(1), "Play Correct Audio Clip");
	}

	@Test
	public void setInterationTextInput() {
		SceneAA textInput = new SceneAA(1, 1);
		textInput.setInteractionTextInput(1, "No Audio File");
		assertEquals(textInput.getInteractionTextInput().get(1), "No Audio File");
	}

	@Test
	public void getButtons() {
		SceneAA buttons = new SceneAA(1, 1);
		List<Integer> Buttons = Arrays.asList();
		assertEquals(buttons.getButtons(), Buttons);
	}

	@Test
	public void getAudioNameOption1() {
		SceneAA audioName = new SceneAA(1, 1);
		String audioNameOption1 = "Audio1";
		audioName.setAudioNameOption1(audioNameOption1);
		assertEquals(audioName.getAudioNameOption1(), "Audio1");
	}

	@Test
	public void getInteractionAudioNameAtIndex() {
		SceneAA audioName = new SceneAA(1, 1);
		audioName.setInteractionAudioNameAtIndex("Audio1", 1);
		assertEquals(audioName.getAudioNameOption1(), "Audio1");
	}

	@Test
	public void audioOption1Exists() {
		SceneAA option1 = new SceneAA(1, 1);
		option1.audioOption1Exists().booleanValue();
	}
}