package tankUI;
	
import client.TankClient;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import server.TankServer;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class Split{

	public Split(){
		try {
			
			Font f = new Font("Segoe Print", 15);
			
			Image img = new Image("images/tank1.png");
			ImageView im = new ImageView();
			im.setImage(img);
			BackgroundImage bgi = new BackgroundImage(img, null, null, null, null);
			Background bg = new Background(bgi);
			
			Button back = new Button("Back");
			Button host = new Button("As Host");
			Button guest = new Button("As Guest");
			host.setAlignment(Pos.CENTER);
			guest.setAlignment(Pos.CENTER);
			
			Label t = new Label("Jion game as a host or guest");
			t.setFont(f);
			t.setTextFill(Paint.valueOf("#fffffb"));
			//BUG，label is under the background

			VBox vb = new VBox();
			vb.getChildren().addAll(t,host,guest);
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
			s1.setTitle("As a host or guest");
			s1.show();
			
			back.setOnAction(new EventHandler<ActionEvent>() {

				@SuppressWarnings("unused")
				@Override
				public void handle(ActionEvent arg0) {
					StartGame sg = new StartGame();
					s1.close();
				}
			});
				
			host.setOnAction(new EventHandler<ActionEvent>() {

				@SuppressWarnings("unused")
				@Override
				public void handle(ActionEvent arg0) {
					
					new Thread(() -> { 
					    TankServer ts = new TankServer();
					    ts.start();
//					    ts.launchFrame();
					  }).start();
					
					System.out.println("服务器启动成功");
			        
			        ServerStart ss = new ServerStart();			        
			        s1.close();
			        
					
				}
			});
			
			guest.setOnAction(new EventHandler<ActionEvent>() {

				@SuppressWarnings("unused")
				@Override
				public void handle(ActionEvent arg0) {
					TankClient tc = new TankClient();
					s1.close();
				}
			});
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
