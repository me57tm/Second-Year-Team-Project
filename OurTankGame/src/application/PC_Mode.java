package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class PC_Mode
{
	private final Stage stage = new Stage();
	
	static int randomN(){

	    Random random = new Random();
	    int n = random.nextInt(10);

	   	if(n==1||n==3||n==5||n==7||n==9){

	         String str = "-"+random.nextInt(45);
	         int a = Integer.parseInt(str);
	         return a;
	         
	       }else{

	               int a = random.nextInt(45);
	               return a;
	               
	              }
	    }
	
	public PC_Mode(String name) {
		
		Sprite background = new Sprite("grimfandango-art/gf-islandbackground.png");
		background.position.set(500, 300);
		

		Sprite tank = new Sprite("imagesProjectAI/tank.png");
		tank.position.set(150, 300);

		Sprite enemy = new Sprite("imagesProjectAI/tank.png");
		enemy.position.set(500, 400);
		
		int hp = 100;
		int Shield = 100;
		int score = 99;
		String str = "Fire speed up";
		String mode = "Player Confrontation Mode";
		
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
			warning.setContentText("Welcome to Player Confrontation Mode");
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
			Menu ccmode = new Menu("Game Mode");
			Menu players = new Menu("Player");
			Menu settings = new Menu("Settings");
			Menu help = new Menu("Help");
			menuBar.getMenus().addAll(ccmode,players,settings,help);
			
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
	        ccmode.getItems().addAll(aiMode,playerMode ,separator,networkMode);
			
			//Pane
			BorderPane root = new BorderPane();
			root.setTop(menuBar);
			root.setBottom(hpBar);
			root.setEffect(dropshadow);
			
			aiMode.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0)
				{
					Co_Mode comode = new Co_Mode(null);
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
			 
			 author.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0)
					{
						Author au = new Author(null);
					}
		        });
			
			 root.setTop(menuBar);
				root.setBottom(hpBar);

				// Scene
				Scene scene = new Scene(root, 985, 690);
				//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				//stage.setMaximized(true);
				stage.setResizable(false);
				stage.setScene(scene);
				stage.setTitle("Player Confrontation Mode");
				stage.show();
				
				Canvas canvas = new Canvas(1000, 640);
				GraphicsContext context = canvas.getGraphicsContext2D();
				//Change setCenter to setLeft
				root.setLeft(canvas);
				
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

				ArrayList<Sprite> laserListT = new ArrayList<Sprite>();
				ArrayList<Sprite> laserListE = new ArrayList<Sprite>();
				//ArrayList<Sprite> asteroidList = new ArrayList<Sprite>();

				AnimationTimer gameloop = new AnimationTimer()
				{
					
					public void handle(long nanotime)
					{
						//enemy
						
						if(Math.random()< 0.015) {
							
							int rN = randomN();												
							enemy.rotation += rN;												
							enemy.velocity.setAngle(enemy.rotation);
							enemy.velocity.setLength(10);
													
						}
						
						if(Math.random()< 0.05) {
							
						context.save();		
						
						Sprite laserE = new Sprite("imagesProjectAI/red-circle.png");						
						laserE.position.set(enemy.position.x, enemy.position.y);
						laserE.velocity.setLength(200);
						laserE.velocity.setAngle(enemy.rotation);
						laserListE.add(laserE);
						
						for (Sprite laser1 : laserListE)
						{
							laser1.update(1 / 60.0);
							
							if (tank.isShot(laser1))
							{
								System.out.println("tank hit");
								tank.hp -= 10;
								System.out.println(tank.hp);
								laserListE.remove(laser1);
								break;

							}
							laser1.updateBullet();
						}
						for (Sprite laser2 : laserListE)
						{
							laser2.render(context);
						}
				
						}
						// process user input
						if (keyPressedList.contains("LEFT"))
						{
							tank.rotation -= 3;
						}

						if (keyPressedList.contains("RIGHT"))
							tank.rotation += 3;

						if (keyPressedList.contains("UP"))
						{
							tank.velocity.setAngle(tank.rotation);
							tank.velocity.setLength(50);
							
						}
						else
						{

							if (keyPressedList.contains("DOWN"))
							{
								tank.velocity.setAngle(tank.rotation);
								tank.velocity.setLength(-50);
								
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
							laser.velocity.setLength(200);
							laser.velocity.setAngle(tank.rotation);
							laserListT.add(laser);

						}
						
						tank.update(1 / 60.0);
						enemy.update(1 / 60.0);
						
						for (Sprite laser : laserListT)
						{
							laser.update(1 / 60.0);
							if (enemy.isShot(laser))
							{
								System.out.println("enemy hit");
								enemy.hp -= 5;
								System.out.println(enemy.hp);
								laserListT.remove(laser);
								break;

							}
							laser.updateBullet();
						}

						background.render(context);
						tank.render(context);
						if(enemy.hp>0) {
							enemy.render(context);
						}
						for (Sprite laser : laserListT)
						{
							laser.render(context);
						}
					}
				};

				gameloop.start();

		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	

}
