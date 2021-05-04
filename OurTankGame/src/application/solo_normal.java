package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
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

public class solo_normal {
	private final Stage stage = new Stage();
	private boolean isWASD;

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

	public solo_normal() {

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
		for(int i=4; i<10; i++) {
			if(i==4) {
				l2.addTile(wallLeft, i, 11);
			} else if(i==9) {
				l2.addTile(wallRight, i, 11);
			} else {
				l2.addTile(wallMid, i, 11);
			}
				
		}
		
		for(int i=26; i<32; i++) {
			if(i==26) {
				l2.addTile(wallLeft, i, 11);
			} else if(i==31) {
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

		Tank tank = new Tank("grimfandango-art/tank64.png",160d,160d);

		ArrayList<PowerUp> toRemove = new ArrayList<>();
		ArrayList<PowerUp> powerups = new ArrayList<>();
		PowerUp speedPowerup = new PowerUp("Speed", 150,500);
		powerups.add(speedPowerup);
		PowerUp coin = new PowerUp("Score",480, 64);
		powerups.add(coin);
		PowerUp battery = new PowerUp("Energy",96, 672);
		powerups.add(battery);

		Tank enemy = new Tank("grimfandango-art/tank-red.png",992,608);
		Tank enemy3 = new Tank("grimfandango-art/tank-red.png",148d,520);

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
			MenuItem returnM = new MenuItem("Return to Menu");
			rq.getItems().addAll(quit, returnM);

			returnM.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					@SuppressWarnings("unused")
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
					@SuppressWarnings("unused")
					Player nomode = new Player(null);
				}
			});

			root.setTop(menuBar);
			root.setBottom(hpBar);

			// Scene
			Scene scene = new Scene(root, 1150, 820);
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			// stage.setMaximized(true);
			stage.setResizable(false);
			stage.setScene(scene);
			stage.setTitle("Singleplayer Mode");
			
			TilePane rootControls = new TilePane();
			rootControls.setPadding(new Insets(20));
			rootControls.setAlignment(Pos.BASELINE_CENTER);
			
			// Choose your keyboard preferences
			Image img = new Image("grimfandango-art/wasd.png");
			ImageView view = new ImageView(img);
			Button wasdButton = new Button();
			wasdButton.setPrefSize(250, 40);
			wasdButton.setGraphic(view);
			//wasdButton.setTranslateX(10);
			//wasdButton.setTranslateY(25);
			wasdButton.setPrefSize(80, 80);
			
			Image imgArrows = new Image("grimfandango-art/arrows.png");
			ImageView viewArrows = new ImageView(imgArrows);
			Button arrowsButton = new Button();
			arrowsButton.setPrefSize(100, 40);
			arrowsButton.setGraphic(viewArrows);
			//arrowsButton.setTranslateX(100);
			//arrowsButton.setTranslateY(25);
			arrowsButton.setPrefSize(80, 80);

			rootControls.getChildren().addAll(wasdButton,arrowsButton);
			Scene sceneControls = new Scene(rootControls,600,200);
			Stage stageControls = new Stage();
			stageControls.setScene(sceneControls);
			stageControls.setTitle("Choose your controls:");

			stageControls.show();
			
			wasdButton.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent arg0) {
					isWASD = true;
					stageControls.close();
					stage.show();
				}
			});
			
			arrowsButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					isWASD = false;
					stageControls.close();
					stage.show();
				}
			});
			
			

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

			ArrayList<Bullet> laserListT = new ArrayList<Bullet>();
			ArrayList<Bullet> laserListE = new ArrayList<Bullet>();
			//Bullet bullet = new Bullet("imagesProjectAI/red-circle.png", enemy);
			//laserListE.add(bullet);
			ArrayList<Bullet> oldBullets = new ArrayList<Bullet>();
			// ArrayList<Sprite> asteroidList = new ArrayList<Sprite>();

			final double FRAMERATE = 1 / 60d;
					
			AnimationTimer gameloop = new AnimationTimer() {

				int times, times3 = 0;
				int oneOrMinOne,oneOrMinOne3  = 0;
						
				int time[] = {0,0,2,0,0,0,2,0,0,0,0,0,0,0,0,2,0,0,0,2,0,0,0,0};
				int a = 0;
				int Time[] = {0,0,0,0,0,2,0,0,0,0,0,0,0,0,2,2,2,1,0,0,0,0,0,0,0,1,1,1,2};
				int b = 0;

				public void handle(long nanotime) {
					
					//HP bar
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
			        rootg.getChildren().addAll(rectangle1,rectangle2);
			        
			        // HBox
					HBox hpBar = new HBox();
					hpBar.getChildren().addAll(hpB,rootg,shield,boost);
					hpBar.setAlignment(Pos.CENTER);
					hpBar.setSpacing(40);
					
					root.setBottom(hpBar);
					
					//enemy1
					double checkRotation = enemy.rotation % 90;
					//System.out.println(enemy.rotation);
					if (checkRotation == 0) {
						oneOrMinOne = 0;
						if (Math.random() < 0.01 && enemy.getHP()>=0) {

							context.save();

							Bullet laserE = new Bullet("imagesProjectAI/red-circle.png", enemy);
							//modified the position a bit so it looks like it shoots from the turret
							laserE.position.set(enemy.position.x, enemy.position.y);
							laserE.velocity.setLength(200);
							laserE.velocity.setAngle(enemy.rotation);
							laserListE.add(laserE);
							//System.out.println("enemy shot");
						}

						times++;
						int i = times % 210;
//						double randomNumber = Math.random();
						
						int randomNumber = time[a];						
//						System.out.println(a);						
						
						enemy.velocity.setAngle(enemy.rotation);
						enemy.velocity.setLength(10);
						if (i == 0) {
							if (randomNumber == 1) {//1右转
								enemy.rotation += 2;
								oneOrMinOne = 1;
								System.out.println("右转a");
								System.out.println(a);
								a++;
							} else if (randomNumber == 2) {//2左转
								enemy.rotation -= 2;
								oneOrMinOne = -1;
								System.out.println("左转a");
								System.out.println(a);
								a++;
							} else if (randomNumber == 0) {//0直走
								enemy.velocity.setAngle(enemy.rotation);
								enemy.velocity.setLength(100);	
								System.out.println("直走a");
								System.out.println(a);
								a++;
							} 
						}
					}
					 if (oneOrMinOne == 1){
						enemy.rotation += 2;
					}
					 if (oneOrMinOne == -1) {
						enemy.rotation -= 2;
					}
					if(a==time.length) {
						a=0;							
					}					
						 
						 double checkRotation3 = enemy3.rotation % 90;
							//System.out.println(enemy.rotation);
							if (checkRotation3 == 0) {
								oneOrMinOne3 = 0;
								if (Math.random() < 0.01 && enemy.getHP()>=0) {

									context.save();

									Bullet laserE = new Bullet("imagesProjectAI/red-circle.png", enemy3);
									//modified the position a bit so it looks like it shoots from the turret
									laserE.position.set(enemy3.position.x, enemy3.position.y);
									laserE.velocity.setLength(200);
									laserE.velocity.setAngle(enemy3.rotation);
									laserListE.add(laserE);
									//System.out.println("other enemy shot");
								}

								times3++;
								int i = times3 % 210;
//								double randomNumber = Math.random();
								
								int randomNumber3 = Time[b];						
//								System.out.println(a);						
								
								enemy3.velocity.setAngle(enemy3.rotation);
								enemy3.velocity.setLength(10);
								if (i == 0) {
									if (randomNumber3 == 1) {//1右转
										enemy3.rotation += 2;
										oneOrMinOne3 = 1;
										System.out.println("右转b");
										System.out.println(b);
										b++;
									} else if (randomNumber3 == 2) {//2左转
										enemy3.rotation -= 2;
										oneOrMinOne3 = -1;
										System.out.println("左转b");
										System.out.println(b);
										b++;
									} else if (randomNumber3 == 0) {//0直走
										enemy3.velocity.setAngle(enemy3.rotation);
										enemy3.velocity.setLength(100);	
										System.out.println("直走b");
										System.out.println(b);
										b++;
									} 
								}
							}
							 if (oneOrMinOne3 == 1){
								enemy3.rotation += 2;
							}
							 if (oneOrMinOne3 == -1) {
								enemy3.rotation -= 2;
							}
							if(b==Time.length) {
								b=0;							
							}
							
					// If bool is true, WASD was chosen
					 if(isWASD) {
					tank.moveWASD(keyPressedList,keyJustPressedList,context,laserListT);
					 }
					// If bool is false, Arrows was chosen
					else {
					tank.move(keyPressedList,keyJustPressedList,context,laserListT);
					 }
					tank.setBulletMsg(0);
					

					// after processing user input, clear keyJustPressedList
					keyJustPressedList.clear();

					tank.update(FRAMERATE,map);
					enemy.update(FRAMERATE,map);
					enemy3.update(FRAMERATE,map);
						
					// Collision Detection for Bullets
					for (Bullet laser1 : laserListE) {
						laser1.update(FRAMERATE,map);
						tank.collide(laser1);
						for(PowerUp powerup : powerups) {
							powerup.collide(laser1);
						}
					}

					for (int n = 0; n < laserListT.size(); n++) {
						Bullet laser = laserListT.get(n);
						
						laser.update(FRAMERATE,map);

						enemy.collide(laser); // TODO: this is a marker that I edited this one
						enemy3.collide(laser);
						
						for(PowerUp powerup : powerups) {
							powerup.collide(laser);
						}
					}
					
					for (PowerUp powerup : powerups) {
						powerup.update(FRAMERATE, map);
						powerup.collide(tank);
						powerup.collide(enemy);
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
					//tank.render(context); 
					//System.out.println("current score:"+tank.getScore());
					//System.out.println("current hp:"+tank.getHP());
				
					for(PowerUp powerup : powerups) {
						if (powerup.hp > 1) {
							powerup.render(context);
						}
						else {
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
					if (enemy3.hp > 0) {
						enemy3.render(context);
					}
					
					for (Sprite laser : laserListT) {
						laser.render(context);
					}
					for (Sprite laser : laserListE) {
						laser.render(context);
					}
					
					//Generate new powerups
					if (powerups.isEmpty()) {
						PowerUp powerup;
						//Allows only three powerups to be spawned at once
						for(int i = 0; i < 3; i++) {
						Random rand = new Random();
						int newPUX;
						int newPUY;
						do {	
						newPUX = rand.nextInt(map.MAP_WIDTH_IN_TILES);
						newPUY = rand.nextInt(map.MAP_HEIGHT_IN_TILES);
						powerup = PowerUp.randomPowerUp(newPUX*map.TILE_WIDTH, newPUY*map.TILE_HEIGHT);
						} 
						//Checks for collision with the walls with the current coordinates
						while (powerup.collideMap(map));
						//If the coordinates do not collide with the walls, the powerup is spawned
						powerups.add(powerup);
						}
				}
					
					
					//Gameover Logic
					if (tank.hp <= 0) {
						Sprite youLose = new Sprite("grimfandango-art/YouLose.png",576, 400);
						gameOver(youLose,context);
						this.stop();
					}
					if (enemy.hp <= 0 && enemy3.hp <= 0) {
						Sprite youWin = new Sprite("grimfandango-art/YouWin.png",576, 400);
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
		context.setFill(new Color(0,0,0,0.017));
		new AnimationTimer() {
			int i = 0;
			public void handle(long nanotime) {
				if (i < 100)
					context.fillRect(0,0,1152,800);
				else if (i == 100) {
					
					message.render(context);
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
