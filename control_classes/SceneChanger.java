package control_classes;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import javafx.stage.Stage;
import model_classes.Ball;
import model_classes.CollisionStruct;
import model_classes.gameObject;
import view_classes.RenderView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by niles on 09/04/2017.
 */
public class SceneChanger {
    private String gameStatement;
    private Label endSceneText;


    public enum gameScreen {
        INTRO, CONTROLS, PLAYERSEL, GAME, END
    }

    private AnimationTimer timer;
    private gameScreen sceneSwitch;

    private IOHandle HandleIO;
    private AI aiHandle;
    private GameSetUp SetUpGame;
    private boolean checking;
    private int playerNO;



    public SceneChanger() {
        this.gameStatement = "You lost";
        //this.sceneSwitch[0] = 0;
        this.sceneSwitch = gameScreen.INTRO;
        this.checking = false;
        this.playerNO = -1;
    }

    //public Scene addIntroScene(Stage primaryStage, Scene inp, int[] switchScene) {
    public Scene addIntroScene(Stage primaryStage, Scene pSelectScene, Scene pControlScene) {

        //Initalise Scene and group structure
        Group root = new Group();
        Scene scene = new Scene(root, 1024, 768, Color.BLACK);

        //Declare width, height and coordinates of rectangles
        int width = 1024, height = 768;
        int widthRect = 700, heightRect = 100;
        int xRect = (width/2) - (widthRect/2);
        int yRect = 50;

        //Declare colours
        Color c1 = Color.web("0x2962FF");
        Color c2 = Color.web("0x00B5FF");
        Color c3 = Color.web("0x4FC3F7");
        Image imgbutton1 = new Image("/images/ButtonGreen.png");
        Image imgbutton2 = new Image("/images/ButtonBlue.png");
        Image imgbutton3 = new Image("/images/ButtonWarmGrey.png");
        Image imgbutton4 = new Image("/images/ButtonGreenBlue.png");

        //Initialise rectangle buttons
        Rectangle rect1 = new Rectangle(xRect,yRect,widthRect,heightRect);
        rect1.setFill(new ImagePattern(imgbutton2));
        Rectangle rect2 = new Rectangle(xRect,(2 * yRect) + heightRect,widthRect,heightRect);
        rect2.setFill(new ImagePattern(imgbutton4));
        Rectangle rect3 = new Rectangle(xRect,(3 * yRect) + (2 * heightRect),widthRect,heightRect);
        rect3.setFill(new ImagePattern(imgbutton1));

        //Round rectangle corners
        rect1.setArcWidth(20);
        rect1.setArcHeight(20);
        rect2.setArcWidth(20);
        rect2.setArcHeight(20);
        rect3.setArcWidth(20);
        rect3.setArcHeight(20);

        //Declare Text
        Label text1 = new Label("Play!");
        Label text2 = new Label("Controls");
        Label text3 = new Label("About");

        //Declare fonts, heights and widths of text
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

        //Add shapes and text to scene and then show it
        root.getChildren().addAll(rect1, rect2, rect3, text1, text2, text3);
        primaryStage.setScene(scene);
        primaryStage.show();

        //Add listeners to shapes and text
        rect1.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                //Change to to game scene
                //sceneSwitch[0] = 1;
                sceneSwitch = gameScreen.PLAYERSEL;
                primaryStage.setScene(pSelectScene);
            }
        });

        text1.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                //Change to to game scene
                //sceneSwitch[0] = 1;
                sceneSwitch = gameScreen.PLAYERSEL;
                primaryStage.setScene(pSelectScene);
            }
        });

        rect2.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                //TODO: Add implementation
                sceneSwitch = gameScreen.CONTROLS;
                primaryStage.setScene(pControlScene);
            }
        });

        text2.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                //TODO: Add implementation
                sceneSwitch = gameScreen.CONTROLS;
                primaryStage.setScene(pControlScene);
            }
        });

        rect3.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                //TODO: Add implementation
            }
        });

        text3.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                //TODO: Add implementation
            }
        });

        return scene;
    }

    public Scene addControlDisplayScene(Stage primaryStage, Scene gameScene) {
        Group root = new Group();
        Scene Scene = new Scene(root, 1024, 768, Color.BLACK);

        // Declare the objects, paddles to be added to the scene
        // Use the old code?
        GameSetUp SetUpControlDisplay = new GameSetUp();
        SetUpControlDisplay.SetUpBall(10, 5);
        SetUpControlDisplay.SetUpPaddles(15, 80, 15);
        ObjectControl demoControl = new ObjectControl();

        RenderView demoRender = new RenderView(SetUpControlDisplay.getPlayer1(), SetUpControlDisplay.getPlayer2(), SetUpControlDisplay.getPlayer3(), SetUpControlDisplay.getPlayer4(), SetUpControlDisplay.getBall(), false);

        ArrayList<gameObject> gameArray = new ArrayList();
        demoRender.SetUpRender(root, gameArray);

        root.getChildren().add(demoRender.getBackRender());
        root.getChildren().add(demoRender.getBallRender());
        root.getChildren().add(demoRender.getP1Render());
        root.getChildren().add(demoRender.getP2Render());
        root.getChildren().add(demoRender.getP3Render());
        root.getChildren().add(demoRender.getP4Render());
        root.getChildren().add(demoRender.getPl1Render());
        root.getChildren().add(demoRender.getPl2Render());
        root.getChildren().add(demoRender.getPl3Render());
        root.getChildren().add(demoRender.getPl4Render());

        IOHandle demoHandle = new IOHandle(Scene, 4);


        Scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if (key.getCode() == KeyCode.A) {
                demoHandle.setMovedLeftP1();
            } else if (key.getCode() == KeyCode.S) {
                demoHandle.setMovedRightP1();
            }
            demoControl.moveAllPaddles(demoRender, demoHandle, SetUpControlDisplay);
            HandleIO.resetPaddle();
        });

        return Scene;
    }

    public Scene addPlayersSelectScene(Stage primaryStage, Scene gameScene) {
        Group root = new Group();

        Scene scene = new Scene(root, 1024, 768, Color.BLACK);

        //Declare width, height and coordinates of rectangles
        int width = 1024, height = 768;
        int widthRect = 700, heightRect = 100;
        int xRect = (width/2) - (widthRect/2);
        int yRect = 50;

        //Declare colours
        Color c1 = Color.web("0x2962FF");
        Color c2 = Color.web("0x00B5FF");

        //Initialise rectangle buttons
        Rectangle rect1 = new Rectangle(xRect,yRect,widthRect,heightRect);
        rect1.setFill(Color.GREY);
        Rectangle rect2 = new Rectangle(xRect,(2 * yRect) + heightRect,widthRect,heightRect);
        rect2.setFill(c2);
        Rectangle rect3 = new Rectangle(xRect,(3 * yRect) + (2 * heightRect),widthRect,heightRect);
        rect3.setFill(c2);
        Rectangle rect4 = new Rectangle(xRect,(4 * yRect) + (3 * heightRect),widthRect,heightRect);
        rect4.setFill(c2);
        Rectangle rect5 = new Rectangle(xRect,(5 * yRect) + (4 * heightRect),widthRect,heightRect);
        rect5.setFill(c2);

        //Round rectangle corners
        rect1.setArcWidth(20);
        rect1.setArcHeight(20);
        rect2.setArcWidth(20);
        rect2.setArcHeight(20);
        rect3.setArcWidth(20);
        rect3.setArcHeight(20);
        rect4.setArcWidth(20);
        rect4.setArcHeight(20);
        rect5.setArcWidth(20);
        rect5.setArcHeight(20);

        //Declare Text
        Label text1 = new Label("Number of players:");
        Label text2 = new Label("1 Player");
        Label text3 = new Label("2 Player");
        Label text4 = new Label("3 Player");
        Label text5 = new Label("4 Player");

        //Decalre fonts, heights and widths of text
        FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();
        text1.setFont(Font.font("Rockwell", FontWeight.THIN, FontPosture.REGULAR, 50));
        text2.setFont(Font.font("Rockwell", FontWeight.THIN, FontPosture.REGULAR, 50));
        text3.setFont(Font.font("Rockwell", FontWeight.THIN, FontPosture.REGULAR, 50));
        text4.setFont(Font.font("Rockwell", FontWeight.THIN, FontPosture.REGULAR, 50));
        text5.setFont(Font.font("Rockwell", FontWeight.THIN, FontPosture.REGULAR, 50));
        text1.setTextFill(Color.WHITE);
        text2.setTextFill(Color.WHITE);
        text3.setTextFill(Color.WHITE);
        text4.setTextFill(Color.WHITE);
        text5.setTextFill(Color.WHITE);

        double widthText1 = fontLoader.computeStringWidth(text1.getText(), text1.getFont());
        double widthText2 = fontLoader.computeStringWidth(text2.getText(), text2.getFont());
        double widthText3 = fontLoader.computeStringWidth(text3.getText(), text3.getFont());
        double widthText4 = fontLoader.computeStringWidth(text4.getText(), text4.getFont());
        double widthText5 = fontLoader.computeStringWidth(text5.getText(), text5.getFont());

        //Declare coordinates for text
        text1.setLayoutX((width/2) - (widthText1/2));
        text1.setLayoutY(yRect + (heightRect/5));
        text2.setLayoutX((width/2) - (widthText2/2));
        text2.setLayoutY((2*yRect) + heightRect + (heightRect/5));
        text3.setLayoutX((width/2) - (widthText3/2));
        text3.setLayoutY((3*yRect) + (2*heightRect) + (heightRect/5));
        text4.setLayoutX((width/2) - (widthText4/2));
        text4.setLayoutY((4*yRect) + (3*heightRect) + (heightRect/5));
        text5.setLayoutX((width/2) - (widthText5/2));
        text5.setLayoutY((5*yRect) + (4*heightRect) + (heightRect/5));

        //Add shapes and text to scene and then show it
        root.getChildren().addAll(rect1, rect2, rect3, rect4, rect5, text1, text2, text3, text4, text5);
        primaryStage.setScene(scene);
        primaryStage.show();

        int[] noOfPlayers = {-1};

        //Add listeners to shapes and text
        rect1.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                //Change to to game scene
                if(noOfPlayers[0] != -1) {
                    sceneSwitch = gameScreen.GAME;
                    primaryStage.setScene(gameScene);
                    timer.start();
                    checking = true;
                    playerNO = noOfPlayers[0];

                }
            }
        });

        text1.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                //Change to to game scene
                if(noOfPlayers[0] != -1) {
                    sceneSwitch = gameScreen.GAME;
                    primaryStage.setScene(gameScene);
                    timer.start();
                    checking = true;
                    playerNO = noOfPlayers[0];
                }

            }
        });

        rect2.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                handlePlayerSelect(rect1, text1, noOfPlayers, 1);
            }
        });

        text2.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                handlePlayerSelect(rect1, text1, noOfPlayers, 1);
            }
        });

        rect3.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                handlePlayerSelect(rect1, text1, noOfPlayers, 2);
            }
        });

        text3.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                handlePlayerSelect(rect1, text1, noOfPlayers, 2);
            }
        });

        rect4.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                handlePlayerSelect(rect1, text1, noOfPlayers, 3);
            }
        });

        text4.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                handlePlayerSelect(rect1, text1, noOfPlayers, 3);
            }
        });

        rect5.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                handlePlayerSelect(rect1, text1, noOfPlayers, 4);
            }
        });

        text5.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                handlePlayerSelect(rect1, text1, noOfPlayers, 4);
            }
        });

        return scene;
    }


    public void handlePlayerSelect(Rectangle rect1, Label text1, int[] noOfPlayers, int in) {

        Color c3 = Color.web("0x00E676");

        noOfPlayers[0] = in;
        rect1.setFill(c3);
        text1.setText("Play " + noOfPlayers[0] + " player mode");
    }

    public void addGameScene (Stage theStage, SceneChanger sceneChanger, Scene scene, Scene endScene) {

        Group root = new Group();
        scene.setRoot(root);

        // Set up object control
        ObjectControl ControlUnit = new ObjectControl();

        // Set up a new game and initialise game status
        this.SetUpGame = new GameSetUp();
        GameStatus status = new GameStatus(SetUpGame.getPlayers());


        // Add Players via GameSetUp
        //SetUpGame.SetUpPlayers();
        SetUpGame.SetUpBall(10, 5);
        SetUpGame.SetUpPaddles(15, 80, 20);

        // Set up rendering of objects
        RenderView render = new RenderView(SetUpGame.getPlayer1(), SetUpGame.getPlayer2(), SetUpGame.getPlayer3(), SetUpGame.getPlayer4(), SetUpGame.getBall(), true);

        //gameObject[] gameArray;
        ArrayList<gameObject> gameArray = new ArrayList();
        render.SetUpRender(root, gameArray);

        // IO handle declaration
        //TODO: determine the number of players from a previous scene of inputs
//        IOHandle HandleIO = new IOHandle(scene, 1);
//        AI aiHandle = new AI(HandleIO, SetUpGame);

        //Try and add other scene
        // sceneSwitch[0] = 0; //0 - Intro, 1 - Game, 2- End
        boolean[] entered = {true};


        // Render the paddles and balls
        root.getChildren().add(render.getBackRender());
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

        //Initialise game sound effect class
        GameSounds sounds = new GameSounds();

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
                if (sceneSwitch == gameScreen.GAME) {


                    if(checking == true) {
                        HandleIO = new IOHandle(scene, playerNO);
                        aiHandle = new AI(HandleIO, SetUpGame);
                        checking = false;
                    }


                    if (HandleIO.isEscGame()) {
                        HandleIO.setEscGame(false);
                        MainGame newGame = new MainGame();
                        newGame.start(theStage);
                        sceneSwitch = gameScreen.END;
                    }




                    if (entered[0] == false) {
                        status.resetGame(SetUpGame.getPlayers(), collisionDetection.getDisposable(), gameArray, root, HandleIO, timeStarted, delayStart);
                        entered[0] = true;
                    }


                    if (timeStarted[0] == false) {
                        startNanoTime[0] = System.nanoTime();
                        timeStarted[0] = true;
                    } else {
                        // Determine the time difference
                        long elapsedTime = System.nanoTime() - startNanoTime[0];
                        pauseSeconds[0] = TimeUnit.SECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS);
                        // Calculate the seconds value;
                        // System.out.println(elapsedTime);


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
                                        CollisionStruct move = collisionDetection.checkCollisions(ballObj, temp, root, gameArray, pos, sounds);
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
                                timerLabel.setX(512 - Math.round(timerLabel.getLayoutBounds().getWidth() / 2));
                                if (gameTime[0] - seconds[0] == 0) {
                                    // TODO: something that will end the game
                                    // Transfer to a different screen
                                }
                            } else if (HandleIO.hasTimeOut() || gameTime[0] - seconds[0] < 0 || status.onePlayerAlive()) {
                                // Display some message
                                // Set the countdown to display 0

                                if (status.onePlayerAlive()) {
                                    sceneChanger.updateEndText("Player " + status.winningPlayer() + " won");
                                } else if (gameTime[0] - seconds[0] < 0) {
                                    sceneChanger.updateEndText("Time up");
                                } else {
                                    sceneChanger.updateEndText("Game Over");
                                }
                                timerLabel.setText("Game Over");
                                timerLabel.setX(512 - Math.round(timerLabel.getLayoutBounds().getWidth() / 2));

                                sceneSwitch = gameScreen.END;

                                System.out.println("INSIDE GAME OVER:");
                                System.out.println(HandleIO.hasTimeOut());
                                System.out.println(gameTime[0] - seconds[0] < 0);
                                System.out.println(status.onePlayerAlive());
                                System.out.println("End:");


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
                                timerLabel.setX(512 - Math.round(timerLabel.getLayoutBounds().getWidth() / 2));
                                // Reset the start time
                                startNanoTime[0] = System.nanoTime();
                                // Set text to go when the paddles are ready to move
                                //timerLabel.setText("GO!");
                            }
                        }
                    }
                }
            }
        };

        timer.start();
    }

    public Scene addEndScene(Stage primaryStage, Scene inp) {

        //Initalise Scene and group structure
        Group root = new Group();
        Scene scene = new Scene(root, 1024, 768, Color.BLACK);
        Scene introScene = inp;

        //Declare width, height and coordinates of rectangles
        int width = 1024, height = 768;
        int widthRect = 700, heightRect = 100;
        int xRect = (width/2) - (widthRect/2);
        int yRect = 50;

        //Declare colours
        Color c2 = Color.web("0x2ECC71");

        //Initialise rectangle buttons
        Rectangle rect2 = new Rectangle(xRect,(2 * yRect) + heightRect,widthRect,heightRect);
        rect2.setFill(c2);

        //Round rectangle corners
        rect2.setArcWidth(20);
        rect2.setArcHeight(20);

        //Declare Text
        Label text1 = new Label("");
        Label text2 = new Label("Main Menu");

        //Declare fonts, heights and widths of text
        FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();
        text1.setFont(Font.font("Rockwell", FontWeight.THIN, FontPosture.REGULAR, 50));
        text2.setFont(Font.font("Rockwell", FontWeight.THIN, FontPosture.REGULAR, 50));
        text1.setTextFill(Color.WHITE);
        text2.setTextFill(Color.WHITE);

        //Get width of text
        double widthText1 = fontLoader.computeStringWidth(text1.getText(), text1.getFont());
        double widthText2 = fontLoader.computeStringWidth(text2.getText(), text2.getFont());

        //Declare coordinates for text
        text1.setLayoutX((width/2) - (widthText1/2));
        text1.setLayoutY(yRect + (heightRect/5));
        text2.setLayoutX((width/2) - (widthText2/2));
        text2.setLayoutY((2*yRect) + heightRect + (heightRect/5));

        //Add shapes and text to scene
        root.getChildren().addAll(rect2, text1, text2);


        //Add event listeners to rectangles
        rect2.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                //Change to introScene
                primaryStage.setScene(introScene);

                MainGame newGame = new MainGame();
                newGame.start(primaryStage);

            }
        });

        text2.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                //Change to introScene
                primaryStage.setScene(introScene);

                MainGame newGame = new MainGame();
                newGame.start(primaryStage);
            }
        });

        this.endSceneText = text1;

        return scene;
    }

    public void updateEndText(String text) {
        endSceneText.setText(text);
        FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();
        double widthText1 = fontLoader.computeStringWidth(endSceneText.getText(), endSceneText.getFont());
        endSceneText.setLayoutX((1024/2) - (widthText1/2));
    }

}
