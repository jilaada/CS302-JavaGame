package view_classes;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import model_classes.Ball;
import model_classes.Player;

/**
 * Created by Jilada on 5/04/17.
 */
public class RenderView {
    // This class wil render all the view and determine what to do next with the objects
    private Player player1;
    private Player player2;
    private Player player3;
    private Player player4;
    private Ball ball;
    private Circle c1;
    private Rectangle p1, p2, p3, p4, pl1, pl2, pl3, pl4;


    public RenderView(Player player1, Player player2, Player player3, Player player4, Ball ball) {
        this.ball = ball;
        this.player1 = player1;
        this.player2 = player2;
        this.player3 = player3;
        this.player4 = player4;
    }

    public void SetUpRender() {
        Image imgP1Hori = new Image("/images/paddleH1.png");
        Image imgP2Hori = new Image("/images/paddleH2.png");
        Image imgP3Hori = new Image("/images/paddleH3.png");
        Image imgP4Hori = new Image("/images/paddleH4.png");
        c1 = new Circle(ball.getCurrentPos().getX(), ball.getCurrentPos().getY(), ball.getBallRadius(), Color.RED);
        this.c1.setFill(Color.RED);

        // Added a rectangle to be painted on the scene
        p1 = new Rectangle(player1.getPlayerPaddle().getCurrentPos().getX(), player1.getPlayerPaddle().getCurrentPos().getY(), player1.getPlayerPaddle().getPaddleSize(), player1.getPlayerPaddle().getPaddleHeight());
        p1.setFill(new ImagePattern(imgP1Hori));

        p2 = new Rectangle(player2.getPlayerPaddle().getCurrentPos().getX(), player2.getPlayerPaddle().getCurrentPos().getY(), player2.getPlayerPaddle().getPaddleSize(), player2.getPlayerPaddle().getPaddleHeight());
        p2.setFill(new ImagePattern(imgP2Hori));

        p3 = new Rectangle(player3.getPlayerPaddle().getCurrentPos().getX(), player3.getPlayerPaddle().getCurrentPos().getY(), player3.getPlayerPaddle().getPaddleSize(), player3.getPlayerPaddle().getPaddleHeight());
        p3.setFill(new ImagePattern(imgP3Hori));

        p4 = new Rectangle(player4.getPlayerPaddle().getCurrentPos().getX(), player4.getPlayerPaddle().getCurrentPos().getY(), player4.getPlayerPaddle().getPaddleSize(), player4.getPlayerPaddle().getPaddleHeight());
        p4.setFill(new ImagePattern(imgP4Hori));

        // Render the player's sprite
        pl1 = new Rectangle((double)player1.getXPos(), (double)player1.getYPos(), 50, 50);
        pl2 = new Rectangle((double)player2.getXPos(), (double)player2.getYPos(), 50, 50);
        pl3 = new Rectangle((double)player3.getXPos(), (double)player3.getYPos(), 50, 50);
        pl4 = new Rectangle((double)player4.getXPos(), (double)player4.getYPos(), 50, 50);
        pl1.setFill(Color.DODGERBLUE);
        pl2.setFill(Color.CORAL);
        pl3.setFill(Color.GREENYELLOW);
        pl4.setFill(Color.GHOSTWHITE);

    }

    public void tickRender() {
        // Re-render each object
        c1.relocate((double)ball.getCurrentPos().getX(), (double)ball.getCurrentPos().getY());
        p1.relocate((double)player1.getPlayerPaddle().getCurrentPos().getX(), (double)player1.getPlayerPaddle().getCurrentPos().getY());
        p2.relocate((double)player2.getPlayerPaddle().getCurrentPos().getX(), (double)player2.getPlayerPaddle().getCurrentPos().getY());
        p3.relocate((double)player3.getPlayerPaddle().getCurrentPos().getX(), (double)player3.getPlayerPaddle().getCurrentPos().getY());
        p4.relocate((double)player4.getPlayerPaddle().getCurrentPos().getX(), (double)player4.getPlayerPaddle().getCurrentPos().getY());
    }

    public Circle getBallRender() {
        return c1;
    }

    public Rectangle getP1Render() {
        return p1;
    }

    public Rectangle getP2Render() {
        return p2;
    }

    public Rectangle getP3Render() {
        return p3;
    }

    public Rectangle getP4Render() {
        return p4;
    }

    public Rectangle getPl1Render() {
        return pl1;
    }

    public Rectangle getPl2Render() {
        return pl2;
    }

    public Rectangle getPl3Render() {
        return pl3;
    }

    public Rectangle getPl4Render() {
        return pl4;
    }
}
