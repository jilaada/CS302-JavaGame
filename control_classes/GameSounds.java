package control_classes;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.net.URISyntaxException;

/**
 * GameSounds will create new thread swhen sounds need to played so they play concurrently to the rest of the game
 * Created by niles on 09/04/2017.
 */
public class GameSounds {
    // Declaring the music files
    MediaPlayer brickPlayer;
    MediaPlayer paddlePlayer;
    MediaPlayer deathPlayer;
    MediaPlayer backgroundPlayer;
    MediaPlayer gamePlayer;
    boolean firstLoop;

    /**
     * Constructor for the GameSounds class
     */
    public GameSounds() {

        //Get path to audio
        String brickSE = "/sounds/BrickSE.wav";
        String paddleSE = "/sounds/PaddleSE.mp3";
        String playerSE = "/sounds/PlayerDeathSE.wav";
        String backgroundSE = "/sounds/backgroundMusic.mp3";
        String gameSE = "/sounds/gameMusic.wav";

        //Get media fom path
        Media brickSound;
        Media paddleSound;
        Media playerSound;
        Media backgroundSound;
        Media gameSound;
        this.firstLoop = true;

        try {
            brickSound = new Media(getClass().getResource(brickSE).toURI().toString());
            paddleSound = new Media(getClass().getResource(paddleSE).toURI().toString());
            playerSound = new Media(getClass().getResource(playerSE).toURI().toString());
            backgroundSound = new Media(getClass().getResource(backgroundSE).toURI().toString());
            gameSound = new Media(getClass().getResource(gameSE).toURI().toString());

            //Declare media player with audio
            this.brickPlayer = new MediaPlayer(brickSound);
            this.paddlePlayer = new MediaPlayer(paddleSound);
            this.deathPlayer = new MediaPlayer(playerSound);
            this.backgroundPlayer= new MediaPlayer(backgroundSound);
            this.gamePlayer= new MediaPlayer(gameSound);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * playBrickSE will play the sounds the ball makes when it collides with a brick
     */
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

    /**
     * playPaddleSE will play the sound the ball makes when it collides with the paddle
     */
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

    /**
     * playDeathSE will play the sound the ball makes when it collides with the player shape
     */
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


    /**
     * playBackgroundSE will play the background music during the game
     * @param keepGoing - an enum value for the scene the game is currently in; Enumeration
     */
    public void playBackgroundSE(SceneChanger.gameScreen keepGoing) {
        //Play audio in thread

        if(this.firstLoop) {
            this.firstLoop = false;
            this.loopSound(backgroundPlayer);
        } else {
            if(keepGoing == SceneChanger.gameScreen.GAME) {
               this.stopSound(backgroundPlayer);
               this.loopSound(gamePlayer);
            } if(keepGoing == SceneChanger.gameScreen.END) {
                this.stopSound(gamePlayer);
            }
        }
    }

    /**
     * loopSound will loop the sound once the track finishes
     * @param sound
     */
    public void loopSound(MediaPlayer sound) {
        try {
            new Thread() {
                public void run() {

                    sound.setOnEndOfMedia(new Runnable() {
                        public void run() {
                            sound.seek(Duration.ZERO);
                        }
                    });
                    sound.play();

                }
            }
                    .start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * stopSound will stop the sound once it is no longer needed
     * @param sound
     */
    public void stopSound(MediaPlayer sound) {
        try {
            new Thread() {
                public void run() {
                    sound.stop();
                }
            }
                    .start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
