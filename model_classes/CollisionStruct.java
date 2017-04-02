package model_classes;

/**
 * Created by nilesh on 02/04/2017.
 * This class was created so that when the collisionDetection algorithim is finished, if there is a collision
 * then the moveBall function can use existing values instead of having to perform similar operations
 */
public class CollisionStruct {
    private Point finalPoint; //This is just the point of collision
    private boolean[] wallHit; //This is an array of booleans decribing which part of the object was hit
    private double[] newValues; //This contains the new X and Y coordinates the ball will travel to

    public CollisionStruct(Point finalPoint, boolean[] wallHit, double[] newValues) {
        this.finalPoint = finalPoint;
        this.wallHit = wallHit;
        this.newValues = newValues;
    }

    public Point getFinalPoint() {
        return finalPoint;
    }

    public void setFinalPoint(Point finalPoint) {
        this.finalPoint = finalPoint;
    }

    public boolean[] getWallHit() {
        return wallHit;
    }

    public void setWallHit(boolean[] wallHit) {
        this.wallHit = wallHit;
    }

    public double[] getNewValues() {
        return newValues;
    }

    public void setNewValues(double[] newValues) {
        this.newValues = newValues;
    }
}
