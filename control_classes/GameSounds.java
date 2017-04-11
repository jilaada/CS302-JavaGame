package control_classes;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.net.URISyntaxException;

/**
 * Created by niles on 09/04/2017.
 */
public class GameSounds {
    MediaPlayer brickPlayer;
    MediaPlayer paddlePlayer;
    MediaPlayer deathPlayer;
    MediaPlayer backgroundPlayer;
    MediaPlayer gamePlayer;
    boolean firstLoop;

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


    public void playBackgroundSE(boolean keepGoing) {
        //Play audio in thread

        if(this.firstLoop) {
            this.firstLoop = false;
            try {
                new Thread() {
                    public void run() {

                        backgroundPlayer.setOnEndOfMedia(new Runnable() {
                            public void run() {
                                backgroundPlayer.seek(Duration.ZERO);
                            }
                        });
                        backgroundPlayer.play();

                    }
                }
                        .start();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            if(!keepGoing) {
                try {
                    new Thread() {
                        public void run() {
                            backgroundPlayer.stop();
                        }
                    }
                            .start();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
