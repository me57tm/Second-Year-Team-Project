package application;

import java.net.URL;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {

		launch(args);
	}
	
	static TextField fusername = new TextField();
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		Stage appStage = primaryStage;
		
		//Shadow
		DropShadow dropshadow = new DropShadow();
        dropshadow.setRadius(10);
        dropshadow.setOffsetX(0);
        dropshadow.setOffsetY(0);
        dropshadow.setSpread(0.1);
        dropshadow.setColor(Color.BLACK);
        
		
		//Label
		Label lb = new Label("Tank Battle");
		Font f = new Font("Segoe Print", 70);
		lb.setFont(f);
		lb.setTextFill(Paint.valueOf("#f2eada"));
		
		//image
		Image img = new Image("application/tank1.png");
		ImageView im = new ImageView();
		im.setImage(img);
		
		//Background
		BackgroundImage bgi = new BackgroundImage(img, null, null, null, null);
		Background bg = new Background(bgi);		
		
		//Border
		BorderStroke bs = new BorderStroke(Paint.valueOf("#4e72b8"), null, new CornerRadii(10), new BorderWidths(2));
		Border br = new Border(bs);
		
		//Button
		Button login = new Button("Start");
		login.setFont(Font.font("Segoe Print"));
		login.setBorder(br);
		
		//TextField
		fusername.setBorder(br);
		Font f1 = new Font("Segoe Print", 15);
		fusername.setFont(f1);
		
		//HBox
		HBox hb = new HBox();
		VBox vb = new VBox();
		Label username = new Label("UserName:");
		username.setFont(f1);
		username.setTextFill(Paint.valueOf("#fffffb"));
		
		hb.setAlignment(Pos.CENTER);
		hb.setSpacing(20);
		hb.getChildren().addAll(username,fusername,login);
		
		vb.getChildren().addAll(lb);
		vb.setAlignment(Pos.CENTER);
		vb.setSpacing(10);
		
		BorderPane root = new BorderPane();
		root.setBackground(bg);
		root.setBottom(hb);
		root.setCenter(vb);
		root.setEffect(dropshadow);
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Tank Battle Login");
		primaryStage.setWidth(500);
		primaryStage.setHeight(575);
		primaryStage.setResizable(false);
		primaryStage.show();
		

		login.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				String name = fusername.getText();
				int len = name.length();

				if (name.equals("") == false && len <= 8) {
					myWindow window = new myWindow(name);
					primaryStage.close();

				} else {
					Alert warning = new Alert(AlertType.WARNING);
					warning.setHeaderText("Login failed!");
					warning.setContentText("Please enter a name less than nine characters.");
					warning.showAndWait();
				}
			}
		});
	}
	
	public static String getName() {
		String names = fusername.getText();
		return names;
	}

}

class myWindow {
	
	private final Stage stage = new Stage();

	public myWindow(String name) {
		
		int hp = 100;
		int Shield = 100;
		String str = "Fire speed up";
		
		DropShadow dropshadow = new DropShadow();
        dropshadow.setRadius(10);
        dropshadow.setOffsetX(0);
        dropshadow.setOffsetY(0);
        dropshadow.setSpread(0.1);
        dropshadow.setColor(Color.BLACK);
		
		//Music
		URL url = this.getClass().getClassLoader().getResource("application/music.mp3");
		
		Media media = new Media(url.toExternalForm());
		MediaPlayer mp = new MediaPlayer(media);
		
		try
		{	
			String player = Main.getName();
			mp.play();
			
			//Welcome!
			Alert warning = new Alert(AlertType.INFORMATION);
			warning.setHeaderText("Welcome!");
			warning.setContentText("Welcome to Tank Game!!!");
			warning.showAndWait();
			
			//Label
			Label intro = new Label("Introduction: how to play the game.");
			intro.setFont(Font.font("Segoe Print"));		
			
			//Menu
			MenuBar menuBar = new MenuBar();
			Menu mode = new Menu("Game Mode");
			Menu players = new Menu("Player");
			Menu settings = new Menu("Settings");
			Menu help = new Menu("Help");
			menuBar.getMenus().addAll(mode,players,settings,help);
			
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
	        mode.getItems().addAll(aiMode,playerMode ,separator,networkMode);
	        
	        aiMode.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0)
				{
					Co_Mode comode = new Co_Mode(null);
				}
	        });
	        
	        playerMode.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0)
				{
					PC_Mode nomode = new PC_Mode(null);
				}
	        });
	        
	        networkMode.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0)
				{
					N_Mode nomode = new N_Mode(null);
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
	        ;
	        
	        author.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0)
				{
					Author au = new Author(null);
				}
	        });
	        
			//Pane
			BorderPane root = new BorderPane();
			root.setTop(menuBar);
			root.setCenter(intro);
			root.setEffect(dropshadow);
			
			//Scene
			Scene scene = new Scene(root, 640, 480);
			stage.setScene(scene);
			stage.setTitle("Main Stage");
			stage.setResizable(false);
			stage.show();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	}

