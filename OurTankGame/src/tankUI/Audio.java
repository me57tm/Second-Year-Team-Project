package tankUI;

import audio.AudioManager;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Audio {
	@SuppressWarnings("unused")
	public Audio(){
		Stage s1 =new Stage();
		s1.setTitle("Tank Game");
		s1.getIcons().add(new Image("images/icon_tank.jpg"));
		Image img  = new Image("images/tank1.png");
		ImageView imgV = new ImageView(img);
		imgV.setScaleX(2.5);
		imgV.setScaleY(2.5);
		
		AudioManager.play("testMusic", "music");
		
		Label set1 = new Label("");
		set1.setLayoutX(0);
		set1.setLayoutY(0);
		
		Slider master = new Slider(0,1,AudioManager.getMasterVolume());
		master.setLayoutX(375);
		master.setLayoutY(400);
		master.setBlockIncrement(0.1);
		master.valueProperty().addListener((observable,old,n) -> {
			AudioManager.setMasterVolume(n.floatValue());
		});
		master.setOnMouseReleased(e ->AudioManager.play("test"));
		
		Slider sound = new Slider(0,1,AudioManager.getSoundVolume());
		sound.setLayoutX(375);
		sound.setLayoutY(500);
		sound.setBlockIncrement(0.1);
		sound.valueProperty().addListener((observable,old,n) -> {
			AudioManager.setSoundVolume(n.floatValue());
		});
		sound.setOnMouseReleased(e ->AudioManager.play("test","sound"));
		
		Slider music = new Slider(0,1,AudioManager.getMusicVolume());
		music.setLayoutX(375);
		music.setLayoutY(600);
		music.setBlockIncrement(0.1);
		music.valueProperty().addListener((observable,old,n) -> {
			AudioManager.setMusicVolume(n.floatValue());
		});
		
		Label set2 = new Label();
		set2.setLayoutX(1000);
		set2.setLayoutY(1000);
		
		
		Button b4 = new Button("Back");
		b4.getStyleClass().add("font");
		b4.setPrefSize(250, 40);
		b4.setLayoutX(375);
		b4.setLayoutY(800);
		b4.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		b4.setOnAction(e -> {
			TankMenu m1 = new TankMenu();
			AudioManager.stopAll();
			s1.close();
		});
		
		Group g1 = new Group();
		g1.getChildren().addAll(set1,set2,b4,sound,music,master);
		
		StackPane sp = new StackPane(); 
		sp.getChildren().addAll(imgV,g1);
		
		Scene scene = new Scene(sp,1920,1080);
		s1.setMaximized(true);
		s1.setScene(scene);		
		s1.show();
	}
}
