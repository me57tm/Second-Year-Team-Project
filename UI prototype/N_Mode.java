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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class N_Mode
{
	private final Stage stage = new Stage();

	public N_Mode(String name) {

		int hp = 100;
		int Shield = 100;
		String str = "Fire speed up";
		String mode = "Network Mode";
		
		URL url = this.getClass().getClassLoader().getResource("application/music.mp3");
		
		Media media = new Media(url.toExternalForm());
		MediaPlayer mp = new MediaPlayer(media);
		
		try
		{	
			//Welcome!
			Alert warning = new Alert(AlertType.INFORMATION);
			warning.setHeaderText("Welcome!");
			warning.setContentText("Welcome to Network Mode");
			warning.showAndWait();
			
			//Label
			Label hpB = new Label("HP: "+ hp);
			Label shield = new Label("Armor: "+ Shield);
			Label boost = new Label("Boost: "+ str);
			
			//HBox
			HBox hpBar = new HBox();
			hpBar.getChildren().addAll(hpB,shield,boost);
			hpBar.setAlignment(Pos.CENTER);
			hpBar.setSpacing(40);			
			
			//Menu
			MenuBar menuBar = new MenuBar();
			Menu cccmode = new Menu("Game Mode");
			Menu players = new Menu("Player");
			Menu settings = new Menu("Settings");
			Menu help = new Menu("Help");
			menuBar.getMenus().addAll(cccmode,players,settings,help);
			
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
	        cccmode.getItems().addAll(aiMode,playerMode ,separator,networkMode);
			
			//Pane
			BorderPane root = new BorderPane();
			root.setTop(menuBar);
			root.setBottom(hpBar);
			
			aiMode.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0)
				{
					Co_Mode comode = new Co_Mode(null);
					stage.close();
				}
	        });
	        
	        playerMode.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0)
				{
					PC_Mode nomode = new PC_Mode(null);
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
	        
	        players.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0)
				{
					Player nomode = new Player(null);
				}
	        });
			
			//Scene
			Scene scene = new Scene(root, 800, 600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.setTitle("Network Mode");
			stage.show();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	


}
