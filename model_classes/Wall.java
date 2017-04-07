package model_classes;
import java.util.ArrayList;

public class Wall {

	private int numberOfBricks;
	private ArrayList<Point> brickCoordinates;
	private ArrayList<Brick> brickList;
	
	public Wall() {
		this.brickList = new ArrayList<Brick>();
		this.brickCoordinates = new ArrayList<Point>();
	}

	public void setNumberOfBricks(int number) {
		this.numberOfBricks = number;
	}
	
	public int getNumberOfBricks() {
		return this.numberOfBricks;
	}
	
	public void addBrick(Brick brick) {
		this.brickList.add(brick);
	}
	
	public ArrayList<Brick> getBricks(Brick brick) {
		return this.brickList;
	}
	
	public void addBrickCoord(Point pos) {
		this.brickCoordinates.add(pos);
	}
	
	public ArrayList<Point> getBrickCoord() {
		if(this.brickCoordinates == null) {
			return null;
		} else {
			return this.brickCoordinates;
		}
	}
}
