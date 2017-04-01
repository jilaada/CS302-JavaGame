package model_classes;

/**
 * Point class is a class that will store the coordinates of the object
 */
public class Point {

	// Declaration of attributes
	private int x;
	private int y;

	/**
	 * Point constructor
	 * @param x - x coordinate of the object from the top left; int
	 * @param y - y coordinate of the object from the top left; int
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * setX will set x the new X value
	 * @param x - x coordinate of the object from the top left; int
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * getY will get the new x position
	 * @return x coordinate; int
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * setY will set y the new Y value
	 * @param y - y coordinate of the object from the top left; int
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * getY will get the y coordinate
	 * @return y coordinate; int
	 */
	public int getY() {
		return this.y;
	}
}
