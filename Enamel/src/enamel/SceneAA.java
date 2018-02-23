package enamel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.media.AudioClip;

public class SceneAA implements Serializable{

	// INCOMPLETE CLASS

	// attribute for each user input in the ScenarioCreationView
	private String question;
	private String audioNameOption1;
	private Boolean audioExistsOption1 = false;
	private List<BrailleCell> brailleCells;
	private String sceneName;
	private List<Integer> buttons;
	private Map<Integer, String> interactionPreset;
	private Map<Integer, String> interactionTextInput;
	private Map<Integer, String> interactionAudioNames;
	private int nob;
	// need another attrubute for interactionAudio, havent decided on the type yet

	public SceneAA(int noc, int nob) {
		this.sceneName = null;
		this.question = null;
		this.nob = nob;

		this.brailleCells = new ArrayList<BrailleCell>();
		for (int i = 0; i < noc; i++) {
			BrailleCell emptyCell = new BrailleCell();
			emptyCell.setPins("00000000");
			this.brailleCells.add(emptyCell);
		}

		this.buttons = new ArrayList<Integer>();
		this.interactionPreset = new HashMap<Integer, String>();
		for (int i = 1; i <= nob; i++) {
			this.interactionPreset.put(i, "No Interaction");
		}
		this.interactionTextInput = new HashMap<Integer, String>();
		this.interactionAudioNames = new HashMap<Integer, String>();
		for (int i = 1; i <=nob; i ++) {
			this.interactionAudioNames.put(i, "No Audio File");
		}
		
	}

	public String getQuestion() {
		return question;
	}
	
	public String getAudioNameOption1() {
		return this.audioNameOption1;
	}
	
	public String getInteractionAudioNameAtIndex(int i) {
		if(i > nob) {
			throw new IndexOutOfBoundsException();
		} else {
			return this.interactionAudioNames.get(i);
		}

	}
	
	public Boolean audioOption1Exists() {
		return this.audioExistsOption1;
	}

	public List<BrailleCell> getBrailleCells() {
		return this.brailleCells;
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
	
	public int getNOB() {
		return this.nob;
	}

	public void setName(String sceneName) {
		this.sceneName = sceneName;
	}
	
	public void setAudioNameOption1(String fileName) {
		this.audioExistsOption1 = true;
		this.audioNameOption1 = fileName;
	}
	
	public void setInteractionAudioNameAtIndex(String fileName, int index) {
		this.interactionAudioNames.put(index, fileName);
	}

	public void setPinsScene(char letter, int index) throws InterruptedException {
		this.brailleCells.get(index).displayCharacter(letter);
	}

	public void setPinsScene(List<Boolean> radioButtons, int index) {
		String pinsString = null;
		for (int i = 0; i < 8; i++) {
			if (radioButtons.get(i) == true) {
				pinsString = pinsString + "1";
			} else {
				pinsString = pinsString + "0";
			}
		}
		this.brailleCells.get(index).setPins(pinsString);
	}

	// need this to get the current pins state of the scene and return as a list of
	// boolean to use with the radio buttons
	public List<Boolean> getPinsAsBoolean(BrailleCell bCell) {

		List<Boolean> pinsAsBoolean = new ArrayList<Boolean>();
		for (int i = 0; i < bCell.getNumberOfPins(); i++) {
			Boolean tempState = bCell.getPinState(i);
			pinsAsBoolean.add(tempState);
		}
		return pinsAsBoolean;
	}

	public void clearPins(int index) {
		this.brailleCells.get(index).clear();
	}

	public void setInteractionTextInput(int currentButtonNumber, String text) {
		this.interactionTextInput.put(currentButtonNumber, text);
	}

	public void setInteractionPreset(int currentButtonNumber, String interaction) {
		this.interactionPreset.put(currentButtonNumber, interaction);
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public void setQuestionAudio(AudioClip audio) {
		// TODO
	}

}
