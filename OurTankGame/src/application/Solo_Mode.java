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
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import physics.Bullet;
import physics.PowerUp;
import physics.Sprite;
import physics.Tank;
import tankUI.Player;
import tankUI.TankMenu;

public class Solo_Mode {
	private final Stage stage = new Stage();

	static int randomN() {

		Random random = new Random();
		int n = random.nextInt(10);

		if (n == 1 || n == 3 || n == 5 || n == 7 || n == 9) {

			String str = "-" + random.nextInt(45);
			int a = Integer.parseInt(str);
			return a;

		} else {
			int a = random.nextInt(45);
			return a;

		}
	}

	public Solo_Mode(String name) {

		Sprite background = new Sprite("grimfandango-art/gf-islandbackground.png");
		background.position.set(500, 300);

		Tank tank = new Tank("imagesProjectAI/tank.png");
		tank.position.set(150, 300);

		PowerUp bulletPowerup = new PowerUp("Speed");
		bulletPowerup.position.set(150, 500);

		Tank enemy = new Tank("grimfandango-art/tank-red.png");
		enemy.position.set(500, 325);

		int hp = 100;
		int Shield = 100;
		int score = 99;
		String str = "Fire speed up";

		DropShadow dropshadow = new DropShadow();
		dropshadow.setRadius(10);
		dropshadow.setOffsetX(0);
		dropshadow.setOffsetY(0);
		dropshadow.setSpread(0.1);
		dropshadow.setColor(Color.BLACK);

		URL url = this.getClass().getClassLoader().getResource("application/music.mp3");

		Media media = new Media(url.toExternalForm());
		MediaPlayer mp = new MediaPlayer(media);

		try {

			// Label
			Label score1 = new Label("Score: " + score);
			score1.setFont(Font.font("Segoe Print"));
			Label hpB = new Label("HP: " + hp);
			hpB.setFont(Font.font("Segoe Print"));
			Label shield = new Label("Shield: " + Shield);
			shield.setFont(Font.font("Segoe Print"));
			Label boost = new Label("Boost: " + str);
			boost.setFont(Font.font("Segoe Print"));

			// HBox
			HBox hpBar = new HBox();
			hpBar.getChildren().addAll(hpB, shield, boost, score1);
			hpBar.setAlignment(Pos.CENTER);
			hpBar.setSpacing(40);

			// Menu
			MenuBar menuBar = new MenuBar();
			Menu rq = new Menu("Quit or Return");
			Menu players = new Menu("Player");
			Menu Audio = new Menu("Audio");
			Menu help = new Menu("Help");
			menuBar.getMenus().addAll(players, Audio, help, rq);

			MenuItem quit = new MenuItem("Quit Game");
			MenuItem returnM = new MenuItem("Return to Meun");
			rq.getItems().addAll(quit, returnM);

			returnM.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					TankMenu m1 = new TankMenu();
					stage.close();
				}
			});

			quit.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					stage.close();
				}
			});

			MenuItem volume = new MenuItem("Music off");
			MenuItem volume1 = new MenuItem("Music on");
			Audio.getItems().addAll(volume, volume1);

			MenuItem player0 = new MenuItem("Player List");
			players.getItems().addAll(player0);

			// Pane
			BorderPane root = new BorderPane();
			root.setTop(menuBar);
			root.setBottom(hpBar);
			root.setEffect(dropshadow);

			volume.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					mp.stop();
				}
			});

			volume1.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					mp.play();
				}
			});

			player0.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					Player nomode = new Player(null);
				}
			});

			root.setTop(menuBar);
			root.setBottom(hpBar);

			// Scene
			Scene scene = new Scene(root, 985, 690);
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			// stage.setMaximized(true);
			stage.setResizable(false);
			stage.setScene(scene);
			stage.setTitle("Co-operative Mode");
			stage.show();

			Canvas canvas = new Canvas(1000, 640);
			GraphicsContext context = canvas.getGraphicsContext2D();
			// Change setCenter to setLeft
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

			ArrayList<Bullet> laserListT = new ArrayList<Bullet>();
			ArrayList<Bullet> laserListE = new ArrayList<Bullet>();
			ArrayList<Bullet> oldBullets = new ArrayList<Bullet>();
			// ArrayList<Sprite> asteroidList = new ArrayList<Sprite>();

			
					
			AnimationTimer gameloop = new AnimationTimer() {

				int times = 0;
			    int framesForNow = 0;
				int oneOrMinOne  = 0;

				public void handle(long nanotime) {

					double checkRotation = enemy.rotation % 90;
					System.out.println(enemy.rotation);
					if (checkRotation == 0) {
						oneOrMinOne = 0;
						if (Math.random() < 0.01) {

							context.save();

							Bullet laserE = new Bullet("imagesProjectAI/red-circle.png", enemy);
							laserE.position.set(enemy.position.x, enemy.position.y);
							laserE.velocity.setLength(200);
							laserE.velocity.setAngle(enemy.rotation);
							laserListE.add(laserE);
						}

						times++;
						int i = times % 210;
						double randomNumber = Math.random();
						enemy.velocity.setAngle(enemy.rotation);
						enemy.velocity.setLength(10);
						if (i == 0) {
							if (randomNumber < 0.5) {
								enemy.rotation += 2;
								oneOrMinOne = 1;
							} else if (randomNumber > 0.5) {
								enemy.rotation -= 2;
								oneOrMinOne = -1;
							} 
						}
					}
					 if (oneOrMinOne == 1){
						enemy.rotation += 2;
					}
					 if (oneOrMinOne == -1) {
						enemy.rotation -= 2;
					}
					
					
					tank.move(keyPressedList,keyJustPressedList,context,laserListT);
					tank.setBulletMsg(0);
					

					// after processing user input, clear keyJustPressedList
					keyJustPressedList.clear();

					tank.update(1 / 60.0);
					enemy.update(1 / 60.0);

					// Collision Detection for Bullets
					for (Bullet laser1 : laserListE) {
						laser1.update(1 / 60.0);
						tank.collide(laser1);
						bulletPowerup.collide(laser1);
					}

					for (int n = 0; n < laserListT.size(); n++) {
						Bullet laser = laserListT.get(n);
						laser.update(1 / 60.0);

						enemy.collide(laser); // TODO: this is a marker that I edited this one

						bulletPowerup.collide(laser);
					}

					// Remove Bullets that have collided with something
					for (Bullet b : laserListT)
						if (b.hp < 0)
							oldBullets.add(b);
					for (Bullet b : laserListE)
						if (b.hp < 0)
							oldBullets.add(b);
					laserListT.removeAll(oldBullets);
					laserListE.removeAll(oldBullets);
					oldBullets.clear();

					// Render everything
					background.render(context);
					tank.render(context);
					if (bulletPowerup.hp > 1) {
						bulletPowerup.render(context);
					}
					if (bulletPowerup.hp < 1) {
						tank.velocity.setLength(800);
					}
					if (enemy.hp > 0) {
						enemy.render(context);
					}
					for (Sprite laser : laserListT) {
						laser.render(context);
					}
					for (Sprite laser : laserListE) {
						laser.render(context);
					}

				}
			};

			gameloop.start();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}