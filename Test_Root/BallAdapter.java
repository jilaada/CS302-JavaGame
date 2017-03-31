package Test_Root;

import model_classes.Ball;

/**
 * Created by Jilada on 31/03/17.
 */
public class BallAdapter implements IBall {

    private Ball currentBall;
    private int prevXPos, prevYPos;

    public BallAdapter() {
        currentBall = new Ball(0,0);
    }

    @Override
    public void setXPos(int x) {

        currentBall.getCurrentPos().setX(x);
    }

    @Override
    public void setYPos(int y) {
        currentBall.getCurrentPos().setY(y);
    }

    @Override
    public int getXPos() {
        return currentBall.getCurrentPos().getX();
    }

    @Override
    public int getYPos() {
        return currentBall.getCurrentPos().getY();
    }

    @Override
    public void setXVelocity(int dX) {
        currentBall.setXVel(dX);
    }

    @Override
    public void setYVelocity(int dY) {
        currentBall.setYVel(dY);
    }

    @Override
    public int getXVelocity() {
        return currentBall.getxVel();
    }

    @Override
    public int getYVelocity() {
        return currentBall.getyVel();
    }

    public void setPrevXPos(int x) {
        this.prevXPos = x;
    }

    public void setPrevYPos(int y) {
        this.prevYPos = y;
    }

    public int getPrevXPos() {
        return this.prevXPos;
    }

    public int getPrevYPos() {
        return this.prevYPos;
    }
}
