package model_classes;

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
	
	public void setHeight(double height) {
		this.height = height;
	}
	
	public double getHeight() {
		return this.height;
	}
	
	public double getArea() {
		return this.area;
	}
	
	public void setPoint(Point position) {
		this.position = position;
	}
	
	public Point getPoint() {
		if(this.position == null) {
			return null;
		} else{
			return this.position;
		}
	}
	@Override
	public modelSuperClass getObj() { return this;}
	
}
