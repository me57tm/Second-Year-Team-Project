package tankUI;

import java.net.URL;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Music
{
	URL url = this.getClass().getClassLoader().getResource("application/music.mp3");
	
	Media media = new Media(url.toExternalForm());
	MediaPlayer mp = new MediaPlayer(media);
	
	public void main(String[] args)
	{
		mp.setVolume(0.4);
		mp.setCycleCount(MediaPlayer.INDEFINITE);

	}
	public void playV(){ 
		mp.play();
	}
	
	public void stopV(){ 
		mp.stop();
	}

}
