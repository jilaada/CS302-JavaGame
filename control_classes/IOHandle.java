package control_classes;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * IOHandle will be a passive class that will determine whether objects need to be moved or not.
 * Makes the main game aware of any user inputs that have occurred
 * Created by Jilada on 26/03/17.
 */
public class IOHandle {
    // Declare the attributes to be set:
    boolean movePaddle1right;
    boolean movePaddle1left;
    boolean movePaddle2;
    boolean movePaddle3;
    boolean movePaddle4;
    boolean pauseGame;
    Scene currentScene;

    /**
     * IOHandle constructor which will allow for key listening into the user inputs
     * @param mainScene - a reference to the main scene input for rendering the screen; scene
     */
    public IOHandle(Scene mainScene) {
        this.currentScene = mainScene;
    }

    // Declare the scene events
    // If a key (A) was pressed then move paddle left will be set to 1 others = 0
    /**
     * HandleMovement is a function that will handle the triggering of booleans to tell the main game to move the object
     */
    public void handleMovement() {
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
        //TODO: Add the other players to the movement
    }

    // Declare getters and setters
    /**
     * HadMovedRightP1 is a function that will return player1's paddle RIGHT MOVE
     * @return - movePaddle1Right; boolean signal to move the paddle RIGHT
     */
    public boolean hasMovedRightP1() {
        return this.movePaddle1right;
    }

    /**
     * HasMovedLeftP1 is a function that will return player1's paddle LEFT MOVE
     * @return - movePaddle1Left; boolean signal to move paddle LEFT
     */
    public boolean hasMovedLeftP1() {
        return this.movePaddle1left;
    }

    /**
     * ResetP1 used to reset paddle to stationary - wait for next signal
     */
    public void resetP1() {
        this.movePaddle1left = false;
        this.movePaddle1right = false;
    }
}
