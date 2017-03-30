package model_classes;

import java.lang.Math;

public class Ball {

	private Point currentPos;
	private Point previousPos;
	private Point nextPos;
	private Player lastTouch;
	private double ballSpeed;
	private double ballRad;
	private double ballArea;
	private double ballAngle;
	
	public Ball(double speed, double rad) {
		this.ballSpeed = speed;
		this.ballRad = rad;
		this.previousPos = new Point(300,300);
		this.currentPos = new Point(299, 301);
	}

	
	public void setCurrentPos(Point pos) {
		this.currentPos = pos;
	}
	
	public Point getCurrentPos() {
		if(this.currentPos == null) {
			return null;
		} else {
			return this.currentPos;
		}
	}
	
	public void setPreviousPos(Point pos) {
		this.previousPos = pos;
	}
	
	public Point getPreviousPos() {
		if(this.previousPos == null) {
			return null;
		} else {
			return this.previousPos;
		}
	}
	
	public void setNextPos(Point pos) {
		this.nextPos = pos;
	}
	
	public Point getNextPos() {
		if(this.nextPos == null) {
			return null;
		} else {
			return this.nextPos;
		}
	}
	
	public void setBallSpeed(double speed) {
		this.ballSpeed = speed;
	}
	
	public double getBallSpeed() {
		return this.ballSpeed;
	}
	
	public void setBallRadius(double rad) {
		this.ballRad = rad;
		this.ballArea = 0.5*(Math.PI * Math.pow(rad,2));
	}
	
	public double getBallRadius() {
		return this.ballRad;
	}
	
	public void setBallArea(double area) {
		this.ballArea = area;
	}
	
	public double getBallArea() {
		return this.ballArea;
	}
	
	public void setBallAngle(double angle) {
		this.ballAngle = angle;
	}

	public double getBallAngle() {
		return this.ballAngle;
	}

}
