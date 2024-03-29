package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import audio.AudioManager;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import physics.Bullet;
import physics.Layer;
import physics.Map;
import physics.PowerUp;
import physics.Sprite;
import physics.Tank;
import physics.Tile;
import tankUI.TankMenu;

/**
 * Single player mode, players and AI battle
 */
public class solo_Mode {
	private final Stage stage = new Stage();

	public solo_Mode(int difficulty, boolean isWASD) {

		final int TOTALGAMETIME = 90;

		AudioManager.stop("music");
		AudioManager.play("fightMusic", "music");

		Map map = new Map();

		Layer l1 = new Layer("background", map.MAP_WIDTH_IN_TILES, map.MAP_HEIGHT_IN_TILES);
		Tile bg = new Tile("art/texture-bg.png", true);

		// the background tile is added to every cell of the layer, so we iterate while
		// adding it
		for (int i = 0; i < l1.widthInTiles; i++) {
			for (int j = 0; j < l1.heightInTiles; j++) {
				l1.addTile(bg, i, j);
			}
		}

		Layer l2 = new Layer("walls", map.MAP_WIDTH_IN_TILES, map.MAP_HEIGHT_IN_TILES);

		Tile areaWallUpperLeft = new Tile("art/areawall-upper-left.png", false);
		Tile areaWallUpperMid = new Tile("art/areawall-upper-mid.png", false);
		Tile areaWallUpperRight = new Tile("art/areawall-upper-right.png", false);
		Tile areaWallMidLeft = new Tile("art/areawall-mid-left.png", false);
		Tile areaWallMidRight = new Tile("art/areawall-mid-right.png", false);
		Tile areaWallLowLeft = new Tile("art/areawall-lower-left.png", false);
		Tile areaWallLowMid = new Tile("art/areawall-lower-mid.png", false);
		Tile areaWallLowRight = new Tile("art/areawall-lower-right.png", false);

		Tile wallLeft = new Tile("art/gamewall-left.png", false);
		Tile wallMid = new Tile("art/gamewall-mid.png", false);
		Tile wallRight = new Tile("art/gamewall-right.png", false);
		Tile wallUp = new Tile("art/gamewall-up.png", false);
		Tile wallDown = new Tile("art/gamewall-down.png", false);
		Tile wallVertMid = new Tile("art/gamewall-vertmid.png", false);

		Tile leavesUpperLeft = new Tile("art/leaves-upper-left.png", true);
		Tile leavesUpperMid = new Tile("art/leaves-upper-mid.png", true);
		Tile leavesUpperRight = new Tile("art/leaves-upper-right.png", true);
		Tile leavesMidLeft = new Tile("art/leaves-mid-left.png", true);
		Tile leavesMid = new Tile("art/leaves-mid.png", true);
		Tile leavesMidRight = new Tile("art/leaves-mid-right.png", true);
		Tile leavesLowLeft = new Tile("art/leaves-low-left.png", true);
		Tile leavesLowMid = new Tile("art/leaves-low-mid.png", true);
		Tile leavesLowRight = new Tile("art/leaves-low-right.png", true);

		// adding the wall tiles according to their positioning on the drawn map (this
		// is hardcoded)

		for (int i = 0; i < map.MAP_WIDTH_IN_TILES; i++) {
			for (int j = 0; j < map.MAP_WIDTH_IN_TILES; j++) {
				if (i == 0 && j == 0) {
					l2.addTile(areaWallUpperLeft, i, j);
				} else {
					if (i == 35 && j == 0) {
						l2.addTile(areaWallUpperRight, i, j);
					} else {
						if (j == 0) {
							l2.addTile(areaWallUpperMid, i, j);
						}
					}
				}
			}
		}

		for (int i = 0; i < map.MAP_WIDTH_IN_TILES; i++) {
			for (int j = 0; j < map.MAP_WIDTH_IN_TILES; j++) {
				if (i == 0 && j == 23) {
					l2.addTile(areaWallLowLeft, i, j);
				} else {
					if (i == 35 && j == 23) {
						l2.addTile(areaWallLowRight, i, j);
					} else {
						if (j == 23) {
							l2.addTile(areaWallLowMid, i, j);
						}
					}
				}
			}
		}

		for (int j = 1; j < map.MAP_HEIGHT_IN_TILES - 1; j++) {
			l2.addTile(areaWallMidLeft, 0, j);
		}

		for (int j = 1; j < map.MAP_HEIGHT_IN_TILES - 1; j++) {
			l2.addTile(areaWallMidRight, 35, j);
		}

		// adding the leaves-upper left
		for (int i = 2; i < 8; i++) {
			if (i == 2) {
				l2.addTile(leavesUpperLeft, i, 2);
			} else if (i == 7) {
				l2.addTile(leavesUpperRight, i, 2);
			} else {
				l2.addTile(leavesUpperMid, i, 2);
			}
		}

		for (int i = 2; i < 8; i++) {
			if (i == 2) {
				l2.addTile(leavesLowLeft, i, 7);
			} else if (i == 7) {
				l2.addTile(leavesLowRight, i, 7);
			} else {
				l2.addTile(leavesLowMid, i, 7);
			}
		}

		for (int j = 3; j < 7; j++) {
			l2.addTile(leavesMidLeft, 2, j);
		}

		for (int j = 3; j < 7; j++) {
			l2.addTile(leavesMidRight, 7, j);
		}

		for (int i = 3; i < 7; i++) {
			for (int j = 3; j < 7; j++) {
				l2.addTile(leavesMid, i, j);
			}
		}

		// adding the leaves-lower right
		for (int i = 28; i < 34; i++) {
			if (i == 28) {
				l2.addTile(leavesUpperLeft, i, 16);
			} else if (i == 33) {
				l2.addTile(leavesUpperRight, i, 16);
			} else {
				l2.addTile(leavesUpperMid, i, 16);
			}
		}

		for (int i = 28; i < 34; i++) {
			if (i == 28) {
				l2.addTile(leavesLowLeft, i, 21);
			} else if (i == 33) {
				l2.addTile(leavesLowRight, i, 21);
			} else {
				l2.addTile(leavesLowMid, i, 21);
			}
		}

		for (int j = 17; j < 21; j++) {
			l2.addTile(leavesMidLeft, 28, j);
		}

		for (int j = 17; j < 21; j++) {
			l2.addTile(leavesMidRight, 33, j);
		}

		for (int i = 29; i < 33; i++) {
			for (int j = 17; j < 21; j++) {
				l2.addTile(leavesMid, i, j);
			}
		}

		// adding the horizontal walls
		for (int i = 4; i < 10; i++) {
			if (i == 4) {
				l2.addTile(wallLeft, i, 11);
			} else if (i == 9) {
				l2.addTile(wallRight, i, 11);
			} else {
				l2.addTile(wallMid, i, 11);
			}

		}

		for (int i = 26; i < 32; i++) {
			if (i == 26) {
				l2.addTile(wallLeft, i, 11);
			} else if (i == 31) {
				l2.addTile(wallRight, i, 11);
			} else {
				l2.addTile(wallMid, i, 11);
			}

		}

		// adding the vertical walls
		for (int j = 5; j < 17; j++) {
			if (j == 5) {
				l2.addTile(wallUp, 13, j);
			} else if (j == 16) {
				l2.addTile(wallDown, 13, j);
			} else {
				l2.addTile(wallVertMid, 13, j);
			}
		}

		for (int j = 5; j < 17; j++) {
			if (j == 5) {
				l2.addTile(wallUp, 21, j);
			} else if (j == 16) {
				l2.addTile(wallDown, 21, j);
			} else {
				l2.addTile(wallVertMid, 21, j);
			}
		}

		// adding the middle vertical walls
		for (int j = 1; j < 9; j++) {
			if (j == 1) {
				l2.addTile(wallUp, 17, j);
			} else if (j == 8) {
				l2.addTile(wallDown, 17, j);
			} else {
				l2.addTile(wallVertMid, 17, j);
			}
		}

		for (int j = 14; j < 23; j++) {
			if (j == 14) {
				l2.addTile(wallUp, 17, j);
			} else if (j == 22) {
				l2.addTile(wallDown, 17, j);
			} else {
				l2.addTile(wallVertMid, 17, j);
			}
		}

		map.addLayer(l1);
		map.addLayer(l2);

		Tank tank = new Tank("art/tank64.png", 160d, 160d);

		ArrayList<PowerUp> toRemove = new ArrayList<>();
		ArrayList<PowerUp> powerups = new ArrayList<>();
		PowerUp speedPowerup = new PowerUp("Speed", 150, 500);
		powerups.add(speedPowerup);
		PowerUp coin = new PowerUp("Score", 480, 64);
		powerups.add(coin);
		PowerUp battery = new PowerUp("Energy", 96, 672);
		powerups.add(battery);

		Tank enemy = new Tank("art/tank-red.png", 992, 540);

		Tank enemy2 = new Tank("art/tank-red.png", 992, 160d);

		Tank enemy3 = new Tank("art/tank-red.png", 185d, 650);
		if (difficulty == 1) {
			enemy2.setHP(0);
			enemy3.setHP(0);
		} else if (difficulty == 2) {
			enemy2.setHP(0);
		}

		DropShadow dropshadow = new DropShadow();
		dropshadow.setRadius(10);
		dropshadow.setOffsetX(0);
		dropshadow.setOffsetY(0);
		dropshadow.setSpread(0.1);
		dropshadow.setColor(Color.BLACK);

		@SuppressWarnings("unused")
		URL url = this.getClass().getClassLoader().getResource("application/music.mp3");

		try {

			// Label
			Label score1 = new Label("Score: " + tank.getScore());
			score1.setFont(Font.font("Segoe Print", 80d));
			Label hpB = new Label("HP: " + tank.hp);
			hpB.setFont(Font.font("Segoe Print"));
			Label timer = new Label("Time Left: " + TOTALGAMETIME);
			timer.setFont(Font.font("Segoe Print"));
			Label score = new Label("Score: " + tank.getScore());
			score.setFont(Font.font("Segoe Print"));

			// HBox
			HBox hpBar = new HBox();
			hpBar.setAlignment(Pos.CENTER);
			hpBar.setSpacing(40);

			// Menu
			MenuBar menuBar = new MenuBar();
			Menu rq = new Menu("Quit or Return");
			Menu Audio = new Menu("Audio");
			menuBar.getMenus().addAll(Audio, rq);

			MenuItem quit = new MenuItem("Quit Game");
			MenuItem returnM = new MenuItem("Return to Menu");
			rq.getItems().addAll(quit, returnM);

			quit.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					System.exit(0);
					stage.close();
				}
			});

			MenuItem volume = new MenuItem("Music off");
			MenuItem volume1 = new MenuItem("Music on");
			Audio.getItems().addAll(volume, volume1);

			// Pane
			BorderPane root = new BorderPane();
			root.setTop(menuBar);
			root.setBottom(hpBar);
			root.setEffect(dropshadow);

			volume.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					AudioManager.mute();
				}
			});

			volume1.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					AudioManager.unmute();
				}
			});

			stage.setOnCloseRequest(new EventHandler<WindowEvent>() {

				@Override
				public void handle(WindowEvent arg0) {
					System.exit(0);
					stage.close();
				}
			});

			root.setTop(menuBar);
			root.setBottom(hpBar);

			// Scene
			Scene scene = new Scene(root, 1150, 820);
			stage.setResizable(false);
			stage.setScene(scene);
			stage.setTitle("Singleplayer Mode");
			stage.getIcons().add(new Image("images/icon_tank.jpg"));
			stage.show();

			Canvas canvas = new Canvas(1150, 770);
			GraphicsContext context = canvas.getGraphicsContext2D();
			// Change setCenter to setLeft
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

			Sprite muteButton;
			if (AudioManager.isMute()) {
				muteButton = new Sprite("art/musicnoteoff.png", 1100, 50);
			} else {
				muteButton = new Sprite("art/musicnote.png", 1100, 50);
			}
			scene.setOnMouseClicked((MouseEvent event) -> {
				if (event.getX() > 1080 && event.getX() < 1110 && event.getY() > 59 && event.getY() < 95) {
					System.out.println(event.getY());
					if (AudioManager.isMute()) {
						muteButton.setImage("art/musicnote.png");
						AudioManager.unmute();
					} else {
						muteButton.setImage("art/musicnoteoff.png");
						AudioManager.mute();
					}
				}
			});

			ArrayList<Bullet> laserListT = new ArrayList<Bullet>();
			ArrayList<Bullet> laserListE = new ArrayList<Bullet>();
			// Bullet bullet = new Bullet("imagesProjectAI/red-circle.png", enemy);
			// laserListE.add(bullet);
			ArrayList<Bullet> oldBullets = new ArrayList<Bullet>();
			// ArrayList<Sprite> asteroidList = new ArrayList<Sprite>();

			// final double FRAMERATE = 1 / 60d;

			AnimationTimer gameloop = new AnimationTimer() {

				int times, times2, times3 = 0;
				int oneOrMinOne, oneOrMinOne2, oneOrMinOne3 = 0;

				int time[] = {2};
				int a = 0;
//				int Time[] = { 0, 2, 0, 0, 0, 0, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
//				int b = 0;
				double elapsedGameTime = 0;
				double frameTime = 0;
				double initialTime = -1;
				final double NANO_TO_S = 1_000_000_000d;

				public void handle(long nanotime) {
					if (initialTime == -1)
						initialTime = nanotime / NANO_TO_S;
					frameTime = (nanotime / NANO_TO_S) - (elapsedGameTime + initialTime);
					elapsedGameTime += frameTime;
					// System.out.println(frameTime);

					// enemy1 fixed
					double checkRotation = enemy.rotation % 90;
					if (enemy.hp > 0) {
						if (checkRotation == 0) {
							oneOrMinOne = 0;
							times++;
							int i = times % 210;
							int randomNumber = time[a];
							enemy.velocity.setAngle(enemy.rotation);
							enemy.velocity.setLength(60 * enemy.getSpeedModifier());
							if (i == 0) {
								if (randomNumber == 1) {
									enemy.rotation += 2;
									oneOrMinOne = 1;
									a++;
								} else if (randomNumber == 2) {
									enemy.rotation -= 2;
									oneOrMinOne = -1;
									a++;
								} else if (randomNumber == 0) {
									enemy.velocity.setAngle(enemy.rotation);
									enemy.velocity.setLength(60 * enemy.getSpeedModifier());
									a++;
								}
							}
						}
						if (Math.random() < 0.08 && enemy.getHP() >= 0) {

							context.save();

							Bullet laserE = new Bullet("imagesProjectAI/red-circle.png", enemy);
							// modified the position a bit so it looks like it shoots from the turret
							laserE.position.set(enemy.position.x, enemy.position.y);
							laserE.velocity.setLength(200);
							laserE.velocity.setAngle(enemy.rotation);
							laserListE.add(laserE);
						}
						if (oneOrMinOne == 1) {
							enemy.rotation += 2;
							enemy.velocity.setLength(0);
						}
						if (oneOrMinOne == -1) {
							enemy.rotation -= 2;
							enemy.velocity.setLength(0);
						}
						if (a == time.length) {
							a = 0;
						}
					}

					// Enemy2 random
					if (enemy2.hp > 0) {
						// enemy2
						double checkRotation2 = enemy2.rotation % 90;
						if (checkRotation2 == 0) {
							oneOrMinOne2 = 0;

							times2++;
							int i = times2 % 45;
							double randomNumber2 = Math.random();
							enemy2.velocity.setAngle(enemy2.rotation);
							enemy2.velocity.setLength(80* enemy2.getSpeedModifier());

							if (i == 0) {
								if (randomNumber2 < 0.5) {
									enemy2.velocity.setLength(0);
									enemy2.rotation += 2;
									oneOrMinOne2 = 1;
								} else if (randomNumber2 > 0.5) {
									enemy2.velocity.setLength(0);
									enemy2.rotation -= 2;
									oneOrMinOne2 = -1;
								}
							}
						}
						if (Math.random() < 0.1 && enemy2.getHP() >= 0) {

							context.save();

							Bullet laserE = new Bullet("imagesProjectAI/red-circle.png", enemy2);
							// modified the position a bit so it looks like it shoots from the turret
							laserE.position.set(enemy2.position.x, enemy2.position.y);
							laserE.velocity.setLength(200);
							laserE.velocity.setAngle(enemy2.rotation);
							laserListE.add(laserE);
						}
						if (oneOrMinOne2 == 1) {
							enemy2.rotation += 2;
						}
						if (oneOrMinOne2 == -1) {
							enemy2.rotation -= 2;
						}
					}

					if (enemy3.hp > 0) {
						// enemy2
						double checkRotation3 = enemy3.rotation % 90;
						if (checkRotation3 == 0) {
							oneOrMinOne3 = 0;

							times3++;
							int i = times3 % 45;
							double randomNumber3 = Math.random();
							enemy3.velocity.setAngle(enemy3.rotation);
							enemy3.velocity.setLength(60 * enemy3.getSpeedModifier());

							if (i == 0) {
								if (randomNumber3 < 0.5) {
									enemy3.velocity.setLength(0);
									enemy3.rotation += 2;
									oneOrMinOne3 = 1;
								} else if (randomNumber3 > 0.5) {
									enemy3.velocity.setLength(0);
									enemy3.rotation -= 2;
									oneOrMinOne3 = -1;
								}
							}
						}
						if (Math.random() < 0.1 && enemy3.getHP() >= 0) {

							context.save();

							Bullet laserE = new Bullet("imagesProjectAI/red-circle.png", enemy3);
							// modified the position a bit so it looks like it shoots from the turret
							laserE.position.set(enemy3.position.x, enemy3.position.y);
							laserE.velocity.setLength(200);
							laserE.velocity.setAngle(enemy3.rotation);
							laserListE.add(laserE);
						}
						if (oneOrMinOne3 == 1) {
							enemy3.rotation += 2;
						}
						if (oneOrMinOne3 == -1) {
							enemy3.rotation -= 2;
						}
					}


					// If bool is true, WASD was chosen
					if (isWASD) {
						tank.moveWASD(keyPressedList, keyJustPressedList, context, laserListT);
					}
					// If bool is false, Arrows was chosen
					else {
						tank.move(keyPressedList, keyJustPressedList, context, laserListT);
					}
					tank.setBulletMsg(0);

					// after processing user input, clear keyJustPressedList
					keyJustPressedList.clear();

					tank.update(frameTime, map);
					hpB.setText("HP: " + tank.hp);
					enemy.update(frameTime, map);
					enemy2.update(frameTime, map);
					enemy3.update(frameTime, map);

					// Collision Detection for Bullets
					for (Bullet laser1 : laserListE) {
						laser1.update(frameTime, map);
						tank.collide(laser1);
						for (PowerUp powerup : powerups) {
							powerup.collide(laser1);
						}
					}

					for (int n = 0; n < laserListT.size(); n++) {
						Bullet laser = laserListT.get(n);

						laser.update(frameTime, map);

						enemy.collide(laser);
						enemy2.collide(laser);
						enemy3.collide(laser);

						for (PowerUp powerup : powerups) {
							powerup.collide(laser);
						}
					}

					for (PowerUp powerup : powerups) {
						powerup.update(frameTime, map);
						powerup.collide(tank);
						powerup.collide(enemy);
						powerup.collide(enemy2);
						powerup.collide(enemy3);
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
					map.renderMap(context);
					// tank.render(context);
					// System.out.println("current score:"+tank.getScore());
					// System.out.println("current hp:"+tank.getHP());

					for (PowerUp powerup : powerups) {
						if (powerup.hp > 1) {
							powerup.render(context);
						} else {
							toRemove.add(powerup);
						}
					}
					powerups.removeAll(toRemove);

//					if (speedPowerup.hp < 1) {
//						tank.velocity.setLength(800);
//					}
					if (tank.hp > 0) {
						tank.render(context);
					}
					if (enemy.hp > 0) {
						enemy.render(context);
					}
					if (enemy2.hp > 0) {
						enemy2.render(context);
					}
					if (enemy3.hp > 0) {
						enemy3.render(context);
					}

					for (Sprite laser : laserListT) {
						laser.render(context);
					}
					for (Sprite laser : laserListE) {
						laser.render(context);
					}
					muteButton.render(context);

					// Generate new powerups
					if (powerups.isEmpty()) {
						PowerUp powerup;
						// Allows only three powerups to be spawned at once
						for (int i = 0; i < 3; i++) {
							Random rand = new Random();
							int newPUX;
							int newPUY;
							do {
								newPUX = rand.nextInt(map.MAP_WIDTH_IN_TILES);
								newPUY = rand.nextInt(map.MAP_HEIGHT_IN_TILES);
								powerup = PowerUp.randomPowerUp(newPUX * map.TILE_WIDTH, newPUY * map.TILE_HEIGHT);
							}
							// Checks for collision with the walls with the current coordinates
							while (powerup.collideMap(map));
							// If the coordinates do not collide with the walls, the powerup is spawned
							powerups.add(powerup);
						}
					}
					score.setText("Score: " + tank.getScore());

					// elapsedGameTime += frameTime;
					timer.setText("Time Left:" + (TOTALGAMETIME - (int) elapsedGameTime));
					// System.out.println(elapsedGameTime + "Time passed");

					// HP bar
					Group rootg = new Group();
					Rectangle rectangle1 = new Rectangle();
					rectangle1.setFill(Paint.valueOf("#FFFFFF"));
					rectangle1.setX(0);
					rectangle1.setY(50);
					rectangle1.setWidth(100.0);
					rectangle1.setHeight(15.0);
					rectangle1.setStroke(Color.RED);

					Rectangle rectangle2 = new Rectangle();
					rectangle2.setFill(Paint.valueOf("#FF0033"));
					rectangle2.setX(0);
					rectangle2.setY(50);
					rectangle2.setWidth(tank.hp);
					rectangle2.setHeight(15.0);
					rectangle2.setStroke(Color.RED);
					rootg.getChildren().addAll(rectangle1, rectangle2);

					// HBox
					HBox hpBar = new HBox();
					hpBar.getChildren().addAll(hpB, rootg, timer, score);
					hpBar.setAlignment(Pos.CENTER);
					hpBar.setSpacing(40);

					root.setBottom(hpBar);

					// Gameover Logic
					if (tank.hp <= 0) {
						Sprite youLose = new Sprite("art/YouLose.png", 576, 400);
						gameOver(youLose, context);
						this.stop();
					}
					if (enemy.hp <= 0 && enemy2.hp <= 0 && enemy3.hp <= 0) {
						Sprite youWin = new Sprite("art/YouWin.png", 576, 400);
						gameOver(youWin, context);
						this.stop();
					} else if (elapsedGameTime > TOTALGAMETIME) {
						Sprite youLose = new Sprite("art/YouLose.png", 576, 400);
						gameOver(youLose, context);
						this.stop();
					}
				}
			};

			gameloop.start();
			returnM.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					@SuppressWarnings("unused")
					TankMenu m1 = new TankMenu();
					gameloop.stop();
					stage.close();
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void gameOver(Sprite message, GraphicsContext context) {
		context.setFill(new Color(0, 0, 0, 0.017));
		new AnimationTimer() {
			int i = 0;

			public void handle(long nanotime) {
				if (i < 100)
					context.fillRect(0, 0, 1152, 800);
				else if (i == 100) {

					message.render(context);
				} else if (i > 300) {
					this.stop();
					stage.close();
					new TankMenu();
				}
				i++;
			}
		}.start();
	}

}
