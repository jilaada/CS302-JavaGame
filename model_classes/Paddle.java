package model_classes;

public class Paddle {
	
	private Point currentPos;
	private Point previousPos;
	private Point paddleBounds;
	private double paddleSpeed;
	private double paddleSize;
	private int paddleToken;
	
	public Paddle(double speed, double size, int token) {
		this.paddleSpeed = speed;
		this.paddleSize = size;
		this.paddleToken = token;
		
		if (token == 1) {
			paddleBounds.setX(350);
			paddleBounds.setY(250);
		} else if (token == 2) {
			
		}
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
	
	/** movePaddle() should take an input of the direction it is moving.*/
	public void movePaddle() {
		
	}
}
