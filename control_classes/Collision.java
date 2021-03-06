package control_classes;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model_classes.*;

import java.util.ArrayList;


/**
 * This class is used to determine the new position of the ball when a collision has been detected
 * This class is an abstract class with no contructor and simply methods
 * Created by niles on 24/03/2017.
 */
public class Collision {

    // Declaring the arraylist of game objects
    private ArrayList<gameObject> disposable = new ArrayList<gameObject>();
    private ArrayList<gameObject> paddleArray;
    Collision(ArrayList<gameObject> paddleArray){
        this.paddleArray = paddleArray;
    }

    /**
     * checkCollisions will take in a number of parameters to determine if a collision has occured on the ball
     * @param ball - the ball object in the game; Ball
     * @param shape - the shape representation of the game of the game; Shape
     * @param root - the group of shapes contained in the scene; Group
     * @param gameArray - an Arraylist of gameObjects in the game; Arraylist
     * @param pos -
     * @param sounds - the sounds used in the game for when a collision does occur; GameSounds
     * @param players - an Arraylist of the players; Arraylist
     * @return Returns a a set of locational and positional values so that if a collision does occur it does not need to check where the ball needs to be again; CollisionStruct
     */
    public CollisionStruct checkCollisions(gameObject ball, gameObject shape, Group root, ArrayList<gameObject> gameArray, int pos, GameSounds sounds, ArrayList<Player> players) {

        double xMin;
        double xMax;
        double yMin;
        double yMax;

        if( shape.getObj().isRotated()) {
            //Get coordinates of rectangular shape
            xMin = shape.getObj().getCurrentPos().getX() - ((Ball) ball.getObj()).getBallRadius();
            xMax = shape.getObj().getCurrentPos().getX() + shape.getObj().getHeight() + ((Ball) ball.getObj()).getBallRadius();
            yMin = shape.getObj().getCurrentPos().getY() - ((Ball) ball.getObj()).getBallRadius();
            yMax = shape.getObj().getCurrentPos().getY() + shape.getObj().getLength() + ((Ball) ball.getObj()).getBallRadius();
        } else {
            //Get coordinates of rectangular shape
            xMin = shape.getObj().getCurrentPos().getX() - ((Ball) ball.getObj()).getBallRadius();
            xMax = shape.getObj().getCurrentPos().getX() + shape.getObj().getLength() + ((Ball) ball.getObj()).getBallRadius();
            yMin = shape.getObj().getCurrentPos().getY() - ((Ball) ball.getObj()).getBallRadius();
            yMax = shape.getObj().getCurrentPos().getY() + shape.getObj().getHeight() + ((Ball) ball.getObj()).getBallRadius();
        }




        //Get previous and current positions of the ball
        Point bPrev = ((Ball) ball.getObj()).getPreviousPos();
        Point bCurr = ((Ball) ball.getObj()).getCurrentPos();

        //Declare the 4 different possible interesction points (four walls: L, R, T, B) and respective collision booleans
        Point firstPoint = null, secondPoint = null, thirdPoint = null, fourthPoint = null, finalPoint = null;
        Boolean wallLeft = false, wallTop = false, wallRight = false, wallBottom = false;

        //Find difference between previous and current positions
        double xDel = Math.abs(bPrev.getX() - bCurr.getX());
        double yDel = Math.abs(bPrev.getY() - bCurr.getY());

        //Find equation of the line between previous and current positions
        double gradient = (double) (bCurr.getY() - bPrev.getY()) / (bCurr.getX() - bPrev.getX());
        double c = bPrev.getY() - (gradient * bPrev.getX());

        //Using math, find intersection for each side of the shape
        if ((yMin <= (gradient * xMin + c)) && ((gradient * xMin + c) < yMax)) { //Checking leftWall
            firstPoint = new Point((int) xMin, (int) (gradient * xMin + c));
            wallLeft = true;
        }
        if ((yMin <= (gradient * xMax + c)) && ((gradient * xMax + c) < yMax)) { //Checking rightWall
            secondPoint = new Point((int) xMax, (int) (gradient * xMax + c));
            wallRight = true;
        }
        if ((xMin <= ((yMin - c) / gradient)) && (((yMin - c) / gradient) < xMax)) { //Checking topWall
            thirdPoint = new Point((int) ((yMin - c) / gradient), (int) yMin);
            wallTop = true;

        }
        if ((xMin <= ((yMax - c) / gradient)) && (((yMax - c) / gradient) < xMax)) { //Checking bottomWall
            fourthPoint = new Point((int) ((yMax - c) / gradient), (int) yMax);
            wallBottom = true;
        }

        //Declare distances from ball for each point
        double r1 = 10000, r2 = 10000, r3 = 10000, r4 = 10000;

        //Checking which point intersects shape first by calculating their distance from the ball
        if (firstPoint != null) {
            r1 = Math.pow((Math.pow(((Ball) ball.getObj()).getCurrentPos().getX() - firstPoint.getX(), 2) + Math.pow(((Ball) ball.getObj()).getCurrentPos().getY() - firstPoint.getY(), 2)), 0.5);
        }
        if (secondPoint != null) {
            r2 = Math.pow((Math.pow(((Ball) ball.getObj()).getCurrentPos().getX() - secondPoint.getX(), 2) + Math.pow(((Ball) ball.getObj()).getCurrentPos().getY() - secondPoint.getY(), 2)), 0.5);
        }
        if (thirdPoint != null) {
            r3 = Math.pow((Math.pow(((Ball) ball.getObj()).getCurrentPos().getX() - thirdPoint.getX(), 2) + Math.pow(((Ball) ball.getObj()).getCurrentPos().getY() - thirdPoint.getY(), 2)), 0.5);
        }
        if (fourthPoint != null) {
            r4 = Math.pow((Math.pow(((Ball) ball.getObj()).getCurrentPos().getX() - fourthPoint.getX(), 2) + Math.pow(((Ball) ball.getObj()).getCurrentPos().getY() - fourthPoint.getY(), 2)), 0.5);
        }

        //Declare arrays that will be used to find the minimum distance
        Point[] pointArray = {firstPoint, secondPoint, thirdPoint, fourthPoint};
        double[] checkingMin = {r1, r2, r3, r4};
        boolean[] wallArray = {wallLeft, wallRight, wallTop, wallBottom};


        //Find index of closest point
        int index = 0;
        double arrayMin = checkingMin[0];
        for (int i = 1; i < checkingMin.length; i++) {
            if (checkingMin[i] < arrayMin) {
                arrayMin = checkingMin[i];
                index = i;
            }
        }

        //Make final intersection point, and set booleans
        for (int i = 0; i < checkingMin.length; i++) {
            if (i != index) {
                wallArray[i] = false;
            } else {
                finalPoint = pointArray[i];
            }
        }

        //Update local booleans
        wallLeft = wallArray[0];
        wallRight = wallArray[1];
        wallTop = wallArray[2];
        wallBottom = wallArray[3];

        //Call function to find new coordinates for the ball
        Point check = this.getDels((Ball) ball.getObj());

        //Get new and current positions
        double newX = check.getX();
        double newY = check.getY();
        double updatePrevX = ((Ball) ball.getObj()).getCurrentPos().getX();
        double updatePrevY = ((Ball) ball.getObj()).getCurrentPos().getY();

        //If finalPoint exists (was previous intersection detected) then make sure it was a collision near object
        if(finalPoint != null) {
            double diffX = Math.abs(finalPoint.getX() - updatePrevX);
            double diffY = Math.abs(finalPoint.getY() - updatePrevY);
            if ((xDel < diffX) && (yDel < diffY)) {
                //if ((diffX <= xDel) && (diffY <= yDel)) {
                finalPoint = null;
            }
        }

        //Check to see if object is a brick, and if it is, remove it from the gameArray and view
        if((finalPoint != null) && (shape.getObj() instanceof Brick)) {
            root.getChildren().remove(shape.getShape());
            ((Brick)shape.getObj()).setRemoved(true);
            this.disposable.add(gameArray.get(pos));
            gameArray.remove(pos);
            sounds.playBrickSE();
        }

        //Check to see if object is a player, and if it is, remove it from the gameArray
        if((finalPoint != null) &&(shape.getObj() instanceof Player)){
            ((Player) shape.getObj()).setAlive(false);
            this.disposable.add(gameArray.get(pos));
            gameArray.remove(pos);
            sounds.playDeathSE();
        }

        //Check to see if object is a paddle, and if it is, play audio
        if((finalPoint != null) &&(shape.getObj() instanceof Paddle)){
            //Tell ball what last hit it
            ((Ball) ball.getObj()).setLastTouch( ((Paddle) shape.getObj()).getPaddleToken());
            sounds.playPaddleSE();
        }

        if((finalPoint != null) &&(shape.getObj() instanceof PowerUps)){
            //Tell ball what last hit it
            root.getChildren().remove(shape.getShape());
            this.disposable.add(gameArray.get(pos));
            gameArray.remove(pos);

            for(int i = 0; i < players.size(); i++) {
                Paddle tempPaddle = players.get(i).getPlayerPaddle();

                //Make all players (except last touch) slow
                if(tempPaddle.getPaddleToken() != ((Ball) ball.getObj()).getLastTouch()) {
                    tempPaddle.setPowerUp(true);
                    tempPaddle.setPower((PowerUps) shape.getObj());
                    tempPaddle.getPower().setTimeOfPU(System.nanoTime());

                    if (((PowerUps) shape.getObj()).getPower() == PowerUps.Power.FREEZE) {
                        tempPaddle.setPaddleSpeed(5);
                    } else if (((PowerUps) shape.getObj()).getPower() == PowerUps.Power.SHRINK) {

                        //Shrink Paddle size
                        for(int a = 0; a < paddleArray.size(); a++) {
                            if(tempPaddle.getID() == ((Paddle) paddleArray.get(a).getObj()).getID()) {
                                gameObject objPaddle =  paddleArray.get(a);

                                Paddle print = ((Paddle) objPaddle.getObj());
                                System.out.println("Player:" + print.getPaddleToken());
                                System.out.println("isRotated:" + print.isRotated());
                                System.out.println("width:" + print.getLength());
                                System.out.println("height:" + print.getHeight());

//                                if(((Paddle)objPaddle.getObj()).isRotated()) {
//                                    ((Rectangle) paddleArray.get(a).getShape()).setHeight(50);
//                                    ((Paddle) objPaddle.getObj()).setHeight(50);
//                                } else {
//                                    ((Rectangle) paddleArray.get(a).getShape()).setWidth(50);
                                    ((Paddle) objPaddle.getObj()).setPaddleSize(50);
//                                }

                                System.out.println("POST: isRotated:" + print.isRotated());
                                System.out.println("POST: width:" + print.getLength());
                                System.out.println("POST: height:" + print.getHeight());
                                System.out.println("");


                            }
                        }


                    } else if (((PowerUps) shape.getObj()).getPower() == PowerUps.Power.INVIS) {
                        for(int a = 0; a < paddleArray.size(); a++) {
                            if(tempPaddle.getID() == ((Paddle) paddleArray.get(a).getObj()).getID()) {
                                gameObject objPaddle =  paddleArray.get(a);
                                    ((Rectangle) paddleArray.get(a).getShape()).setFill(Color.TRANSPARENT);
                            }
                        }
                    }

                }
            }

            sounds.playBrickSE();
            finalPoint = null;
        }

        //Prepare output
        double[] newValues = {newX, newY};
        double[] prevValues = {updatePrevX, updatePrevY};
        CollisionStruct output = new CollisionStruct(finalPoint, wallArray, newValues);
        return output;
    }


    /**
     * getDels essentially finds the next point the ball will travel assuiming no collisions
     * @param currentBall
     * @return Point
     */
    protected Point getDels(Ball currentBall) {
        //TODO: change so angle can be changed randomly
        // to the current position and previous position

        Point previousPos = currentBall.getPreviousPos();
        Point currentPos = currentBall.getCurrentPos();
        double ballAngle;
        double ballSpeed = currentBall.getBallSpeed();

        //Find changes in x and y
        double xDel = Math.abs(previousPos.getX() - currentPos.getX());
        double yDel = Math.abs(previousPos.getY() - currentPos.getY());

        double hypot = Math.pow((Math.pow(xDel, 2) + Math.pow(yDel, 2)), 0.5);
        ballAngle = Math.atan(xDel / hypot);


        double newX, newY;
        // Determine direction X
        if ((previousPos.getX() - currentPos.getX()) > 0) {
            // Moving to the left
            newX = currentPos.getX() - Math.cos(ballAngle) * ballSpeed;
        } else if ((previousPos.getX() - currentPos.getX()) < 0) {
            // Moving to the right
            newX = Math.cos(ballAngle) * ballSpeed + currentPos.getX();
        } else {
            newX = currentPos.getX();
        }

        // Determine direction Y
        if ((previousPos.getY() - currentPos.getY()) > 0) {
            // Moving up
            newY = currentPos.getY() - Math.sin(ballAngle) * ballSpeed;
        } else if ((previousPos.getY() - currentPos.getY()) < 0) {
            newY = Math.sin(ballAngle) * ballSpeed + currentPos.getY();
        } else {
            newY = currentPos.getY();
        }

        //Create variables to update previous positions
        double updatePrevX = currentPos.getX();
        double updatePrevY = currentPos.getY();

        Point newValues = new Point((int)newX, (int)newY);
        return newValues;
    }

    /**
     * getDisposables gets the disposable arraylist
     * @return Returns the disposable list; Arraylist
     */
    public ArrayList<gameObject> getDisposable() {
        return this.disposable;
    }
}
