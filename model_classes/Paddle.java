package model_classes;

public class Paddle {
	
	private Point currentPos;
	private Point previousPos;
	private Point paddleBounds;
	private double paddleSpeed;
	private double paddleSize;
	private int paddleToken;
	
	/** Constructor of player paddle
	 * The constructor will take three inputs and sets the speed, size and paddle bounds
	 * of the object.
	 * @param speed - sets the speed of the paddle
	 * @param size - sets the size of the paddle
	 * @param token - determines the player number (P1, P2, P3, P4)
	 */
	public Paddle(double speed, double size, int token) throws Exception{
		this.paddleSpeed = speed;
		this.paddleSize = size;
		this.paddleToken = token;
		
		// Throw exception when the size of the paddle is too large or too small(?)
		if ((paddleSize <= 10) || (paddleSize >= 50)) {
			throw new Exception("Paddle size is not between 10 and 50 pixels");
		}
		
		// Added the paddle bounds selection based on player location
		if (paddleToken == 1) {
			paddleBounds.setX(350);
			paddleBounds.setY(250);
		} else if (paddleToken == 2) {
			paddleBounds.setX(674);
			paddleBounds.setY(250);
		} else if (paddleToken == 3) {
			paddleBounds.setX(350);
			paddleBounds.setY(518);
		} else if (paddleToken == 4) {
			paddleBounds.setX(678);
			paddleBounds.setY(518);
		} else {
			// Throw exception when the player token is anything other than 1-4
			throw new Exception("Invalid Player Token");
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
	 * Allows for the player to change the speed of the paddle
	 * Paddle should not be allowed to go into speeds that are less than 1
	 * @param Double input of the new speed value
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
	 * @param size
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
}
