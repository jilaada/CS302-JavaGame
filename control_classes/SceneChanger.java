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

/**
 * SceneChagner will change the scenes when they occur
 * Created by niles on 09/04/2017.
 */
public class SceneChanger {
    private String gameStatement;
    private Label endSceneText;

    // Enumeration of the game
    public enum gameScreen {
        INTRO, DIFFICULTY, CONTROLS, PLAYERSEL, ABOUT, GAME, END
    }

    private AnimationTimer timer;
    private gameScreen sceneSwitch;
    private Scene IntroScene;

    private IOHandle HandleIO;
    private AI aiHandle;
    private GameSetUp SetUpGame;
    private boolean checking;
    private boolean advancedAI;
    private int playerNO;


    /**
     * Constructor for the SceneChanger
     */
    public SceneChanger() {
        this.gameStatement = "You lost";
        //this.sceneSwitch[0] = 0;
        this.sceneSwitch = gameScreen.INTRO;
        this.checking = false;
        this.playerNO = -1;
    }

    /**
     * addIntroScene will add the intro scenen to the game
     * @param primaryStage - the stage of the game; stage
     * @param pSelectScene - the select scene; Scene
     * @param pControlScene - the control scene; scene
     * @param pAboutScene - the about Scene; scene
     * @return returns the itroscene object
     */
    public Scene addIntroScene(Stage primaryStage, Scene pSelectScene, Scene pControlScene, Scene pAboutScene) {

        //Initalise Scene and group structure
        Group root = new Group();
        Scene scene = new Scene(root, 1024, 768, Color.BLACK);

        //Declare width, height and coordinates of rectangles
        int width = 1024, height = 768;
        int widthRect = 700, heightRect = 100;
        int xRect = (width/2) - (widthRect/2);
        int yRect = 50;

        //Declare images
        Image imgbutton1 = new Image("/images/ButtonBrightGreen.png");
        Image imgbutton2 = new Image("/images/ButtonBrightBlue.png");
        Image imgbutton3 = new Image("/images/ButtonBrightOrange.png");
        Image titleImage = new Image("/images/LabXLogo.png");
        // Credits: Science graphic by <a href="http://www.flaticon.com/authors/freepik">Freepik</a> from <a href="http://www.flaticon.com/">Flaticon</a> is licensed under <a href="http://creativecommons.org/licenses/by/3.0/" title="Creative Commons BY 3.0">CC BY 3.0</a>. Made with <a href="http://logomakr.com" title="Logo Maker">Logo Maker</a>

        //Initialise rectangle buttons
        Rectangle rect1 = new Rectangle(xRect, (6 * yRect),widthRect,heightRect);
        rect1.setFill(new ImagePattern(imgbutton2));
        Rectangle rect2 = new Rectangle(xRect,(7 * yRect) + heightRect,widthRect,heightRect);
        rect2.setFill(new ImagePattern(imgbutton3));
        Rectangle rect3 = new Rectangle(xRect,(8 * yRect) + (2 * heightRect),widthRect,heightRect);
        rect3.setFill(new ImagePattern(imgbutton1));

        // Set up the title block
        Rectangle titleBlock = new Rectangle(xRect-50,yRect,widthRect+100,heightRect*2);
        titleBlock.setFill(new ImagePattern(titleImage));

        //Round rectangle corners
        rect1.setArcWidth(20);
        rect1.setArcHeight(20);
        rect2.setArcWidth(20);
        rect2.setArcHeight(20);
        rect3.setArcWidth(20);
        rect3.setArcHeight(20);

        //Declare Text
        Label text1 = new Label("PLAY! [1]");
        Label text2 = new Label("INSTRUCTIONS [2]");
        Label text3 = new Label("ABOUT [3]");

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
        text1.setLayoutY(6*yRect + (heightRect/5));
        text2.setLayoutX((width/2) - (widthText2/2));
        text2.setLayoutY((7*yRect) + heightRect + (heightRect/5));
        text3.setLayoutX((width/2) - (widthText3/2));
        text3.setLayoutY((8*yRect) + (2*heightRect) + (heightRect/5));

        //Add shapes and text to scene and then show it
        root.getChildren().addAll(rect1, rect2, rect3, text1, text2, text3, titleBlock);
        primaryStage.setScene(scene);
        primaryStage.show();



        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if (key.getCode()== KeyCode.DIGIT1) {
                sceneSwitch = gameScreen.PLAYERSEL;
                primaryStage.setScene(pSelectScene);
            } else if (key.getCode()== KeyCode.DIGIT2) {
                sceneSwitch = gameScreen.CONTROLS;
                primaryStage.setScene(pControlScene);
            } else if (key.getCode()== KeyCode.DIGIT3) {
                sceneSwitch = gameScreen.ABOUT;
                primaryStage.setScene(pAboutScene);
            }
        });

        //Add listeners to shapes and text
        rect1.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                //Change to to player select scene
                sceneSwitch = gameScreen.DIFFICULTY;
                primaryStage.setScene(pSelectScene);
            }
        });

        text1.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                //Change to to player select scene
                sceneSwitch = gameScreen.DIFFICULTY;
                primaryStage.setScene(pSelectScene);
            }
        });

        rect2.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                sceneSwitch = gameScreen.CONTROLS;
                primaryStage.setScene(pControlScene);
                //Change to to controls scene
            }
        });

        text2.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                sceneSwitch = gameScreen.CONTROLS;
                primaryStage.setScene(pControlScene);
                //Change to to controls scene
            }
        });

        rect3.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                //TODO: Add implementation
                sceneSwitch = gameScreen.ABOUT;
                primaryStage.setScene(pAboutScene);
            }
        });

        text3.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                //TODO: Add implementation
                sceneSwitch = gameScreen.ABOUT;
                primaryStage.setScene(pAboutScene);
            }
        });

        this.IntroScene = scene;
        return scene;
    }

    /**
     * addControlDisplayScene will add the control display scene
     * @param primaryStage - the stage of the gamel stage
     * @return Returns the control display scene
     */
    public Scene addControlDisplayScene(Stage primaryStage) {
        Group root = new Group();
        Scene Scene = new Scene(root, 1024, 768, Color.BLACK);

        // Declare the objects, paddles to be added to the scene
        // Use the old code?
        GameSetUp SetUpControlDisplay = new GameSetUp();
        SetUpControlDisplay.setUpBall(10, 5);
        SetUpControlDisplay.setUpPaddles(15, 80, 15);
        ObjectControl demoControl = new ObjectControl();

        RenderView demoRender = new RenderView(SetUpControlDisplay.getPlayer1(), SetUpControlDisplay.getPlayer2(), SetUpControlDisplay.getPlayer3(), SetUpControlDisplay.getPlayer4(), SetUpControlDisplay.getBall(), false);

        ArrayList<gameObject> gameArray = new ArrayList();
        demoRender.setUpRender(root, gameArray);

        // Adding these objects to the root
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

        // set up background Rectangles
        Image imgRec1 = new Image("/images/rec1.png");
        Rectangle rec1 = new Rectangle(25, 50, 300, 150);
        rec1.setFill(new ImagePattern(imgRec1));
        root.getChildren().add(rec1);
        Image imgRec2 = new Image("/images/rec2.png");
        Rectangle rec2 = new Rectangle(699, 50, 300, 150);
        rec2.setFill(new ImagePattern(imgRec2));
        root.getChildren().add(rec2);
        Image imgRec3 = new Image("/images/rec3.png");
        Rectangle rec3 = new Rectangle(25, 568, 300, 150);
        rec3.setFill(new ImagePattern(imgRec3));
        root.getChildren().add(rec3);
        Image imgRec4 = new Image("/images/rec4.png");
        Rectangle rec4 = new Rectangle(699, 568, 300, 150);
        rec4.setFill(new ImagePattern(imgRec4));
        root.getChildren().add(rec4);
        Image imgBackButton = new Image("/images/ButtonBrightOrange.png");
        Rectangle backButton = new Rectangle(372, 618, 280, 50);
        backButton.setFill(new ImagePattern(imgBackButton));
        root.getChildren().add(backButton);

        // Set the positions of the text
        //Declare Text
        Text text1 = new Text("PRESS A TO MOVE LEFT\nPRESS S TO MOVE RIGHT");
        Text text2 = new Text("PRESS F TO MOVE LEFT\nPRESS G TO MOVE RIGHT");
        Text text3 = new Text("PRESS J TO MOVE LEFT\nPRESS K TO MOVE RIGHT");
        Text text4 = new Text("PRESS <- TO MOVE LEFT\nPRESS -> TO MOVE RIGHT");
        Text titleText = new Text("INSTRUCTIONS");
        Text infoText = new Text("The aim of the game is to be the last mutant standing!\n" +
                "Move your paddle to defend your base against the ball.\n" +
                "When your mutant is dead, the game isn't over for you!\n" +
                "You can decide who wins!\n\n" +
                "PRESS P TO PAUSE THE GAME\n" +
                "PRESS ESC TO LEAVE THE GAME\n" +
                "PRESS PAGE DOWN TO SKIP TO THE END\n");

        //Declare fonts, heights and widths of text
        FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();
        text1.setFont(Font.font("Rockwell", FontWeight.THIN, FontPosture.REGULAR, 20));
        text2.setFont(Font.font("Rockwell", FontWeight.THIN, FontPosture.REGULAR, 20));
        text3.setFont(Font.font("Rockwell", FontWeight.THIN, FontPosture.REGULAR, 20));
        text4.setFont(Font.font("Rockwell", FontWeight.THIN, FontPosture.REGULAR, 20));
        titleText.setFont(Font.font("Rockwell", FontWeight.THIN, FontPosture.REGULAR, 30));
        infoText.setFont(Font.font("Rockwell", FontWeight.THIN, FontPosture.REGULAR, 20));
        text1.setFill(Color.WHITE);
        text2.setFill(Color.WHITE);
        text3.setFill(Color.WHITE);
        text4.setFill(Color.WHITE);
        titleText.setFill(Color.WHITE);
        infoText.setFill(Color.WHITE);
        text1.setTextAlignment(TextAlignment.CENTER);
        text1.setTextOrigin(VPos.CENTER);
        text2.setTextAlignment(TextAlignment.CENTER);
        text2.setTextOrigin(VPos.CENTER);
        text3.setTextAlignment(TextAlignment.CENTER);
        text3.setTextOrigin(VPos.CENTER);
        text4.setTextAlignment(TextAlignment.CENTER);
        text4.setTextOrigin(VPos.CENTER);
        titleText.setTextAlignment(TextAlignment.CENTER);
        titleText.setTextOrigin(VPos.CENTER);
        infoText.setTextAlignment(TextAlignment.CENTER);
        infoText.setTextOrigin(VPos.CENTER);

        text1.setLayoutX((350/2) - text1.getLayoutBounds().getWidth()/2);
        text1.setLayoutY(250/2);
        text2.setLayoutX((1024 - 350) + ((350/2) - (text2.getLayoutBounds().getWidth()/2)));
        text2.setLayoutY(250/2);
        text3.setLayoutX((350/2) - text3.getLayoutBounds().getWidth()/2);
        text3.setLayoutY((768 - 250) + (250/2));
        text4.setLayoutX((1024 - 350) + ((350/2) - (text4.getLayoutBounds().getWidth()/2)));
        text4.setLayoutY((768 - 250) + (250/2));
        titleText.setLayoutX((1024/2) - (titleText.getLayoutBounds().getWidth()/2));
        titleText.setLayoutY(250/2);
        infoText.setLayoutX((1024/2) - (infoText.getLayoutBounds().getWidth()/2));
        infoText.setLayoutY(768/2 + 15);

        root.getChildren().add(text1);
        root.getChildren().add(text2);
        root.getChildren().add(text3);
        root.getChildren().add(text4);
        root.getChildren().add(titleText);
        root.getChildren().add(infoText);

        // Set up the button to go back
        Text backButtonClick = new Text("BACK TO MAIN MENU [B]");
        backButtonClick.setFont(Font.font("Rockwell", FontWeight.THIN, FontPosture.REGULAR, 20));
        backButtonClick.setFill(Color.WHITE);
        backButtonClick.setTextAlignment(TextAlignment.CENTER);
        backButtonClick.setTextOrigin(VPos.CENTER);
        backButtonClick.setLayoutX((1024/2) - backButtonClick.getLayoutBounds().getWidth()/2);
        backButtonClick.setLayoutY((768 - 250) + (250/2));
        root.getChildren().add(backButtonClick);

        // Event handlers for keys
        Scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if (key.getCode() == KeyCode.A) {
                demoHandle.setMovedLeftP1();
            }
            if (key.getCode() == KeyCode.S) {
                demoHandle.setMovedRightP1();
            }
            if (key.getCode() == KeyCode.F) {
                demoHandle.setMovedLeftP2();
            }
            if (key.getCode() == KeyCode.G) {
                demoHandle.setMovedRightP2();
            }
            if (key.getCode() == KeyCode.J) {
                demoHandle.setMovedLeftP3();
            }
            if (key.getCode() == KeyCode.K) {
                demoHandle.setMovedRightP3();
            }
            if (key.getCode() == KeyCode.LEFT) {
                demoHandle.setMovedLeftP4();
            }
            if (key.getCode() == KeyCode.RIGHT) {
                demoHandle.setMovedRightP4();
            }
            if (key.getCode() == KeyCode.B) {
                sceneSwitch = gameScreen.INTRO;
                primaryStage.setScene(IntroScene);
            }
            demoControl.moveAllPaddles(demoRender, demoHandle, SetUpControlDisplay);
            demoHandle.resetPaddle();
            demoRender.tickRender();
        });

        backButton.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                //Change to to game scene
                    sceneSwitch = gameScreen.INTRO;
                    primaryStage.setScene(IntroScene);
            }
        });

        backButtonClick.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                //Change to to game scene
                sceneSwitch = gameScreen.INTRO;
                primaryStage.setScene(IntroScene);
            }
        });
        // Add event handlers for events on click
        return Scene;
    }

    /**
     * addDifficultSelectScene will allow the user to select the difficulty of the game
     * @param primaryStage - the stage of the game; stage
     * @param pSelectScene - the select scene(next scene); scene
     * @return Returns the difficulty select scene
     */
    public Scene addDifficultySelectScene(Stage primaryStage, Scene pSelectScene) {
        Group root = new Group();
        Scene Scene = new Scene(root, 1024, 768, Color.BLACK);
        // Display the buttons
        int width = 1024, height = 768;
        int widthRect = 700, heightRect = 100;
        int xRect = (width/2) - (widthRect/2);
        int yRect = 50;

        Image imgbutton1 = new Image("/images/ButtonBrightGreen.png");
        Image imgbutton2 = new Image("/images/ButtonBrightBlue.png");

        Rectangle rect1 = new Rectangle(xRect,(2 * yRect) + heightRect,widthRect,heightRect);
        rect1.setFill(new ImagePattern(imgbutton2));
        Rectangle rect2 = new Rectangle(xRect,(3 * yRect) + (2 * heightRect),widthRect,heightRect);
        rect2.setFill(new ImagePattern(imgbutton1));

        //Round rectangle corners
        rect1.setArcWidth(20);
        rect1.setArcHeight(20);
        rect2.setArcWidth(20);
        rect2.setArcHeight(20);

        Label text1 = new Label("EASY AI [1]");
        Label text2 = new Label("HARD AI [2]");
        Text titleText = new Text("SELECT DIFFICULTY");

        FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();
        text1.setFont(Font.font("Rockwell", FontWeight.THIN, FontPosture.REGULAR, 50));
        text2.setFont(Font.font("Rockwell", FontWeight.THIN, FontPosture.REGULAR, 50));
        titleText.setFont(Font.font("Rockwell", FontWeight.THIN, FontPosture.REGULAR, 50));
        text1.setTextFill(Color.WHITE);
        text2.setTextFill(Color.WHITE);
        titleText.setFill(Color.WHITE);


        double widthText1 = fontLoader.computeStringWidth(text1.getText(), text2.getFont());
        double widthText2 = fontLoader.computeStringWidth(text2.getText(), text2.getFont());

        text1.setLayoutX((width/2) - (widthText1/2));
        text1.setLayoutY((2*yRect) + heightRect + (heightRect/5));
        text2.setLayoutX((width/2) - (widthText2/2));
        text2.setLayoutY((3*yRect) + (2*heightRect) + (heightRect/5));
        titleText.setTextAlignment(TextAlignment.CENTER);
        titleText.setTextOrigin(VPos.CENTER);
        titleText.setLayoutX((1024/2) - (titleText.getLayoutBounds().getWidth()/2));
        titleText.setLayoutY(250/2);

        // Add back button
        Image imgBackButton = new Image("/images/ButtonBrightOrange.png");
        Rectangle backButton = new Rectangle(372, 618, 280, 50);
        backButton.setFill(new ImagePattern(imgBackButton));

        Text backButtonClick = new Text("BACK TO MAIN MENU [B]");
        backButtonClick.setFont(Font.font("Rockwell", FontWeight.THIN, FontPosture.REGULAR, 20));
        backButtonClick.setFill(Color.WHITE);
        backButtonClick.setTextAlignment(TextAlignment.CENTER);
        backButtonClick.setTextOrigin(VPos.CENTER);
        backButtonClick.setLayoutX((1024/2) - backButtonClick.getLayoutBounds().getWidth()/2);
        backButtonClick.setLayoutY((768 - 250) + (250/2));

        root.getChildren().addAll(rect1, rect2, text1, text2, backButton, backButtonClick, titleText);


        Scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if (key.getCode()== KeyCode.DIGIT1) {
                sceneSwitch = gameScreen.PLAYERSEL;
                primaryStage.setScene(pSelectScene);
                advancedAI = false;
            } else if (key.getCode()== KeyCode.DIGIT2) {
                sceneSwitch = gameScreen.PLAYERSEL;
                primaryStage.setScene(pSelectScene);
                advancedAI = true;
            } else if (key.getCode()== KeyCode.B) {
                sceneSwitch = gameScreen.INTRO;
                primaryStage.setScene(IntroScene);
            }
        });

        //Add listeners to shapes and text
        rect1.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                //Change to to player select scene
                sceneSwitch = gameScreen.PLAYERSEL;
                primaryStage.setScene(pSelectScene);
                advancedAI = false;
            }
        });

        text1.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                //Change to to player select scene
                sceneSwitch = gameScreen.PLAYERSEL;
                primaryStage.setScene(pSelectScene);
                advancedAI = false;
            }
        });

        rect2.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                sceneSwitch = gameScreen.PLAYERSEL;
                primaryStage.setScene(pSelectScene);
                advancedAI = true;
                //Change to to controls scene
            }
        });

        text2.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                sceneSwitch = gameScreen.PLAYERSEL;
                primaryStage.setScene(pSelectScene);
                advancedAI = true;
                //Change to to controls scene
            }
        });

        backButton.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                sceneSwitch = gameScreen.INTRO;
                primaryStage.setScene(IntroScene);
                //Change to to controls scene
            }
        });

        backButtonClick.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                sceneSwitch = gameScreen.INTRO;
                primaryStage.setScene(IntroScene);
                //Change to to controls scene
            }
        });


        return Scene;
    }

    /**
     * addPlayerSelectScene will allow the player to select the number of players in the game
     * @param primaryStage - the stage of the game; stage
     * @param gameScene - the next scene game scene; scene
     * @return
     */
    public Scene addPlayersSelectScene(Stage primaryStage, Scene gameScene) {
        Group root = new Group();

        Scene scene = new Scene(root, 1024, 768, Color.BLACK);

        //Declare width, height and coordinates of rectangles
        int width = 1024, height = 768;
        int widthRect = 650, heightRect = 80;
        int xRect = (width/2) - (widthRect/2);
        int yRect = 50;

        //Declare colours

        Image imgButton1 = new Image("/images/ButtonWarmGrey.png");
        Image imgButtonClick = new Image("/images/ButtonBrightBlue.png");


        //Initialise rectangle buttons
        Rectangle rect1 = new Rectangle(xRect - 95,yRect,widthRect + 190,heightRect+20);
        rect1.setFill(new ImagePattern(imgButton1));
        Rectangle rect2 = new Rectangle(xRect,(2 * yRect) + heightRect,widthRect,heightRect);
        rect2.setFill(new ImagePattern(imgButtonClick));
        Rectangle rect3 = new Rectangle(xRect,(2.5 * yRect) + (2 * heightRect),widthRect,heightRect);
        rect3.setFill(new ImagePattern(imgButtonClick));
        Rectangle rect4 = new Rectangle(xRect,(3 * yRect) + (3 * heightRect),widthRect,heightRect);
        rect4.setFill(new ImagePattern(imgButtonClick));
        Rectangle rect5 = new Rectangle(xRect,(3.5 * yRect) + (4 * heightRect),widthRect,heightRect);
        rect5.setFill(new ImagePattern(imgButtonClick));

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
        Label text1 = new Label("NUMBER OF PLAYERS");
        Label text2 = new Label("ONE PLAYER   [1]");
        Label text3 = new Label("TWO PLAYER   [2]");
        Label text4 = new Label("THREE PLAYER [3]");
        Label text5 = new Label("FOUR PLAYER  [4]");

        //Decalre fonts, heights and widths of text
        FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();
        text1.setFont(Font.font("Rockwell", FontWeight.THIN, FontPosture.REGULAR, 50));
        text2.setFont(Font.font("Rockwell", FontWeight.THIN, FontPosture.REGULAR, 40));
        text3.setFont(Font.font("Rockwell", FontWeight.THIN, FontPosture.REGULAR, 40));
        text4.setFont(Font.font("Rockwell", FontWeight.THIN, FontPosture.REGULAR, 40));
        text5.setFont(Font.font("Rockwell", FontWeight.THIN, FontPosture.REGULAR, 40));
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
        text3.setLayoutY((2.5*yRect) + (2*heightRect) + (heightRect/5));
        text4.setLayoutX((width/2) - (widthText4/2));
        text4.setLayoutY((3*yRect) + (3*heightRect) + (heightRect/5));
        text5.setLayoutX((width/2) - (widthText5/2));
        text5.setLayoutY((3.5*yRect) + (4*heightRect) + (heightRect/5));

        // Add back button
        Image imgBackButton = new Image("/images/ButtonBrightOrange.png");
        Rectangle backButton = new Rectangle(372, 618, 280, 50);
        backButton.setFill(new ImagePattern(imgBackButton));

        Text backButtonClick = new Text("BACK TO MAIN MENU [B]");
        backButtonClick.setFont(Font.font("Rockwell", FontWeight.THIN, FontPosture.REGULAR, 20));
        backButtonClick.setFill(Color.WHITE);
        backButtonClick.setTextAlignment(TextAlignment.CENTER);
        backButtonClick.setTextOrigin(VPos.CENTER);
        backButtonClick.setLayoutX((1024/2) - backButtonClick.getLayoutBounds().getWidth()/2);
        backButtonClick.setLayoutY((768 - 250) + (250/2));

        //Add shapes and text to scene and then show it
        root.getChildren().addAll(rect1, rect2, rect3, rect4, rect5, text1, text2, text3, text4, text5, backButton, backButtonClick);
        primaryStage.setScene(scene);
        primaryStage.show();

        int[] noOfPlayers = {-1};

        //Add listeners to shapes and text
        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if (key.getCode()== KeyCode.DIGIT1) {
                handlePlayerSelect(rect1, text1, noOfPlayers, 1);
            } else if (key.getCode()== KeyCode.DIGIT2) {
                handlePlayerSelect(rect1, text1, noOfPlayers, 2);
            } else if (key.getCode()== KeyCode.DIGIT3) {
                handlePlayerSelect(rect1, text1, noOfPlayers, 3);
            } else if (key.getCode()== KeyCode.DIGIT4) {
                handlePlayerSelect(rect1, text1, noOfPlayers, 4);
            } else if (key.getCode()== KeyCode.ENTER) {
                if(noOfPlayers[0] != -1) {
                    sceneSwitch = gameScreen.GAME;
                    primaryStage.setScene(gameScene);
                    timer.start();
                    checking = true;
                    playerNO = noOfPlayers[0];

                }
            } else if (key.getCode() == KeyCode.B) {
                sceneSwitch = gameScreen.INTRO;
                primaryStage.setScene(IntroScene);
            }
        });


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

        backButton.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                sceneSwitch = gameScreen.INTRO;
                primaryStage.setScene(IntroScene);
            }
        });

        backButtonClick.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                sceneSwitch = gameScreen.INTRO;
                primaryStage.setScene(IntroScene);
            }
        });

        return scene;
    }

    /**
     * addAboutScene will add the about scene of the game
     * @param primaryStage - the stage of the game; stage
     * @return Returns the about scene of the game
     */
    public Scene addAboutScene(Stage primaryStage) {
        Group root = new Group();
        Scene Scene = new Scene(root, 1024, 768, Color.BLACK);

        // Add the developer title box
        Image imgRec1 = new Image("/images/ButtonRed.png");
        Rectangle rec1 = new Rectangle(162, 50, 700, 100);
        rec1.setFill(new ImagePattern(imgRec1));
        root.getChildren().add(rec1);

        // Add text of the title
        FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();
        Text titleText = new Text("ABOUT");
        titleText.setFont(Font.font("Rockwell", FontWeight.THIN, FontPosture.REGULAR, 50));
        titleText.setFill(Color.WHITE);
        titleText.setTextAlignment(TextAlignment.CENTER);
        titleText.setTextOrigin(VPos.CENTER);
        titleText.setLayoutX((1024/2) - (titleText.getLayoutBounds().getWidth()/2));
        titleText.setLayoutY(95);
        root.getChildren().add(titleText);

        Text infoNileshText = new Text("Nilesh is a student studying computer systems engineering due to his \n" +
                "love for programming and design. He has a knack for thinking outside the box and\n" +
                "problem solving which makes him a valued team member, especially when it\n" +
                "comes to UI design and functionality. Nilesh also has extracurricular experiences\n" +
                "in coding web-apps; one of such involves using an api to find information about musical artists. ");
        infoNileshText.setFont(Font.font("Rockwell", FontWeight.THIN, FontPosture.REGULAR, 15));
        infoNileshText.setFill(Color.WHITE);
        infoNileshText.setTextAlignment(TextAlignment.LEFT);
        infoNileshText.setTextOrigin(VPos.CENTER);
        infoNileshText.setLayoutX((1024/2) - (infoNileshText.getLayoutBounds().getWidth()/2));
        infoNileshText.setLayoutY(500/2 + 15);

        Text infoJiladaText = new Text("Jilada is in her third year studying Computer Systems engineering at the \n" +
                "University of Auckland. She enjoys challenging herself to pursue new and more creative \n" +
                "ways of solving problems. In previous years, she has worked on small photoshop projects as well \n" +
                "as learnt graphical and product design processes. She hopes to bring my artistic skills \n" +
                "into the game design.");
        infoJiladaText.setFont(Font.font("Rockwell", FontWeight.THIN, FontPosture.REGULAR, 15));
        infoJiladaText.setFill(Color.WHITE);
        infoJiladaText.setTextAlignment(TextAlignment.LEFT);
        infoJiladaText.setTextOrigin(VPos.CENTER);
        infoJiladaText.setLayoutX((1024/2) - (infoJiladaText.getLayoutBounds().getWidth()/2));
        infoJiladaText.setLayoutY(700/2 + 15);

        Text infoStoryText = new Text("Story:\n" +
                "You and a group of mutants are stuck in a lab when it suddenly breaks down\n" +
                "There is a chance of escape but only one of you can make it!\n" +
                "Will you be able to break free or will you be stuck in the lab forever!\n" +
                "Use your paddle to defend yourself from other mutants!\n" +
                "Stay alive for as long as you can and be one to make it out!");
        infoStoryText.setFont(Font.font("Rockwell", FontWeight.THIN, FontPosture.REGULAR, 15));
        infoStoryText.setFill(Color.WHITE);
        infoStoryText.setTextAlignment(TextAlignment.CENTER);
        infoStoryText.setTextOrigin(VPos.CENTER);
        infoStoryText.setLayoutX((1024/2) - (infoStoryText.getLayoutBounds().getWidth()/2));
        infoStoryText.setLayoutY(1000/2 + 15);

        root.getChildren().add(infoNileshText);
        root.getChildren().add(infoJiladaText);
        root.getChildren().add(infoStoryText);

        Image imgBackButton = new Image("/images/ButtonBrightOrange.png");
        Rectangle backButton = new Rectangle(372, 618, 280, 50);
        backButton.setFill(new ImagePattern(imgBackButton));
        root.getChildren().add(backButton);

        Text backButtonClick = new Text("BACK TO MAIN MENU [B]");
        backButtonClick.setFont(Font.font("Rockwell", FontWeight.THIN, FontPosture.REGULAR, 20));
        backButtonClick.setFill(Color.WHITE);
        backButtonClick.setTextAlignment(TextAlignment.CENTER);
        backButtonClick.setTextOrigin(VPos.CENTER);
        backButtonClick.setLayoutX((1024/2) - backButtonClick.getLayoutBounds().getWidth()/2);
        backButtonClick.setLayoutY((768 - 250) + (250/2));
        root.getChildren().add(backButtonClick);

        // Add the text for the developer information

        Scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if (key.getCode() == KeyCode.B) {
                sceneSwitch = gameScreen.INTRO;
                primaryStage.setScene(IntroScene);
            }
        });

        backButton.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                //Change to to game scene
                sceneSwitch = gameScreen.INTRO;
                primaryStage.setScene(IntroScene);
            }
        });

        backButtonClick.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                //Change to to game scene
                sceneSwitch = gameScreen.INTRO;
                primaryStage.setScene(IntroScene);
            }
        });
        return Scene;
    }

    /**
     * handlePlayerSelect will handle the selection of the player numebrs
     * @param rect1 - the button that was pressed; rectangle
     * @param text1 - the text that was pressed; text
     * @param noOfPlayers - the number of player contained in that; int
     * @param in
     */
    public void handlePlayerSelect(Rectangle rect1, Label text1, int[] noOfPlayers, int in) {

        Image imgButton2 = new Image("/images/ButtonRed.png");
        noOfPlayers[0] = in;
        rect1.setFill(new ImagePattern(imgButton2));
        text1.setText("PLAY " + noOfPlayers[0] + " PLAYER MODE [ENTER]");

        //Update width of text
        FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();
        double widthText = fontLoader.computeStringWidth(text1.getText(), text1.getFont());
        text1.setLayoutX((1024/2) - (widthText/2));
    }

    /**
     * addGameScene ads the main scene to the game
     * @param theStage - the stage of the game; stage
     * @param sceneChanger - the scene changer classl scenechanger
     * @param scene - the scene of the game; scene
     * @param endScene - the end scene; scene
     */
    public void addGameScene (Stage theStage, SceneChanger sceneChanger, Scene scene, Scene endScene) {

        Group root = new Group();
        scene.setRoot(root);

        // Set up a new game and initialise game status
        this.SetUpGame = new GameSetUp();
        GameStatus status = new GameStatus(SetUpGame.getPlayers());


        // Add Players via GameSetUp
        //SetUpGame.SetUpPlayers();
        SetUpGame.setUpBall(10, 5);
        SetUpGame.setUpPaddles(15, 80, 20);

        // Set up rendering of objects
        RenderView render = new RenderView(SetUpGame.getPlayer1(), SetUpGame.getPlayer2(), SetUpGame.getPlayer3(), SetUpGame.getPlayer4(), SetUpGame.getBall(), true);

        //gameObject[] gameArray;
        ArrayList<gameObject> gameArray = new ArrayList();
        render.setUpRender(root, gameArray);

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


        gameObject ballObj = new gameObject(render.getBallRender(), SetUpGame.getBall());
        gameObject paddleObj1 = new gameObject(render.getP1Render(), SetUpGame.getPlayer1().getPlayerPaddle());
        gameObject paddleObj2 = new gameObject(render.getP2Render(), SetUpGame.getPlayer2().getPlayerPaddle());
        gameObject paddleObj3 = new gameObject(render.getP3Render(), SetUpGame.getPlayer3().getPlayerPaddle());
        gameObject paddleObj4 = new gameObject(render.getP4Render(), SetUpGame.getPlayer4().getPlayerPaddle());
        ArrayList<gameObject> paddleObjs = new ArrayList<gameObject>();
        paddleObjs.add(paddleObj1);
        paddleObjs.add(paddleObj2);
        paddleObjs.add(paddleObj3);
        paddleObjs.add(paddleObj4);

        // Set up object control and collision detection algorithim
        ObjectControl ControlUnit = new ObjectControl(paddleObjs);
        Collision collisionDetection = new Collision(paddleObjs);


        //Add paddles to gameArray
        gameArray.add(paddleObj1);
        gameArray.add(paddleObj2);
        gameArray.add(paddleObj3);
        gameArray.add(paddleObj4);

        // Testing rectangle wall
        SetUpGame.setUpWall(root, gameArray);

        //Initialise game sound effect class
        GameSounds sounds = new GameSounds();

        //Set up delay boolean
        final boolean[] delayStart = {true};
        long[] seconds = new long[1];
        long[] pauseSeconds = new long[1];
        long[] difference  = new long[1];
        //final long[] startNanoTime = {System.nanoTime()};
        long[] startNanoTime = new long[1];
        Text timerLabel = new Text(512, 20,"3");
        timerLabel.setFont(new Font(16));
        timerLabel.setFill(Color.WHITE);
        timerLabel.setTextAlignment(TextAlignment.CENTER);
        timerLabel.setTextOrigin(VPos.CENTER);
        timerLabel.setX(512 - Math.round(timerLabel.getLayoutBounds().getWidth()/2));
        double[] gameTime = {120};
        root.getChildren().add(timerLabel);
        boolean[] timeStarted = {false};
        double[] countdown = {3};

        timer = new AnimationTimer() {
            public void handle(long currentNanoTime) {
                // Run the input handle
//                if(sceneSwitch != gameScreen.END) {
                    //sounds.playBackgroundSE(true);
                    sounds.playBackgroundSE(sceneSwitch);
//                } else  {
//                    sounds.playBackgroundSE(false);
//                }

                if (sceneSwitch == gameScreen.GAME) {


                    if(checking == true) {
                        HandleIO = new IOHandle(scene, playerNO);
                        aiHandle = new AI(HandleIO, SetUpGame);
                        checking = false;
                    }


                    if (HandleIO.isEscGame()) {
                        HandleIO.setEscGame(false);
                        //sounds.playBackgroundSE(false);
                        sceneSwitch = gameScreen.END;
                        MainGame newGame = new MainGame();
                        newGame.start(theStage);
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
                        pauseSeconds[0] = System.nanoTime();
                        long elapsedTime = System.nanoTime() - startNanoTime[0];
                        //pauseSeconds[0] = TimeUnit.SECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS);
                        // Calculate the seconds value;


                        if (!delayStart[0]) {
                            if (!HandleIO.isPaused() && !(HandleIO.hasTimeOut() || gameTime[0] < 0 || status.onePlayerAlive())) {

                                ControlUnit.addPowerUp(root, gameArray, gameTime[0], render);
                                //ControlUnit.checkAndRemovePowerUps(SetUpGame.getPlayers());

                                HandleIO.keyPressed();
                                // Move the AI paddles
                                if (advancedAI) {
                                    aiHandle.moveAIAdvanced();
                                } else {
                                    aiHandle.moveAI();
                                }

                                ControlUnit.moveAllPaddles(render, HandleIO, SetUpGame);
                                HandleIO.resetPaddle();

                                ((Ball) ballObj.getObj()).setMoved(false);

                                //Loop through every object in gameObject array and check for collisions and player deaths
                                for (int pos = 0; pos < gameArray.size(); pos++) {
                                    gameObject temp = gameArray.get(pos);
                                    if (!((Ball) ballObj.getObj()).hasMoved()) {
                                        CollisionStruct move = collisionDetection.checkCollisions(ballObj, temp, root, gameArray, pos, sounds, SetUpGame.getPlayers());
                                        ControlUnit.moveBall(SetUpGame.getBall(), move);
                                        ControlUnit.playerDeaths(temp, root, gameArray, pos);
                                    }
                                }

                                ControlUnit.checkAndRemovePowerUps(SetUpGame.getPlayers());

                                //If the ball did not collide with any objects earlier, move it within bounds of screen
                                if (!((Ball) ballObj.getObj()).hasMoved()) {
                                    ControlUnit.moveInBounds(SetUpGame.getBall(), collisionDetection);
                                }
                                render.tickRender();

                                //Declaring
                                gameTime[0] = gameTime[0] - (elapsedTime * Math.pow(10, -9));
                                //String str = String.valueOf(gameTime[0]);
                                String str = String.format("%.0f", gameTime[0]);

                                timerLabel.setText(str);
                                timerLabel.setX(512 - Math.round(timerLabel.getLayoutBounds().getWidth() / 2));


                            } else if (HandleIO.hasTimeOut() || gameTime[0] < 0 || status.onePlayerAlive()) {
                                // Display some message
                                // Set the countdown to display 0
                                if (status.onePlayerAlive()) {
                                    String output = "";

                                    switch (status.winningPlayer()) {
                                        case (1):
                                            output = "Blue player";
                                            break;
                                        case (2):
                                            output = "Red player";
                                            break;
                                        case (3):
                                            output = "Green player";
                                            break;
                                        case (4):
                                            output = "White player";
                                            break;
                                    }

                                    sceneChanger.updateEndText(output + " wins");
                                } else if (gameTime[0] < 0) {
                                    sceneChanger.updateEndText("Time up");
                                } else {
                                    sceneChanger.updateEndText("Game Over");
                                }
                                timerLabel.setText("Game Over");
                                timerLabel.setX(512 - Math.round(timerLabel.getLayoutBounds().getWidth() / 2));

                                sceneSwitch = gameScreen.END;


                                theStage.setScene(endScene);
                            } else {

                                HandleIO.resetPaddle();
                                HandleIO.keyPressed();
                            }
                        } else {

                            //seconds[0] = pauseSeconds[0];
                            // Render the countdown timer
                            countdown[0] = countdown[0] - (elapsedTime * Math.pow(10, -9));


                            //String str = String.valueOf(countdown[0]);
                            String str = String.format("%.0f", (countdown[0]));
                            timerLabel.setText(str);
                            //if (TimeUnit.SECONDS.convert(pauseSeconds[0], TimeUnit.NANOSECONDS) > 3) {
                            if (countdown[0] < 0) {
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
                        startNanoTime[0] = pauseSeconds[0];


                    }
                }
            }
        };

        timer.start();
    }

    /**
     * addEndScene will add the end scene to the game
     * @param primaryStage - the stage of the game; stage
     * @param inp
     * @return returns the end scene of the game
     */
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
        Image imgButton = new Image("/images/ButtonBrightBlue.png");

        //Initialise rectangle buttons
        Rectangle rect2 = new Rectangle(xRect,(2 * yRect) + heightRect,widthRect,heightRect);
        rect2.setFill(new ImagePattern(imgButton));

        //Round rectangle corners
        rect2.setArcWidth(20);
        rect2.setArcHeight(20);

        //Declare Text
        Label text1 = new Label("");
        Label text2 = new Label("Main Menu [B]");

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

        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if (key.getCode()== KeyCode.B) {
                primaryStage.setScene(introScene);                MainGame newGame = new MainGame();
                newGame.start(primaryStage);
            }
        });

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

    /**
     * updateEndText will determine the text displayed at the end of the game
     * @param text - the text that should be displayed; text
     */
    public void updateEndText(String text) {
        endSceneText.setText(text);
        FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();
        double widthText1 = fontLoader.computeStringWidth(endSceneText.getText(), endSceneText.getFont());
        endSceneText.setLayoutX((1024/2) - (widthText1/2));
    }

}
