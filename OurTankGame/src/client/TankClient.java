package client;

import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import physics.Tank;
import tankUI.Auto_window;
import tankUI.Split;
import tankUI.Warning;

/**
 * Enter the name interface before entering the game in multiplayer mode
 */
public class TankClient {
	public int id;
	static TextField fusername = new TextField();
	Auto_window tips;
	public Tank player1, player2;
	private List<Tank> tanks = new ArrayList<>();
	private NetClient nc = new NetClient(this);

	public TankClient() {

		Tooltip tooltip = new Tooltip();
		tooltip.setText("\nYour password must be\n" + "at least 9 characters in length\n");
		fusername.setTooltip(tooltip);
		fusername.setLayoutX(375);
		fusername.setLayoutY(600);

		// Shadow
		DropShadow dropshadow = new DropShadow();
		dropshadow.setRadius(10);
		dropshadow.setOffsetX(0);
		dropshadow.setOffsetY(0);
		dropshadow.setSpread(0.1);
		dropshadow.setColor(Color.BLACK);

		// Button
		Button back = new Button("Back");
		back.getStyleClass().add("font");
		back.setPrefSize(250, 40);
		back.setLayoutX(355);
		back.setLayoutY(800);
		back.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		// image
		Image img = new Image("images/TankMenu.jpg");
		ImageView im = new ImageView();
		im.setImage(img);
		
		Label set1 = new Label();
		set1.setLayoutX(0);
		set1.setLayoutY(0);
		
		Label set2 = new Label();
		set2.setLayoutX(1000);
		set2.setLayoutY(1000);

		// Button
		Button login = new Button("Start");
		login.getStyleClass().add("font");
		login.setPrefSize(250, 40);
		login.setLayoutX(355);
		login.setLayoutY(700);
		login.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		Label username = new Label("Username:");
		username.getStyleClass().add("font");
		username.setLayoutX(400);
		username.setLayoutY(500);
		username.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		Group g = new Group(set1,set2,username, fusername, login,back);

		StackPane sp = new StackPane(); 
		sp.getChildren().addAll(im,g);

		Scene scene = new Scene(sp,1152,800);
		Stage s1 = new Stage();
		s1.setScene(scene);
		s1.setResizable(false);
		s1.setTitle("Tank BattleField");
		s1.getIcons().add(new Image("images/icon_tank.jpg"));
		s1.show();

		back.setOnAction(new EventHandler<ActionEvent>() {

			@SuppressWarnings("unused")
			@Override
			public void handle(ActionEvent arg0) {
				Split sp = new Split();
				s1.close();
			}
		});
		s1.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent arg0) {
				System.exit(0);
				s1.close();
			}
		});

		login.setOnAction(new EventHandler<ActionEvent>() {

			@SuppressWarnings("unused")
			@Override
			public void handle(ActionEvent event) {
				String name = fusername.getText();

				int len = name.length();

				if (!name.equals("") && len <= 8) {
					// tips = new Auto_window(2000, dialog, new String("tips"), false);
					player1 = new Tank("art/tank64.png", 160d, 160d);
					player2 = new Tank("art/tank-red.png", 992, 608);
                    player2.setRotation(180);
					nc.connect("127.0.0.1");

					if (id < 102) {
						if ((id & 1) == 0 ? true : false) {
							player1.setId(id);
							player2.setId(id + 1);
							tanks.add(player1);
							tanks.add(player2);
						} else {
							player1.setId(id - 1);
							player2.setId(id);
							tanks.add(player2);
							tanks.add(player1);
						}
						tankNameMsg nameMsg = new tankNameMsg(id, name);
						nc.send(nameMsg);

						s1.close();
						waiting_Room waitingRoom = new waiting_Room(tanks, id, nc);
					}else {
						s1.close();
						Warning warning = new Warning();
					}
				} else if (name.equals("")) {
					Auto_window a1 = new Auto_window(2200, "Please type in your name.", "Tips", false);
				} else {
					String s1 = "Please enter a name less than 9 characters.";
					Auto_window a1 = new Auto_window(2200, s1, "Tips", false);
				}
			}
		});
	}

	public void clientID(int id) {
		this.id = id;
	}

	public int getClientID() {
		return id;
	}

	public List<Tank> getTanks() {
		return tanks;
	}

	public void setTanks(List<Tank> tanks) {
		this.tanks = tanks;
	}

	public NetClient getNc() {
		return nc;
	}

	public void setNc(NetClient nc) {
		this.nc = nc;
	}

	public int myID() {
		return id;
	}

	public static String getName() {
		String names = fusername.getText();
		return names;
	}

}
