package tankUI;

import audio.AudioManager;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Audio {
	@SuppressWarnings("unused")
	public Audio(){
		Stage s1 =new Stage();
		s1.setTitle("Tank BattleField");
		s1.getIcons().add(new Image("images/icon_tank.jpg"));
		Image img  = new Image("images/TankMenu.jpg");
		ImageView imgV = new ImageView(img);
		
		//AudioManager.play("music", "music");
		
		Label set1 = new Label("");
		set1.setLayoutX(0);
		set1.setLayoutY(0);
		
		
		
		Slider master = new Slider(0,1,AudioManager.getMasterVolume());
		master.setLayoutX(375);
		master.setLayoutY(450);
		master.setPrefSize(250, 40);
		master.setBlockIncrement(0.1);
		master.valueProperty().addListener((observable,old,n) -> {
			AudioManager.setMasterVolume(n.floatValue());
		});
		master.setOnMouseReleased(e ->AudioManager.play("test"));
		
		Label lblMaster = new Label("Master Volume");
		lblMaster.getStyleClass().add("font");
		lblMaster.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		lblMaster.setLayoutX(375);
		lblMaster.setLayoutY(420);
		
		Slider sound = new Slider(0,1,AudioManager.getSoundVolume());
		sound.setLayoutX(375);
		sound.setLayoutY(550);
		sound.setPrefSize(250, 40);
		sound.setBlockIncrement(0.1);
		sound.valueProperty().addListener((observable,old,n) -> {
			AudioManager.setSoundVolume(n.floatValue());
		});
		sound.setOnMouseReleased(e ->AudioManager.play("test","sound"));
		
		Label lblSound = new Label("Sound Volume");
		lblSound.getStyleClass().add("font");
		lblSound.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		lblSound.setLayoutX(375);
		lblSound.setLayoutY(520);
		
		Slider music = new Slider(0,1,AudioManager.getMusicVolume());
		music.setLayoutX(375);
		music.setLayoutY(650);
		music.setPrefSize(250, 40);
		music.setBlockIncrement(0.1);
		music.valueProperty().addListener((observable,old,n) -> {
			AudioManager.setMusicVolume(n.floatValue());
		});
		Label lblMusic = new Label("Music Volume");
		lblMusic.getStyleClass().add("font");
		lblMusic.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		lblMusic.setLayoutX(375);
		lblMusic.setLayoutY(620);
		
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
			AudioManager.play("button","sound");
			TankMenu m1 = new TankMenu();
			//AudioManager.stopAll();
			s1.close();
		});
		s1.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent arg0) {
				System.exit(0);
				s1.close();
			}
		});
		
		Group g1 = new Group();
		g1.getChildren().addAll(set1,set2,b4,sound,music,master,lblMaster,lblSound,lblMusic);
		
		StackPane sp = new StackPane(); 
		sp.getChildren().addAll(imgV,g1);
		
		Scene scene = new Scene(sp,1152,800);
		s1.setScene(scene);	
		s1.setResizable(false);
		s1.show();
	}
}
