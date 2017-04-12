package model_classes;

/**
 * modelSuperClass is an abstract parent class that allows all objects in the game to be treated equally
 * Created by niles on 31/03/2017.
 */
public class modelSuperClass {
    // Declaring the private variables
    private Point currentPos;
    private boolean rotated = false;
    private double height;
    private double length;

    /**
     * getObj will get the object it is representing
     * @return Returns an object representation; modelSuperClass
     */
    public modelSuperClass getObj()  { return this; }

    /**
     * getCurrentPos will get the current position of the current object
     * @return Returns the current position of a given object; Point
     */
    public Point getCurrentPos() {
        if(this.currentPos == null) {
            return null;
        } else {
            return this.currentPos;
        }
    }

    /**
     * isRotated returns true if a shape is rotated
     * @return Returns true if an obejct is rotated; boolean
     */
    public boolean isRotated() {
        return rotated;
    }

    /**
     * getHeight will get the height of the obejct
     * @return Returns the hieght of the object; double
     */
    public double getHeight() {
        return height;
    }

    /**
     * getLength will get the length of the object
     * @return Returns the length of the object; double
     */
    public double getLength() {
        return length;
    }
}
