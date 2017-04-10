package control_classes;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * Created by niles on 09/04/2017.
 */
public class SceneChanger {
    private String gameStatement;
    private Label endSceneText;

    public SceneChanger() {
        this.gameStatement = "You lost";
    }

    public Scene addIntroScene(Stage primaryStage, Scene inp, int[] switchScene) {

        //Initalise Scene and group structure
        Group root = new Group();
        Scene scene = new Scene(root, 1024, 768, Color.BLACK);
        Scene gameScene = inp;

        //Declare width, height and coordinates of rectangles
        int width = 1024, height = 768;
        int widthRect = 700, heightRect = 100;
        int xRect = (width/2) - (widthRect/2);
        int yRect = 50;

        //Declare colours
        Color c1 = Color.web("0x2962FF");
        Color c2 = Color.web("0x00B5FF");
        Color c3 = Color.web("0x4FC3F7");
        Image imgbutton1 = new Image("/images/GreenButton.png");
        Image imgbutton2 = new Image("/images/BlueButton.png");
        Image imgbutton3 = new Image("/images/WarmGreyButton.png");
        Image imgbutton4 = new Image("/images/GreenBlueButton.png");

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
                primaryStage.setScene(gameScene);
                switchScene[0] = 1;
            }
        });

        text1.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                //Change to to game scene
                primaryStage.setScene(gameScene);
                switchScene[0] = 1;
            }
        });

        rect2.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                //TODO: Add implementation
            }
        });

        text2.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                //TODO: Add implementation
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


    public Scene addEndScene(Stage primaryStage, Scene inp, int[] switchScene, String text) {

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
        Label text1 = new Label(text);
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
                switchScene[0] = 0;

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
                switchScene[0] = 0;

                MainGame newGame = new MainGame();
                newGame.start(primaryStage);
            }
        });

        this.endSceneText = text1;

        return scene;
    }

//    public void gameTextChanger (Scene inpScene, String text) {
//
//    }

    public void updateEndText(String text) {
        endSceneText.setText(text);
        FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();
        double widthText1 = fontLoader.computeStringWidth(endSceneText.getText(), endSceneText.getFont());
        endSceneText.setLayoutX((1024/2) - (widthText1/2));
    }

}
