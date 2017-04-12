package model_classes;

/**
 * The Point class allows for the x and y coordinates of an object to be stored in a an easily accessible and clean area
 */
public class Point {
	private int x;
	private int y;

	/**
	 * Constructor for the Point class
	 * @param x - the x coordinate of the object; int
	 * @param y - the y coordinate of the object; int
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * setX will set the x value of the coordinate
	 * @param x - x value of the coordinate; int
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * getX will get the x value of the coordinate
	 * @return Returns the x value of the coordinate; int
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * setY will set the y coordinate of the object
	 * @param y - y coordinate of the object; int
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * getY will get the y coordinate of the object
	 * @return - y coordinate of the object; int
	 */
	public int getY() {
		return this.y;
	}
}
