package audio;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound{
	boolean playing = true;
	boolean loop;
	Clip clip;
	String channel;
	
	Sound(String path, float vol){
		this(path,vol,false);
	}
	
	Sound (String path, float vol, String channel, boolean loop){
		this(path,vol,loop);
		this.channel=channel;
	}

	Sound(String path, float vol, boolean loop){
		this.loop = loop;
		this.channel = "master";
		
		try {
			
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile());
			this.clip = AudioSystem.getClip();			
	        clip.open(inputStream);
			setVolume(vol);
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
	
	public void play() {
		clip.start();
		this.playing = true;
	}
	
	public void pause() {
		clip.stop();
		this.playing = false;
	}
	
	public void stop() {
		this.playing = false;
	}
	
	public boolean isPlaying() {
		if (playing) {
			return clip.isActive();
		}
		else {
			return false;
		}
	}
	public void setVolume(float vol) {
		FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		float gainRange = gainControl.getMaximum() - gainControl.getMinimum();
		float newGain = gainControl.getMinimum() + vol * gainRange;
		gainControl.setValue(newGain);
	}
	public String getChannel() {
		return channel;
	}
}
