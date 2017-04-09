package control_classes;

import model_classes.Player;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.ArrayList;

/**
 * Created by niles on 09/04/2017.
 */
public class GameStatus {

    //Declare player array
    private ArrayList<Player> players = new ArrayList<Player>();

    //Game status constructor
    public GameStatus(ArrayList<Player> players) {
        this.players = players;
    }

    //Check if there is only one player alive
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

}
