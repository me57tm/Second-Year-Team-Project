package audio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class AudioManager {
	private static float masterVolume = 0.6f;
	private static float musicVolume = 1;
	private static float soundVolume = 1;
	private static boolean mute = false;
	private static ArrayList<Sound> sounds = new ArrayList<Sound>();
	private static final Map<String,String> SOUND_TABLE;
	static {
		Map<String,String> setupSoundTable = new HashMap<String,String>();//This allows us to play sounds by just using 'play("test")' Add more bindings here to add more sounds!
		setupSoundTable.put("test", "src/sounds/simpleBeep.wav");//Generated with ChipTone: https://sfbgames.itch.io/chiptone
		setupSoundTable.put("shoot", "src/sounds/8BitLaser.wav");//Generated with ChipTone: https://sfbgames.itch.io/chiptone
		setupSoundTable.put("explode", "src/sounds/8BitExplosion.wav");//Generated with ChipTone: https://sfbgames.itch.io/chiptone
		setupSoundTable.put("fightMusic", "src/sounds/Boomerang_David_Renda.wav");//in the name: https://www.fesliyanstudios.com/royalty-free-music/download/boomerang/751
		setupSoundTable.put("button", "src/sounds/type-machine.wav");//https://freesound.org/people/Danelle150055Venter/sounds/326357/
		setupSoundTable.put("music", "src/sounds/7.wav");//
		SOUND_TABLE = Collections.unmodifiableMap(setupSoundTable);
	}

	//Overwrite Default construction, this class is "static" and will be the same everywhere, constructing this class does not make sense
	private AudioManager(){
		return;
	}

	static public void play(String soundName) {
		if (!mute) {
			Sound s = new Sound(SOUND_TABLE.get(soundName),masterVolume);
			sounds.add(s);
		}
		cleanUp();
	}

	static public void play(String soundName, String track) {
		if (!mute) {
			Sound s;
			cleanUp();
			switch (track){
				case "sound":
					s = new Sound(SOUND_TABLE.get(soundName),masterVolume*soundVolume,"sound",false);
					break;
				case "music":
					s = new Sound(SOUND_TABLE.get(soundName),masterVolume*musicVolume,"music",true);
					break;
				default:
					s = new Sound(SOUND_TABLE.get(soundName),masterVolume);
					System.out.println("Audio Manager Warning: A sound was played on a non-existant track. Defaulting to \"Master\".");
			}
			sounds.add(s);
		}
	}
	
	static public void stopAll() {
		for (Sound s:sounds) {
			s.stop();
		}
		sounds.clear();
	}
	
	static public void stop(String soundName) {
		String soundPath = SOUND_TABLE.get(soundName);
		for (Sound s:sounds) {
			if (s.getPath().equals(soundPath)) 
				s.stop();
		}
	}
	
	static public void cleanUp() {
		if (!mute) sounds.removeIf(s -> (!s.isPlaying()));
	}

	public static float getMasterVolume() {
		return masterVolume;
	}

	public static void setMasterVolume(float masterVolume) {
		AudioManager.masterVolume = masterVolume;
		for (Sound s : sounds) {
			if (s.getChannel().equals("sound")) {
				s.setVolume(masterVolume*soundVolume);
			}
			if (s.getChannel().equals("music")) {
				s.setVolume(masterVolume*musicVolume);
			}
		}
	}

	public static float getMusicVolume() {
		return musicVolume;
	}

	public static void setMusicVolume(float musicVolume) {
		AudioManager.musicVolume = musicVolume;
		for (Sound s : sounds) {
			if (s.getChannel().equals("music")) {
				s.setVolume(masterVolume*musicVolume);
			}
		}
	}

	public static float getSoundVolume() {
		return soundVolume;
	}

	public static void setSoundVolume(float soundVolume) {
		AudioManager.soundVolume = soundVolume;
		for (Sound s : sounds) {
			if (s.getChannel().equals("sound")) {
				s.setVolume(masterVolume*soundVolume);
			}
		}
	}

	public static boolean isMute() {
		return mute;
	}

	public static void mute() {
		AudioManager.mute = true;
			for (Sound s : sounds) {
				s.pause();
			}
		
	}
	
	public static boolean isPlaying(String soundName) {
		String soundPath = SOUND_TABLE.get(soundName);
		boolean out = false;
		for (Sound s : sounds) {
			if (s.getPath().equals(soundPath)) {
				out = true;
				break;
			}
		}
		return out;
	}
	
	public static void unmute() {
		AudioManager.mute = false;
		for (Sound s : sounds) {
			s.play();
		}
	}
	
	
	public static void toggleMute() {
		if (mute) unmute();
		else mute();
	}
}
