package model_classes;

import javafx.scene.shape.Shape;

/**
 * Created by niles on 31/03/2017.
 */
public class gameObject {
    private Shape shapeTemp;
    private modelSuperClass modelTemp;

    public gameObject(Shape shapeTemp, modelSuperClass modelTemp) {
        this.shapeTemp = shapeTemp;
        this.modelTemp = modelTemp;
    }

    public gameObject(modelSuperClass modelTemp) {
        this.modelTemp = modelTemp;
    }

    public modelSuperClass getObj() {
        return modelTemp;
    }

    public void setObj(modelSuperClass modelTemp) {
        this.modelTemp = modelTemp;
    }

    public Shape getShape() {
        return shapeTemp;
    }

    public void setShape(Shape shapeTemp) {
        this.shapeTemp = shapeTemp;
    }
}
