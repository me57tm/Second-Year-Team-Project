package sample.audio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class AudioManager {
	static float masterVolume = 1;
	static boolean mute = false;
	static ArrayList<Sound> sounds = new ArrayList<Sound>();
	static final Map<String,String> SOUND_TABLE;
	static {
		Map<String,String> setupSoundTable = new HashMap<String,String>();
		setupSoundTable.put("test", "sounds/simpleBeep.wav");//This allows us to play sounds by just using 'play("test")' Add more bindings here to add more sounds!
		SOUND_TABLE = Collections.unmodifiableMap(setupSoundTable);
	}
	
	static public void play(String soundName) {
		Sound s = new Sound(SOUND_TABLE.get(soundName),masterVolume);
	}
}
