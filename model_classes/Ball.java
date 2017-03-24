package model_classes;
//import java.lang.Math;
//import.System;
import java.lang.Math;

public class Ball {

	private Point currentPos;
	private Point previousPos;
	private Point nextPos;
	private double ballSpeed;
	private double ballRad;
	private double ballArea;
	private double ballAngle;
	
	public Ball(double speed, double rad) {
		this.ballSpeed = speed;
		this.ballRad = rad;
		this.previousPos = new Point(300,300);
		this.currentPos = new Point(301,301);
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
	
	public boolean checkForCollision() {
		return true; //MUST CHANGE
	}

	public void moveBall() {
		//TODO: moveBall is a function that will move the ball to the next position with respect 
		// to the current position and previous position

		//Find changes in x and y
		double xDel = Math.abs(previousPos.getX() - currentPos.getX());
		double yDel = Math.abs(previousPos.getY() - currentPos.getY());

		double hypot = Math.pow((Math.pow(xDel, 2) + Math.pow(yDel, 2)), 0.5);
		this.ballAngle = Math.atan(xDel/hypot);



		double newX, newY;
		// Determine direction X
		if ((previousPos.getX() - currentPos.getX()) > 0) {
			// Moving to the left
			newX = this.currentPos.getX() - Math.cos(ballAngle)*ballSpeed;
		} else if ((previousPos.getX() - currentPos.getX()) < 0) {
			// Moving to the right
			newX = Math.cos(ballAngle)*ballSpeed + this.currentPos.getX();
		} else {
			newX = currentPos.getX();
		}
		
		// Determine direction Y
		if ((previousPos.getY() - currentPos.getY()) > 0) {
			// Moving up
			newY = this.currentPos.getY() - Math.sin(ballAngle)*ballSpeed;
		} else if ((previousPos.getY() - currentPos.getY()) < 0) {
			newY = Math.sin(ballAngle)*ballSpeed + this.currentPos.getY();
		} else {
			newY = currentPos.getY();
		}

		//Create variables to update previous positions
		double updatePrevX = this.currentPos.getX();
		double updatePrevY = this.currentPos.getY();


		// Check to see if the new position will collide with the bounds
		// Change the angle
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
		this.previousPos.setX((int)updatePrevX);
		this.previousPos.setY((int)updatePrevY);

		// Set the new point with the new x and y coordinate
		this.currentPos.setX((int)Math.round(newX));
		this.currentPos.setY((int)Math.round(newY));


	}
}
