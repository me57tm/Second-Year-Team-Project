package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
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
import tankUI.Auto_window;
import tankUI.TankMenu;


public class Main extends Application
{

	public static void main(String[] args)
	{

		launch(args);
	}

	static TextField fusername = new TextField();
	Auto_window tips;
	
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		
		Tooltip tooltip = new Tooltip();
		tooltip.setText(
				"\nYour password must be\n" +
		        "at least 9 characters in length\n"
				);
		fusername.setTooltip(tooltip);
		
		// Shadow
		DropShadow dropshadow = new DropShadow();
		dropshadow.setRadius(10);
		dropshadow.setOffsetX(0);
		dropshadow.setOffsetY(0);
		dropshadow.setSpread(0.1);
		dropshadow.setColor(Color.BLACK);

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
		root.setEffect(dropshadow);

		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Tank Battle Login");
		primaryStage.setWidth(500);
		primaryStage.setHeight(575);
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
					String dialog = "login successfully, welcome!   " + name;
//					System.out.println(" 'W' moves up, 'S' moves down, 'A' moves left, 'D' moves right and 'J' shoots bullet the side you're facing");
					tips = new Auto_window(2000, dialog, new String("tips"),false);
					TankMenu m1 = new TankMenu();
					primaryStage.close();

				} else
				{
					String s1 = "Please enter a name less than 9 characters.";
					Auto_window a1 = new Auto_window(2200, s1, new String("tips"),false);
				}
			}
		});
	}

	public static String getName()
	{
		String names = fusername.getText();
		return names;
	}

}
