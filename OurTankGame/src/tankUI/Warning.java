package tankUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class Warning{

	public Warning(){
		try {

			
			Image img  = new Image("images/TankMenu.jpg");
			ImageView im = new ImageView();
			im.setImage(img);
			BackgroundImage bgi = new BackgroundImage(img, null, null, null, null);
			Background bg = new Background(bgi);
			
			Button back = new Button("Back to the menu");
			Button Quit = new Button("Quit Game");
			Quit .setAlignment(Pos.CENTER);

			VBox vb = new VBox();
			vb.getChildren().addAll(Quit);
			vb.setAlignment(Pos.CENTER);
			vb.setSpacing(40);

			BorderPane root = new BorderPane();
			root.setBottom(vb);
			root.setBackground(bg);
			root.setRight(back);
			
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
				
			Quit.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
			        
			        System.exit(0);
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
