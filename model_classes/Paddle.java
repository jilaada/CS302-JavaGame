package model_classes;

/**
 * This class models the properties of the paddles used by the 4 players in the game
 */
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
	private boolean hasPowerUp;
	private PowerUps power;
	private static int nextID = 0;
	private final int myID;

	/** Constructor of player paddle
	 * The constructor will take three inputs and sets the speed, size and paddle bounds
	 * of the object.
	 * @param speed - sets the speed of the paddle; double
	 * @param size - sets the size of the paddle; double
	 * @param token - determines the player number (P1, P2, P3, P4); int
	 * @param height - sts the height of the paddle; double
	 */
	public Paddle(double speed, double size, double height, int token) {
		this.paddleSpeed = speed;
		this.paddleSize = size;
		this.paddleToken = token;
		this.height = height;
		this.rotated = false;
		this.hasPowerUp = false;
		this.power = null;
		this.myID = nextID++;


		previousPos = new Point(0,0);
		currentPos = new Point(0,0);
		paddleStart = new Point(0 ,0);
		paddleEnd = new Point(0, 0);
		paddleBounds = new Point(0,0);
	}

	/**
	 * setBounds will set the bound of the paddles depending on the token parsed
	 */
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
	 * @param pos - a point class of the new coordinates for the paddle; Point
	 */
	public void setCurrentPos(Point pos) {
		this.currentPos = pos;
	}
	
	/**
	 * Returns a point class containing the current position of the paddle
	 * @return Returns a point class containing the current position of the paddle; Point
	 */
	@Override
	public Point getCurrentPos() {
		if(this.currentPos == null) {
			return null;
		} else {
			return this.currentPos;
		}
	}
	
	/**
	 * Sets the previous position
	 * @param pos - a point class containing the coordinates of the paddle; Point
	 */
	public void setPreviousPos(Point pos) {
		this.previousPos = pos;
	}
	
	/**
	 * Gets the previous position of the paddle
	 * @return Returns a point class containing the previous position of the paddle; Point
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
	 * @return returns a point class containing the paddle bounds; Point
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
	 * @param speed input for the speed of the paddle; double
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
	 * @return Returns the paddle speed; double
	 */
	public double getPaddleSpeed() {
		return this.paddleSpeed;
	}
	
	/**
	 * Sets the size of the paddle 
	 * Should not be less than the 10 or greater than 50
	 * @param size size of the ball; double
	 */
	public void setPaddleSize(double size) {
		this.paddleSize = size;
	}
	
	/**
	 * Get the paddle size
	 * @return Returns the paddle size; double
	 */
	@Override
	public double getLength() {
		return this.paddleSize;
	}

	/**
	 * getPaddleToken gets the token associated to the paddle
	 * @return Returns the token of the paddle; int
	 */
	public int getPaddleToken() {
		return this.paddleToken;
	}

	/**
	 * getObj will get the object it is representing
	 * @return Returns an object representation; modelSuperClass
	 */
	@Override
	public modelSuperClass getObj() { return this;}

	/**
	 * getHeight will get the height of the obejct
	 * @return Returns the hieght of the object; double
	 */
	@Override
	public double getHeight() {
		return height;
	}

	/**
	 * setHeight will set the height of the paddle
	 * @param height - sets the height of the paddle; double
	 */
	public void setHeight(double height) {
		this.height = height;
	}

	/**
	 * isRotated returns true if a shape is rotated
	 * @return Returns true if an obejct is rotated; boolean
	 */
	public boolean isRotated() {
		return rotated;
	}

	/**
	 * setRotated will set the rotation factor of the object
	 * @param rotated - true if rotated; boolean
	 */
	public void setRotated(boolean rotated) {
		this.rotated = rotated;
	}

	public boolean hasPowerUp() {
		return hasPowerUp;
	}

	public void setPowerUp(boolean hasPowerUp) {
		this.hasPowerUp = hasPowerUp;
	}

	public PowerUps getPower() {
		return power;
	}

	public void setPower(PowerUps power) {
		this.power = power;
	}

	public int getID() {
		return myID;
	}
}
