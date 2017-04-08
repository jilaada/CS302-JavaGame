package control_classes;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model_classes.*;

import java.util.ArrayList;
import java.util.Random;

/**
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

    public GameSetUp() {
        //TODO: Add names to the players
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


//        gameArray.addAll(this.wallSet1);
//        gameArray.addAll(this.wallSet2);
//        gameArray.addAll(this.wallSet3);
//        gameArray.addAll(this.wallSet4);

//        this.gamePlayers.add(new gameObject(player1));
//        this.gamePlayers.add(new gameObject(player2));
//        this.gamePlayers.add(new gameObject(player3));
//        this.gamePlayers.add(new gameObject(player4));
//        gameArray.addAll(gamePlayers);


    }

    public void SetUpPlayers() {
        //TODO: Detect the number of players
//        Player player1 = new Player("Test1");
//        this.player1 = player1;
//        Player player2 = new Player("Test2");
//        this.player2 = player2;
//        Player player3 = new Player("Test3");
//        this.player3 = player3;
//        Player player4 = new Player("Test4");
//        this.player4 = player4;
    }

    public void SetUpBall(int speed, int rad) {
        ball = new Ball(speed, rad);
    }

    public void SetUpPaddles(double speed, double size, double height) {
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

    public void SetUpWall(Group root, ArrayList<gameObject> gameArray) {
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

        int count;
        Random c = new Random();

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
                    //TODO: fix the game object bug
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
                    //TODO: fix the game object bug
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
                    //TODO: fix the game object bug
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
                    //TODO: fix the game object bug
                    this.wallSet4.add(new gameObject(w4, b4));
                    player4Wall.addBrick(b4);
                    root.getChildren().add(w4);
                }
            }
        }

        player1.addPlayerWall(player1Wall);
        player2.addPlayerWall(player2Wall);
        player3.addPlayerWall(player3Wall);
        player4.addPlayerWall(player4Wall);
        gameArray.addAll(this.wallSet1);
        gameArray.addAll(this.wallSet2);
        gameArray.addAll(this.wallSet3);
        gameArray.addAll(this.wallSet4);

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

    public int getNumPlayers() {
        return numPlayers;
    }

    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    public ArrayList<Player> getPlayers() {
        return this.players;
    }

}
