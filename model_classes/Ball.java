package model_classes;

public class Ball {

	private Point currentPos;
	private Point previousPos;
	private Point nextPos;
	private double ballSpeed;
	private double ballSize;
	
	
	
	public Ball(double speed, double size) {
		
		//Add other variable constructors
		this.ballSpeed = speed;
		this.ballSize = size;
	}

	
	public void setCurrentPos(Point pos) {
		this.currentPos = pos;
	}
	
	public Point getCurrentPos() {
		return this.currentPos;
	}
	
	public void setPreviousPos(Point pos) {
		this.previousPos = pos;
	}
	
	public Point getPreviousPos() {
		return this.previousPos;
	}
	
	public void setNextPos(Point pos) {
		this.nextPos = pos;
	}
	
	public Point getNextPos() {
		return this.nextPos;
	}
	
	public void setBallSpeed(double speed) {
		this.ballSpeed = speed;
	}
	
	public double getBallSpeed() {
		return this.ballSpeed;
	}
	
	public void setBallSize(double size) {
		this.ballSize = size;
	}
	
	public double getPaddleSize() {
		return this.ballSize;
	}
	
	public boolean checkForCollision() {
		//TO DO:
	}
	}
	
}
