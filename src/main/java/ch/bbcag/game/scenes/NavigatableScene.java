package ch.bbcag.game.scenes;

import javafx.scene.Parent;
import javafx.scene.Scene;

public abstract class NavigatableScene extends Scene {
    public NavigatableScene(Parent root) {
        super(root);
    }
    public abstract void reStart();
}
