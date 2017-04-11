package control_classes;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import model_classes.Ball;
import model_classes.CollisionStruct;
import model_classes.gameObject;
import view_classes.RenderView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MainGame extends Application {

    public MainGame() {}


    @Override
    public void start(Stage theStage)
    {
        theStage.setTitle("Game window");
        SceneChanger sceneChanger = new SceneChanger();

        Group root = new Group();
        Scene gameScene = new Scene(root, 1024, 768, Color.BLACK);


        Scene pSelectScene = sceneChanger.addPlayerSelectScene(theStage,gameScene);
        Scene introScene = sceneChanger.addIntroScene(theStage, pSelectScene);


        Scene endScene = sceneChanger.addEndScene(theStage, introScene);
        sceneChanger.addGameScene(theStage, sceneChanger, gameScene, endScene);
       // Scene introScene = sceneChanger.addIntroScene(theStage, gameScene);

        theStage.setScene(introScene);

        theStage.show();

    }
}
