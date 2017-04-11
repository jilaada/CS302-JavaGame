package control_classes;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

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
    private boolean escGame;
    private boolean timeOut;
    private boolean pauseGame;
    private Scene currentScene;
    private int numPlayers;
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
    public IOHandle(Scene mainScene, int numPlayers) {
        this.numPlayers = numPlayers;
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
            // Determine the number of players in a game and check for the corresponding key pressed else ignore
//            if(e == "1") {
//                this.stage.setScene(sceneB1);
//                sceneSwitch = SceneChanger.gameScreen.PLAYERSEL;
//            } else if(e == "2") {
//                this.stage.setScene(sceneB2);
//                sceneSwitch = SceneChanger.gameScreen.PLAYERSEL;
//            }


            if (numPlayers == 1) {
                if (e == "A") {
                    movePaddle1left = true;
                } else if (e == "S") {
                    movePaddle1right = true;
                }
            } else if (numPlayers == 2) {
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
                if (e == "A") {
                    System.out.println("inA");
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
            if (e == "P") {
                pauseGame = !pauseGame;
            } else if (e == "ESCAPE") {
                escGame = true;
            } else if (e == "PAGE_DOWN") {
                timeOut = true;
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

    public void setMovedRightP1() {
        movePaddle1right = !movePaddle1right;
    }

    public void setMovedLeftP1() {
        movePaddle1left = !movePaddle1left;
    }

    public void setMovedRightP2() {
        movePaddle2right = !movePaddle2right;
    }

    public void setMovedLeftP2() {
        movePaddle2left = !movePaddle2left;
    }

    // Declare getters and setters
    public void setMovedRightP3() {
        movePaddle3right = !movePaddle3right;
    }

    public void setMovedLeftP3() {
        movePaddle3left = !movePaddle3left;
    }

    // Declare getters and setters
    public void setMovedRightP4() {
        movePaddle4right = !movePaddle4right;
    }

    public void setMovedLeftP4() {
        movePaddle4left = !movePaddle4left;
    }

    public boolean isPaused() {
        return this.pauseGame;
    }

    public boolean isEscGame() { return this.escGame; }

    public void setEscGame(boolean in) { this.escGame = in; }

    public void resetHandler() {
        this.escGame = false;
        this.timeOut = false;
        this.pauseGame = false;
}

    public boolean hasTimeOut() {
        return this.timeOut;
    }

    public int getNumPlayers() {
        return this.numPlayers;
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
