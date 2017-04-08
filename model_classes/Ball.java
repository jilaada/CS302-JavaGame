package model_classes;

import java.lang.Math;
import java.util.Random;

public class Ball extends modelSuperClass{

	private Point currentPos;
	private Point previousPos;
	private Point nextPos;
	private Player lastTouch;
	private double ballSpeed;
	private double ballRad;
	private double ballArea;
	private double ballAngle;
	private boolean moved;
	
	public Ball(double speed, double rad) {
		this.ballSpeed = speed;
		this.ballRad = rad;
		Random c = new Random();
		int x = c.nextInt(324) + 350;
		int y = c.nextInt(268) + 250;
		this.previousPos = new Point(x, y);
		this.currentPos = new Point(512, 384);
		this.moved = false;
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

	@Override
	public modelSuperClass getObj() { return this;}


	public boolean hasMoved() {
		return moved;
	}

	public void setMoved(boolean moved) {
		this.moved = moved;
	}
}
