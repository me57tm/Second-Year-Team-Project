package application;

import javafx.application.Application;
import javafx.stage.Stage;
import tankUI.TankMenu;

public class Main extends Application
/**
 * Entrance to the entire program
 */
{
	public static void main(String[] args)
	{
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception
	{	
		@SuppressWarnings("unused")
		TankMenu tm = new TankMenu();		
	}
}