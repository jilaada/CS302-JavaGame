package control_classes;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.net.URISyntaxException;

/**
 * Created by niles on 09/04/2017.
 */
public class GameSounds {
    MediaPlayer brickPlayer;
    MediaPlayer paddlePlayer;
    MediaPlayer deathPlayer;

    public GameSounds() {

        //Get path to audio
        String brickSE = "/sounds/BrickSE.mp3";
        String paddleSE = "/sounds/PaddleSE.mp3";
        String playerSE = "/sounds/PlayerDeathSE.wav";

        //Get media fom path
        Media brickSound;
        Media paddleSound;
        Media playerSound;

        try {
            brickSound = new Media(getClass().getResource(brickSE).toURI().toString());
            paddleSound = new Media(getClass().getResource(paddleSE).toURI().toString());
            playerSound = new Media(getClass().getResource(playerSE).toURI().toString());

            //Declare media player with audio
            this.brickPlayer = new MediaPlayer(brickSound);
            this.paddlePlayer = new MediaPlayer(paddleSound);
            this.deathPlayer = new MediaPlayer(playerSound);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
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

    public void playDeathSE() {
        //Play audio in thread
        try{
            new Thread() {
                public void run() {
                    deathPlayer.stop();
                    deathPlayer.play();
                }
            }
            .start();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

}
