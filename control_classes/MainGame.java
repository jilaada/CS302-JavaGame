package control_classes;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
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
import model_classes.Player;
import model_classes.gameObject;
import view_classes.RenderView;

import java.lang.reflect.Array;
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
        Scene endScene = new Scene(end, 1024, 768, Color.SNOW);
        //theStage.setScene( scene );
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

        //gameObject[] gameArray;
        ArrayList<gameObject> gameArray = new ArrayList();
        render.SetUpRender(root, gameArray);

        // IO handle declaration
        //TODO: determine the number of players from a previous scene of inputs
        IOHandle HandleIO = new IOHandle(scene, 1);
        AI aiHandle = new AI(HandleIO, SetUpGame);

        //Try and add other scene
        int[] sceneSwitch = {0}; //0 - Intro, 1 - Game, 2- End
        Scene introScene = addSceneStuff(theStage, scene, sceneSwitch);
        theStage.setScene( introScene );


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
        Text timerLabel = new Text(512, 20, "3");
        timerLabel.setFont(new Font(16));
        timerLabel.setFill(Color.WHITE);
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
                            if (!HandleIO.isPaused() && !HandleIO.hasTimeOut()) {
                                seconds[0] = pauseSeconds[0];
                                HandleIO.keyPressed();
                                // Move the AI paddles
                                aiHandle.moveAI();
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
                                timerLabel.setTextAlignment(TextAlignment.CENTER);
                                timerLabel.setText(str);
                                if (gameTime[0] - seconds[0] == 0) {
                                    // TODO: something that will end the game
                                    // Transfer to a different screen
                                }
                            } else if (HandleIO.hasTimeOut() || gameTime[0] - seconds[0] == -1) {
                                // Display some message
                                // Set the countdown to display 0
                                timerLabel.setText("Game Over");
                                // Display game over dialog
                                timer.stop();
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
                                // Reset the start time
                                startNanoTime[0] = System.nanoTime();
                                // Set text to go when the paddles are ready to move
                                //timerLabel.setText("GO!");
                            }
                        }
                    }
                } else if(sceneSwitch[0] == 0) {
                    //
                }
               //System.out.println(sceneSwitch[0]);
            }
        };

        timer.start();

        theStage.show();

        System.out.print("I am here");

    }


    private Scene addSceneStuff(Stage primaryStage, Scene inp, int[] switchScene) {
        Group root = new Group();
        //Group end = new Group();

        Scene scene = new Scene(root, 1024, 768, Color.BLACK);
        //Scene endScene = new Scene(end, 1024, 768, Color.SNOW);
        Scene gameScene = inp;

        //Declare width, height and coordinates of rectangles
        int width = 1024, height = 768;
        int widthRect = 700, heightRect = 100;
        int xRect = (width/2) - (widthRect/2);
        int yRect = 50;


        Color c1 = Color.web("0x2962FF");
        Color c2 = Color.web("0x00B5FF");
        Color c3 = Color.web("0x4FC3F7");

        Rectangle rect1 = new Rectangle(xRect,yRect,widthRect,heightRect);
        rect1.setFill(c1);

        Rectangle rect2 = new Rectangle(xRect,(2 * yRect) + heightRect,widthRect,heightRect);
        rect2.setFill(c2);

        Rectangle rect3 = new Rectangle(xRect,(3 * yRect) + (2 * heightRect),widthRect,heightRect);
        rect3.setFill(c3);

        //Round rectangle corners
        rect1.setArcWidth(20);
        rect1.setArcHeight(20);
        rect2.setArcWidth(20);
        rect2.setArcHeight(20);
        rect3.setArcWidth(20);
        rect3.setArcHeight(20);

        //Declare Text
        Label text1 = new Label("Play!");
        Label text2 = new Label("Options");
        Label text3 = new Label("About");

        //Decalre fonts, heights and widths of text
        FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();
        text1.setFont(Font.font("Rockwell", FontWeight.THIN, FontPosture.REGULAR, 50));
        text2.setFont(Font.font("Rockwell", FontWeight.THIN, FontPosture.REGULAR, 50));
        text3.setFont(Font.font("Rockwell", FontWeight.THIN, FontPosture.REGULAR, 50));
        text1.setTextFill(Color.WHITE);
        text2.setTextFill(Color.WHITE);
        text3.setTextFill(Color.WHITE);

        double widthText1 = fontLoader.computeStringWidth(text1.getText(), text1.getFont());
        double widthText2 = fontLoader.computeStringWidth(text2.getText(), text2.getFont());
        double widthText3 = fontLoader.computeStringWidth(text3.getText(), text3.getFont());

        //Declare coordinates for text
        text1.setLayoutX((width/2) - (widthText1/2));
        text1.setLayoutY(yRect + (heightRect/5));
        text2.setLayoutX((width/2) - (widthText2/2));
        text2.setLayoutY((2*yRect) + heightRect + (heightRect/5));
        text3.setLayoutX((width/2) - (widthText3/2));
        text3.setLayoutY((3*yRect) + (2*heightRect) + (heightRect/5));

        root.getChildren().addAll(rect1, rect2, rect3, text1, text2, text3);
        primaryStage.setScene(scene);
        primaryStage.show();

        rect1.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                rect1.setFill(Color.WHITE);
                primaryStage.setScene(gameScene);
                switchScene[0] = 1;
            }
        });

        text1.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                rect1.setFill(Color.WHITE);
                primaryStage.setScene(gameScene);
                switchScene[0] = 1;
            }
        });

        rect2.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                rect2.setFill(Color.WHITE);
            }
        });

        text2.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                rect2.setFill(Color.WHITE);
            }
        });

        rect3.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                rect3.setFill(Color.WHITE);
            }
        });

        text3.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                rect3.setFill(Color.WHITE);
            }
        });

        return scene;
    }
}
