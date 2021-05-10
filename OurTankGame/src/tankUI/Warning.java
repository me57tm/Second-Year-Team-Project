package tankUI;

import javafx.event.ActionEvent;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;


/**
 * The warning window that pops up when the number of server players is greater than 2
 */
public class Warning{

	public Warning(){
		try {
			
			Font f = new Font("Segoe Print", 20);
			Label text = new Label("There are already two people playing this mode playing this mode");									
			Label text1 = new Label( "\n" +", please play other modes.");
			text.setAlignment(Pos.CENTER);
			text.setFont(f);
			text.setTextFill(Paint.valueOf("#f2eada"));
			text1.setFont(f);
			text1.setTextFill(Paint.valueOf("#f2eada"));
			
			Group g = new Group(text,text1);
			
			Image img  = new Image("images/TankMenu.jpg");
			ImageView im = new ImageView();
			im.setImage(img);
			BackgroundImage bgi = new BackgroundImage(img, null, null, null, null);
			Background bg = new Background(bgi);
			
			Button back = new Button("Back");
			back.getStyleClass().add("font");
			back.setPrefSize(250, 40);
			back.setLayoutX(375);
			back.setLayoutY(500);
			back.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			BorderPane root = new BorderPane();
			StackPane sp = new StackPane(); 
			sp.getChildren().addAll(g,back);
			root.setBottom(sp);
			root.setBackground(bg);
			root.setCenter(g);
			
			Stage s1 =new Stage();
			Scene scene = new Scene(root);
			s1.setWidth(1160);
			s1.setHeight(820);
			s1.setResizable(false);
			s1.setScene(scene);
			s1.setTitle("Warning");
			s1.setResizable(false);
			s1.show();
			
			back.setOnAction(new EventHandler<ActionEvent>() {

				@SuppressWarnings("unused")
				@Override
				public void handle(ActionEvent arg0) {
					StartGame s = new StartGame();
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
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
