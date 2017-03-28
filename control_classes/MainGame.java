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

        // IO handle declaration
        IOHandle HandleIO = new IOHandle(scene);

        ObjectControl ControlUnit = new ObjectControl();
        Ball newBall = new Ball(6, 5);
        // Set player one
        Player player1 = new Player("TestPlayer");
        Paddle paddle1 = new Paddle(5,15,1);
        paddle1.setBounds();
        player1.addPlayerPaddle(paddle1);
        Point ppoint = new Point(150, 250);
        paddle1.setCurrentPos(ppoint);


        Circle c1 = new Circle(newBall.getCurrentPos().getX(), newBall.getCurrentPos().getY(), newBall.getBallRadius(), Color.RED);
        c1.setFill(Color.RED);
        root.getChildren().add(c1);

        // Added a rectangle to be painted on the scene
        Rectangle p1 = new Rectangle(paddle1.getCurrentPos().getX(), paddle1.getCurrentPos().getY(), paddle1.getPaddleSize(), 7);
        p1.setFill(Color.AQUAMARINE);
        root.getChildren().add(p1);

        final long startNanoTime = System.nanoTime();

        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                // Run the input handle
                HandleIO.handleMovement();

                if (HandleIO.hasMovedLeftP1()) {
                    ControlUnit.movePaddle(paddle1, 0);
                } else if (HandleIO.hasMovedRightP1()) {
                    ControlUnit.movePaddle(paddle1, 1);
                }

                double t = (currentNanoTime - startNanoTime) / 1000000000.0;

                double x = 232 + 128 * Math.cos(t);
                double y = 232 + 128 * Math.sin(t);

                ControlUnit.moveBall(newBall);
                /**System.out.print((double)newBall.getCurrentPos().getX());
                System.out.print("  ");
                System.out.println((double)newBall.getCurrentPos().getY());*/
                c1.relocate((double)newBall.getCurrentPos().getX(), (double)newBall.getCurrentPos().getY());
                p1.relocate((double)paddle1.getCurrentPos().getX(), (double)paddle1.getCurrentPos().getY());
            }
        }.start();

        theStage.show();
    }

}