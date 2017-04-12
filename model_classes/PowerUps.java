package model_classes;

/**
 * Created by niles on 12/04/2017.
 */
public class PowerUps extends modelSuperClass{

    public enum Power {
        FREEZE, SHRINK, LARGE, INVIS
    }

    private Point currentPos;
    private double size;
    private double height;
    private Power power;

    public PowerUps(double size, double height, Power power) {
        this.size = size;
        this.height = height;
        this.currentPos = new Point(0,0);
        this.power = power;
    }


    public Point getCurrentPos() {
        if(this.currentPos == null) {
            return null;
        } else {
            return this.currentPos;
        }
    }


    public double getLength() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Power getPower() {
        return this.power;
    }

    public void setPower(Power power) {
        this.power = power;
    }

}
