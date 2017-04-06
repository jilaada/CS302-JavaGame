package control_classes;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

import java.util.HashSet;
import java.util.Set;

/**
 * IOHandle will be a passive class that will determine whether objects need to be moved or not.
 * Makes the main game aware of any user inputs that have occured
 * Created by Jilada on 26/03/17.
 */
public class IOHandle {
    // Declare the attributes to be set:
    private boolean movePaddle1right;
    private boolean movePaddle1left;
    private boolean movePaddle2right;
    private boolean movePaddle2left;
    private boolean movePaddle3right;
    private boolean movePaddle3left;
    private boolean movePaddle4right;
    private boolean movePaddle4left;
    private boolean pauseGame;
    private Scene currentScene;
    private final Set<String> KeysPressed = new HashSet<String>();


    EventHandler<KeyEvent> startEvent = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            if (!KeysPressed.contains(event.getCode().toString())) {
                KeysPressed.add(event.getCode().toString());
            }
            HandleMovement(KeysPressed);
        }
    };

    EventHandler<KeyEvent> closeEvent = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            KeysPressed.remove(event.getCode().toString());
        }
    };

    // Declare the constructor
    public IOHandle(Scene mainScene) {
        this.currentScene = mainScene;
    }

    // Declare the scene events
    // If a key (A) was pressed then move paddle left will be set to 1 others = 0
    public void keyPressed() {
        currentScene.setOnKeyPressed(startEvent);
        currentScene.setOnKeyReleased(closeEvent);
    }

    public void HandleMovement(Set<String> pressed) {
        for (String e : pressed) {
            if (e == "A") {
                movePaddle1left = true;
                movePaddle1right = false;
            } else if (e == "S") {
                movePaddle1left = false;
                movePaddle1right = true;
            } else if (e == "F") {
                movePaddle2left = true;
                movePaddle2right = false;
            } else if (e == "G") {
                movePaddle2left = false;
                movePaddle2right = true;
            } else if (e == "J") {
                movePaddle3left = true;
                movePaddle3right = false;
            } else if (e == "K") {
                movePaddle3left = false;
                movePaddle3right = true;
            } else if (e == "LEFT") {
                movePaddle4left = true;
                movePaddle4right = false;
            } else if (e == "RIGHT") {
                movePaddle4left = false;
                movePaddle4right = true;
            } else if (e == "P") {
                pauseGame = !pauseGame;
            }

        }
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

    public boolean isPaused() {
        return this.pauseGame;
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
