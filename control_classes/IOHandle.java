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
    private boolean movePaddle1right = false;
    private boolean movePaddle1left = false;
    private boolean movePaddle2right= false;
    private boolean movePaddle2left= false;
    private boolean movePaddle3right= false;
    private boolean movePaddle3left= false;
    private boolean movePaddle4right= false;
    private boolean movePaddle4left= false;
    private boolean escGame;
    private boolean timeOut;
    private boolean pauseGame;
    private boolean unPause;
    private Scene currentScene;
    private int numPlayers;
    private final Set<String> KeysPressed = new HashSet<String>();


    /**
     * startevent is the event handler for when the key is pressed
     */
    EventHandler<KeyEvent> startEvent = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            if (!KeysPressed.contains(event.getCode().toString())) {
                KeysPressed.add(event.getCode().toString());
            }
            handleMovement(KeysPressed);
        }
    };

    /**
     * closeEvent is the event handler for removing the keys once they have been released
     */
    EventHandler<KeyEvent> closeEvent = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            KeysPressed.remove(event.getCode().toString());
        }
    };

    /**
     * Constructor for the IOHandler
     * @param mainScene - the scene of the game; Scene
     * @param numPlayers - the number of players that are human players; int
     */
    public IOHandle(Scene mainScene, int numPlayers) {
        this.numPlayers = numPlayers;
        this.currentScene = mainScene;
    }

    /**
     * keyPressed will activate the event handlers of this class
     */
    public void keyPressed() {
        currentScene.setOnKeyPressed(startEvent);
        currentScene.setOnKeyReleased(closeEvent);
    }

    /**
     * handleMovement will set the booleans for the movemement of the paddles in the game
     * @param pressed - the set of strings passed into the function will be determined by the bool; Set
     */
    public void handleMovement(Set<String> pressed) {
        for (String e : pressed) {
            if (numPlayers == 1) {
                // only one human player
                if (e == "A") {
                    movePaddle1left = true;
                } else if (e == "S") {
                    movePaddle1right = true;
                }
            } else if (numPlayers == 2) {
                // Two huamn players
                if (e == "A") {
                    movePaddle1left = true;
                } else if (e == "S") {
                    movePaddle1right = true;
                } else if (e == "F") {
                    movePaddle2left = true;
                } else if (e == "G") {
                    movePaddle2right = true;
                }
            } else if (numPlayers == 3) {
                // Three human players
                if (e == "A") {
                    movePaddle1left = true;
                } else if (e == "S") {
                    movePaddle1right = true;
                } else if (e == "F") {
                    movePaddle2left = true;
                } else if (e == "G") {
                    movePaddle2right = true;
                } else if (e == "J") {
                    movePaddle3left = true;
                } else if (e == "K") {
                    movePaddle3right = true;
                }
            } else {
                // All are huamn players
                if (e == "A") {
                    movePaddle1left = true;
                } else if (e == "S") {
                    movePaddle1right = true;
                } else if (e == "F") {
                    movePaddle2left = true;
                } else if (e == "G") {
                    movePaddle2right = true;
                } else if (e == "J") {
                    movePaddle3left = true;
                } else if (e == "K") {
                    movePaddle3right = true;
                } else if (e == "LEFT") {
                    movePaddle4left = true;
                } else if (e == "RIGHT") {
                    movePaddle4right = true;
                }
            }
            // The other buttons in the game
            if (e == "P") {
                pauseGame = !pauseGame;
                if (!pauseGame) {
                    unPause = true;
                }
            } else if (e == "ESCAPE") {
                escGame = true;
            } else if (e == "PAGE_DOWN") {
                timeOut = true;
            }
        }
    }


    // Declare getters and setters

    /**
     * hasMovedRightP1 will check to see if player one has moved right
     * @return Returns a boolean specifying if the change has been made; boolean
     */
    public boolean hasMovedRightP1() {
        return this.movePaddle1right;
    }

    /**
     * hasMovedLeftP1 will check to see if player one has moved left
     * @return Returns a boolean specifying if the change has been made; boolean
     */
    public boolean hasMovedLeftP1() {
        return this.movePaddle1left;
    }

    // Declare getters and setters
    /**
     * hasMovedRightP2 will check to see if player two has moved right
     * @return Returns a boolean specifying if the change has been made; boolean
     */
    public boolean hasMovedRightP2() {
        return this.movePaddle2right;
    }

    /**
     * hasMovedLeftP2 will check to see if player two has moved left
     * @return Returns a boolean specifying if the change has been made; boolean
     */
    public boolean hasMovedLeftP2() {
        return this.movePaddle2left;
    }

    // Declare getters and setters
    /**
     * hasMovedRightP3 will check to see if player three has moved right
     * @return Returns a boolean specifying if the change has been made; boolean
     */
    public boolean hasMovedRightP3() {
        return this.movePaddle3right;
    }

    /**
     * hasMovedLeftP3 will check to see if player three has moved left
     * @return Returns a boolean specifying if the change has been made; boolean
     */
    public boolean hasMovedLeftP3() {
        return this.movePaddle3left;
    }

    // Declare getters and setters
    /**
     * hasMovedRightP4 will check to see if player four has moved right
     * @return Returns a boolean specifying if the change has been made; boolean
     */
    public boolean hasMovedRightP4() {
        return this.movePaddle4right;
    }

    /**
     * hasMovedLeftP4 will check to see if player four has moved left
     * @return Returns a boolean specifying if the change has been made; boolean
     */
    public boolean hasMovedLeftP4() {
        return this.movePaddle4left;
    }

    /**
     * setMovedRightP1 inverses the boolean
     */
    public void setMovedRightP1() {
        movePaddle1right = !movePaddle1right;
    }

    /**
     * setMovedLeftP1 inverses the boolean
     */
    public void setMovedLeftP1() {
        movePaddle1left = !movePaddle1left;
    }

    /**
     * setMovedRightP2 inverses the boolean
     */
    public void setMovedRightP2() {
        movePaddle2right = !movePaddle2right;
    }

    /**
     * setMovedLeftP2 inverses the boolean
     */
    public void setMovedLeftP2() {
        movePaddle2left = !movePaddle2left;
    }

    // Declare getters and setters
    /**
     * setMovedRightP3 inverses the boolean
     */
    public void setMovedRightP3() {
        movePaddle3right = !movePaddle3right;
    }

    /**
     * setMovedLeftP3 inverses the boolean
     */
    public void setMovedLeftP3() {
        movePaddle3left = !movePaddle3left;
    }

    // Declare getters and setters
    /**
     * setMovedRightP4 inverses the boolean
     */
    public void setMovedRightP4() {
        movePaddle4right = !movePaddle4right;
    }

    /**
     * setMovedLeftP4 inverses the boolean
     */
    public void setMovedLeftP4() {
        movePaddle4left = !movePaddle4left;
    }

    /**
     * isPaused returns true if the game is currently paused
     * @return Returns the boolean to determine if the game is paused;boolean
     */
    public boolean isPaused() {
        return this.pauseGame;
    }

    /**
     * isEscGame returns if the player has pressed esc during the game
     * @return returns if the gsme need to escape; boolean
     */
    public boolean isEscGame() { return this.escGame; }

    /**
     * setEscGame sets the esc bool
     * @param in - the esc bool; boolean
     */
    public void setEscGame(boolean in) { this.escGame = in; }

    /**
     * resetHandler will reset the game status bools
     */
    public void resetHandler() {
        this.escGame = false;
        this.timeOut = false;
        this.pauseGame = false;
}

    /**
     * hasTimeOut will return true if the game has timed out
     * @return return if the game has timed out; boolean
     */
    public boolean hasTimeOut() {
        return this.timeOut;
    }

    /**
     * getNumPlayers will returns the number of players
     * @return Returns the number of players; int
     */
    public int getNumPlayers() {
        return this.numPlayers;
    }

    /**
     * resetPaddle will reset the paddle movement bools
     */
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
