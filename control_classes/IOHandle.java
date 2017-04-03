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
    boolean movePaddle3right;
    boolean movePaddle3left;
    boolean movePaddle4right;
    boolean movePaddle4left;
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

    public void handleMovementP3() {
        //Handler for key pressed for player two's paddle
        currentScene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if (key.getCode() == KeyCode.J) {
                movePaddle3left = true;
                movePaddle3right = false;
            } else if (key.getCode() == KeyCode.K) {
                movePaddle3left = false;
                movePaddle3right = true;
            }
        });
    }

    public void handleMovementP4() {
        //Handler for key pressed for player two's paddle
        currentScene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if (key.getCode() == KeyCode.LEFT) {
                movePaddle4left = true;
                movePaddle4right = false;
            } else if (key.getCode() == KeyCode.RIGHT) {
                movePaddle4left = false;
                movePaddle4right = true;
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

    // Declare getters and setters
    public boolean hasMovedRightP3() {
        return this.movePaddle3right;
    }

    public boolean hasMovedLeftP3() {
        return this.movePaddle3left;
    }

    // Declare getters and setters
    public boolean hasMovedRightP4() {
        return this.movePaddle4right;
    }

    public boolean hasMovedLeftP4() {
        return this.movePaddle4left;
    }

    public void resetPaddle() {
        this.movePaddle1left = false;
        this.movePaddle1right = false;
        this.movePaddle2left = false;
        this.movePaddle2right = false;
        this.movePaddle3left = false;
        this.movePaddle3right = false;
        this.movePaddle4left = false;
        this.movePaddle4right = false;
    }


}
