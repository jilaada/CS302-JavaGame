package model_classes;

/**
 * Created by nilesh on 02/04/2017.
 * This class was created so that when the collisionDetection algorithm is finished, if there is a collision
 * then the moveBall function can use existing values instead of having to perform similar operations
 */
public class CollisionStruct {
    private Point finalPoint; //This is just the point of collision
    private boolean[] wallHit; //This is an array of booleans describing which part of the object was hit
    private double[] newValues; //This contains the new X and Y coordinates the ball will travel to

    /**
     * Constructor for the Collisionstruct class
     * @param finalPoint - point of collision; Point
     * @param wallHit - array of booleans describing which part of the object was hit; booleans[]
     * @param newValues - contains the new X and Y coordinates the ball will travel to; double[]
     */
    public CollisionStruct(Point finalPoint, boolean[] wallHit, double[] newValues) {
        this.finalPoint = finalPoint;
        this.wallHit = wallHit;
        this.newValues = newValues;
    }

    /**
     * getfinalPoint gets the final point of the ball
     * @return Returns the final point of the ball; Point
     */
    public Point getFinalPoint() {
        return finalPoint;
    }

    /**
     * setFinalPoint will set the final point of the ball
     * @param finalPoint - the final point of collision; Point
     */
    public void setFinalPoint(Point finalPoint) {
        this.finalPoint = finalPoint;
    }

    /**
     * getWallHit gets the array of booleans describing which part of the object was hit
     * @return Returns an array of booleans describing which part of the object was hit; boolean[]
     */
    public boolean[] getWallHit() {
        return wallHit;
    }

    /**
     * setWallHit sets the array of booleans describing which part of the object was hit
     * @param wallHit - array of booleans describing which part of the object was hit; boolean[]
     */
    public void setWallHit(boolean[] wallHit) {
        this.wallHit = wallHit;
    }

    /**
     * getNewValues gets the new X and Y coordinates the ball will travel to
     * @return Returns the the new X and Y coordinates the ball will travel to; double[]
     */
    public double[] getNewValues() {
        return newValues;
    }

    /**
     * setNewValues sets the new X and Y coordinates the ball will travel to
     * @param newValues - the new X and Y coordinates the ball will travel to; double[]
     */
    public void setNewValues(double[] newValues) {
        this.newValues = newValues;
    }
}
