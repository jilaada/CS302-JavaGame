package model_classes;

import java.lang.Math;
import java.util.Random;

/**
 * This class was created to model the properties of the ball such as location, speed and size.
 */
public class Ball extends modelSuperClass{
	// Declaring the private variables for the ball
	private Point currentPos;
	private Point previousPos;
	private Point nextPos;
	private Player lastTouch;
	private double ballSpeed;
	private double ballRad;
	private double ballArea;
	private double ballAngle;
	private boolean moved;

	/**
	 * The constructor for the ball
	 * @param speed - magnitude speed of the ball; double
	 * @param rad - the radius of the ball; double
	 */
	public Ball(double speed, double rad) {
		this.ballSpeed = speed;
		this.ballRad = rad;
		// Setting random velocity direction
		Random c = new Random();
		int x = c.nextInt(324) + 350;
		int y = c.nextInt(268) + 250;
		this.previousPos = new Point(x, y);
		this.currentPos = new Point(512, 384);
		this.moved = false;
	}

	/**
	 * setCurrentPos is a function that will set the current position of the ball to a new Point class
	 * @param pos - new current position of the ball; Point
	 */
	public void setCurrentPos(Point pos) {
		this.currentPos = pos;
	}

	/**
	 * getCurrentPos is a function that will get the current position of the ball if it has one
	 * @return Returns the current position of the ball; Point
	 */
	public Point getCurrentPos() {
		if(this.currentPos == null) {
			return null;
		} else {
			return this.currentPos;
		}
	}

	/**
	 * setPreviousPos is a function that will set the previous position of the ball to a new Point class
	 * @param pos - new previous position of the ball; Point
	 */
	public void setPreviousPos(Point pos) {
		this.previousPos = pos;
	}

	/**
	 * getPreviousPos is a function that will get the previous position of the ball is it has one
	 * @return Returns the previous position of the ball; Point
	 */
	public Point getPreviousPos() {
		if(this.previousPos == null) {
			return null;
		} else {
			return this.previousPos;
		}
	}

	/**
	 * setNextPos is a function that will set the next position of the ball to a new Point class
	 * @param pos - new next position of the ball; Point
	 */
	public void setNextPos(Point pos) {
		this.nextPos = pos;
	}

	/**
	 * getNextPos is a function that will get the next position of the ball is it has one
	 * @return Returns the next position of the ball; Point
	 */
	public Point getNextPos() {
		if(this.nextPos == null) {
			return null;
		} else {
			return this.nextPos;
		}
	}

	/**
	 * setBallSpeed sets the speed of the ball
	 * @param speed - the speed of the ball; double
	 */
	public void setBallSpeed(double speed) {
		this.ballSpeed = speed;
	}

	/**
	 * getBallSpeed gets the speed of the ball
	 * @return Returns the speed of the ball; double
	 */
	public double getBallSpeed() {
		return this.ballSpeed;
	}

	/**
	 * setBallRadius sets the ball radius
	 * @param rad - the radius of the ball; double
	 */
	public void setBallRadius(double rad) {
		this.ballRad = rad;
		this.ballArea = 0.5*(Math.PI * Math.pow(rad,2));
	}

	/**
	 * getBallRadius gets the radius of the ball
	 * @return Returns the radius of the ball; double
	 */
	public double getBallRadius() {
		return this.ballRad;
	}

	/**
	 * setBallArea sets the area of the ball
	 * @param area - the area of the ball; double
	 */
	public void setBallArea(double area) {
		this.ballArea = area;
	}

	/**
	 * getBallArea gets the area of the ball
	 * @return Returns the area of the ball; double
	 */
	public double getBallArea() {
		return this.ballArea;
	}

	/**
	 * setBallAngle sets the angle of the ball
	 * @param angle - the angle of the ball; double
	 */
	public void setBallAngle(double angle) {
		this.ballAngle = angle;
	}

	/**
	 * getBallAngle gets the angle of the ball
	 * @return Returns the angle of the ball; double
	 */
	public double getBallAngle() {
		return this.ballAngle;
	}

	/**
	 * getObj overrides the super class method and will return the current object
	 * @return this object; object
	 */
	@Override
	public modelSuperClass getObj() { return this;}

	/**
	 * hasMoved returns true if the ball has moved
	 * @return Returns moved as true if movement occurred; boolean
	 */
	public boolean hasMoved() {
		return moved;
	}

	/**
	 * setMoved sets if the ball has moved or not
	 * @param moved - is moved then true; boolean
	 */
	public void setMoved(boolean moved) {
		this.moved = moved;
	}
}
