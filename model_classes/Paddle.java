package model_classes;

public class Paddle extends modelSuperClass{
	
	private Point currentPos;
	private Point previousPos;
	private Point paddleStart;
	private Point paddleEnd;
	private Point paddleBounds;
	private double paddleSpeed;
	private double paddleSize;
	private int paddleToken;
	private double height;
	private boolean rotated;
	
	/** Constructor of player paddle
	 * The constructor will take three inputs and sets the speed, size and paddle bounds
	 * of the object.
	 * @param speed - sets the speed of the paddle
	 * @param size - sets the size of the paddle
	 * @param token - determines the player number (P1, P2, P3, P4)
	 */
	public Paddle(double speed, double size, double height, int token) {
		this.paddleSpeed = speed;
		this.paddleSize = size;
		this.paddleToken = token;
		this.height = height;
		this.rotated = false;

		previousPos = new Point(0,0);
		currentPos = new Point(0,0);
		paddleStart = new Point(0 ,0);
		paddleEnd = new Point(0, 0);
		paddleBounds = new Point(0,0);
	}

	public void setBounds() {
		int size = (int)paddleSize/2;
		if (this.paddleToken == 1) {
			this.paddleStart.setX(0);
			this.paddleStart.setY(250);
			this.paddleBounds.setX(350);
			this.paddleBounds.setY(250);
			this.paddleEnd.setX(350);
			this.paddleEnd.setY(0);
		} else if (this.paddleToken == 2) {
			this.paddleStart.setX(674 - (int)height);
			this.paddleStart.setY(0);
			this.paddleBounds.setX(674 - (int)height);
			this.paddleBounds.setY(250);
			this.paddleEnd.setX(1024 - (int)paddleSize);
			this.paddleEnd.setY(250);
		} else if (this.paddleToken == 3) {
			this.paddleStart.setX(0);
			this.paddleStart.setY(518 - (int)height);
			this.paddleBounds.setX(350);
			this.paddleBounds.setY(518 - (int)height);
			this.paddleEnd.setX(350);
			this.paddleEnd.setY(768 - (int)paddleSize);
		} else if (this.paddleToken == 4) {
			this.paddleStart.setX(674 - (int)height);
			this.paddleStart.setY(768 - (int)paddleSize);
			this.paddleBounds.setX(674 - (int)height);
			this.paddleBounds.setY(518 - (int)height);
			this.paddleEnd.setX(1024 - (int)paddleSize);
			this.paddleEnd.setY(518 - (int)height);
		}
	}

	/** Sets the current position of the paddle
	 * @param pos - a point class of the new coordinates for the paddle
	 */
	public void setCurrentPos(Point pos) {
		this.currentPos = pos;
	}
	
	/**
	 * Returns a point class containing the current position of the paddle
	 * @return Returns a point class containing the current position of the paddle
	 */
	public Point getCurrentPos() {
		if(this.currentPos == null) {
			return null;
		} else {
			return this.currentPos;
		}
	}
	
	/**
	 * Sets the previous position
	 * @param pos - a point class containing the coordinates of the paddle
	 */
	public void setPreviousPos(Point pos) {
		this.previousPos = pos;
	}
	
	/**
	 * Gets the previous position of the paddle
	 * @return Returns a point class containing the previous position of the paddle
	 */
	public Point getPreviousPos() {
		if(this.previousPos == null) {
			return null;
		} else {
			return this.previousPos;
		}
	}

	/**
	 * Get paddle bound point class
	 * @return returns a point class containing the paddle bounds
	 */
	public Point getPaddleBounds() {
		return paddleBounds;
	}

	public Point getPaddleStart() {
		return paddleStart;
	}

	public Point getPaddleEnd() {
		return paddleEnd;
	}
	
	/**
	 * Allows for the player to change the speed of the paddle
	 * Paddle should not be allowed to go into speeds that are less than 1
	 * @param speed input for the speed of the paddle
	 */
	public void setPaddleSpeed(double speed) {
		if (speed < 1) {
			this.paddleSpeed = 1;
		} else {
			this.paddleSpeed = speed;	
		}

	}
	
	/**
	 * Returns the paddle speed 
	 * @return Returns the paddle speed
	 */
	public double getPaddleSpeed() {
		return this.paddleSpeed;
	}
	
	/**
	 * Sets the size of the paddle 
	 * Should not be less than the 10 or greater than 50
	 * @param size size of the ball
	 */
	public void setPaddleSize(double size) {
		if (size < 10) {
			this.paddleSize = 10;
		} else if (size > 50) {
			this.paddleSize = 50;
		} else {
			this.paddleSize = size;	
		}
	}
	
	/**
	 * Get the paddle size
	 * @return Returns the paddle size in type double
	 */
	public double getPaddleSize() {
		return this.paddleSize;
	}

	public int getPaddleToken() {
		return this.paddleToken;
	}

	@Override
	public modelSuperClass getObj() { return this;}

	public double getPaddleHeight() {
		return height;
	}

	public void setPaddleHeight(double height) {
		this.height = height;
	}

	public boolean isRotated() {
		return rotated;
	}

	public void setRotated(boolean rotated) {
		this.rotated = rotated;
	}
}
