package control_classes;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model_classes.Ball;
import model_classes.CollisionStruct;
import model_classes.gameObject;
import view_classes.RenderView;

import java.util.ArrayList;
import java.util.Random;

public class MainGame extends Application {

    @Override
    public void start(Stage theStage)
    {
        Group root = new Group();
        Scene scene = new Scene(root, 1024, 768, Color.BLACK);
        theStage.setScene( scene );
        theStage.setTitle("Game window");

        // Set up object control
        ObjectControl ControlUnit = new ObjectControl();

        // Set up a new game
        GameSetUp SetUpGame = new GameSetUp();

        // Add Players via GameSetUp
        //SetUpGame.SetUpPlayers();
        SetUpGame.SetUpBall(10, 5);
        SetUpGame.SetUpPaddles(15, 80, 20);

        // Set up rendering of objects
        RenderView render = new RenderView(SetUpGame.getPlayer1(), SetUpGame.getPlayer2(), SetUpGame.getPlayer3(), SetUpGame.getPlayer4(), SetUpGame.getBall());
        render.SetUpRender();

        // IO handle declaration
        IOHandle HandleIO = new IOHandle(scene);

        // Render the paddles and balls
        root.getChildren().add(render.getBallRender());
        root.getChildren().add(render.getP1Render());
        root.getChildren().add(render.getP2Render());
        root.getChildren().add(render.getP3Render());
        root.getChildren().add(render.getP4Render());

        // Testing rectangle wall
        Image img = new Image("/images/boxWhite1.png");
        Image img1 = new Image("/images/boxWhite2.png");
        Image img2 = new Image("/images/boxWhite3.png");
        //Rectangle w1 = new Rectangle(0,150,50,50);
        //w1.setFill(new ImagePattern(img));
        //root.getChildren().add(w1);
        Rectangle w2 = new Rectangle(50,150,50,50);
        w2.setFill(new ImagePattern(img1));
        root.getChildren().add(w2);
        Rectangle w3 = new Rectangle(100,150,50,50);
        w3.setFill(new ImagePattern(img2));
        root.getChildren().add(w3);

        int count;
        Random c = new Random();
        for (int i = 0; i < 325; i+=25) {
            for (int j = 0; j < 225; j+=25) {
                if (!((i < 225) && (j < 125))) {
                    Rectangle w1 = new Rectangle(i,j,25,25);

                    count = c.nextInt(3);
                    switch (count) {
                        case 0 :    w1.setFill(new ImagePattern(img));
                                    break;
                        case 1 :    w1.setFill(new ImagePattern(img1));
                                    break;
                        case 2 :    w1.setFill(new ImagePattern(img2));
                                    break;
                        default:    w1.setFill(new ImagePattern(img));
                                    break;
                    }
                    root.getChildren().add(w1);
                }
            }
        }

        Collision collisionDetection = new Collision();
        gameObject ballObj = new gameObject(render.getBallRender(), SetUpGame.getBall());
        gameObject paddleObj1 = new gameObject(render.getP1Render(), SetUpGame.getPlayer1().getPlayerPaddle());
        gameObject paddleObj2 = new gameObject(render.getP2Render(), SetUpGame.getPlayer2().getPlayerPaddle());
        gameObject paddleObj3 = new gameObject(render.getP3Render(), SetUpGame.getPlayer3().getPlayerPaddle());
        gameObject paddleObj4 = new gameObject(render.getP4Render(), SetUpGame.getPlayer4().getPlayerPaddle());

        //gameObject[] gameArray;
        ArrayList<gameObject> gameArray = new ArrayList();
        gameArray.add(paddleObj1);
        gameArray.add(paddleObj2);
        gameArray.add(paddleObj3);
        gameArray.add(paddleObj4);


        final long startNanoTime = System.nanoTime();

        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                // Run the input handle
                //TODO: this repetitive action can be set in a different function game().tick()?

                if (HandleIO.isPaused() == false) {
                    HandleIO.keyPressed();
                    ControlUnit.moveAllPaddles(render, HandleIO, SetUpGame);
                    HandleIO.resetPaddle();

                    ((Ball) ballObj.getObj()).setMoved(false);
                    for (gameObject temp : gameArray) {
                        if (!((Ball) ballObj.getObj()).hasMoved()) {
                            CollisionStruct move = collisionDetection.checkCollisions(ballObj, temp);
                            ControlUnit.moveBall(SetUpGame.getBall(), move);
                        }
                    }

                    if (!((Ball) ballObj.getObj()).hasMoved()) {
                        ControlUnit.moveInBounds(SetUpGame.getBall(), collisionDetection);
                    }
                    render.tickRender();

                } else {
                    HandleIO.keyPressed();
                }
            }
        }.start();

        theStage.show();
    }
}