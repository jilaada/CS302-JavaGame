package control_classes;

import javafx.scene.Group;
import model_classes.Brick;
import model_classes.Player;
import model_classes.gameObject;
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
