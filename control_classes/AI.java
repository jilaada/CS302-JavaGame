package control_classes;

import model_classes.Point;

/**
 * AI class has two methods that contain the movement specified for the level of difficult of the AI needed
 * Created by Jilada on 26/03/17.
 */
public class AI {
    private IOHandle handle;
    private GameSetUp gameFace;
    private int numPlayers, counter;
    // Assumes that player one will always be a human player
    private boolean moveP2Right, moveP3Right, moveP4Right;

    /**
     * Constructor for the AI class
     * @param handle - the IOHandle for the game; IOHandle
     * @param gameFace - the GameSetUp for th game; GameSetUp
     */
    public AI(IOHandle handle, GameSetUp gameFace) {
        this.handle = handle;
        this.gameFace = gameFace;
        this.numPlayers = handle.getNumPlayers();
        this.counter = 0;
        this.moveP4Right = true;
        this.moveP3Right = true;
        this.moveP2Right = true;
    }

    /**
     * moveAI is a function that that simply moved the AI side to side
     * The AI is not imtelligent and is used in the esay mode for the players
     */
    public void moveAI() {
        //TODO: This function will move all the AI paddles
        // If the number of players were 3, only
        if (counter == 1) {
            if (numPlayers < 4) {
                // Move player 4 paddle
                if (moveP4Right) {
                    // Check if at the end, keep moving right other move left
                    if (gameFace.getPlayer4().getPlayerPaddle().getCurrentPos().getX() == gameFace.getPlayer4().getPlayerPaddle().getPaddleEnd().getX()) {
                        handle.setMovedLeftP4();
                        moveP4Right = false;
                    } else {
                        handle.setMovedRightP4();
                    }
                } else {
                    if (gameFace.getPlayer4().getPlayerPaddle().getCurrentPos().getY() == gameFace.getPlayer4().getPlayerPaddle().getPaddleStart().getY()) {
                        handle.setMovedRightP4();
                        moveP4Right = true;
                    } else {
                        handle.setMovedLeftP4();
                    }
                }

                if (numPlayers < 3) {
                    // number of players = 2 therefore player 3 will also be AI
                    if (moveP3Right) {
                        // Check if at the end, keep moving right other move left
                        if (gameFace.getPlayer3().getPlayerPaddle().getCurrentPos().getY() == gameFace.getPlayer3().getPlayerPaddle().getPaddleEnd().getY()) {
                            handle.setMovedLeftP3();
                            moveP3Right = false;
                        } else {
                            handle.setMovedRightP3();
                        }
                    } else {
                        if (gameFace.getPlayer3().getPlayerPaddle().getCurrentPos().getX() == gameFace.getPlayer3().getPlayerPaddle().getPaddleStart().getX()) {
                            handle.setMovedRightP3();
                            moveP3Right = true;
                        } else {
                            handle.setMovedLeftP3();
                        }
                    }

                    if (numPlayers < 2) {
                        if (moveP2Right) {
                            // Check if at the end, keep moving right other move left
                            if (gameFace.getPlayer2().getPlayerPaddle().getCurrentPos().getX() == gameFace.getPlayer2().getPlayerPaddle().getPaddleEnd().getX()) {
                                handle.setMovedLeftP2();
                                moveP2Right = false;
                            } else {
                                handle.setMovedRightP2();
                            }
                        } else {
                            if (gameFace.getPlayer2().getPlayerPaddle().getCurrentPos().getY() == gameFace.getPlayer2().getPlayerPaddle().getPaddleStart().getY()) {
                                handle.setMovedRightP2();
                                moveP2Right = true;
                            } else {
                                handle.setMovedLeftP2();
                            }
                        }
                    }
                }
            }
            // Stops the AI moving too quickly (half speed)
            counter = 0;
        } else {
            counter++;
        }
    }

    /**
     * moveAIAdvanced is a function that moves all available AIs in an intelligent way to an extent
     * If it cannot detect the ball the (goes out of the line of sight) it will simply move like the unintelligent AI
     */
    public void moveAIAdvanced() {
        // Determine the balls location
        // Toggle direction of paddle movement
        Point prevPoint = gameFace.getBall().getPreviousPos();
        Point curPoint = gameFace.getBall().getPreviousPos();

        if (counter == 1) {
            //Check the number of player and where to move it
            if (numPlayers < 4) {
                // At least one AI player
                if (curPoint.getX() > gameFace.getPlayer4().getPlayerPaddle().getPaddleBounds().getX()) {
                    // Ball is in the line of sight of the paddle
                    if (gameFace.getPlayer4().getPlayerPaddle().getCurrentPos().getX() < curPoint.getX()) {
                        // Need to move the paddle right
                        handle.setMovedRightP4();
                        moveP4Right = true;
                    } else if (gameFace.getPlayer4().getPlayerPaddle().getCurrentPos().getX() > curPoint.getX()) {
                        // Move the paddle left
                        handle.setMovedLeftP4();
                        moveP4Right = false;
                    }
                } else if (curPoint.getY() > gameFace.getPlayer4().getPlayerPaddle().getPaddleBounds().getY()) {
                    if (gameFace.getPlayer4().getPlayerPaddle().getCurrentPos().getY() < curPoint.getY()) {
                        // Move the paddle left
                        handle.setMovedLeftP4();
                        moveP4Right = false;
                    } else if (curPoint.getY() < gameFace.getPlayer4().getPlayerPaddle().getPaddleBounds().getY()) {
                        // Move the paddle right
                        handle.setMovedRightP4();
                        moveP4Right = true;
                    }
                } else {
                    if (moveP4Right) {
                        // Check if at the end, keep moving right other move left
                        if (gameFace.getPlayer4().getPlayerPaddle().getCurrentPos().getX() == gameFace.getPlayer4().getPlayerPaddle().getPaddleEnd().getX()) {
                            handle.setMovedLeftP4();
                            moveP4Right = false;
                        } else {
                            handle.setMovedRightP4();
                        }
                    } else {
                        if (gameFace.getPlayer4().getPlayerPaddle().getCurrentPos().getY() == gameFace.getPlayer4().getPlayerPaddle().getPaddleStart().getY()) {
                            handle.setMovedRightP4();
                            moveP4Right = true;
                        } else {
                            handle.setMovedLeftP4();
                        }
                    }
                }

                if (numPlayers < 3) {
                    // At least two AI players
                    if (curPoint.getX() < gameFace.getPlayer3().getPlayerPaddle().getPaddleBounds().getX()) {
                        // Ball is in sight of paddle
                        if (gameFace.getPlayer3().getPlayerPaddle().getCurrentPos().getX() < curPoint.getX()) {
                            // Move paddle to the right
                            handle.setMovedRightP3();
                            moveP3Right = true;
                        } else if (gameFace.getPlayer3().getPlayerPaddle().getCurrentPos().getX() > curPoint.getX()) {
                            // Move paddle to the left
                            handle.setMovedLeftP3();
                            moveP3Right = false;
                        }
                    } else if (curPoint.getY() > gameFace.getPlayer3().getPlayerPaddle().getPaddleBounds().getY()) {
                        // Ball is in sight of paddle
                        if (gameFace.getPlayer3().getPlayerPaddle().getCurrentPos().getY() < curPoint.getY()) {
                            // Move paddle to the right
                            handle.setMovedRightP3();
                            moveP3Right = true;
                        } else if (gameFace.getPlayer3().getPlayerPaddle().getCurrentPos().getY() > curPoint.getY()) {
                            // Move paddle to the left
                            handle.setMovedLeftP3();
                            moveP3Right = false;
                        }
                    } else {
                        // Move ambiguously
                        if (moveP3Right) {
                            // Check if at the end, keep moving right other move left
                            if (gameFace.getPlayer3().getPlayerPaddle().getCurrentPos().getY() == gameFace.getPlayer3().getPlayerPaddle().getPaddleEnd().getY()) {
                                handle.setMovedLeftP3();
                                moveP3Right = false;
                            } else {
                                handle.setMovedRightP3();
                            }
                        } else {
                            if (gameFace.getPlayer3().getPlayerPaddle().getCurrentPos().getX() == gameFace.getPlayer3().getPlayerPaddle().getPaddleStart().getX()) {
                                handle.setMovedRightP3();
                                moveP3Right = true;
                            } else {
                                handle.setMovedLeftP3();
                            }
                        }
                    }

                    if (numPlayers < 2) {
                        if (curPoint.getX() > gameFace.getPlayer2().getPlayerPaddle().getPaddleBounds().getX()) {
                            // Ball is within horizontal detection
                            if (gameFace.getPlayer2().getPlayerPaddle().getCurrentPos().getX() < curPoint.getX()) {
                                // Move to the right
                                handle.setMovedRightP2();
                                moveP2Right = true;
                            } else if (gameFace.getPlayer2().getPlayerPaddle().getCurrentPos().getX() > curPoint.getX()) {
                                // Move to the left
                                handle.setMovedLeftP2();
                                moveP2Right = false;
                            }
                        } else if (curPoint.getY() < gameFace.getPlayer2().getPlayerPaddle().getPaddleBounds().getY()) {
                            // Ball is within vertical detection
                            if (gameFace.getPlayer2().getPlayerPaddle().getCurrentPos().getY() < curPoint.getY()) {
                                // Move to the right
                                handle.setMovedRightP2();
                                moveP2Right = true;
                            } else if (gameFace.getPlayer2().getPlayerPaddle().getCurrentPos().getY() > curPoint.getY()) {
                                // Move to the left
                                handle.setMovedLeftP2();
                                moveP2Right = false;
                            }
                        } else {
                            // Move ambiguously
                            if (moveP2Right) {
                                // Check if at the end, keep moving right other move left
                                if (gameFace.getPlayer2().getPlayerPaddle().getCurrentPos().getX() == gameFace.getPlayer2().getPlayerPaddle().getPaddleEnd().getX()) {
                                    handle.setMovedLeftP2();
                                    moveP2Right = false;
                                } else {
                                    handle.setMovedRightP2();
                                }
                            } else {
                                if (gameFace.getPlayer2().getPlayerPaddle().getCurrentPos().getY() == gameFace.getPlayer2().getPlayerPaddle().getPaddleStart().getY()) {
                                    handle.setMovedRightP2();
                                    moveP2Right = true;
                                } else {
                                    handle.setMovedLeftP2();
                                }
                            }
                        }
                    }
                }
            }
            // Stops the AIs from moving too quickly (half speed)
            counter = 0;
        } else {
            counter++;
        }
    }
}
