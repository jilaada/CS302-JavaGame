package model_classes;

/**
 * Brick class contains data on the individual bricks
 */
public class Brick extends modelSuperClass{

	// Private variable declaration
	private double length;
	private double height;
	private double area;
	private Point position;

	/**
	 * Brick constructor - building blocks for the walls for each player
	 * @param length - length of the brick
	 * @param height - height of the brick
	 */
	public Brick(double length, double height) {
		this.length = length;
		this.height = height;
	}

	/**
	 * setLength will set the length of the brick
	 * @param length - length as a double
	 */
	public void setLength(double length) {
		this.length = length;
	}

	/**
	 * getLength will return the length of the brick
	 * @return length will be returned
	 */
	public double getLength() {
		return this.length;
	}

	/**
	 * setHeight will set the height of the brick
	 * @param height - height of the brick; double
	 */
	public void setHeight(double height) {
		this.height = height;
	}

	/**
	 * getHeight will get the height of the brick
	 * @return the height of the brick; double
	 */
	public double getHeight() {
		return this.height;
	}

	/**
	 * getArea will get the area of the brick
	 * @return the area of the brick; double
	 */
	public double getArea() {
		return this.area;
	}

	/**
	 * setPoint will set the position of the brick
	 * @param position - new position to be set; Point
	 */
	public void setPoint(Point position) {
		this.position = position;
	}

	/**
	 * getPoint will get the position of the brick
	 * @return position of the brick; Point
	 */
	public Point getPoint() {
		if(this.position == null) {
			return null;
		} else{
			return this.position;
		}
	}

	//TODO: change the commenting
	/**
	 * The super class object
	 * @return
	 */
	@Override
	public modelSuperClass getObj() { return this;}
	
}
