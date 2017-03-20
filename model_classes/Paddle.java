package model_classes;

public class Paddle {
	
	private Point currentPos;
	private Point previousPos;
	private double paddleSpeed;
	private double paddleSize;
	
	public Paddle(double speed, double size) {
		this.paddleSpeed = speed;
		this.paddleSize = size;
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
	
	public void setPaddleSpeed(double speed) {
		this.paddleSpeed = speed;
	}
	
	public double getPaddleSpeed() {
		return this.paddleSpeed;
	}
	
	public void setPaddleSize(double size) {
		this.paddleSize = size;
	}
	
	public double getPaddleSize() {
		return this.paddleSize;
	}
	
}
