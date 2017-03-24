package view_classes;

import javafx.stage.Stage;

/**
 * Created by niles on 24/03/2017.
 */
public class Boundaries {
    private double width;
    private double height;

    //TODO: This class will contain information about the window size. Still needs more implemenation
    public Boundaries(Stage stage) {
        width = stage.getX();
        height = stage.getY();
    }

}
