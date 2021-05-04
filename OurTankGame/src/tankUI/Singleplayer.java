package tankUI;


import application.solo_hard;
import application.solo_easy;
import application.solo_normal;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Singleplayer {
	@SuppressWarnings("unused")
	public Singleplayer(){
	Stage s1 =new Stage();
	s1.setTitle("Tank Game");
	s1.getIcons().add(new Image("images/icon_tank.jpg"));
	Image img  = new Image("images/tankmenuimg.png");
	ImageView imgV = new ImageView(img);
	//imgV.setScaleX(2.5);
	//imgV.setScaleY(2.5);
	
	Label set1 = new Label();
	set1.setLayoutX(0);
	set1.setLayoutY(0);
	
	Label set2 = new Label();
	set2.setLayoutX(1000);
	set2.setLayoutY(1000);
	
	
	
	Button b1 = new Button("Easy");
	b1.getStyleClass().add("font");
	
	b1.setPrefSize(250, 40);
	b1.setLayoutX(375);
	b1.setLayoutY(500);
	b1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	
	b1.setOnAction(new EventHandler<ActionEvent>()
	{

		@Override
		public void handle(ActionEvent arg0)
		{
			solo_easy se = new solo_easy();
			s1.close();
		}
	});
	
	Button b2 = new Button("Normal");
	b2.getStyleClass().add("font");
	
	b2.setPrefSize(250, 40);
	b2.setLayoutX(380 );
	b2.setLayoutY(600);
	b2.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	
	b2.setOnAction(new EventHandler<ActionEvent>()
	{

		@Override
		public void handle(ActionEvent arg0)
		{
			solo_normal se = new solo_normal();
			s1.close();
		}
	});
	
	Button b3 = new Button("Hard");
	b3.getStyleClass().add("font");
	
	b3.setPrefSize(250, 40);
	b3.setLayoutX(375);
	b3.setLayoutY(700);
	b3.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	
	b3.setOnAction(new EventHandler<ActionEvent>()
	{

		@Override
		public void handle(ActionEvent arg0)
		{
			solo_hard comode = new solo_hard();
			s1.close();
		}
	});
	
	Button b4 = new Button("Back");
	b4.getStyleClass().add("font");
	b4.setPrefSize(250, 40);
	b4.setLayoutX(375);
	b4.setLayoutY(800);
	b4.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	
	b4.setOnAction(e -> {
		StartGame S1 = new StartGame();
		s1.close();
	});
	
	Group g1 = new Group();
	g1.getChildren().addAll(set1,set2,b1,b2,b3,b4);
	
	StackPane sp = new StackPane(); 
	sp.getChildren().addAll(imgV,g1);
	
	Scene scene = new Scene(sp,1152,800);
	s1.setScene(scene);		
	s1.show();
	}
}
