package Test_Root;

/**
 * Created by Jilada Eccleston on 31/03/17.
 */
public class GameAdaptor implements IGame {

    private BallAdapter currentBall;
    private int timeRemaining;
    private PlayerAdapter playerOne, playerTwo;
    private PaddleAdapter currentPaddle;
    private WallAdapter p1wall;
    private int newX, newY;

    public GameAdaptor() {
        timeRemaining = 120;
    }


    @Override
    public void tick() {
        moveBall();
        timeRemaining = timeRemaining - 1;
        checkWins();
    }

    @Override
    public boolean isFinished() {
        if ((timeRemaining <= 0) || playerOne.hasWon() || playerTwo.hasWon()) {
            return true;
        }
        return false;
    }

    @Override
    public void setTimeRemaining(int seconds) {
        this.timeRemaining = seconds;
    }

    public void GameSetUp(BallAdapter ball, PlayerAdapter player1, PlayerAdapter player2, PaddleAdapter paddle, WallAdapter player1wall) {
        this.currentBall = ball;
        this.playerOne = player1;
        this.playerTwo = player2;
        this.currentPaddle = paddle;
        this.p1wall = player1wall;
    }

    public void moveBall() {
        //Add the velocity to the ball

        newX = currentBall.getXPos() + currentBall.getXVelocity();
        newY = currentBall.getYPos() + currentBall.getYVelocity();

        checkPaddleCollision();
        checkWallCollision();

        // Check if collision with Boundary
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

    public void checkWins() {
        if (playerOne.isDead()) {
            playerTwo.setWin(true);
            playerOne.setWin(false);
        } else {
            playerOne.setWin(true);
            playerTwo.setWin(false);
        }
    }

    public void checkPaddleCollision() {
        if ((newX >= currentPaddle.getxPos()) && (currentBall.getXPos() <= currentPaddle.getxPos())) {
            if ((newY >= currentPaddle.getyPos()) && (currentBall.getYPos() <= currentPaddle.getyPos())) {
                // within the path of the of the ball going down and right
                newX = newX - (newY - currentPaddle.getyPos());
                newY = currentPaddle.getyPos();
                // Y velocity to change
                currentBall.setYVelocity(-(currentBall.getYVelocity()));
            }
        }
    }

    public void checkWallCollision() {
        if ((newX >= p1wall.getXPos()) && (currentBall.getXPos() <= p1wall.getXPos())) {
            if ((newY >= p1wall.getYPos()) && (currentBall.getYPos() <= p1wall.getYPos())) {
                // within the path of the of the ball going down and right
                newX = newX - (newY - p1wall.getYPos());
                newY = p1wall.getYPos();
                // Y velocity to change
                currentBall.setYVelocity(-(currentBall.getYVelocity()));
                // Wall need to be destroyed
                p1wall.setDestroyedWall(true);
            }
        } else {
            p1wall.setDestroyedWall(false);
        }
    }

}
