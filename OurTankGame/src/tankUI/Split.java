package tankUI;

import audio.AudioManager;
import client.TankClient;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;


/**
 * The network mode distinguishes the interface between the host and guest
 */
public class Split {

	public Split() {
		try {
			
			Stage s1 =new Stage();
			s1.setTitle("Tank BattleField");
			s1.getIcons().add(new Image("images/icon_tank.jpg"));

			Image img  = new Image("images/TankMenu.jpg");
			ImageView imgV = new ImageView(img);
			
			Label set1 = new Label();
			set1.setLayoutX(0);
			set1.setLayoutY(0);
			
			Label set2 = new Label();
			set2.setLayoutX(1000);
			set2.setLayoutY(1000);
			
			Button host = new Button("As Host");
			host.getStyleClass().add("font");
			host.setPrefSize(250, 40);
			host.setLayoutX(375);
			host.setLayoutY(500);
			host.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			Button guest = new Button("As Guest");
			guest.getStyleClass().add("font");
			guest.setPrefSize(250, 40);
			guest.setLayoutX(375);
			guest.setLayoutY(600);
			guest.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			Button back = new Button("Back");
			back.getStyleClass().add("font");
			back.setPrefSize(250, 40);
			back.setLayoutX(375);
			back.setLayoutY(700);
			back.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			Group g1 = new Group();
			g1.getChildren().addAll(set1,set2,host,guest,back);
			
			StackPane sp = new StackPane(); 
			sp.getChildren().addAll(imgV,g1);
			
			Scene scene = new Scene(sp,1152,800);
			s1.setScene(scene);	
			s1.setResizable(false);
			s1.show();

			back.setOnAction(new EventHandler<ActionEvent>() {

				@SuppressWarnings("unused")
				@Override
				public void handle(ActionEvent arg0) {
					AudioManager.play("button","sound");
					StartGame sg = new StartGame();
					s1.close();
				}
			});

			host.setOnAction(new EventHandler<ActionEvent>() {

				@SuppressWarnings("unused")
				@Override
				public void handle(ActionEvent arg0) {
					AudioManager.play("button","sound");
					ServerStart ss = new ServerStart();
					s1.close();

				}
			});

			guest.setOnAction(new EventHandler<ActionEvent>() {

				@SuppressWarnings("unused")
				@Override
				public void handle(ActionEvent arg0) {
					AudioManager.play("button","sound");
					TankClient tc = new TankClient();
					s1.close();
				}
			});
			s1.setOnCloseRequest(new EventHandler<WindowEvent>() {

				@Override
				public void handle(WindowEvent arg0) {
					System.exit(0);
					s1.close();
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
