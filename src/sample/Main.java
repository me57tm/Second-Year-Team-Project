package sample;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import sample.Sprite;

public class Main extends Application
{

	public static void main(String[] args)
	{

		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception
	{

		Stage appStage = primaryStage;

		Image img = new Image("sample/tank1.png");
		ImageView im = new ImageView();
		im.setImage(img);

		Button login = new Button("Start");

		HBox hb = new HBox();
		Label username = new Label("UserName:");
		TextField fusername = new TextField();

		hb.setAlignment(Pos.CENTER);
		hb.setSpacing(20);
		hb.getChildren().addAll(username, fusername, login);

		BorderPane root = new BorderPane();
		root.setBottom(hb);
		root.setCenter(im);

		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Tank Battle Login");
		//primaryStage.setWidth(500);
		//primaryStage.setHeight(575);
		primaryStage.setMaximized(true);
		primaryStage.setResizable(false);
		primaryStage.show();

		login.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{
				String name = fusername.getText();
				int len = name.length();

				if (name.equals("") == false && len <= 8)
				{
					myWindow window = new myWindow(name);
					primaryStage.close();

				}
				else
				{
					Alert warning = new Alert(AlertType.WARNING);
					warning.setHeaderText("Login failed!");
					warning.setContentText("Please enter a name less than nine characters.");
					warning.showAndWait();
				}
			}
		});
	}

}

class myWindow
{

	private final Stage stage = new Stage();

	public myWindow(String name)
	{

		Sprite background = new Sprite("grimfandango-art/gf-islandbackground.png");
		background.position.set(600, 400);

		Sprite tank = new Sprite("imagesProjectAI/tank.png");
		tank.position.set(100, 300);

		Sprite enemy = new Sprite("imagesProjectAI/tank.png");
		enemy.position.set(900, 700);
		
		
		//System.out.println(enemy.getBoundary().toString());

		//int hp = 100;
		int Shield = 100;
		String str = "Fire speed up";
		String coMode = "Co-operative Mode";

		try
		{

			// Welcome!
			Alert warning = new Alert(AlertType.INFORMATION);
			warning.setHeaderText("Welcome!");
			warning.setContentText("Welcome to Tank Game!!!");
			warning.showAndWait();

			// Label
			Label COMode = new Label("Mode: " + coMode);
			Label hpB = new Label("HP: " + enemy.hp);
			Label shield = new Label("Shield: " + Shield);
			Label boost = new Label("Boost: " + str);

			// HBox
			HBox hpBar = new HBox();
			hpBar.getChildren().addAll(hpB, shield, boost, COMode);
			hpBar.setAlignment(Pos.CENTER);
			hpBar.setSpacing(40);

			// Menu
			MenuBar menuBar = new MenuBar();
			Menu mode = new Menu("Game Mode");
			Menu players = new Menu("Player");
			Menu settings = new Menu("Settings");
			Menu help = new Menu("Help");
			menuBar.getMenus().addAll(mode, players, settings, help);

			MenuItem author = new MenuItem("Author");
			help.getItems().addAll(author);

			MenuItem volume = new MenuItem("Volume");
			settings.getItems().addAll(volume);

			MenuItem aiMode = new MenuItem("Co-operative Mode");
			MenuItem playerMode = new MenuItem("Player Confrontation Mode");
			MenuItem networkMode = new MenuItem("Network Mode");
			SeparatorMenuItem separator = new SeparatorMenuItem();
			mode.getItems().addAll(aiMode, playerMode, separator, networkMode);

			// Pane
			BorderPane root = new BorderPane();
			root.setTop(menuBar);
			root.setBottom(hpBar);

			// Scene
			Scene scene = new Scene(root, 1800, 850);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setMaximized(true);
			stage.setScene(scene);
			stage.show();

			Canvas canvas = new Canvas(1800, 850);
			GraphicsContext context = canvas.getGraphicsContext2D();
			root.setCenter(canvas);

			// handle continuous inputs (as long as key is pressed)
			ArrayList<String> keyPressedList = new ArrayList<>();

			// handle unique inputs (once per key press)
			ArrayList<String> keyJustPressedList = new ArrayList<>();

			scene.setOnKeyPressed((KeyEvent event) -> {
				String keyName = event.getCode().toString();
				// avoid adding duplicates to the list
				if (!keyPressedList.contains(keyName))
					keyPressedList.add(keyName);
				if (!keyJustPressedList.contains(keyName))
					keyJustPressedList.add(keyName);

			});

			scene.setOnKeyReleased((KeyEvent event) -> {
				String keyName = event.getCode().toString();
				if (keyPressedList.contains(keyName))
					keyPressedList.remove(keyName);
			});

			ArrayList<Sprite> laserList = new ArrayList<Sprite>();
			//ArrayList<Sprite> asteroidList = new ArrayList<Sprite>();

			AnimationTimer gameloop = new AnimationTimer()
			{
				public void handle(long nanotime)
				{
					// process user input
					if (keyPressedList.contains("LEFT"))
					{
						tank.rotation -= 3;
					}

					if (keyPressedList.contains("RIGHT"))
						tank.rotation += 3;

					if (keyPressedList.contains("UP"))
					{
						tank.velocity.setLength(150);
						tank.velocity.setAngle(tank.rotation);
					}
					else
					{

						if (keyPressedList.contains("DOWN"))
						{
							tank.velocity.setLength(-150);
							tank.velocity.setAngle(tank.rotation);
						}
						else
						{
							tank.velocity.setLength(0);
						}
					}

					if (keyPressedList.contains("SPACE"))
					{
						context.save();

						Sprite laser = new Sprite("imagesProjectAI/red-circle.png");

						laser.position.set(tank.position.x, tank.position.y);
						laser.velocity.setLength(400);
						laser.velocity.setAngle(tank.rotation);
						laserList.add(laser);

					}
					tank.update(1 / 60.0);
					for (Sprite laser : laserList)
					{
						laser.update(1 / 60.0);
						if (enemy.isShot(laser))
						{
							System.out.println("hit");
							enemy.hp -= 5;
							System.out.println(enemy.hp);
							laserList.remove(laser);
							break;

						}
						laser.updateBullet();
					}

					background.render(context);
					tank.render(context);
					if(enemy.hp>0) {
						enemy.render(context);
					}
					for (Sprite laser : laserList)
					{
						laser.render(context);
					}
				}
			};

			gameloop.start();

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
