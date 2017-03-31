package Test_Root;

/**
 * Created by Jilada Eccleston on 31/03/17.
 */
public class GameAdaptor implements IGame {

    private IBall currentBall;

    public GameAdaptor(IBall ball) {
        this.currentBall = ball;
    }


    @Override
    public void tick() {
        moveBall();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void setTimeRemaining(int seconds) {

    }

    public void moveBall() {
        //Add the velocity to the ball
        int newX, newY;

        newX = currentBall.getXPos() + currentBall.getXVelocity();
        newY = currentBall.getYPos() + currentBall.getYVelocity();

        if (newX < 0) {
            // Collided with the left wall
            newX = 0;
            currentBall.setXVelocity(-(currentBall.getXVelocity()));
        } else if (newX > 1024) {
            // Collided with the right wall
            newX = 1024;
            currentBall.setXVelocity(-(currentBall.getXVelocity()));
        }

        if (newY < 0) {
            // Collided with the top wall
            newY = 0;
            currentBall.setYVelocity(-(currentBall.getYVelocity()));
        } else if (newY > 768) {
            newY = 768;
            currentBall.setYVelocity(-(currentBall.getYVelocity()));
        }

        currentBall.setXPos(newX);
        currentBall.setYPos(newY);
    }

}
