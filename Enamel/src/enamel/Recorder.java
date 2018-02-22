package enamel;

import javax.sound.sampled.*;
import java.io.*;

/**
 * I modified this recording class which is based on the class that was
 * originally created by www.codejava.net
 */
public class Recorder {
	// maximum recording duration
	static final long RECORD_TIME = 60000;

	File wavFile;

	// format of audio file, formatted as wav
	AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;

	// the line from which audio data is captured
	TargetDataLine line;

	AudioFormat getAudioFormat() {
		AudioFormat format = new AudioFormat(16000, 8, 2, true, true);
		return format;
	}

	void setFileName(String pathAndFile) {
		wavFile = new File(pathAndFile);
	}

	void start() {
		try {
			AudioFormat format = getAudioFormat();
			DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

			// checks if system supports the data line
			if (!AudioSystem.isLineSupported(info)) {
				System.exit(0);
			}
			line = (TargetDataLine) AudioSystem.getLine(info);
			line.open(format);
			line.start(); // start capturing

			AudioInputStream ais = new AudioInputStream(line);

			// start recording
			AudioSystem.write(ais, fileType, wavFile);

		} catch (LineUnavailableException ex) {
			ex.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	void finish() {
		line.stop();
		line.close();
	}
}