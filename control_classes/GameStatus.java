package control_classes;

import javafx.scene.Group;
import model_classes.Brick;
import model_classes.Player;
import model_classes.gameObject;

import java.util.ArrayList;

/**
 * GameStatus is a class that will determine the status of the game
 * Created by niles on 09/04/2017.
 */
public class GameStatus {

    //Declare player array
    private ArrayList<Player> players = new ArrayList<Player>();

    /**
     * GameStatus constructor
     * @param players - the list of players in the game
     */
    public GameStatus(ArrayList<Player> players) {
        this.players = players;
    }

    /**
     * onePlayerAlive will check to see if only one player is alive
     * @return Returns true of there is only one player; boolean
     */
    public boolean onePlayerAlive() {

        //Loop through array and find how many players are still alive
        int noAlive = 0;
        for(int i = 0; i < this.players.size(); i++) {
            if(this.players.get(i).isAlive()) {
                noAlive += 1;
            }
        }

        //Return true if only one player is alive
        if(noAlive <= 1) {
            return true;
        }
        return false;
    }

    //Return the number of the player

    /**
     * winningPlayer gets the player than has won the game
     * @return Returns the number representing the winning player; int
     */
    public int winningPlayer() {

        //Loop through player array and find which player is the winner
        int index = 0;
        for(int i = 0; i < this.players.size(); i++) {
            if(this.players.get(i).isAlive()) {
                index = i;
            }
        }

        //Return the number of the winning player
        return this.players.get(index).getPlayerPaddle().getPaddleToken();
    }

    /**
     * resetGame will reset the game once the game is over and will allow the user to create a new instance of the game
     * @param players - the list of players to be destroyed; arraylist
     * @param bin - all the remaining objects in the game; arraylist
     * @param gameArray - all other objects in the game; arraylist
     * @param root - all shape objects in the group of the scene; Group
     * @param io - the IOHandle of the game; IOHandle
     * @param time - resetting the changes in the animation timer; boolean[]
     * @param delay - resetting the changes in the animation timer; boolean[]
     */
    public void resetGame(ArrayList<Player> players, ArrayList<gameObject> bin, ArrayList<gameObject> gameArray, Group root, IOHandle io, boolean[] time, boolean[] delay) {

        //Reset player alive status
        for(int i = 0; i < players.size(); i++) {
            players.get(i).setAlive(true);
        }


        System.out.println("here");
        //Restore bin gameObjects and bricks
        for(int i = 0; i < bin.size(); i++) {
            gameObject temp = bin.get(i);
            if(temp.getObj() instanceof Brick) {
                Brick tempBrick = ((Brick) temp.getObj());
                tempBrick.setRemoved(false);
                root.getChildren().add(temp.getShape());
                System.out.println(i);
            }

            gameArray.add(temp);
            bin.remove(i);
        }

        //Fix esc and timeout
        io.resetHandler();

        //Reset time
        time[0] = false;
        delay[0] = true;


    }

}
