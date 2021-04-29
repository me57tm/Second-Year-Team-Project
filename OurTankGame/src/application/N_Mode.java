package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import client.MovingMsg;
import client.NetClient;
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
import physics.Layer;
import physics.Map;
import physics.PowerUp;
import physics.Sprite;
import physics.Tank;
import physics.Tile;
import tankUI.Player;
import tankUI.TankMenu;

public class N_Mode  {
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

	public N_Mode(List<Tank> newTanks,int id,NetClient nc) {

		Map map = new Map();

		Layer l1 = new Layer("background",map.MAP_WIDTH_IN_TILES,map.MAP_HEIGHT_IN_TILES);
		Tile bg = new Tile("src/grimfandango-art/texture-bg.png",true);
		
		
		//the background tile is added to every cell of the layer, so we iterate while adding it
		for(int i=0; i<l1.widthInTiles; i++) {
			for(int j=0; j<l1.heightInTiles; j++) {
					l1.addTile(bg, i, j);
			}
		}
		
		Layer l2 = new Layer("walls",map.MAP_WIDTH_IN_TILES,map.MAP_HEIGHT_IN_TILES);
		
		Tile areaWallUpperLeft = new Tile("src/grimfandango-art/areawall-upper-left.png",false);
		Tile areaWallUpperMid = new Tile("src/grimfandango-art/areawall-upper-mid.png",false);
		Tile areaWallUpperRight = new Tile("src/grimfandango-art/areawall-upper-right.png",false);
		Tile areaWallMidLeft = new Tile("src/grimfandango-art/areawall-mid-left.png",false);
		Tile areaWallMidRight = new Tile("src/grimfandango-art/areawall-mid-right.png",false);
		Tile areaWallLowLeft = new Tile("src/grimfandango-art/areawall-lower-left.png",false);
		Tile areaWallLowMid = new Tile("src/grimfandango-art/areawall-lower-mid.png",false);
		Tile areaWallLowRight = new Tile("src/grimfandango-art/areawall-lower-right.png",false);
		
		Tile wallLeft = new Tile("src/grimfandango-art/gamewall-left.png",false);
		Tile wallMid = new Tile("src/grimfandango-art/gamewall-mid.png",false);
		Tile wallRight = new Tile("src/grimfandango-art/gamewall-right.png",false);
		Tile wallUp = new Tile("src/grimfandango-art/gamewall-up.png",false);
		Tile wallDown = new Tile("src/grimfandango-art/gamewall-down.png",false);
		Tile wallVertMid = new Tile("src/grimfandango-art/gamewall-vertmid.png",false);
		
		Tile leavesUpperLeft = new Tile("src/grimfandango-art/leaves-upper-left.png",true);
		Tile leavesUpperMid = new Tile("src/grimfandango-art/leaves-upper-mid.png",true);
		Tile leavesUpperRight = new Tile("src/grimfandango-art/leaves-upper-right.png",true);
		Tile leavesMidLeft = new Tile("src/grimfandango-art/leaves-mid-left.png",true);
		Tile leavesMid = new Tile("src/grimfandango-art/leaves-mid.png",true);
		Tile leavesMidRight = new Tile("src/grimfandango-art/leaves-mid-right.png",true);
		Tile leavesLowLeft = new Tile("src/grimfandango-art/leaves-low-left.png",true);
		Tile leavesLowMid = new Tile("src/grimfandango-art/leaves-low-mid.png",true);
		Tile leavesLowRight = new Tile("src/grimfandango-art/leaves-low-right.png",true);
		
		
		
		//adding the wall tiles according to their positioning on the drawn map (this is hardcoded)
		
		for(int i = 0; i<map.MAP_WIDTH_IN_TILES; i++) {
			for(int j = 0; j<map.MAP_WIDTH_IN_TILES; j++) {
				if(i==0 && j==0) {
					l2.addTile(areaWallUpperLeft, i, j);
				} else {
					if(i==35 && j==0) {
						l2.addTile(areaWallUpperRight, i, j);
					} else {
						if(j==0) {
							l2.addTile(areaWallUpperMid, i, j);
						}
					}
				} 
			}
		}
		
		for(int i = 0; i<map.MAP_WIDTH_IN_TILES; i++) {
			for(int j = 0; j<map.MAP_WIDTH_IN_TILES; j++) {
				if(i==0 && j==23) {
					l2.addTile(areaWallLowLeft, i, j);
				} else {
					if(i==35 && j==23) {
						l2.addTile(areaWallLowRight, i, j);
					} else {
						if(j==23) {
							l2.addTile(areaWallLowMid, i, j);
						}
					}
				} 
			}
		}
		
		for(int j = 1; j<map.MAP_HEIGHT_IN_TILES-1; j++) {
			l2.addTile(areaWallMidLeft, 0, j);
		}
		
		for(int j = 1; j<map.MAP_HEIGHT_IN_TILES-1; j++) {
			l2.addTile(areaWallMidRight, 35, j);
		}
		
		//adding the leaves-upper left
		for(int i =2; i<8; i++) {
				if(i==2) {
					l2.addTile(leavesUpperLeft, i, 2);
				} else if(i==7) {
					l2.addTile(leavesUpperRight, i, 2);
				} else {
					l2.addTile(leavesUpperMid, i, 2);
				}
		}
		
		for(int i =2; i<8; i++) {
			if(i==2) {
				l2.addTile(leavesLowLeft, i, 7);
			} else if(i==7) {
				l2.addTile(leavesLowRight, i, 7);
			} else {
				l2.addTile(leavesLowMid, i, 7);
			}
		}
		
		for(int j =3; j<7; j++) {
				l2.addTile(leavesMidLeft, 2, j);
		}
		
		for(int j =3; j<7; j++) {
			l2.addTile(leavesMidRight, 7, j);
		}
		
		for(int i=3; i<7; i++) {
			for(int j=3; j<7; j++) {
				l2.addTile(leavesMid, i, j);
			}
		}
		
		//adding the leaves-lower right
				for(int i =28; i<34; i++) {
						if(i==28) {
							l2.addTile(leavesUpperLeft, i, 16);
						} else if(i==33) {
							l2.addTile(leavesUpperRight, i, 16);
						} else {
							l2.addTile(leavesUpperMid, i, 16);
						}
				}
				
				for(int i =28; i<34; i++) {
					if(i==28) {
						l2.addTile(leavesLowLeft, i, 21);
					} else if(i==33) {
						l2.addTile(leavesLowRight, i, 21);
					} else {
						l2.addTile(leavesLowMid, i, 21);
					}
				}
				
				for(int j =17; j<21; j++) {
						l2.addTile(leavesMidLeft, 28, j);
				}
				
				for(int j =17; j<21; j++) {
					l2.addTile(leavesMidRight, 33, j);
				}
				
				for(int i=29; i<33; i++) {
					for(int j=17; j<21; j++) {
						l2.addTile(leavesMid, i, j);
					}
				}
	
		
		//adding the horizontal walls
		for(int i=3; i<10; i++) {
			if(i==3) {
				l2.addTile(wallLeft, i, 11);
			} else if(i==9) {
				l2.addTile(wallRight, i, 11);
			} else {
				l2.addTile(wallMid, i, 11);
			}
				
		}
		
		for(int i=26; i<33; i++) {
			if(i==26) {
				l2.addTile(wallLeft, i, 11);
			} else if(i==32) {
				l2.addTile(wallRight, i, 11);
			} else {
				l2.addTile(wallMid, i, 11);
			}
				
		}
		
		//adding the vertical walls
		for(int j=5; j<17;j++) {
			if(j==5) {
				l2.addTile(wallUp, 13, j);
			} else if(j==16) {
				l2.addTile(wallDown, 13, j);
			} else {
				l2.addTile(wallVertMid, 13, j);
			}
		}
		
		for(int j=5; j<17;j++) {
			if(j==5) {
				l2.addTile(wallUp, 21, j);
			} else if(j==16) {
				l2.addTile(wallDown, 21, j);
			} else {
				l2.addTile(wallVertMid, 21, j);
			}
		}
		
		//adding the middle vertical walls
		for(int j=1; j<9;j++) {
			if(j==1) {
				l2.addTile(wallUp, 17, j);
			} else if(j==8) {
				l2.addTile(wallDown, 17, j);
			} else {
				l2.addTile(wallVertMid, 17, j);
			}
		}
		
		for(int j=14; j<23;j++) {
			if(j==14) {
				l2.addTile(wallUp, 17, j);
			} else if(j==22) {
				l2.addTile(wallDown, 17, j);
			} else {
				l2.addTile(wallVertMid, 17, j);
			}
		}
		
		map.addLayer(l1);
		map.addLayer(l2);

		Tank tank, enemy;
		tank = newTanks.get(0);
		tank.position.set(tank.getX(), tank.getY());

		ArrayList<PowerUp> powerups = new ArrayList<>();
		PowerUp bulletPowerup = new PowerUp("Speed");
		powerups.add(bulletPowerup);
		bulletPowerup.position.set(150, 500);
		PowerUp coin1 = new PowerUp("Score");
		PowerUp coin2 = new PowerUp("Score");
		PowerUp coin3 = new PowerUp("Score");
		PowerUp coin4 = new PowerUp("Score");
		powerups.add(coin1);
		powerups.add(coin2);
		powerups.add(coin3);
		powerups.add(coin4);
		coin1.position.set(480, 64);
		coin2.position.set(480, 672);
		coin3.position.set(608, 64);
		coin4.position.set(608, 672);
		PowerUp battery1 = new PowerUp("Energy");
		PowerUp battery2 = new PowerUp("Energy");
		powerups.add(battery1);
		powerups.add(battery2);
		battery1.position.set(96, 672);
		battery2.position.set(991, 64);

		enemy = newTanks.get(1);
		enemy.position.set(enemy.getX(), enemy.getY());

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
			Scene scene = new Scene(root, 1152, 800);
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			// stage.setMaximized(true);
			stage.setResizable(false);
			stage.setScene(scene);
			stage.setTitle("Singleplayer Mode");
			stage.show();

			Canvas canvas = new Canvas(1152, 800);
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

				

				public void handle(long nanotime) {

					
					
					tank.move(keyPressedList, keyJustPressedList, context, laserListT);
					enemy.enemyFire(enemy, laserListE);
					MovingMsg msg = new MovingMsg(id, tank.getRotation(), tank.getFifty(), tank.getBulletMsg());
					nc.send(msg);
					tank.setBulletMsg(0);
					

					// after processing user input, clear keyJustPressedList
					keyJustPressedList.clear();

					tank.update(1 / 60.0,map);
					enemy.update(1 / 60.0,map);

					// Collision Detection for Bullets
					for (Bullet laser1 : laserListE) {
						laser1.update(1 / 60.0,map);
						tank.collide(laser1);
						for(PowerUp powerup : powerups) {
							powerup.collide(laser1);
						}
					}

					for (int n = 0; n < laserListT.size(); n++) {
						Bullet laser = laserListT.get(n);
						
						laser.update(1 / 60.0,map);

						enemy.collide(laser); // TODO: this is a marker that I edited this one

						for(PowerUp powerup : powerups) {
							powerup.collide(laser);
						}
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
					//tank.render(context); 
					//System.out.println("current score:"+tank.getScore());
					//System.out.println("current hp:"+tank.getHP());
					for(PowerUp powerup : powerups) {
						if (powerup.hp > 1) {
							powerup.render(context);
						}
					}
					if (bulletPowerup.hp < 1) {
						tank.velocity.setLength(800);
					}
					if (tank.hp > 0) {       
						tank.render(context);
					}
					if (enemy.hp > 0) {
						enemy.render(context);
					}
					else {
						System.out.println("The game is done");
					}
					for (Sprite laser : laserListT) {
						laser.render(context);
					}
					for (Sprite laser : laserListE) {
						laser.render(context);
					}
					
					//Gameover Logic
					if (tank.hp <= 0) {
						Sprite youLose = new Sprite("grimfandango-art/YouLose.png");
						youLose.position.set(576, 400);
						gameOver(youLose,context);
						this.stop();
					}
					if (enemy.hp <= 0) {
						Sprite youWin = new Sprite("grimfandango-art/YouWin.png");
						youWin.position.set(576, 400);
						gameOver(youWin,context);
						this.stop();
					}
				}
			};

			gameloop.start();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void gameOver(Sprite message, GraphicsContext context) {
		//Create black square opacity 0.1
		//open animation timer, repeat x times, increment i, break
			//increase opacity square
		//add text message
		//add exit button
//		javafx.scene.shape.Rectangle shadow = new javafx.scene.shape.Rectangle();
//		shadow.setX(0);
//		shadow.setY(0);
//		shadow.setWidth(1152);
//		shadow.setHeight(800);
//		shadow.setFill(new Color(0,0,0,0.001));
		context.setFill(new Color(0,0,0,0.017));
		new AnimationTimer() {
			int i = 0;
			public void handle(long nanotime) {
				if (i < 100)
					context.fillRect(0,0,1152,800);
				else if (i == 100) {
					
					message.render(context);
//					
//					context.setTextAlign(TextAlignment.CENTER);
//					context.setStroke(Color.WHITE);
//					context.setFill(Color.WHITE);
//					
//					context.setFont(new Font("Source Code Pro",128));					
//					context.strokeText("Game Over", 1152/2, 800/2-150);
//					context.fillText("Game Over", 1152/2, 800/2-150);
//					
//					context.setFont(new Font("Source Code Pro",64));
//					context.strokeText(message, 1152/2, 800/2);
//					context.fillText(message, 1152/2, 800/2);
				}
				else if(i > 300)  {
					this.stop();
					stage.close();
					new TankMenu();
				}
				i++;
			}
		}.start();
	}
}
