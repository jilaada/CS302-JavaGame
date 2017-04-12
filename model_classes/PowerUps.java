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
    private double timeOfPU;
    private double duration;

    public PowerUps(double size, double height, Power power, double duration) {
        this.size = size;
        this.height = height;
        this.currentPos = new Point(0,0);
        this.power = power;
        this.timeOfPU = -1;
        this.duration = duration;
    }


    public Point getCurrentPos() {
        if(this.currentPos == null) {
            return null;
        } else {
            return this.currentPos;
        }
    }

    public void setCurrentPos(Point temp) {
        this.currentPos = temp;
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

    public double getTimeOfPU() {
        return timeOfPU;
    }

    public void setTimeOfPU(double timeOfPU) {
        this.timeOfPU = timeOfPU;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

}
