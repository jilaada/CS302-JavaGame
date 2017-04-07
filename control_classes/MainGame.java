package control_classes;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model_classes.*;

import java.util.ArrayList;

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
        Paddle paddle1 = new Paddle(15,80,15, 1);
        Paddle paddle2 = new Paddle(15,80,15, 2);
        Paddle paddle3 = new Paddle(15,80,15, 3);
        Paddle paddle4 = new Paddle(15,80,15, 4);
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
        Point point3 = new Point(150,503);
        Point point4 = new Point(850,503);
        paddle1.setCurrentPos(point1);
        paddle2.setCurrentPos(point2);
        paddle3.setCurrentPos(point3);
        paddle4.setCurrentPos(point4);
        //boolean h;

        //Testing
        Rectangle t1 = new Rectangle(0, 0,350, 250);
        Rectangle t2 = new Rectangle(674, 0,350, 250);
        Rectangle t3 = new Rectangle(0, 518,350, 250);
        Rectangle t4 = new Rectangle(674, 518,350, 250);
        Rectangle w1 = new Rectangle(0,180,20,20);

        Image img = new Image("/images/boxGreen1.png");
        w1.setFill(new ImagePattern(img));
        t1.setFill(Color.BLACK);
        t2.setFill(Color.CRIMSON);
        t3.setFill(Color.CRIMSON);
        t4.setFill(Color.CRIMSON);
        root.getChildren().add(t1);
        root.getChildren().add(t2);
        root.getChildren().add(t3);
        root.getChildren().add(t4);
        root.getChildren().add(w1);

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

        //gameObject[] gameArray;
        ArrayList<gameObject> gameArray = new ArrayList();
        gameArray.add(paddleObj1);
        gameArray.add(paddleObj2);
        gameArray.add(paddleObj3);
        gameArray.add(paddleObj4);


        final long startNanoTime = System.nanoTime();

        new AnimationTimer()
        {
            @Override
            public void handle(long currentNanoTime)
            {
                // Run the input handle
                //TODO: this repetitive action can be set in a different function game().tick()?
                /*HandleIO.handleMovementP1();
                HandleIO.handleMovementP2();
                HandleIO.handleMovementP3();
                HandleIO.handleMovementP4();*/

                if (HandleIO.pauseGame == false) {
                    HandleIO.keyPressed();

                    if (HandleIO.hasMovedLeftP1()) {
                        if (ControlUnit.movePaddle(paddle1, 0)) {
                            // Is not horizontal
                            paddle1.setRotated(false);
                            p1.setHeight(15);
                            p1.setWidth(80);
                        } else {
                            paddle1.setRotated(true);
                            p1.setHeight(80);
                            p1.setWidth(15);
                        }
                    } else if (HandleIO.hasMovedRightP1()) {
                        if (ControlUnit.movePaddle(paddle1, 1)) {
                            // Is not horizontal
                            paddle1.setRotated(false);
                            p1.setHeight(15);
                            p1.setWidth(80);
                        } else {
                            paddle1.setRotated(true);
                            p1.setHeight(80);
                            p1.setWidth(15);
                        }
                    }

                    if (HandleIO.hasMovedLeftP2()) {
                        if (ControlUnit.movePaddle(paddle2, 0)) {
                            // Is not horizontal
                            paddle2.setRotated(false);
                            p2.setHeight(15);
                            p2.setWidth(80);
                        } else {
                            paddle2.setRotated(true);
                            p2.setHeight(80);
                            p2.setWidth(15);
                        }
                    } else if (HandleIO.hasMovedRightP2()) {
                        if (ControlUnit.movePaddle(paddle2, 1)) {
                            // Is not horizontal
                            paddle2.setRotated(false);
                            p2.setHeight(15);
                            p2.setWidth(80);
                        } else {
                            paddle2.setRotated(true);
                            p2.setHeight(80);
                            p2.setWidth(15);

                        }
                    }

                    if (HandleIO.hasMovedLeftP3()) {
                        if (ControlUnit.movePaddle(paddle3, 0)) {
                            // Is not horizontal
                            paddle3.setRotated(false);
                            p3.setHeight(15);
                            p3.setWidth(80);
                        } else {
                            paddle3.setRotated(true);
                            p3.setHeight(80);
                            p3.setWidth(15);
                        }
                    } else if (HandleIO.hasMovedRightP3()) {
                        if (ControlUnit.movePaddle(paddle3, 1)) {
                            // Is not horizontal
                            paddle3.setRotated(false);
                            p3.setHeight(15);
                            p3.setWidth(80);
                        } else {
                            paddle3.setRotated(true);
                            p3.setHeight(80);
                            p3.setWidth(15);
                        }
                    }

                    if (HandleIO.hasMovedLeftP4()) {
                        if (ControlUnit.movePaddle(paddle4, 0)) {
                            // Is not horizontal
                            paddle4.setRotated(false);
                            p4.setHeight(15);
                            p4.setWidth(80);
                        } else {
                            paddle4.setRotated(true);
                            p4.setHeight(80);
                            p4.setWidth(15);
                        }
                    } else if (HandleIO.hasMovedRightP4()) {
                        if (ControlUnit.movePaddle(paddle4, 1)) {
                            // Is not horizontal
                            paddle4.setRotated(false);
                            p4.setHeight(15);
                            p4.setWidth(80);
                        } else {
                            paddle4.setRotated(true);
                            p4.setHeight(80);
                            p4.setWidth(15);
                        }
                    }

                    HandleIO.resetPaddle();

                    ((Ball) ballObj.getObj()).setMoved(false);
                    for(gameObject temp:gameArray) {
                        if(!((Ball) ballObj.getObj()).hasMoved()) {
                            CollisionStruct move = collisionDetection.checkCollisions(ballObj, temp);
                            ControlUnit.moveBall(newBall, move);
                        }
                    }

                    if(!((Ball) ballObj.getObj()).hasMoved()) {
                        ControlUnit.moveInBounds(newBall, collisionDetection);
                    }



                   // CollisionStruct move = collisionDetection.checkCollisions(ballObj, paddleObj1);
                   // ControlUnit.moveBall(newBall, move);
                    c1.relocate((double)newBall.getCurrentPos().getX(), (double)newBall.getCurrentPos().getY());
                    p1.relocate((double)paddle1.getCurrentPos().getX(), (double)paddle1.getCurrentPos().getY());
                    p2.relocate((double)paddle2.getCurrentPos().getX(), (double)paddle2.getCurrentPos().getY());
                    p3.relocate((double)paddle3.getCurrentPos().getX(), (double)paddle3.getCurrentPos().getY());
                    p4.relocate((double)paddle4.getCurrentPos().getX(), (double)paddle4.getCurrentPos().getY());

                } else {
                    HandleIO.keyPressed();
                }
            }
        }.start();

        theStage.show();
    }
}