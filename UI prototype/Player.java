package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Player
{
	private final Stage stage = new Stage();

	public Player(String name) {
		
		try
		{
		
		String name1 = Main.getName();
			
		BorderPane root = new BorderPane();
		Label player1 = new Label("Player: " + name1);
		
		VBox vb = new VBox();
		vb.getChildren().addAll(player1);
		vb.setAlignment(Pos.CENTER);
		vb.setSpacing(40);
		
		root.setCenter(vb);
		
		Scene scene = new Scene(root, 350, 50);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.setTitle("Player List");
		stage.show();
		
	} catch (Exception e)
	{
		e.printStackTrace();
	}
}

}

