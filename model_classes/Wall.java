package model_classes;
import java.util.ArrayList;

/**
 * The Wall will be used to store an array of iformation regarding the bricks
 */
public class Wall {

	private int numberOfBricks;
	private ArrayList<Point> brickCoordinates;
	private ArrayList<Brick> brickList;

	/**
	 * Constructor for the wall
	 */
	public Wall() {
		this.brickList = new ArrayList<Brick>();
		this.brickCoordinates = new ArrayList<Point>();
	}

	/**
	 * setNumberOfBricks will set the number of bricks in the wall
	 * @param number - number of bricks; int
	 */
	public void setNumberOfBricks(int number) {
		this.numberOfBricks = number;
	}

	/**
	 * getNumberOfBricks will get the number of bricks in the wall
	 * @return returns the number of bricks; int
	 */
	public int getNumberOfBricks() {
		return this.numberOfBricks;
	}

	/**
	 * addBrick will add bricks to the wall array
	 * @param brick - the brick to be added; Brick
	 */
	public void addBrick(Brick brick) {
		this.brickList.add(brick);
	}

	/**
	 * getBricks will return an array containing all the bricks that belong to the wall
	 * @return Returns an arraylist of bricks; ArrayList
	 */
	public ArrayList<Brick> getBricks() {
		return this.brickList;
	}

	/**
	 * addBrickCoord will add a coordinates to the coordinate array
	 * @param pos - a coordinate of the brick; Point
	 */
	public void addBrickCoord(Point pos) {
		this.brickCoordinates.add(pos);
	}

	/**
	 * getBrickCoord will return the array of brick coordinates
	 * @return Returns the array of brick coordinates; Arraylist
	 */
	public ArrayList<Point> getBrickCoord() {
		if(this.brickCoordinates == null) {
			return null;
		} else {
			return this.brickCoordinates;
		}
	}
}
