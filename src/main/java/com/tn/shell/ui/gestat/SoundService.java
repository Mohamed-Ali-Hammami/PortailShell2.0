package com.tn.shell.ui.gestat;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundService implements LineListener {
	  Clip clip;
	  File soundFile;
	 
	public void update(LineEvent event) {
		// TODO Auto-generated method stub

	}
   
	public void sound(String path2) { 
		//System.out.println("\n\n path2"+path2);
			
				try {
					//File audioFile = new File(path2);
					soundFile = new File(path2);
					if(path2!=null) {
				//	File audioFile2 = new File(path2);
					/*AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(audioFile2);
					AudioFormat format2 = audioStream2.getFormat();
					DataLine.Info info2 = new DataLine.Info(Clip.class, format2);
					Clip audioClip2 = (Clip) AudioSystem.getLine(info2);
					audioClip2.addLineListener(this);
					audioClip2.open(audioStream2);
					audioClip2.start();
					audioClip2.close(); */
					
					Line.Info linfo = new Line.Info(Clip.class);
				    Line line = AudioSystem.getLine(linfo);
				    clip = (Clip) line;
				    clip.addLineListener(this);
				    AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
				    clip.open(ais);
				    clip.start();
					}

					/*if(path!=null) {
						soundFile = new File(path);
						Line.Info linfo = new Line.Info(Clip.class);
					    Line line = AudioSystem.getLine(linfo);
					    clip = (Clip) line;
					    clip.addLineListener(this);
					    AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
					    clip.open(ais);
					    clip.start();
					}*/
					
				 } catch (UnsupportedAudioFileException ex) {
				} catch (LineUnavailableException ex) {
				} catch (IOException ex) {
				}

			
		}

}
