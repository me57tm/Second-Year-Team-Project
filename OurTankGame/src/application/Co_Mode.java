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
import physics.Layer;
import physics.Map;
import physics.PowerUp;
import physics.Sprite;
import physics.Tank;
import physics.Tile;
import tankUI.Player;
import tankUI.TankMenu;

public class Co_Mode {
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

	public Co_Mode(String name) {

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

		Tank tank = new Tank("imagesProjectAI/tank.png");
		tank.position.set(160, 160);

		PowerUp bulletPowerup = new PowerUp("Speed");
		bulletPowerup.position.set(150, 500);

		Tank enemy = new Tank("grimfandango-art/tank-red.png");
		enemy.position.set(992, 608);

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
			stage.setTitle("Co-operative Mode");
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

				int times = 0;
			    int framesForNow = 0;
				int oneOrMinOne  = 0;

				public void handle(long nanotime) {

					double checkRotation = enemy.rotation % 90;
					//System.out.println(enemy.rotation);
					if (checkRotation == 0) {
						oneOrMinOne = 0;
						if (Math.random() < 0.01) {

							context.save();

							Bullet laserE = new Bullet("imagesProjectAI/red-circle.png", enemy);
							//modified the position a bit so it looks like it shoots from the turret
							laserE.position.set(enemy.position.x+enemy.getBoundary().getWidth(), enemy.position.y+(enemy.getBoundary().getHeight())/2);
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

					tank.update(1 / 60.0,map);
					enemy.update(1 / 60.0,map);

					// Collision Detection for Bullets
					for (Bullet laser1 : laserListE) {
						laser1.update(1 / 60.0,map);
						tank.collide(laser1);
						bulletPowerup.collide(laser1);
					}

					for (int n = 0; n < laserListT.size(); n++) {
						Bullet laser = laserListT.get(n);
						
						laser.update(1 / 60.0,map);

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
					map.renderMap(context);
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
