package model_classes;

public class Brick {

	private double length;
	private double height;
	private double area;
	//private Point coordinate;
	private Point position;
	
	public Brick(double length, double height) {
		this.length = length;
		this.height = height;
	}
	
	public void setLength(double length) {
		this.length = length;
	}
	
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
	
}
