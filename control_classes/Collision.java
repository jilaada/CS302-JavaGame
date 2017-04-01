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

    public void checkCollisions(gameObject ball, gameObject shape) {
        if (ball.getShape().getBoundsInParent().intersects(shape.getShape().getBoundsInParent())) {

            //POSSIBLE POINTER ERROR STUFF
//            double xMin = shape.getShape().getLayoutBounds().getMinX();
//            double xMax = shape.getShape().getLayoutBounds().getMaxX();
//            double yMin = shape.getShape().getLayoutBounds().getMinY();
//            double yMax = shape.getShape().getLayoutBounds().getMaxY();

            double xMin = ((Paddle)shape.getObj()).getPreviousPos().getX();
            double xMax = ((Paddle)shape.getObj()).getPreviousPos().getX();
            double yMin = ((Paddle)shape.getObj()).getPreviousPos().getY();
            double yMax = ((Paddle)shape.getObj()).getPreviousPos().getY();

            Point bPrev = ((Ball) ball.getObj()).getPreviousPos();
            Point bCurr = ((Ball) ball.getObj()).getCurrentPos();
            Point firstPoint = null;
            Point secondPoint = null;
            Point finalPoint = null;

            System.out.print("SHAPE (xMin, xMax, yMin,yMax):");
            System.out.print(xMin);
            System.out.print(",");
            System.out.print(xMax);
            System.out.print(",");
            System.out.print(yMin);
            System.out.print(",");
            System.out.print(yMax);
            System.out.print("\n");

            System.out.print("PREVIOUS:");
            System.out.print(bPrev.getX());
            System.out.print(",");
            System.out.print(bPrev.getY());
            System.out.print("\n");
            System.out.print("Current:");
            System.out.print(bCurr.getX());
            System.out.print(",");
            System.out.print(bCurr.getY());
            System.out.print("\n");
            System.out.print("SHAPE:");
            //System.out.print(((Circle) ball.getShape()).getCenterX());
            double xCoord = ((Circle) ball.getShape()).getLayoutBounds().getMaxX() - ((Circle) ball.getShape()).getLayoutBounds().getMinX();
            double yCoord = ((Circle) ball.getShape()).getLayoutBounds().getMaxY() - ((Circle) ball.getShape()).getLayoutBounds().getMinY();
           // System.out.print(xCoord + ((Circle) ball.getShape()).getLayoutBounds().getMinX());
            //System.out.print(((Rectangle) ball.getShape()).getLocation().getX());
            System.out.print(((Rectangle) shape.getShape()).getX());
            System.out.print(",");
            //System.out.print(((Circle) ball.getShape()).getCenterY());
            //System.out.print(yCoord + ((Circle) ball.getShape()).getLayoutBounds().getMinY());
            System.out.print(((Rectangle) shape.getShape()).getY());
            System.out.print("\n");


            double xDel = Math.abs(bPrev.getX() - bCurr.getX());
            double yDel = Math.abs(bPrev.getY() - bCurr.getY());

            double gradient = (bPrev.getY() - bCurr.getY())/(bPrev.getX() - bCurr.getX());

            double c = bPrev.getY() - (gradient * bPrev.getX());
            //double yE = (gradient * ) + c
            Boolean wallLeft = false;
            Boolean wallTop = false;
            Boolean wallRight = false;
            Boolean wallBottom = false;

            //Line equations
            //--algorithim: double xE = ((yMin - c) / gradient);
            if ((yMin <= (gradient * xMin + c)) || ((gradient * xMin + c) < yMax)) {
                firstPoint = new Point((int)xMin, (int)(gradient * xMin + c));
                //firstPoint.setX((int)xMin);
                //firstPoint.setY((int)(gradient * xMin + c));
                wallLeft = true;
            } else if ((yMin <= (gradient * xMax + c)) || ((gradient * xMax + c) < yMax)) {
                firstPoint = new Point((int)xMax, (int)(gradient * xMax + c));
                //firstPoint.setX((int)xMax);
                //firstPoint.setY((int)(gradient * xMin + c));
                wallRight = true;
            }

            if ((xMin <= ((yMin - c) / gradient)) || (((yMin - c) / gradient) < xMax)) {
                secondPoint = new Point((int)((yMin - c) / gradient), (int)yMin);
                //secondPoint.setX((int)((yMin - c) / gradient));
                //secondPoint.setY((int)yMin);
                wallTop = true;

            } else if ((xMin <= ((yMax - c) / gradient)) || (((yMax - c) / gradient) < xMax)) {
                secondPoint = new Point((int)((yMax - c) / gradient), (int)yMax);
                //secondPoint.setX((int)((yMax - c) / gradient));
                //secondPoint.setY((int)yMax);
                wallBottom = true;
            }

            //Checking which point intersects shape first
            if((firstPoint != null) && (secondPoint != null)) {
               // ball.getShape().getCenterX();

                double r1 = Math.pow((Math.pow(((Circle)ball.getShape()).getCenterX() - firstPoint.getX(), 2) + Math.pow(((Circle)ball.getShape()).getCenterY() - firstPoint.getY(), 2)), 0.5);
                double r2 = Math.pow((Math.pow(((Circle)ball.getShape()).getCenterX() - secondPoint.getX(), 2) + Math.pow(((Circle)ball.getShape()).getCenterY() - secondPoint.getY(), 2)), 0.5);

                if(r1 < r2) {
                    finalPoint = firstPoint;
                    wallBottom = false;
                    wallTop = false;
                } else {
                    finalPoint = secondPoint;
                    wallLeft = false;
                    wallRight = false;
                }
            } else if((firstPoint != null) || (secondPoint != null)) {
                if(firstPoint != null) {
                    finalPoint = firstPoint;
                } else {
                    finalPoint = secondPoint;
                }
            }

//Make sure equation is within bounds
            System.out.print("T,R,B,L: ");
            System.out.print(wallTop);
            System.out.print(wallRight);
            System.out.print(wallBottom);
            System.out.print(wallLeft);
            System.out.print("\n");


            Point check = this.getDels((Ball) ball.getObj());
            double newX = check.getX();
            double newY = check.getY();
            double updatePrevX = ((Ball)ball.getObj()).getCurrentPos().getX();
            double updatePrevY = ((Ball)ball.getObj()).getCurrentPos().getY();

            if ((newX > finalPoint.getX()) && (wallLeft = true)){ //(newX > leftPosWall
                // Change the angle
                newX = finalPoint.getX() - (newX - finalPoint.getX());
                updatePrevX = newX + xDel;
                System.out.println("leftDetected");
            } else if ((newX < finalPoint.getX()) && (wallRight = true)) { //(newX > rightPosWall
                newX = finalPoint.getX() + (finalPoint.getX() - newX);
                updatePrevX = newX - xDel;
                System.out.println("rightDetected");
            }

            if ((newY > finalPoint.getY()) && (wallBottom = true)) { ////top
                newY = finalPoint.getY() - (newY - finalPoint.getY());
                updatePrevY = newY + yDel;
                System.out.println("topDetected");
            } else if ((newY < finalPoint.getY()) && (wallTop = true)) { //(newX > leftPosWall
                newY = finalPoint.getY() + (finalPoint.getY() - newY);
                updatePrevY = newY - yDel;
                System.out.println("bottom  Detected");
            }

            System.out.println("\n");

            //Update previous coordinates
            bPrev.setX((int)updatePrevX);
            bPrev.setY((int)updatePrevY);

            // Set the new point with the new x and y coordinate
            bCurr.setX((int)Math.round(newX));
            bCurr.setY((int)Math.round(newY));
        } else {
            Point check = this.getDels((Ball)ball.getObj());
            double newX = check.getX();
            double newY = check.getY();
            double updatePrevX = ((Ball)ball.getObj()).getCurrentPos().getX();
            double updatePrevY = ((Ball)ball.getObj()).getCurrentPos().getY();

            double xDel = Math.abs(((Ball)ball.getObj()).getPreviousPos().getX() - updatePrevX);
            double yDel = Math.abs(((Ball)ball.getObj()).getPreviousPos().getY() - updatePrevY);

            if (newX > 1024) {
                newX = 1024 - (newX - 1024);
                updatePrevX = newX + xDel;
            } else if (newX < 0) {
                newX = Math.abs(newX);
                updatePrevX = newX - xDel;
            }

            if (newY > 768) {
                newY = 768 - (newY - 768);
                updatePrevY = newY + yDel;
            } else if (newY < 0) {
                newY = Math.abs(newY);
                updatePrevY = newY - yDel;
            }

            //Update previous coordinates
            ((Ball)ball.getObj()).getPreviousPos().setX((int)updatePrevX);
            ((Ball)ball.getObj()).getPreviousPos().setY((int)updatePrevY);

            // Set the new point with the new x and y coordinate
            ((Ball)ball.getObj()).getCurrentPos().setX((int)Math.round(newX));
            ((Ball)ball.getObj()).getCurrentPos().setY((int)Math.round(newY));


        }
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


//    public static boolean checkCollisions(Circle circle, Shape shape) {
//        if (circle.getBoundsInParent().intersects(shape.getBoundsInParent())) {
//            //shape.setFill(Color.YELLOW);
//            return true;
//        }
//        return false;
//    }
}
