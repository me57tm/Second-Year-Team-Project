package tankUI;


import audio.AudioManager;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class TankMenu {
	/**
	 * The main interface of the game
	 */
	@SuppressWarnings("unused")
	public TankMenu() {
		Stage s1 =new Stage();
		s1.setTitle("Tank BattleField");
		s1.getIcons().add(new Image("images/icon_tank.jpg"));
		Image img  = new Image("images/TankMenu.jpg");
		ImageView imgV = new ImageView(img);
		//imgV.setScaleX(2.5);
		//imgV.setScaleY(2.5);
		AudioManager.stop("fightMusic");
		if (!AudioManager.isPlaying("music"))
			AudioManager.play("music","music");
		
		Label set1 = new Label();
		set1.setLayoutX(0);
		set1.setLayoutY(0);
		
		Label set2 = new Label();
		set2.setLayoutX(1000);
		set2.setLayoutY(1000);
				
		Button b1 = new Button("Start Game");
		b1.getStyleClass().add("font");
		
		b1.setPrefSize(250, 40);
		b1.setLayoutX(375);
		b1.setLayoutY(500);
		b1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		b1.setOnAction(e -> {
			AudioManager.play("button","sound");
			StartGame SG = new StartGame();
			s1.close();
		});
		
		Button b2 = new Button("Audio");
		b2.getStyleClass().add("font");
		
		b2.setPrefSize(250, 40);
		b2.setLayoutX(380);
		b2.setLayoutY(600);
		b2.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		b2.setOnAction(e -> {
			AudioManager.play("button","sound");
			Audio A = new Audio();
			s1.close();
		});
		
		Button b3 = new Button("Introduction");
		b3.getStyleClass().add("font");
		
		b3.setPrefSize(250, 40);
		b3.setLayoutX(375);
		b3.setLayoutY(700);
		b3.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		b3.setOnAction(e -> {
			AudioManager.play("button","sound");
//			String paragraph = " 'W' moves up, 'S' moves down, 'A' moves left, 'D' moves right and 'J' shoots bullet the side you're facing ";
//			Auto_window Aw = new Auto_window(5000, paragraph, new String("Introduction"), true);
			Introduction I = new Introduction();
			s1.close();
		});
		
		Button b4 = new Button("Quit Game");
		b4.getStyleClass().add("font");
		b4.setPrefSize(250, 40);
		b4.setLayoutX(375);
		b4.setLayoutY(800);
		b4.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		b4.setOnAction(e -> {
			AudioManager.play("button","sound");
			s1.close();
			System.exit(0);
		});
		s1.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent arg0) {
				System.exit(0);
				s1.close();
			}
		});
		
		Group g1 = new Group();
		g1.getChildren().addAll(set1,set2,b1,b2,b3,b4);
		
		StackPane sp = new StackPane(); 
		sp.getChildren().addAll(imgV,g1);
		
		Scene scene = new Scene(sp,1152,800);
		s1.setScene(scene);	
		s1.setResizable(false);
		s1.show();
	}
}
