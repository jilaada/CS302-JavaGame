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
        Player player3 = new Player("TestPlayer3");
        Player player4 = new Player("TestPlayer4");
        // set up paddles
        Paddle paddle1 = new Paddle(15,80,1);
        Paddle paddle2 = new Paddle(15,80,2);
        Paddle paddle3 = new Paddle(15,80,3);
        Paddle paddle4 = new Paddle(15,80,4);
        paddle1.setBounds();
        paddle2.setBounds();
        paddle3.setBounds();
        paddle4.setBounds();
        // Add paddles to players
        player1.addPlayerPaddle(paddle1);
        player2.addPlayerPaddle(paddle2);
        player3.addPlayerPaddle(paddle3);
        player4.addPlayerPaddle(paddle4);
        Point point1 = new Point(150,250);
        Point point2 = new Point(850,250);
        Point point3 = new Point(150,518);
        Point point4 = new Point(850,518);
        paddle1.setCurrentPos(point1);
        paddle2.setCurrentPos(point2);
        paddle3.setCurrentPos(point3);
        paddle4.setCurrentPos(point4);
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

        Rectangle p3 = new Rectangle(paddle3.getCurrentPos().getX(), paddle3.getCurrentPos().getY(), paddle3.getPaddleSize(), 15);
        p3.setFill(Color.PALEGREEN);
        root.getChildren().add(p3);

        Rectangle p4 = new Rectangle(paddle4.getCurrentPos().getX(), paddle4.getCurrentPos().getY(), paddle4.getPaddleSize(), 15);
        p4.setFill(Color.LAVENDER);
        root.getChildren().add(p4);

        Collision collisionDetection = new Collision();
        gameObject ballObj = new gameObject(c1, newBall);
        gameObject paddleObj1 = new gameObject(p1, paddle1);
        gameObject paddleObj2 = new gameObject(p2, paddle2);
        gameObject paddleObj3 = new gameObject(p3, paddle3);
        gameObject paddleObj4 = new gameObject(p4, paddle4);

        final long startNanoTime = System.nanoTime();


        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                // Run the input handle
                //TODO: this repetitive action can be set in a different function game().tick()?
                HandleIO.handleMovementP1();
                HandleIO.handleMovementP2();
                HandleIO.handleMovementP3();
                HandleIO.handleMovementP4();

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

                if (HandleIO.hasMovedLeftP3()) {
                    if (ControlUnit.movePaddle(paddle3, 0)) {
                        // Is not horizontal
                        p3.setHeight(15);
                        p3.setWidth(80);
                    } else {
                        p3.setHeight(80);
                        p3.setWidth(15);
                    }
                } else if (HandleIO.hasMovedRightP3()) {
                    if (ControlUnit.movePaddle(paddle3, 1)) {
                        // Is not horizontal
                        p3.setHeight(15);
                        p3.setWidth(80);
                    } else {
                        p3.setHeight(80);
                        p3.setWidth(15);
                    }
                }

                if (HandleIO.hasMovedLeftP4()) {
                    if (ControlUnit.movePaddle(paddle4, 0)) {
                        // Is not horizontal
                        p4.setHeight(15);
                        p4.setWidth(80);
                    } else {
                        p4.setHeight(80);
                        p4.setWidth(15);
                    }
                } else if (HandleIO.hasMovedRightP4()) {
                    if (ControlUnit.movePaddle(paddle4, 1)) {
                        // Is not horizontal
                        p4.setHeight(15);
                        p4.setWidth(80);
                    } else {
                        p4.setHeight(80);
                        p4.setWidth(15);
                    }
                }

                HandleIO.resetPaddle();

                CollisionStruct move = collisionDetection.checkCollisions(ballObj,paddleObj1);
                move = collisionDetection.checkCollisions(ballObj, paddleObj1);
                ControlUnit.moveBall(newBall, move);
                c1.relocate((double)newBall.getCurrentPos().getX(), (double)newBall.getCurrentPos().getY());
                p1.relocate((double)paddle1.getCurrentPos().getX(), (double)paddle1.getCurrentPos().getY());
                p2.relocate((double)paddle2.getCurrentPos().getX(), (double)paddle2.getCurrentPos().getY());
                p3.relocate((double)paddle3.getCurrentPos().getX(), (double)paddle3.getCurrentPos().getY());
                p4.relocate((double)paddle4.getCurrentPos().getX(), (double)paddle4.getCurrentPos().getY());

            }
        }.start();

        theStage.show();
    }

}