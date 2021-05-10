package client;

import java.util.List;

import application.N_Mode;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import physics.Tank;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
/**
 * Multiplayer mode waiting for other players to enter the interface
 */
public class waiting_Room {

	public waiting_Room(List<Tank> newTanks, int id, NetClient nc) {

		try {

			Image img  = new Image("images/TankMenu.jpg");
			ImageView im = new ImageView();
			im.setImage(img);
			BackgroundImage bgi = new BackgroundImage(img, null, null, null, null);
			Background bg = new Background(bgi);

			Label t = new Label("Please waiting");
			Label t1 = new Label("The rest of player is joining the game");
			t.getStyleClass().add("font");
			t.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			t1.getStyleClass().add("font");
			t1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			VBox vb = new VBox();
			vb.getChildren().addAll(t, t1);
			vb.setAlignment(Pos.CENTER);
			vb.setSpacing(40);

			BorderPane root = new BorderPane();
			root.setCenter(vb);
			root.setBackground(bg);

			Stage s1 = new Stage();
			Scene scene = new Scene(root);
			s1.setWidth(1160);
			s1.setHeight(820);
			s1.setResizable(false);
			s1.setScene(scene);
			s1.setTitle("Tank BattleField");
			s1.show();
			s1.setOnCloseRequest(new EventHandler<WindowEvent>() {

				@Override
				public void handle(WindowEvent arg0) {
					System.exit(0);
					s1.close();
				}
			});
			AnimationTimer gameloop = new AnimationTimer() {

				@Override
				public void handle(long now) {
//					    System.out.println("-------" + id);
					for (int j = 0; j < newTanks.size(); j++) {
//						System.out.println(newTanks.get(j).getId()+":     " + newTanks.get(j).getName());
						if ((newTanks.get(j).getId() % 2) != 0 && !newTanks.get(j).getName().equals("PlayerUnknown")) {
							
							this.stop();
							s1.close();
							System.gc();
							@SuppressWarnings("unused")
							N_Mode nomode = new N_Mode(newTanks, id, nc);							
							
						}
					}

				}

			};
			gameloop.start();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
