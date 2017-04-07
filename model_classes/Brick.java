package model_classes;

public class Brick extends modelSuperClass{

	private double length;
	private double height;
	private double area;
	private Point position;
	
	public Brick(double length, double height, Point position) {
		this.length = length;
		this.height = height;
		this.area = length*height;
		this.position = position;
	}
	
	public void setLength(double length) {
		this.length = length;
	}

	@Override
	public double getLength() {
		return this.length;
	}
	
	public void setHeight(double height) {
		this.height = height;
	}

	@Override
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
	public Point getCurrentPos() { return this.getPoint();}

}
