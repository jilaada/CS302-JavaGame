package control_classes;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainGame extends Application {

    public MainGame() {}


    @Override
    public void start(Stage theStage)
    {
        theStage.setTitle("Game window");
        SceneChanger sceneChanger = new SceneChanger();

        Group root = new Group();
        Scene gameScene = new Scene(root, 1024, 768, Color.BLACK);


        Scene pSelectScene = sceneChanger.addPlayersSelectScene(theStage, gameScene);
        Scene pControlScene = sceneChanger.addControlDisplayScene(theStage);
        Scene pDifficultyScene = sceneChanger.addDifficultySelectScene(theStage, pSelectScene);
        Scene pAboutScene = sceneChanger.addAboutScene(theStage);
        Scene introScene = sceneChanger.addIntroScene(theStage, pDifficultyScene, pControlScene, pAboutScene);


        Scene endScene = sceneChanger.addEndScene(theStage, introScene);
        sceneChanger.addGameScene(theStage, sceneChanger, gameScene, endScene);
       // Scene introScene = sceneChanger.addIntroScene(theStage, gameScene);

        theStage.setScene(introScene);

        theStage.show();

    }
}
