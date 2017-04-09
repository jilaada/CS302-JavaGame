package control_classes;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

/**
 * Created by niles on 09/04/2017.
 */
public class GameSounds {
    MediaPlayer brickPlayer;
    MediaPlayer paddlePlayer;

    public GameSounds() {

        //Get path to audio
        String brickSE = "sounds/BrickSE.mp3";
        String paddleSE = "sounds/PaddleSE.mp3";

        //Get media fom path
        Media brickSound = new Media(new File(brickSE).toURI().toString());
        Media paddleSound = new Media(new File(paddleSE).toURI().toString());

        //Declare media player with audio
        this.brickPlayer = new MediaPlayer(brickSound);
        this.paddlePlayer = new MediaPlayer(paddleSound);
    }

    public void playBrickSE() {

        //Play audio in thread
        try{
            new Thread() {
                public void run() {
                    brickPlayer.stop();
                    brickPlayer.play();
                }
            }
            .start();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public void playPaddleSE() {
        //Play audio in thread
        try{
            new Thread() {
                public void run() {
                    paddlePlayer.stop();
                    paddlePlayer.play();
                }
            }
                    .start();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

}
