package model_classes;

import javafx.scene.shape.Shape;

/**
 * This class will allow for the shape and the model properties to be stores together for easy retrieval of data on objects
 * Created by niles on 31/03/2017.
 */
public class gameObject {
    // declaring private variables
    private Shape shapeTemp;
    private modelSuperClass modelTemp;

    /**
     * Constructor for GameObject
     * @param shapeTemp - a shape object fo the model; Shape
     * @param modelTemp - a model representation of the shape; modelSuperClass
     */
    public gameObject(Shape shapeTemp, modelSuperClass modelTemp) {
        this.shapeTemp = shapeTemp;
        this.modelTemp = modelTemp;
    }

    /**
     * Constructor for GameObject
     * @param modelTemp - a model representation of the shape; modelSuperClass
     */
    public gameObject(modelSuperClass modelTemp) {
        this.modelTemp = modelTemp;
    }

    /**
     * getObj is a function that will return the model of the object
     * @return Returns the object; modelSuperClass
     */
    public modelSuperClass getObj() {
        return modelTemp;
    }

    /**
     * setObj is a function that will set the model object
     * @param modelTemp - model representation of the object; modelSuperClass
     */
    public void setObj(modelSuperClass modelTemp) {
        this.modelTemp = modelTemp;
    }

    /**
     * getShape is a function that will get the view representation of the object
     * @return Returns the shape of the object; Shape
     */
    public Shape getShape() {
        return shapeTemp;
    }

    /**
     * setShape will set the shape of the object
     * @param shapeTemp - shape of the object; Shape
     */
    public void setShape(Shape shapeTemp) {
        this.shapeTemp = shapeTemp;
    }
}
