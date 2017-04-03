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
        // Set player one and player two
        Player player1 = new Player("TestPlayer1");
        Player player2 = new Player("TestPlayer2");
        Paddle paddle1 = new Paddle(15,80,1);
        Paddle paddle2 = new Paddle(15,80,2);
        paddle1.setBounds();
        paddle2.setBounds();
        player1.addPlayerPaddle(paddle1);
        player2.addPlayerPaddle(paddle2);
        Point point1 = new Point(150,250);
        Point point2 = new Point(900,250);
        paddle1.setCurrentPos(point1);
        paddle2.setCurrentPos(point2);
        //boolean h;


        Circle c1 = new Circle(newBall.getCurrentPos().getX(), newBall.getCurrentPos().getY(), newBall.getBallRadius(), Color.RED);
        c1.setFill(Color.RED);
        root.getChildren().add(c1);

        // Added a rectangle to be painted on the scene
        Rectangle p1 = new Rectangle(paddle1.getCurrentPos().getX(), paddle1.getCurrentPos().getY(), paddle1.getPaddleSize(), 15);
        p1.setFill(Color.AQUAMARINE);
        root.getChildren().add(p1);

        Rectangle p2 = new Rectangle(paddle2.getCurrentPos().getX(), paddle2.getCurrentPos().getY(), paddle2.getPaddleSize(), 15);
        p2.setFill(Color.CORAL);
        root.getChildren().add(p2);

        Collision collisionDetection = new Collision();
        gameObject ballObj = new gameObject(c1, newBall);
        gameObject paddleObj1 = new gameObject(p1, paddle1);
        gameObject paddleObj2 = new gameObject(p2, paddle2);

        final long startNanoTime = System.nanoTime();


        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                // Run the input handle
                //TODO: this repetitive action can be set in a different function game().tick()?
                HandleIO.handleMovementP1();
                HandleIO.handleMovementP2();

                if (HandleIO.hasMovedLeftP1()) {
                    if (ControlUnit.movePaddle(paddle1, 0)) {
                        // Is not horizontal
                        p1.setHeight(15);
                        p1.setWidth(80);
                    } else {
                        p1.setHeight(80);
                        p1.setWidth(15);
                    }
                } else if (HandleIO.hasMovedRightP1()) {
                    if (ControlUnit.movePaddle(paddle1, 1)) {
                        // Is not horizontal
                        p1.setHeight(15);
                        p1.setWidth(80);
                    } else {
                        p1.setHeight(80);
                        p1.setWidth(15);
                    }
                }

                if (HandleIO.hasMovedLeftP2()) {
                    if (ControlUnit.movePaddle(paddle2, 0)) {
                        // Is not horizontal
                        p2.setHeight(15);
                        p2.setWidth(80);
                    } else {
                        p2.setHeight(80);
                        p2.setWidth(15);
                    }
                } else if (HandleIO.hasMovedRightP2()) {
                    if (ControlUnit.movePaddle(paddle2, 1)) {
                        // Is not horizontal
                        p2.setHeight(15);
                        p2.setWidth(80);
                    } else {
                        p2.setHeight(80);
                        p2.setWidth(15);
                    }
                }
                HandleIO.resetPaddle();

                CollisionStruct move = collisionDetection.checkCollisions(ballObj,paddleObj1);
                move = collisionDetection.checkCollisions(ballObj, paddleObj2);
                ControlUnit.moveBall(newBall, move);
                c1.relocate((double)newBall.getCurrentPos().getX(), (double)newBall.getCurrentPos().getY());
                p1.relocate((double)paddle1.getCurrentPos().getX(), (double)paddle1.getCurrentPos().getY());
                p2.relocate((double)paddle2.getCurrentPos().getX(), (double)paddle2.getCurrentPos().getY());

            }
        }.start();

        theStage.show();
    }

}