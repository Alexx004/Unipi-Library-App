package unipi.library.ui;

import javafx.scene.Scene;
 
//yperklash olwn twn scenes
public abstract class SceneCreator {
    protected double width;
    protected double height;

    public SceneCreator(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public abstract Scene createScene();
}
