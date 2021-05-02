package client;

import java.util.ArrayList;
import java.util.List;
import application.N_Mode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import physics.Tank;
import tankUI.Auto_window;
import tankUI.Split;

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

		// Shadow
		DropShadow dropshadow = new DropShadow();
		dropshadow.setRadius(10);
		dropshadow.setOffsetX(0);
		dropshadow.setOffsetY(0);
		dropshadow.setSpread(0.1);
		dropshadow.setColor(Color.BLACK);

		// Button
		Button back = new Button("Back");

		// Label
		Label lb = new Label("Tank Battle");
		Font f = new Font("Segoe Print", 70);
		lb.setFont(f);
		lb.setTextFill(Paint.valueOf("#f2eada"));

		// image
		Image img = new Image("images/tank1.png");
		ImageView im = new ImageView();
		im.setImage(img);

		// Background
		BackgroundImage bgi = new BackgroundImage(img, null, null, null, null);
		Background bg = new Background(bgi);

		// Border
		BorderStroke bs = new BorderStroke(Paint.valueOf("#4e72b8"), null, new CornerRadii(10), new BorderWidths(2));
		Border br = new Border(bs);

		// Button
		Button login = new Button("Start");
		login.setFont(Font.font("Segoe Print"));
		login.setBorder(br);

		// TextField
		fusername.setBorder(br);
		Font f1 = new Font("Segoe Print", 15);
		fusername.setFont(f1);

		// HBox
		HBox hb = new HBox();
		VBox vb = new VBox();
		Label username = new Label("UserName:");
		username.setFont(f1);
		username.setTextFill(Paint.valueOf("#fffffb"));

		hb.setAlignment(Pos.CENTER);
		hb.setSpacing(20);
		hb.getChildren().addAll(username, fusername, login);

		vb.getChildren().addAll(lb);
		vb.setAlignment(Pos.CENTER);
		vb.setSpacing(10);

		BorderPane root = new BorderPane();
		root.setBackground(bg);
		root.setBottom(hb);
		root.setCenter(vb);
		root.setRight(back);
		root.setEffect(dropshadow);

		Scene scene = new Scene(root);
		Stage s1 = new Stage();
		s1.setScene(scene);
		s1.setTitle("Tank Battle Login");
		s1.setWidth(500);
		s1.setHeight(575);
		s1.setResizable(false);
		s1.show();

		back.setOnAction(new EventHandler<ActionEvent>() {

			@SuppressWarnings("unused")
			@Override
			public void handle(ActionEvent arg0) {
				Split sp = new Split();
				s1.close();
			}
		});

		login.setOnAction(new EventHandler<ActionEvent>() {

			@SuppressWarnings("unused")
			@Override
			public void handle(ActionEvent event) {
				String name = fusername.getText();

				int len = name.length();

				if (name.equals("") == false && len <= 8) {
					// tips = new Auto_window(2000, dialog, new String("tips"), false);
					player1 = new Tank("imagesProjectAI/tank.png",160d, 160d );
					player2 = new Tank("grimfandango-art/tank-red.png",992, 608 );

					nc.connect("127.0.0.1");
					
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
					N_Mode nomode = new N_Mode(tanks, id, nc);
				} else {
					String s1 = "Please enter a name less than 9 characters.";
					Auto_window a1 = new Auto_window(2200, s1, new String("tips"), false);
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

//	public void giveID(int id) {
//		this.id = id;
//	}
	public int myID() {
		return id;
	}

	public static String getName() {
		String names = fusername.getText();
		return names;
	}

}
