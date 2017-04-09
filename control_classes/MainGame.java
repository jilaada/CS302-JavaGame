package control_classes;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import javafx.stage.Stage;
import model_classes.Ball;
import model_classes.CollisionStruct;
import model_classes.gameObject;
import view_classes.RenderView;

import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class MainGame extends Application {

    private AnimationTimer timer;

    @Override
    public void start(Stage theStage)
    {
        Group root = new Group();
        Group end = new Group();

        Scene scene = new Scene(root, 1024, 768, Color.BLACK);
        //theStage.setScene( scene );
        theStage.setTitle("Game window");

        // Set up object control
        ObjectControl ControlUnit = new ObjectControl();

        // Set up a new game
        GameSetUp SetUpGame = new GameSetUp();
        GameStatus status = new GameStatus(SetUpGame.getPlayers());


        // Add Players via GameSetUp
        //SetUpGame.SetUpPlayers();
        SetUpGame.SetUpBall(10, 5);
        SetUpGame.SetUpPaddles(15, 80, 20);

        // Set up rendering of objects
        RenderView render = new RenderView(SetUpGame.getPlayer1(), SetUpGame.getPlayer2(), SetUpGame.getPlayer3(), SetUpGame.getPlayer4(), SetUpGame.getBall());

        //gameObject[] gameArray;
        ArrayList<gameObject> gameArray = new ArrayList();
        render.SetUpRender(root, gameArray);

        // IO handle declaration
        //TODO: determine the number of players from a previous scene of inputs
        IOHandle HandleIO = new IOHandle(scene, 1);
        AI aiHandle = new AI(HandleIO, SetUpGame);

        //Try and add other scene
        int[] sceneSwitch = {0}; //0 - Intro, 1 - Game, 2- End
        SceneChanger sceneChanger = new SceneChanger();
        Scene introScene = sceneChanger.addIntroScene(theStage, scene, sceneSwitch);
        theStage.setScene( introScene );
        Scene endScene = sceneChanger.addEndScene(theStage, introScene, sceneSwitch, "You lose");


        // Render the paddles and balls
        root.getChildren().add(render.getBallRender());
        root.getChildren().add(render.getP1Render());
        root.getChildren().add(render.getP2Render());
        root.getChildren().add(render.getP3Render());
        root.getChildren().add(render.getP4Render());
        root.getChildren().add(render.getPl1Render());
        root.getChildren().add(render.getPl2Render());
        root.getChildren().add(render.getPl3Render());
        root.getChildren().add(render.getPl4Render());

        Collision collisionDetection = new Collision();
        gameObject ballObj = new gameObject(render.getBallRender(), SetUpGame.getBall());
        gameObject paddleObj1 = new gameObject(render.getP1Render(), SetUpGame.getPlayer1().getPlayerPaddle());
        gameObject paddleObj2 = new gameObject(render.getP2Render(), SetUpGame.getPlayer2().getPlayerPaddle());
        gameObject paddleObj3 = new gameObject(render.getP3Render(), SetUpGame.getPlayer3().getPlayerPaddle());
        gameObject paddleObj4 = new gameObject(render.getP4Render(), SetUpGame.getPlayer4().getPlayerPaddle());

        //Add paddles to gameArray
        gameArray.add(paddleObj1);
        gameArray.add(paddleObj2);
        gameArray.add(paddleObj3);
        gameArray.add(paddleObj4);

        // Testing rectangle wall
        SetUpGame.SetUpWall(root, gameArray);

        //Set up delay boolean
        final boolean[] delayStart = {true};
        final long[] seconds = new long[1];
        final long[] pauseSeconds = new long[1];
        //final long[] startNanoTime = {System.nanoTime()};
        long[] startNanoTime = new long[1];
        Text timerLabel = new Text(512, 20,"3");
        timerLabel.setFont(new Font(16));
        timerLabel.setFill(Color.WHITE);
        timerLabel.setTextAlignment(TextAlignment.CENTER);
        timerLabel.setTextOrigin(VPos.CENTER);
        timerLabel.setX(512 - Math.round(timerLabel.getLayoutBounds().getWidth()/2));
        final long[] gameTime = {120};
        root.getChildren().add(timerLabel);
        boolean[] timeStarted = {false};

        timer = new AnimationTimer() {
            public void handle(long currentNanoTime) {
                // Run the input handle
                if(sceneSwitch[0] == 1) {
                    if(timeStarted[0] == false) {
                        startNanoTime[0] = System.nanoTime();
                        timeStarted[0] = true;
                    } else {
                        // Determine the time difference
                        long elapsedTime = System.nanoTime() - startNanoTime[0];
                        pauseSeconds[0] = TimeUnit.SECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS);
                        // Calculate the seconds value;

                        if (!delayStart[0]) {
                            if (!HandleIO.isPaused() && !(HandleIO.hasTimeOut() || gameTime[0] - seconds[0] < 0 || status.onePlayerAlive())) {

                                seconds[0] = pauseSeconds[0];
                                HandleIO.keyPressed();
                                // Move the AI paddles
                                aiHandle.moveAIAdvanced();
                                ControlUnit.moveAllPaddles(render, HandleIO, SetUpGame);
                                HandleIO.resetPaddle();

                                ((Ball) ballObj.getObj()).setMoved(false);

                                //Loop through every object in gameObject array and check for collisions and player deaths
                                for (int pos = 0; pos < gameArray.size(); pos++) {
                                    gameObject temp = gameArray.get(pos);
                                    if (!((Ball) ballObj.getObj()).hasMoved()) {
                                        CollisionStruct move = collisionDetection.checkCollisions(ballObj, temp, root, gameArray, pos);
                                        ControlUnit.moveBall(SetUpGame.getBall(), move);
                                        ControlUnit.playerDeaths(temp, root, gameArray, pos);
                                    }
                                }

                                //If the ball did not collide with any objects earlier, move it within bounds of screen
                                if (!((Ball) ballObj.getObj()).hasMoved()) {
                                    ControlUnit.moveInBounds(SetUpGame.getBall(), collisionDetection);
                                }
                                render.tickRender();

                                //Declaring
                                String str = String.valueOf(gameTime[0] - seconds[0]);
                                timerLabel.setText(str);
                                timerLabel.setX(512 - Math.round(timerLabel.getLayoutBounds().getWidth()/2));
                                if (gameTime[0] - seconds[0] == 0) {
                                    // TODO: something that will end the game
                                    // Transfer to a different screen
                                }
                            } else if (HandleIO.hasTimeOut() || gameTime[0] - seconds[0] < 0 || status.onePlayerAlive()) {
                                // Display some message
                                // Set the countdown to display 0

                                if(status.onePlayerAlive()) {
                                    sceneChanger.updateEndText("Player " + status.winningPlayer() + " won");
                                } else if (gameTime[0] - seconds[0] < 0){
                                    sceneChanger.updateEndText("Time up");
                                } else {
                                    sceneChanger.updateEndText("Game Over");
                                }
                                timerLabel.setText("Game Over");
                                timerLabel.setX(512 - Math.round(timerLabel.getLayoutBounds().getWidth()/2));
                                // Display game over dialog
                                timer.stop();


                                //sceneChanger.updateEndText("testing");
                                theStage.setScene(endScene);

                            } else {
                                HandleIO.resetPaddle();
                                HandleIO.keyPressed();
                                // Don't update seconds, instead store a current time
                                if (!HandleIO.isPaused()) {
                                    // Change the to the new game time so the game seconds pause doesn't register as elapsed time
                                    gameTime[0] = gameTime[0] - (pauseSeconds[0] - seconds[0]);
                                }
                            }
                        } else {
                            seconds[0] = pauseSeconds[0];
                            // Render the countdown timer
                            String str = String.valueOf(3 - seconds[0]);
                            timerLabel.setText(str);
                            if (TimeUnit.SECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS) > 3) {
                                // Game has started
                                delayStart[0] = false;
                                // Set the timer to a constant
                                timerLabel.setText("0");
                                timerLabel.setX(512 - Math.round(timerLabel.getLayoutBounds().getWidth()/2));
                                // Reset the start time
                                startNanoTime[0] = System.nanoTime();
                                // Set text to go when the paddles are ready to move
                                //timerLabel.setText("GO!");
                            }
                        }
                    }
                } else if(sceneSwitch[0] == 0) {
                    //

                } else if(sceneSwitch[0] == 2) {
                    //
                    //System.out.println(endScene.getRoot().getChildren());
                }
               //System.out.println(sceneSwitch[0]);
            }
        };

        timer.start();

        theStage.show();

    }






}
