package model_classes;
import java.util.ArrayList;

/**
 * Wall class will store an array list of brci kthat will be broken when collision occurs
 */
public class Wall {

	// Declare the attributes
	private int numberOfBricks;
	private ArrayList<Point> brickCoordinates;
	private ArrayList<Brick> brickList;

	/**
	 * Wall constructor
	 * @param brickNo - number of bricks to be added to the wall; int
	 */
	public Wall(int brickNo) {		
		this.numberOfBricks = brickNo;
	}

	/**
	 * setNumberOfBricks is a function that will set the correct number of bricks
	 * @param number - the new number of bricks on the wall; int
	 */
	public void setNumberOfBricks(int number) {
		this.numberOfBricks = number;
	}

	/**
	 * getNumberOfBrick will get the number of bricks contained by the wall
	 * @return the number of brick; int
	 */
	public int getNumberOfBricks() {
		return this.numberOfBricks;
	}

	/**
	 * addBrick will add a brick to the wall
	 * @param brick - the brick to be added to the wall; Brick
	 */
	public void addBrick(Brick brick) {
		this.brickList.add(brick);
	}

	/**
	 * getBricks will get the arraylist of bricks
	 * @return the arraylist containing the bricks of the wall; arraylist
	 */
	public ArrayList<Brick> getBricks() {
		return this.brickList;
	}

	/**
	 * addBrickCoord will add the positions of the bricks into the arraylist
	 * @param pos - position of the brick; Point
	 */
	public void addBrickCoord(Point pos) {
		this.brickCoordinates.add(pos);
	}

	/**
	 * getBrickCoord will return an arraylist of brick coordinates
	 * @return arraylist of brick coordinates; arraylist
	 */
	public ArrayList<Point> getBrickCoord() {
		if(this.brickCoordinates == null) {
			return null;
		} else {
			return this.brickCoordinates;
		}
	}

	/**
	 * constructWall will contrsuct the wall of a player specified by quarter
	 * @param brickNo - number of bricks to add to the wall; int
	 * @param quarter - quarter of the grid; int
	 */
	public void constructWall(int brickNo, int quarter) {
		for(int i = 0; i < brickNo; i++) {
			//Brick b1 = new Brick(globalLength, globalHeight);
			Brick b1 = new Brick(40, 10);
			this.addBrick(b1);
		}
	}
}
