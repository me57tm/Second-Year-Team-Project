package tankUI;

import client.TankClient;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class ServerStart{

	public ServerStart(){
		try {

			
			Image img = new Image("images/tankmenuimg.png");
			ImageView im = new ImageView();
			im.setImage(img);
			BackgroundImage bgi = new BackgroundImage(img, null, null, null, null);
			Background bg = new Background(bgi);
			
			Button back = new Button("Back");
			Button host = new Button("Join Game");
			host.setAlignment(Pos.CENTER);

			VBox vb = new VBox();
			vb.getChildren().addAll(host);
			vb.setAlignment(Pos.CENTER);
			vb.setSpacing(40);

			BorderPane root = new BorderPane();
			root.setBottom(vb);
			root.setBackground(bg);
			root.setRight(back);
			
			Stage s1 =new Stage();
			Scene scene = new Scene(root);
			s1.setWidth(500);
			s1.setHeight(575);
			s1.setResizable(false);
			s1.setScene(scene);
			s1.setTitle("Jion Game");
			s1.show();
			
			back.setOnAction(new EventHandler<ActionEvent>() {

				@SuppressWarnings("unused")
				@Override
				public void handle(ActionEvent arg0) {
					Split s = new Split();
					s1.close();
				}
			});
				
			host.setOnAction(new EventHandler<ActionEvent>() {

				@SuppressWarnings("unused")
				@Override
				public void handle(ActionEvent arg0) {
//					
//					TankServer ts = new TankServer();
//			        ts.launchFrame();
//			        ts.start();
			        
			        TankClient tc = new TankClient();
			        s1.close();
				}
			});			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
