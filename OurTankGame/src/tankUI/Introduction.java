package tankUI;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Introduction {
	public Introduction(){
		Stage s1 =new Stage();
		s1.setTitle("Tank Game");
		s1.getIcons().add(new Image("images/icon_tank.jpg"));
		Image img  = new Image("images/tank1.png");
		ImageView imgV = new ImageView(img);
		imgV.setScaleX(2.5);
		imgV.setScaleY(2.5);
		
		Label set1 = new Label();
		set1.setLayoutX(0);
		set1.setLayoutY(0);
		
		Label set2 = new Label();
		set2.setLayoutX(1000);
		set2.setLayoutY(1000);
		
		
		Button b4 = new Button("Back");
		b4.getStyleClass().add("font");
		b4.setPrefSize(250, 40);
		b4.setLayoutX(375);
		b4.setLayoutY(800);
		b4.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		b4.setOnAction(e -> {
			TankMenu m1 = new TankMenu();
			s1.close();
		});
		
		Group g1 = new Group();
		g1.getChildren().addAll(set1,set2,b4);
		
		StackPane sp = new StackPane(); 
		sp.getChildren().addAll(imgV,g1);
		
		Scene scene = new Scene(sp,1920,1080);
		s1.setMaximized(true);
		s1.setScene(scene);		
		s1.show();
	}
}
