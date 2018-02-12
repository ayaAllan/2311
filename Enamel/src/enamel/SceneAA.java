package enamel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.media.AudioClip;

public class SceneAA {

	// INCOMPLETE CLASS
	// It works and can be tested but alot of methods that are needed dont exist as
	// well as the implementation of certain methods need to be changed in the
	// future

	// attribute for each user input in the ScenarioCreationView
	private String question;

	private BrailleCell brailleCell;

	private String sceneName;

	private List<Integer> buttons;

	private Map<Integer, String> interactionPreset;

	private Map<Integer, String> interactionTextInput;

	// need another attrubute for interactionAudio, havent decided on the type yet

	// number of buttons
	private int nob;

	// constructor that creates the scene with the scene name, pins to display, the
	// question the user inputed, and the list of buttons
	public SceneAA(String sceneName, String question, BrailleCell bCell, List<Integer> buttons) {
		this.sceneName = sceneName;
		this.question = question;
		this.brailleCell = bCell;
		this.buttons = buttons;
		this.interactionPreset = new HashMap<Integer, String>();
		this.interactionTextInput = new HashMap<Integer, String>();

		for (int i = 1; i <= 8; i++) {
			this.interactionPreset.put(i, null);
			this.interactionTextInput.put(i, null);
		}
	}

	public SceneAA() {
		this.sceneName = null;
		this.question = null;
		this.brailleCell = new BrailleCell();
		this.buttons = new ArrayList<Integer>();
		this.interactionPreset = new HashMap<Integer, String>();
		this.interactionTextInput = new HashMap<Integer, String>();
	}

	public String getQuestion() {
		return question;
	}

	public BrailleCell getBrailleCell() {
		return this.brailleCell;
	}

	public String getSceneName() {
		return this.sceneName;
	}

	public List<Integer> getButtons() {
		return this.buttons;
	}

	public Map<Integer, String> getInteractionPreset() {
		return this.interactionPreset;
	}

	public Map<Integer, String> getInteractionTextInput() {
		return this.interactionTextInput;
	}

	public void setName(String sceneName) {
		this.sceneName = sceneName;
	}

	public void setPinsScene(char letter) throws InterruptedException {
		this.brailleCell.displayCharacter(letter);
	}

	public void setPinsScene(List<Boolean> radioButtons) {
		String pinsString = null;
		for (int i = 0; i < 8; i++) {
			if (radioButtons.get(i) == true) {
				pinsString = pinsString + "1";
			} else {
				pinsString = pinsString + "0";
			}

		}

		this.brailleCell.setPins(pinsString);
	}

	// need this to get the current pins state of the scene and return as a list of
	// boolean to use with the radio buttons
	public List<Boolean> getPinsAsBoolean(BrailleCell bCell) {

		List<Boolean> pinsAsBoolean = new ArrayList<Boolean>();

		for (int i = 0; i < bCell.getNumberOfPins(); i++) {
			Boolean tempState = this.brailleCell.getPinState(i);
			pinsAsBoolean.add(tempState);

		}

		return pinsAsBoolean;

	}

	public void clearPins() {
		this.brailleCell.clear();
	}

	public void setInteractionTextInput(int currentButtonNumber, String text) {
		this.interactionTextInput.put(currentButtonNumber, text);
	}

	public void setInteractionPreset(int currentButtonNumber, String interaction) {
		this.interactionPreset.put(currentButtonNumber, interaction);
	}

	public void audioOnButtonClick(int buttonNumber, AudioClip audio) {
		// TODO
	}

	public void audioNextScene(int buttonNumber, AudioClip audio) {
		// TODO
	}

	public void nextSceneOnClick(int buttonNumber) {
		// TODO
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public void setQuestionAudio(AudioClip audio) {
		// TODO
	}

}
