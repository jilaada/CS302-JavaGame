package control_classes;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.*;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.*;
import model_classes.*;

public class MainGame extends Application {

    @Override
    public void start(Stage theStage)
    {
        Group root = new Group();
        Scene scene = new Scene(root, 1024, 768, Color.BLACK);
        theStage.setScene( scene );
        theStage.setTitle("Game window");

        Ball newBall = new Ball(1, 5);


        Circle c1 = new Circle(newBall.getCurrentPos().getX(), newBall.getCurrentPos().getY(), newBall.getBallRadius(), Color.RED);
        c1.setFill(Color.RED);
        root.getChildren().add(c1);


        final long startNanoTime = System.nanoTime();

        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                double t = (currentNanoTime - startNanoTime) / 1000000000.0;

                double x = 232 + 128 * Math.cos(t);
                double y = 232 + 128 * Math.sin(t);

                newBall.moveBall();
                /**System.out.print((double)newBall.getCurrentPos().getX());
                System.out.print("  ");
                System.out.println((double)newBall.getCurrentPos().getY());*/
                c1.relocate((double)newBall.getCurrentPos().getX(), (double)newBall.getCurrentPos().getY());
            }
        }.start();

        theStage.show();
    }

}