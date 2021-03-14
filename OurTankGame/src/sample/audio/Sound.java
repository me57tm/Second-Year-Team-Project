package sample.audio;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
	boolean playing = true;
	float vol;
	boolean loop;
	Clip clip;
	
	Sound(String path, float vol){
		this(path,vol,false);
	}

	Sound(String path, float vol, boolean loop){
		this.vol = vol;
		this.loop = loop;
		
		try {
			
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile());
			this.clip = AudioSystem.getClip();
	        clip.open(inputStream);
	        if (loop) clip.loop(Clip.LOOP_CONTINUOUSLY);
	        clip.start();
			
		} catch (UnsupportedAudioFileException e) {
			System.out.println("Sound @ "+path+" failed to play due to UnsupportedAudioFileException");
			e.printStackTrace();
			this.playing = false;
		} catch(IOException e) {
			System.out.println("Sound @ "+path+" failed to play due to IOException");
			e.printStackTrace();
			this.playing = false;
		} catch (LineUnavailableException e) {
			System.out.println("Sound @ "+path+" failed to play due to LineUnavalilableException");
			e.printStackTrace();
			this.playing = false;
		}
	}
}
