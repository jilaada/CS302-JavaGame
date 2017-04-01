package model_classes;

import java.lang.Math;

/**
 * Ball class will contain data about the ball position, speed and other attributes regarding the ball's role in the game
 */
public class Ball extends modelSuperClass{

	// Declaring the attributes for the ball
	private Point currentPos;
	private Point previousPos;
	private Point nextPos;
	private Player lastTouch;
	private double ballSpeed;
	private double ballRad;
	private double ballArea;
	private double ballAngle;

	// For testing JUNIT
	private int xVel;
	private int yVel;

	/**
	 * Constructor for the ball
	 * @param speed - speed of the ball; double
	 * @param rad - radius of the ball; double
	 */
	public Ball(double speed, double rad) {
		this.ballSpeed = speed;
		this.ballRad = rad;
		this.previousPos = new Point(299,299);
		this.currentPos = new Point(300, 300);
	}

	/**
	 * setCurrentPos will set the current position and set it
	 * @param pos - current pos; Point
	 */
	public void setCurrentPos(Point pos) {
		this.currentPos = pos;
	}

	/**
	 * getCurrentPos will get the currnet position of the ball
	 * @return the position vector; Point
	 */
	public Point getCurrentPos() {
		if(this.currentPos == null) {
			return null;
		} else {
			return this.currentPos;
		}
	}

	/**
	 * setPreviousPos will set a position as the previous point
	 * @param pos - point to add; Point
	 */
	public void setPreviousPos(Point pos) {
		this.previousPos = pos;
	}

	/**
	 * getPreviousPos will get the previous position of the ball
	 * @return the position vector; Point
	 */
	public Point getPreviousPos() {
		if(this.previousPos == null) {
			return null;
		} else {
			return this.previousPos;
		}
	}

	/**
	 * setNextPos will set the next position of the ball
	 * @param pos - point to set as the next current point; Point
	 */
	public void setNextPos(Point pos) {
		this.nextPos = pos;
	}

	/**
	 * getNextPos will get the next position of the ball
	 * @return the position vector; Point
	 */
	public Point getNextPos() {
		if(this.nextPos == null) {
			return null;
		} else {
			return this.nextPos;
		}
	}

	/**
	 * setBallSpeed will set the speed of the ball
	 * @param speed - new speed to be set; double
	 */
	public void setBallSpeed(double speed) {
		this.ballSpeed = speed;
	}

	/**
	 * getBallSpeed will get the speed of the ball
	 * @return the speed of the ball; double
	 */
	public double getBallSpeed() {
		return this.ballSpeed;
	}

	/**
	 * setBallRadius will set the radius of the ball and calculate the new ball area
	 * @param rad - the new radius of the ball; double
	 */
	public void setBallRadius(double rad) {
		this.ballRad = rad;
		this.ballArea = 0.5*(Math.PI * Math.pow(rad,2));
	}

	/**
	 * getBallRadius will get the radius of the ball
	 * @return the ball radius; double
	 */
	public double getBallRadius() {
		return this.ballRad;
	}

	/**
	 * getBallArea will get the area of the ball
	 * @return the ball area; double
	 */
	public double getBallArea() {
		return this.ballArea;
	}

	/**
	 * setBallAngle will set the ball angle
	 * @param angle - the angle to be set; double
	 */
	public void setBallAngle(double angle) {
		this.ballAngle = angle;
	}

	/**
	 * getBallAngle will get the angle the ball is going
	 * @return the ball angle; double
	 */
	public double getBallAngle() {
		return this.ballAngle;
	}

	//TODO: commenting for this method
	/**
	 * The super class object
	 * @return
	 */
	@Override
	public modelSuperClass getObj() { return this;}

	// For testing purposes
	public void setXVel(int x) {
		this.xVel = x;
	}

	public void setYVel(int y) {
		this.yVel = y;
	}

	public int getxVel() {
		return this.xVel;
	}

	public int getyVel() {
		return this.yVel;
	}
}
