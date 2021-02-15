package application;

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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {

		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		Stage appStage = primaryStage;
		
		Image img = new Image("application/tank1.png");
		ImageView im = new ImageView();
		im.setImage(img);

		Button login = new Button("Start");
		
		HBox hb = new HBox();
		Label username = new Label("UserName:");
		TextField fusername = new TextField();
		
		hb.setAlignment(Pos.CENTER);
		hb.setSpacing(20);
		hb.getChildren().addAll(username,fusername,login);
		
		
		BorderPane root = new BorderPane();
		root.setBottom(hb);
		root.setCenter(im);

		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Tank Battle Login");
		primaryStage.setWidth(500);
		primaryStage.setHeight(575);
		primaryStage.setResizable(false);
		primaryStage.show();

		//这一部分实现的页面跳转。
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

}

class myWindow {
	
	private final Stage stage = new Stage();

	public myWindow(String name) {

		int hp = 100;
		int Shield = 100;
		String str = "Fire speed up";
		
		try
		{	
			//Welcome!
			Alert warning = new Alert(AlertType.INFORMATION);
			warning.setHeaderText("Welcome!");
			warning.setContentText("Welcome to Tank Game!!!");
			warning.showAndWait();
			
			//Label
			Label hpB = new Label("HP: "+ hp);
			Label shield = new Label("Shield: "+ Shield);
			Label boost = new Label("Boost: "+ str);
			
			//HBox
			HBox hpBar = new HBox();
			hpBar.getChildren().addAll(hpB,shield,boost);
			hpBar.setAlignment(Pos.CENTER);
			hpBar.setSpacing(40);			
			
			//Menu
			MenuBar menuBar = new MenuBar();
			Menu mode = new Menu("Game Mode");
			Menu players = new Menu("Player");
			Menu settings = new Menu("Settings");
			Menu help = new Menu("Help");
			menuBar.getMenus().addAll(mode,players,settings,help);
			
			MenuItem author = new MenuItem("Author");
			help.getItems().addAll(author);
			
			MenuItem volume = new MenuItem("Volume");
			settings.getItems().addAll(volume);
			
			MenuItem aiMode = new MenuItem("Co-operative Mode");
	        MenuItem playerMode = new MenuItem("Player Confrontation Mode");
	        MenuItem networkMode = new MenuItem("Network Mode");
	        SeparatorMenuItem separator = new SeparatorMenuItem();
	        mode.getItems().addAll(aiMode,playerMode ,separator,networkMode);
			
			//Pane
			BorderPane root = new BorderPane();
			root.setTop(menuBar);
			root.setBottom(hpBar);
			
			//Scene
			Scene scene = new Scene(root, 800, 600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	}

