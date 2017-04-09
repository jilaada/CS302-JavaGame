package control_classes;

import model_classes.Player;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.ArrayList;

/**
 * Created by niles on 09/04/2017.
 */
public class GameStatus {
    private ArrayList<Player> players = new ArrayList<Player>();

    public GameStatus(ArrayList<Player> players) {
        this.players = players;
    }

    public boolean onePlayerAlive() {
        int noAlive = 0;
        for(int i = 0; i < this.players.size(); i++) {
            if(this.players.get(i).isAlive()) {
                noAlive += 1;
            }
        }


        if(noAlive <= 1) {
            return true;
        }
        return false;
    }

    public int winningPlayer() {
        int index = 0;
        for(int i = 0; i < this.players.size(); i++) {
            if(this.players.get(i).isAlive()) {
                index = i;
            }
        }

        return this.players.get(index).getPlayerPaddle().getPaddleToken();
    }

}
