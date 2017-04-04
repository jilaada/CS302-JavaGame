package control_classes;

import model_classes.Ball;
import model_classes.Paddle;
import model_classes.Player;
import model_classes.Point;

/**
 * Created by Jilada on 5/04/17.
 */
public class GameSetUp {
    // This class will set up the game objects and bring to render the view
    // This class will store all the game objects to be parsed during the game
    private Player player1, player2, player3, player4;
    //private String name1, name2, name3, name4;
    private int numPlayers;
    private Ball ball;
    private Paddle paddle1, paddle2, paddle3, paddle4;

    public GameSetUp() {
        //TODO: Add names to the players
        player1 = new Player("Test1");
        //this.player1 = player1;
        player2 = new Player("Test2");
        //this.player2 = player2;
        player3 = new Player("Test3");
        //this.player3 = player3;
        player4 = new Player("Test4");
        //this.player4 = player4;
        ball = new Ball(0, 0);
        numPlayers = 4;
        paddle1 = new Paddle(0,0,0);
        paddle2 = new Paddle(0,0,0);
        paddle3 = new Paddle(0,0,0);
        paddle4 = new Paddle(0,0,0);
    }

    public void SetUpPlayers() {
        //TODO: Detect the number of players
        Player player1 = new Player("Test1");
        this.player1 = player1;
        Player player2 = new Player("Test2");
        this.player2 = player2;
        Player player3 = new Player("Test3");
        this.player3 = player3;
        Player player4 = new Player("Test4");
        this.player4 = player4;
    }

    public void SetUpBall(int speed, int rad) {
        Ball ball = new Ball(speed, rad);
    }

    public void SetUpPaddles(int speed, int size) {
        // Instantiate all the paddles
        Paddle paddle1 = new Paddle(speed, size, 1);
        this.paddle1 = paddle1;
        Paddle paddle2 = new Paddle(speed, size, 2);
        this.paddle2 = paddle2;
        Paddle paddle3 = new Paddle(speed, size, 3);
        this.paddle3 = paddle3;
        Paddle paddle4 = new Paddle(speed, size, 4);
        this.paddle4 = paddle4;

        // Set up the bounds for all the paddles
        paddle1.setBounds();
        paddle2.setBounds();
        paddle3.setBounds();
        paddle4.setBounds();

        // Add all the paddles to the players
        player1.addPlayerPaddle(paddle1);
        player2.addPlayerPaddle(paddle2);
        player3.addPlayerPaddle(paddle3);
        player4.addPlayerPaddle(paddle4);

        // Set up start points
        Point point1 = new Point(150,250);
        Point point2 = new Point(850,250);
        Point point3 = new Point(150,518);
        Point point4 = new Point(850,518);

        // Add points to current position
        paddle1.setCurrentPos(point1);
        paddle2.setCurrentPos(point2);
        paddle3.setCurrentPos(point3);
        paddle4.setCurrentPos(point4);
    }

    /*private String GetName(int playerNumber) {
        if (playerNumber == 1) {
            return name1;
        } else if (playerNumber == 2) {
            return name2;
        } else if (playerNumber == 3) {
            return name3;
        } else if (playerNumber == 4) {
            return name4;
        } else {
            return null;
        }
    }*/

    // Return method for ball
    public Ball getBall() {
        return this.ball;
    }

    // Return methods for players
    public Player getPlayer1() {
        return this.player1;
    }
    public Player getPlayer2() {
        return this.player2;
    }
    public Player getPlayer3() {
        return this.player3;
    }
    public Player getPlayer4() {
        return this.player4;
    }
}
