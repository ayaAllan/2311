package enamel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SceneAAWriter {

	private List<String> sceneAsTxt;
	private SceneAA scene;
	private Boolean isFirstScene;

	public SceneAAWriter(SceneAA scene, Boolean isFirstScene) {
		sceneAsTxt = new ArrayList<String>();
		this.scene = scene;
		this.isFirstScene = isFirstScene;
	}

	public void writeQuestion() {
		if (this.scene.getQuestion() == null || this.scene.getQuestion() == ("")) {
			// write nothing
		} else {
			// eventually will implement writing on seperate lines if the user hits enter to
			// space a line with a pause
			// it still works though and saves the format somehow
			sceneAsTxt.add(this.scene.getQuestion());
		}

	}

	public void writeAudioOption1() {
		if (this.scene.audioOption1Exists()) {//checks to see if the user has recorded audio 
			String fileName = this.scene.getAudioNameOption1();
			this.sceneAsTxt.add("/~sound:" + fileName); //this is what is written
		}
	}

	public void writePins() {

		sceneAsTxt.add("/~pause:1");

		for (int i = 0; i < this.scene.getBrailleCells().size(); i++) {
			BrailleCell currentCell = this.scene.getBrailleCells().get(i);
			String binaryPins = "";

			for (int j = 0; j < currentCell.getNumberOfPins(); j++) {

				if (currentCell.getPinState(j) == true) {
					binaryPins = binaryPins + "1";
				} else {
					binaryPins = binaryPins + "0";
				}
			}
			sceneAsTxt.add("/~disp-cell-pins:" + i + " " + binaryPins);
		}
	}

	public void writeInteractions() {
		Boolean hasInteraction = false;
		for (int i = 1; i <= this.scene.getNOB(); i++) {
			String interactionPreset = this.scene.getInteractionPreset().get(i);
			if (interactionPreset == ("No Interaction")) {
				// dont add a skip or repeat button
			} else if (interactionPreset.equals("Repeat Question Text")) {
				this.sceneAsTxt.add("/~repeat-button:" + (i - 1));
			} else {
				this.sceneAsTxt.add("/~skip-button:" + (i - 1) + " GOTO:" + i);
			}

			// check to see if it has an interaction at all to write /~user-input
			if (!(this.sceneAsTxt.get(i) == ("No Interaction"))) {
				hasInteraction = true;
			}
		}
		// if not there is no user input so just go to next scene
		if (hasInteraction) {
			this.sceneAsTxt.add("/~user-input");
		} else {
			this.sceneAsTxt.add("/~pause:1");
		}

		for (int j = 1; j <= this.scene.getNOB(); j++) {
			String interactionPreset = this.scene.getInteractionPreset().get(j);
			String interactionText = this.scene.getInteractionTextInput().get(j);

			if (interactionPreset == ("Play Correct Audio Clip")) {
				this.sceneAsTxt.add("/~GOTO:" + j);
				this.sceneAsTxt.add("/~sound:correct.wav");
				if (this.scene.getInteractionTextInput().get(j) == null) {

				} else {
					this.sceneAsTxt.add(interactionText);
				}
				if (this.scene.getInteractionAudioNameAtIndex(j).equals("No Audio File")) {
					// do nothing dont play audio
				} else {
					this.sceneAsTxt.add("/~pause:1");
					this.sceneAsTxt.add("/~sound:" + this.scene.getInteractionAudioNameAtIndex(j));
				}
				this.sceneAsTxt.add("/~disp-clearAll");
				this.sceneAsTxt.add("/~skip:END-OF-SCENE=" + this.scene.getSceneName());

			} else if (interactionPreset == ("Play Wrong Audio Clip")) {
				this.sceneAsTxt.add("/~GOTO:" + j);
				this.sceneAsTxt.add("/~sound:wrong.wav");
				if (this.scene.getInteractionTextInput().get(j) == null) {

				} else {
					this.sceneAsTxt.add(interactionText);
				}
				if (this.scene.getInteractionAudioNameAtIndex(j).equals("No Audio File")) {
					// do nothing dont play audio
				} else {
					this.sceneAsTxt.add("/~pause:1");
					this.sceneAsTxt.add("/~sound:" + this.scene.getInteractionAudioNameAtIndex(j));
				}
				this.sceneAsTxt.add("/~disp-clearAll");
				this.sceneAsTxt.add("/~skip:END-OF-SCENE=" + this.scene.getSceneName());

			} else if (interactionPreset == ("Skip to Next Scene")) {
				this.sceneAsTxt.add("/~GOTO:" + j);
				this.sceneAsTxt.add("/~skip:END-OF-SCENE=" + this.scene.getSceneName());
			} else if (interactionPreset == ("Repeat Question Text")) {
				// do nothing this is implemented before the scene starts

			} else {
				// do nothing

			}

		}

	}

	public List<String> getSceneAsTxt() {
		String sceneName = this.scene.getSceneName();
		this.sceneAsTxt.add("");
		this.sceneAsTxt.add("/~BEGINNING-OF-SCENE=" + sceneName);

		// now we need to check if this scene has a repeat button interaction, if it
		// does we need to surround the beggining and end with repeat code
		Boolean hasRepeatInteraction = false;
		for (int j = 1; j <= this.scene.getNOB(); j++) {
			String interactionPreset = this.scene.getInteractionPreset().get(j);
			if (interactionPreset == "Repeat Question Text") {
				hasRepeatInteraction = true;
			}
		}

		// clear pins for start of new scene
		this.sceneAsTxt.add("/~disp-clearAll");
		if (this.isFirstScene) {
			// dont reset buttons because there is nothing to reset and you will get an
			// error
		} else {
			this.sceneAsTxt.add("/~reset-buttons");
		}

		this.writePins();

		// so now if it does have repeat button add the repeat commmand line at
		// beggining and end
		if (hasRepeatInteraction) {
			this.sceneAsTxt.add("/~repeat");
		}

		this.writeQuestion();

		if (hasRepeatInteraction) {
			this.sceneAsTxt.add("/~endrepeat");
		}

		this.sceneAsTxt.add("/~pause:1");
		this.writeAudioOption1();

		this.writeInteractions();

		this.sceneAsTxt.add("/~END-OF-SCENE=" + sceneName);
		return this.sceneAsTxt;
	}
}
