package control_classes;

import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.shape.Shape;
import model_classes.*;


/**
 * Created by niles on 24/03/2017.
 */
public class Collision {
    //TODO: This class is a controller and will control collisions
    Collision(){}

    public CollisionStruct checkCollisions(gameObject ball, gameObject shape) {

            //Get coordinates of rectangular shape
            double xMin = ((Paddle) shape.getObj()).getCurrentPos().getX();
            double xMax = ((Paddle) shape.getObj()).getCurrentPos().getX() + ((Paddle) shape.getObj()).getPaddleSize();
            double yMin = ((Paddle) shape.getObj()).getCurrentPos().getY();
            double yMax = ((Paddle) shape.getObj()).getCurrentPos().getY() + ((Paddle) shape.getObj()).getHeight();

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
            for (int i = 1; i < checkingMin.length; i++) {
                if (checkingMin[i - 1] > checkingMin[i]) {
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
                    finalPoint = null;
                }
            }

            //Prepare output
            double[] newValues = {newX, newY};
            double[] prevValues = {updatePrevX, updatePrevY};
            CollisionStruct output = new CollisionStruct(finalPoint, wallArray, newValues);
            return output;
    }


    private Point getDels(Ball currentBall) {
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
}
