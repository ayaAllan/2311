package enamel;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.media.AudioClip;

public class SceneAA {

	//Testing the push/commit content
	private String testcode;
	
	// INCOMPLETE CLASS
	// It works and can be tested but alot of methods that are needed dont exist as
	// well as the implementation of certain methods need to be changed in the
	// future

	// attribute for each user input in the ScenarioCreationView
	private String question;

	private BrailleCell pins;

	private String sceneName;

	private List<Integer> buttons;

	// number of buttons
	private int nob;

	// constructor that creates the scene with the scene name, pins to display, the
	// question the user inputed, and the list of buttons
	public SceneAA(String sceneName, String question, BrailleCell pins, List<Integer> buttons) {
		this.sceneName = sceneName;
		this.question = question;
		this.pins = pins;
		this.buttons = buttons;
	}

	public SceneAA() {
		this.sceneName = null;
		this.question = null;
		this.pins = new BrailleCell();
		this.buttons = new ArrayList<Integer>();
	}

	public String getQuestion() {
		return question;
	}

	public BrailleCell getPins() {
		return pins;
	}

	public String getSceneName() {
		return sceneName;
	}

	public List<Integer> getButtons() {
		return buttons;
	}

	public void setName(String sceneName) {
		this.sceneName = sceneName;
	}

	public void setPins(char letter) throws InterruptedException {
		pins.displayCharacter(letter);
	}

	public void setPins(List<Boolean> radioButtons) {
		String pinsString = null;
		for (int i = 0; i < 8; i++) {
			if (radioButtons.get(i) == true) {
				pinsString = pinsString + "1";
			} else {
				pinsString = pinsString + "0";
			}

		}

		pins.setPins(pinsString);
	}

	public void clearPins() {
		pins.clear();
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
