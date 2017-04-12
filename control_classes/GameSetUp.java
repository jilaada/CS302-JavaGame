package control_classes;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model_classes.*;

import java.util.ArrayList;
import java.util.Random;

/**
 * GameSetUp is a class that creates the main game objects. It creates the players, paddles, bricks and walls as well as the ball
 * Created by Jilada on 5/04/17.
 */
public class GameSetUp {
    // This class will set up the game objects and bring to render the view
    // This class will store all the game objects to be parsed during the game
    private Player player1, player2, player3, player4;
    //Player ArrayList
    private ArrayList<Player> players;
    //private String name1, name2, name3, name4;
    private int numPlayers;
    private Ball ball;
    private Paddle paddle1, paddle2, paddle3, paddle4;
    private ArrayList<gameObject> wallSet1, wallSet2, wallSet3, wallSet4;

    /**
     * Constructor for the GameSetUp class
     */
    public GameSetUp() {
        // Declaring the positionas and the players in the game
        Point p1 = new Point(87, 37);
        Point p2 = new Point(886, 37);
        Point p3 = new Point(87, 684);
        Point p4 = new Point(886, 684);
        player1 = new Player("Test1", p1, 50, 50);
        player2 = new Player("Test2", p2, 50, 50);
        player3 = new Player("Test3", p3, 50, 50);
        player4 = new Player("Test4", p4, 50, 50);
        numPlayers = 4;
        players = new ArrayList<Player>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);
    }

    /**
     * setUpBall is a function that will determine construction of the new ball
     * @param speed - the speed of the ball; int
     * @param rad - the radius of the ball; int
     */
    public void setUpBall(int speed, int rad) {
        ball = new Ball(speed, rad);
    }

    /**
     * setUpPaddles is a method that reates the paddles in the game for each player
     * @param speed - the speed of the paddles; double
     * @param size - the horizontal width of the paddle; double
     * @param height - the horizontal height of the paddle;
     */
    public void setUpPaddles(double speed, double size, double height) {
        // Instantiate all the paddles
        Paddle paddle1 = new Paddle(speed, size, height, 1);
        this.paddle1 = paddle1;
        Paddle paddle2 = new Paddle(speed, size, height, 2);
        this.paddle2 = paddle2;
        Paddle paddle3 = new Paddle(speed, size, height, 3);
        this.paddle3 = paddle3;
        Paddle paddle4 = new Paddle(speed, size, height, 4);
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
        Point point3 = new Point(150,(int)(518 - height));
        Point point4 = new Point(850,(int)(518 - height));

        // Add points to current position
        paddle1.setCurrentPos(point1);
        paddle2.setCurrentPos(point2);
        paddle3.setCurrentPos(point3);
        paddle4.setCurrentPos(point4);
    }

    /**
     * setUpWalls is a method that sets up the walls in the game
     * @param root - the group that belongs to the scene; Group
     * @param gameArray - an array that the bricks will be contained in;
     */
    public void setUpWall(Group root, ArrayList<gameObject> gameArray) {
        //Declaring the walls for each player so the bricks will go in them
        Wall player1Wall = new Wall();
        Wall player2Wall = new Wall();
        Wall player3Wall = new Wall();
        Wall player4Wall = new Wall();
        this.wallSet1 = new ArrayList<gameObject>();
        this.wallSet2 = new ArrayList<gameObject>();
        this.wallSet3 = new ArrayList<gameObject>();
        this.wallSet4 = new ArrayList<gameObject>();

        //Declaring the images to be used
        Image imgp11 = new Image("/images/boxBlue1.png");
        Image imgp12 = new Image("/images/boxBlue2.png");
        Image imgp13 = new Image("/images/boxBlue3.png");
        Image imgp21 = new Image("/images/boxOrange1.png");
        Image imgp22 = new Image("/images/boxOrange2.png");
        Image imgp23 = new Image("/images/boxOrange3.png");
        Image imgp31 = new Image("/images/boxGreen1.png");
        Image imgp32 = new Image("/images/boxGreen2.png");
        Image imgp33 = new Image("/images/boxGreen3.png");
        Image imgp41 = new Image("/images/boxWhite1.png");
        Image imgp42 = new Image("/images/boxWhite2.png");
        Image imgp43 = new Image("/images/boxWhite3.png");

        //Introducing random parameter
        int count;
        Random c = new Random();

        // For each player add the walls to the game determined by a randomised image
        for (int i = 0; i < 325; i+=25) {
            for (int j = 0; j < 225; j+=25) {
                if (!((i < 225) && (j < 125))) {
                    Rectangle w1 = new Rectangle(i,j,25,25);
                    Point p1 = new Point(i, j);
                    Brick b1 = new Brick(25, 25, p1);
                    b1.addSprite(w1);
                    count = c.nextInt(3);
                    switch (count) {
                        case 0 :    w1.setFill(new ImagePattern(imgp11));
                                    break;
                        case 1 :    w1.setFill(new ImagePattern(imgp12));
                                    break;
                        case 2 :    w1.setFill(new ImagePattern(imgp13));
                                    break;
                        default:    w1.setFill(new ImagePattern(imgp11));
                                    break;
                    }
                    // Add to game array
                    this.wallSet1.add(new gameObject(w1, b1));
                    // Add to the wall array list
                    player1Wall.addBrick(b1);
                    root.getChildren().add(w1);
                }
            }
        }

        for (int i = 699; i < 1024; i+=25) {
            for (int j = 0; j < 225; j+=25) {
                if (!((i > 777) && (j < 124))) {
                    Rectangle w2 = new Rectangle(i,j,25,25);
                    Brick b2 = new Brick(25, 25, new Point(i, j));
                    b2.addSprite(w2);
                    count = c.nextInt(3);
                    switch (count) {
                        case 0 :    w2.setFill(new ImagePattern(imgp21));
                            break;
                        case 1 :    w2.setFill(new ImagePattern(imgp22));
                            break;
                        case 2 :    w2.setFill(new ImagePattern(imgp23));
                            break;
                        default:    w2.setFill(new ImagePattern(imgp21));
                            break;
                    }
                    // Add to game array
                    this.wallSet2.add(new gameObject(w2, b2));
                    player2Wall.addBrick(b2);
                    root.getChildren().add(w2);
                }
            }
        }

        for (int i = 0; i < 325; i+=25) {
            for (int j = 543; j < 768; j+=25) {
                if (!((i < 225) && (j > 618))) {
                    Rectangle w3 = new Rectangle(i,j,25,25);
                    Brick b3 = new Brick(25, 25, new Point(i, j));
                    b3.addSprite(w3);
                    count = c.nextInt(3);
                    switch (count) {
                        case 0 :    w3.setFill(new ImagePattern(imgp31));
                            break;
                        case 1 :    w3.setFill(new ImagePattern(imgp32));
                            break;
                        case 2 :    w3.setFill(new ImagePattern(imgp33));
                            break;
                        default:    w3.setFill(new ImagePattern(imgp31));
                            break;
                    }
                    // Add to game array
                    this.wallSet3.add(new gameObject(w3, b3));
                    player3Wall.addBrick(b3);
                    root.getChildren().add(w3);
                }
            }
        }

        for (int i = 699; i < 1024; i+=25) {
            for (int j = 543; j < 768; j+=25) {
                if (!((i > 777) && (j > 618))) {
                    Rectangle w4 = new Rectangle(i,j,25,25);
                    Brick b4 = new Brick(25, 25, new Point(i, j));
                    b4.addSprite(w4);
                    count = c.nextInt(3);
                    switch (count) {
                        case 0 :    w4.setFill(new ImagePattern(imgp41));
                            break;
                        case 1 :    w4.setFill(new ImagePattern(imgp42));
                            break;
                        case 2 :    w4.setFill(new ImagePattern(imgp43));
                            break;
                        default:    w4.setFill(new ImagePattern(imgp41));
                            break;
                    }
                    // Add to game array
                    this.wallSet4.add(new gameObject(w4, b4));
                    player4Wall.addBrick(b4);
                    root.getChildren().add(w4);
                }
            }
        }

        // Add the walls to the player and the gameArray
        player1.addPlayerWall(player1Wall);
        player2.addPlayerWall(player2Wall);
        player3.addPlayerWall(player3Wall);
        player4.addPlayerWall(player4Wall);
        gameArray.addAll(this.wallSet1);
        gameArray.addAll(this.wallSet2);
        gameArray.addAll(this.wallSet3);
        gameArray.addAll(this.wallSet4);

    }

    /**
     * getBall will get the ball of the game
     * @return Returns the ball in the game; Ball
     */
    public Ball getBall() {
        return this.ball;
    }

    /**
     * getPlayer1 will get the player 1 of the game
     * @return Returns player1; Player
     */
    public Player getPlayer1() {
        return this.player1;
    }

    /**
     * getPlayer2 will get the player 2 of the game
     * @return Returns player2; Player
     */
    public Player getPlayer2() {
        return this.player2;
    }

    /**
     * getPlayer3 will get the player 3 of the game
     * @return Returns player3; Player
     */
    public Player getPlayer3() {
        return this.player3;
    }

    /**
     * getPlayer4 will get the player 4 of the game
     * @return Returns player4; Player
     */
    public Player getPlayer4() {
        return this.player4;
    }

    /**
     * getNumPlayers will get the number of players in the game
     * @return Reutrns the number of players; int
     */
    public int getNumPlayers() {
        return numPlayers;
    }

    /**
     * setNumPlayers will set the number of players
     * @param numPlayers - the number of players in the game; int
     */
    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    /**
     * getPlayers will get the Arraylist containing the players in the game
     * @return Returns the Arraylist of players in the game; Arraylist
     */
    public ArrayList<Player> getPlayers() {
        return this.players;
    }
}
