package sample;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.image.Image;

import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main extends Application {

    public static void main(String[] args)
    {
        try
        {
            launch(args);
        }
        catch (Exception error)
        {
            error.printStackTrace();
        }
        finally
        {
            System.exit(0);
        }

    }

    public void start(Stage mainStage)
    {
        mainStage.setTitle("TANKZ");

        BorderPane root = new BorderPane();
        Scene mainScene = new Scene(root);
        mainStage.setScene(mainScene);

        Canvas canvas = new Canvas(1200,800);
        GraphicsContext context = canvas.getGraphicsContext2D();
        root.setCenter(canvas);

        // handle continuous inputs (as long as key is pressed)
        ArrayList<String> keyPressedList = new ArrayList<>();

        //handle unique inputs (once per key press)
        ArrayList<String> keyJustPressedList = new ArrayList<>();


        mainScene.setOnKeyPressed(
                (KeyEvent event) ->
                {
                    String keyName = event.getCode().toString();
                    //avoid adding duplicates to the list
                    if (!keyPressedList.contains(keyName))
                        keyPressedList.add(keyName);
                    if (!keyJustPressedList.contains(keyName))
                        keyJustPressedList.add(keyName);

                }
        );

        mainScene.setOnKeyReleased(
                (KeyEvent event) ->
                {
                    String keyName = event.getCode().toString();
                    if (keyPressedList.contains(keyName))
                        keyPressedList.remove(keyName);
                }
        );

        Sprite background = new Sprite("imagesProjectAI/space.png");
        background.position.set(600,400);

        Sprite spaceShip = new Sprite("imagesProjectAI/tank.png");
        spaceShip.position.set(100,300);

        ArrayList<Sprite> laserList = new ArrayList<Sprite>();
        ArrayList<Sprite> asteroidList = new ArrayList<Sprite>();

        AnimationTimer gameloop = new AnimationTimer() {
            public void handle(long nanotime)
            {
                // process user input
                if(keyPressedList.contains("LEFT")) {
                    spaceShip.rotation -= 3;
                }

                if(keyPressedList.contains("RIGHT"))
                    spaceShip.rotation += 3;

                if(keyPressedList.contains("UP"))
                {
                    spaceShip.velocity.setLength(150);
                    spaceShip.velocity.setAngle(spaceShip.rotation);
                }
                else
                {
                    spaceShip.velocity.setLength(0);
                }

                if ( keyPressedList.contains("SPACE"))
                {
                    context.save();
                    Sprite laser = new Sprite("imagesProjectAI/red-circle.png");
                    laser.position.set(spaceShip.position.x, spaceShip.position.y);
                    laser.velocity.setLength(400);
                    laser.velocity.setAngle(spaceShip.rotation);
                    laserList.add(laser);

                }
                spaceShip.update(1/60.0);
                for(Sprite laser : laserList) {
                    laser.update(1 / 60.0);
                    laser.updateBullet();
                }

                background.render(context);
                spaceShip.render(context);
                for(Sprite laser : laserList)
                    laser.render(context);
            }
        };

        gameloop.start();

        mainStage.show();
    }
}
