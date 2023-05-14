package ch.bbcag.game.objects;

import ch.bbcag.game.Space;
import javafx.scene.image.Image;

public class Background extends GameObject{
    public Background(Space space, String imagePath) {
        super(space);
        image = new Image(imagePath);

    }

    @Override
    public void update(double deltaInSec) {

    }
}
