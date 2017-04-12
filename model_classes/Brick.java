package model_classes;

import javafx.scene.shape.Shape;

/**
 * Brick is a class that extends from the modelSuperClass and contains information regarding the bricks
 * on the players walls
 */
public class Brick extends modelSuperClass{
	// Declaring the private variables
	private double length;
	private double height;
	private double area;
	private Point position;
	private Shape sprite;
	private boolean isRemoved;
	private static int nextID = 0;
	private final int myID;

	/**
	 * Constructor for the Brick class
	 * @param length - the length of the brick; double
	 * @param height - the height of the brick; double
	 * @param position - the position of the current brick; Point
	 */
	public Brick(double length, double height, Point position) {
		this.length = length;
		this.height = height;
		this.area = length*height;
		this.position = position;
		this.isRemoved = false;
		this.myID = nextID++;
	}

	/**
	 * setLength will set the length of the brick
	 * @param length - the length of the brick; double
	 */
	public void setLength(double length) {
		this.length = length;
	}

	/**
	 * getLength is an overridden function of the parent class where the length of the object is returned
	 * @return Return the length of the brick; double
	 */
	@Override
	public double getLength() {
		return this.length;
	}

	/**
	 * setHeight is a function that sets the height of the brick
	 * @param height - the height of the brick; double
	 */
	public void setHeight(double height) {
		this.height = height;
	}

	/**
	 * setHeight is an overridden function of the parent class where the height of the object is returned
	 * @return Return the height of the brick; double
	 */
	@Override
	public double getHeight() {
		return this.height;
	}

	/**
	 * getArea is a function that will return the area of the brick
	 * @return Return the area of teh brick; double
	 */
	public double getArea() {
		return this.area;
	}

	/**
	 * setPoint will set the position of the brick
	 * @param position - the position of teh brick; Point
	 */
	public void setPoint(Point position) {
		this.position = position;
	}

	/**
	 * getPoint gets the position of the brick
	 * @return Returns the position of the brick; Point
	 */
	public Point getPoint() {
		if(this.position == null) {
			return null;
		} else{
			return this.position;
		}
	}

	/**
	 * getCurrentPos is an overridden function that gets the current position of the brick
	 * @return Returns the current position of the brick; Point
	 */
	@Override
	public Point getCurrentPos() { return this.getPoint();}

	/**
	 * getSprite will return the shape of teh sprite
	 * @return Returns the shape of the sprite; Shape
	 */
	public Shape getSprite() {
		return sprite;
	}

	/**
	 * addSprite adds information regarding the shape of the sprite
	 * @param sprite - the shape of the sprite; Shape
	 */
	public void addSprite(Shape sprite) {
		this.sprite = sprite;
	}

	/**
	 * isRemoved is a function that returns true of the brick is removed
	 * @return Returns true if removed; boolean
	 */
	public boolean isRemoved() {
		return isRemoved;
	}

	/**
	 * setRemoved is a function that sets true of removed is true
	 * @param removed - true if the brick is to be removed; boolean
	 */
	public void setRemoved(boolean removed) {
		isRemoved = removed;
	}

	/**
	 * getID will get the ID of the object
	 * @return - ID of the object; int
	 */
	public int getID() {
		return myID;
	}
}
