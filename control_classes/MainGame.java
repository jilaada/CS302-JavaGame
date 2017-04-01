package control_classes;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model_classes.Ball;
import model_classes.Paddle;
import model_classes.Player;
import model_classes.Point;

public class MainGame extends Application {

    @Override
    public void start(Stage theStage)
    {
        // Create the group root and scene for rendering the GUI
        Group root = new Group();
        Scene scene = new Scene(root, 1024, 768, Color.BLACK);
        theStage.setScene( scene );
        theStage.setTitle("Game window");

        // IO handle declaration
        IOHandle HandleIO = new IOHandle(scene);

        // Declaring the classes to be used
        ObjectControl ControlUnit = new ObjectControl();
        Ball newBall = new Ball(5, 5);
        // Set player one
        Player player1 = new Player("TestPlayer");
        Paddle paddle1 = new Paddle(5,40,1);
        paddle1.setBounds();
        player1.addPlayerPaddle(paddle1);
        Point ppoint = new Point(150, 250);
        paddle1.setCurrentPos(ppoint);

        // Added a circle to scene to represent the ball
        Circle c1 = new Circle(newBall.getCurrentPos().getX(), newBall.getCurrentPos().getY(), newBall.getBallRadius(), Color.RED);
        c1.setFill(Color.RED);
        root.getChildren().add(c1);

        // Added a rectangle to be painted on the scene to represent the paddle
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

                //TODO: move the movement detection to another class to CONTROL the movement
                if (HandleIO.hasMovedLeftP1()) {
                    ControlUnit.movePaddle(paddle1, 0);
                    HandleIO.resetP1();
                } else if (HandleIO.hasMovedRightP1()) {
                    ControlUnit.movePaddle(paddle1, 1);
                    HandleIO.resetP1();
                }

                ControlUnit.moveBall(newBall);

                c1.relocate((double)newBall.getCurrentPos().getX(), (double)newBall.getCurrentPos().getY());
                p1.relocate((double)paddle1.getCurrentPos().getX(), (double)paddle1.getCurrentPos().getY());
            }
        }.start();

        theStage.show();
    }

}