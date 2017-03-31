package Test_Root;

import model_classes.Player;

/**
 * Created by Jilada on 31/03/17.
 */
public class PlayerAdapter implements IWarlord {

    private Player currentPlayer;
    private IBall currentBall;
    private int xPos;
    private int yPos;

    public PlayerAdapter(IBall ball) {
        currentPlayer = new Player("Test");
        currentBall = ball;
    }

    @Override
    public void setXPos(int x) {
        this.xPos = x;
    }

    @Override
    public void setYPos(int y) {
        this.yPos = y;
    }

    @Override
    public boolean isDead() {
        int prevXPos = currentBall.getXPos() - currentBall.getXVelocity();
        int prevYPos = currentBall.getYPos() - currentBall.getYVelocity();

        if ((xPos <= currentBall.getXPos()) && (xPos >= prevXPos)) {
            if ((yPos <= currentBall.getYPos()) && (yPos >= prevYPos)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasWon() {
        return false;
    }

}
