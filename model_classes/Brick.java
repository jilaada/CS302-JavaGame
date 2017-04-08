package model_classes;

import javafx.scene.shape.Shape;

public class Brick extends modelSuperClass{

	private double length;
	private double height;
	private double area;
	private Point position;
	private Shape sprite;
	private boolean isRemoved;
	private static int nextID = 0;
	private final int myID;
	
	public Brick(double length, double height, Point position) {
		this.length = length;
		this.height = height;
		this.area = length*height;
		this.position = position;
		this.isRemoved = false;
		this.myID = nextID++;
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

	public Shape getSprite() {
		return sprite;
	}

	public void addSprite(Shape sprite) {
		this.sprite = sprite;
	}

	public boolean isRemoved() {
		return isRemoved;
	}

	public void setRemoved(boolean removed) {
		isRemoved = removed;
	}

	public int getID() {
		return myID;
	}
}
