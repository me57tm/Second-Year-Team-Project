package tankUI;

import application.solo_Mode;
import audio.AudioManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class Choose {
	boolean isWASD;
	// Choose your keyboard preferences
	Choose(int difficulty) {
		TilePane rootControls = new TilePane();
		rootControls.setPadding(new Insets(20));
		rootControls.setAlignment(Pos.BASELINE_CENTER);

		Image menuImg = new Image("images/TankMenu.jpg");
		ImageView menuImgV = new ImageView(menuImg);
		Image img = new Image("art/wasd.png");
		ImageView view = new ImageView(img);
		Button wasdButton = new Button();
		wasdButton.setPrefSize(250, 40);
		wasdButton.setGraphic(view);
		wasdButton.setStyle("-fx-background-color: transparent;");
		wasdButton.setPrefSize(80, 80);

		Image imgArrows = new Image("art/arrows.png");
		ImageView viewArrows = new ImageView(imgArrows);
		Button arrowsButton = new Button();
		arrowsButton.setPrefSize(100, 40);
		arrowsButton.setGraphic(viewArrows);
		arrowsButton.setStyle("-fx-background-color: transparent;");
		arrowsButton.setPrefSize(80, 80);

		rootControls.getChildren().addAll(wasdButton, arrowsButton);
		rootControls.setAlignment(Pos.CENTER);
		StackPane sp = new StackPane();
		sp.getChildren().addAll(menuImgV, rootControls);
		Scene sceneControls = new Scene(sp, 1152, 800);
		Stage stageControls = new Stage();
		stageControls.setScene(sceneControls);
		stageControls.getIcons().add(new Image("images/icon_tank.jpg"));
		stageControls.setTitle("Choose your controls:");

		stageControls.show();

		wasdButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				AudioManager.play("button", "sound");
				isWASD = true;
				@SuppressWarnings("unused")
				solo_Mode solomode = new solo_Mode(difficulty,isWASD);
				stageControls.close();
				
			}
		});

		arrowsButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				AudioManager.play("button", "sound");
				isWASD = false;
				@SuppressWarnings("unused")
				solo_Mode solomode = new solo_Mode(difficulty,isWASD);
				stageControls.close();
			}
		});
	}
}
