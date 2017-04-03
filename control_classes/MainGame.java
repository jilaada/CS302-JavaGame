package control_classes;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
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
        Ball newBall = new Ball(10, 5);
        // Set player one
        Player player1 = new Player("TestPlayer");
        Paddle paddle1 = new Paddle(15,80,1);
        paddle1.setBounds();
        player1.addPlayerPaddle(paddle1);
        Point point = new Point(150, 250);
        paddle1.setCurrentPos(point);
        boolean h;


        Circle c1 = new Circle(newBall.getCurrentPos().getX(), newBall.getCurrentPos().getY(), newBall.getBallRadius(), Color.RED);
        c1.setFill(Color.RED);
        root.getChildren().add(c1);

        // Added a rectangle to be painted on the scene
        Rectangle p1 = new Rectangle(paddle1.getCurrentPos().getX(), paddle1.getCurrentPos().getY(), paddle1.getPaddleSize(), 7);
        p1.setFill(Color.AQUAMARINE);
        root.getChildren().add(p1);


        Collision collisionDetection = new Collision();
        gameObject ballObj = new gameObject(c1, newBall);
        gameObject paddleObj = new gameObject(p1, paddle1);

        final long startNanoTime = System.nanoTime();


        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                // Run the input handle
                //TODO: this repetitive action can be set in a different function game().tick()?
                HandleIO.handleMovement();

                if (HandleIO.hasMovedLeftP1()) {
                    if (ControlUnit.movePaddle(paddle1, 0)) {
                        // Is not horizontal
                        p1.setHeight(7);
                        p1.setWidth(80);
                    } else {
                        p1.setHeight(80);
                        p1.setWidth(7);
                    }
                } else if (HandleIO.hasMovedRightP1()) {
                    if (ControlUnit.movePaddle(paddle1, 1)) {
                        // Is not horizontal
                        p1.setHeight(7);
                        p1.setWidth(80);
                    } else {
                        p1.setHeight(80);
                        p1.setWidth(7);
                    }
                }
                HandleIO.resetPaddle();

                CollisionStruct move = collisionDetection.checkCollisions(ballObj,paddleObj);
                ControlUnit.moveBall(newBall, move);
                c1.relocate((double)newBall.getCurrentPos().getX(), (double)newBall.getCurrentPos().getY());
                p1.relocate((double)paddle1.getCurrentPos().getX(), (double)paddle1.getCurrentPos().getY());


            }
        }.start();

        theStage.show();
    }

}