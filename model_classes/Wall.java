package model_classes;

public class Wall {

	private int numberOfBricks;
	private Point brickCoordinates;
	
	public Wall(int brickNo) {		
		//Add other variable constructors
		this.numberOfBricks = brickNo;
	}

	public void setNumberOfBricks(int number) {
		this.numberOfBricks = number;
	}
	
	public int setNumberOfBricks() {
		return this.numberOfBricks;
	}
	
	public void setBrickCoord(Point pos) {
		this.brickCoordinates = pos;
	}
	
	public Point getBrickCoord() {
		return this.brickCoordinates;
	}
	
	
}
