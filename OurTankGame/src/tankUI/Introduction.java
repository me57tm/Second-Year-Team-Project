package tankUI;

import audio.AudioManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
/**
 * Show players how the control the tank, its goals, etc.
 */
public class Introduction
{
	public Introduction(){
		try {
			
			//Label text = new Label("\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"Solo:"+"\n"+"Control: "+"Choose before the mode starts."+"\n"+"Goal：" +"Destroy all AI tanks before the end of time."+"\n"+"Local PvP:"+"\n"+"Control: "+"Yellow tank: WASD moving, J shooting."+"\n"+"Red tank: ↑↓←→ moving, Space shooting."+"\n"+"Goal："+"Destroy your opponent tank before the end of time."+"\n"+"PvP: "+"\n"+"Control: "+"Yellow tank: WASD moving, J shooting."+"\n"+"Red tank: ↑↓←→ moving, Space shooting."+"\n"+"Goal："+"Destroy your opponent tank."+"\n"+"Aduio："+"\n" +"The sound can be adjusted from the menu bar above."+"\n"+"Others: "+"\n"+"Each coin is worth 5 scores");									

			
			Image img  = new Image("images/introduction.png");
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
			sp.getChildren().addAll(back);
			root.setBottom(sp);
			root.setBackground(bg);

			
			Stage s1 =new Stage();
			Scene scene = new Scene(root);
			s1.setWidth(1160);
			s1.setHeight(820);
			s1.setResizable(false);
			s1.setScene(scene);
			s1.setTitle("Tank Game");
			s1.setResizable(false);
			s1.getIcons().add(new Image("images/icon_tank.jpg"));
			s1.show();
			
			back.setOnAction(new EventHandler<ActionEvent>() {

				@SuppressWarnings("unused")
				@Override
				public void handle(ActionEvent arg0) {
					AudioManager.play("button","sound");
					TankMenu tk = new TankMenu();
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
