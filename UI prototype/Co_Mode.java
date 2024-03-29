package application;

import java.net.URL;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Co_Mode
{
	private final Stage stage = new Stage();
	

	public Co_Mode(String name) {

		int hp = 100;
		int Shield = 100;
		int score = 99;
		String str = "Fire speed up";
		String mode = "Co-operative Mode";
		
		DropShadow dropshadow = new DropShadow();
        dropshadow.setRadius(10);
        dropshadow.setOffsetX(0);
        dropshadow.setOffsetY(0);
        dropshadow.setSpread(0.1);
        dropshadow.setColor(Color.BLACK);
		
		URL url = this.getClass().getClassLoader().getResource("application/music.mp3");
		
		Media media = new Media(url.toExternalForm());
		MediaPlayer mp = new MediaPlayer(media);
		
		try
		{	
			//Welcome!
			Alert warning = new Alert(AlertType.INFORMATION);
			warning.setHeaderText("Welcome!");
			warning.setContentText("Welcome to Co-operative Mode");
			warning.showAndWait();
			
			//Label
			Label score1 = new Label("Score: " + score);
			score1.setFont(Font.font("Segoe Print"));
			Label hpB = new Label("HP: "+ hp);
			hpB.setFont(Font.font("Segoe Print"));
			Label shield = new Label("Shield: "+ Shield);
			shield.setFont(Font.font("Segoe Print"));
			Label boost = new Label("Boost: "+ str);
			boost.setFont(Font.font("Segoe Print"));
			
			//HBox
			HBox hpBar = new HBox();
			hpBar.getChildren().addAll(hpB,shield,boost,score1);
			hpBar.setAlignment(Pos.CENTER);
			hpBar.setSpacing(40);			
			
			//Menu
			MenuBar menuBar = new MenuBar();
			Menu cmode = new Menu("Game Mode");
			Menu players = new Menu("Player");
			Menu settings = new Menu("Settings");
			Menu help = new Menu("Help");
			menuBar.getMenus().addAll(cmode,players,settings,help);
			
			MenuItem author = new MenuItem("Author");
			help.getItems().addAll(author);
			
			MenuItem volume = new MenuItem("Music off");
			MenuItem volume1 = new MenuItem("Music on");
			settings.getItems().addAll(volume,volume1);
			
			MenuItem player0 = new MenuItem("Player List");
			players.getItems().addAll(player0);
			
			MenuItem aiMode = new MenuItem("Co-operative Mode");
	        MenuItem playerMode = new MenuItem("Player Confrontation Mode");
	        MenuItem networkMode = new MenuItem("Network Mode");
	        SeparatorMenuItem separator = new SeparatorMenuItem();
	        cmode.getItems().addAll(aiMode,playerMode ,separator,networkMode);
			
			//Pane
			BorderPane root = new BorderPane();
			root.setTop(menuBar);
			root.setBottom(hpBar);
			root.setEffect(dropshadow);
			
	        playerMode.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0)
				{
					PC_Mode nomode = new PC_Mode(null);
					stage.close();
				}
	        });
	        
	        networkMode.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0)
				{
					N_Mode nomode = new N_Mode(null);
					stage.close();
				}
	        });
	        
	        volume.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0)
				{
					mp.stop();
				}
	        });
	        
	        volume1.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0)
				{
					mp.play();
				}
	        });
	        
	        player0.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0)
				{
					Player nomode = new Player(null);
				}
	        });
	        
			
			//Scene
	        Scene scene = new Scene(root, 640, 480);
			stage.setScene(scene);
			stage.setTitle("Co-operative Mode");
			stage.show();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
}
