package control_classes;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model_classes.Ball;
import model_classes.CollisionStruct;
import model_classes.gameObject;
import view_classes.RenderView;

import java.util.ArrayList;

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
        SetUpGame.SetUpWall(root);

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