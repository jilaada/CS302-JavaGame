package control_classes;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * IOHandle will be a passive class that will determine whether objects need to be moved or not.
 * Makes the main game aware of any user inputs that have occured
 * Created by Jilada on 26/03/17.
 */
public class IOHandle {
    // Declare the attributes to be set:
    boolean movePaddle1right;
    boolean movePaddle1left;
    boolean movePaddle2right;
    boolean movePaddle2left;
    boolean movePaddle3;
    boolean movePaddle4;
    boolean pauseGame;
    Scene currentScene;

    // Declare the constructor
    public IOHandle(Scene mainScene) {
        this.currentScene = mainScene;
    }
    // Declare the scene events
    // If a key (A) was pressed then move paddle left will be set to 1 others = 0
    public void handleMovementP1() {
        //Handler for the key pressed for player one's paddle
        currentScene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if (key.getCode() == KeyCode.A) {
                movePaddle1left = true;
                movePaddle1right = false;
            } else if (key.getCode() == KeyCode.S) {
                movePaddle1left = false;
                movePaddle1right = true;
            }
        });
    }

    public void handleMovementP2() {
        //Handler for key pressed for player two's paddle
        currentScene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if (key.getCode() == KeyCode.F) {
                movePaddle2left = true;
                movePaddle2right = false;
            } else if (key.getCode() == KeyCode.G) {
                movePaddle2left = false;
                movePaddle2right = true;
            }
        });
    }

    // Declare getters and setters
    public boolean hasMovedRightP1() {
        return this.movePaddle1right;
    }

    public boolean hasMovedLeftP1() {
        return this.movePaddle1left;
    }

    // Declare getters and setters
    public boolean hasMovedRightP2() {
        return this.movePaddle2right;
    }

    public boolean hasMovedLeftP2() {
        return this.movePaddle2left;
    }

    public void resetPaddle() {
        this.movePaddle1left = false;
        this.movePaddle1right = false;
        this.movePaddle2left = false;
        this.movePaddle2right = false;
    }


}
