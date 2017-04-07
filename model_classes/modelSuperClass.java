package model_classes;

/**
 * Created by niles on 31/03/2017.
 */
public class modelSuperClass {
    private Point currentPos;
    private boolean rotated = false;
    private double height;
    private double length;

    public modelSuperClass getObj()  { return this; }

    public Point getCurrentPos() {
        if(this.currentPos == null) {
            return null;
        } else {
            return this.currentPos;
        }
    }

    public boolean isRotated() {
        return rotated;
    }

    public double getHeight() {
        return height;
    }

    public double getLength() {
        return length;
    }

}
