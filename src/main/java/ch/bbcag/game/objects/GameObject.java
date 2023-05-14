package ch.bbcag.game.objects;

import ch.bbcag.game.Space;
import javafx.geometry.BoundingBox;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class GameObject {
    protected Image image;
    protected double x;
    protected double y;
    protected Space space;

    public GameObject(Space space) {
        this.space = space;
    }


    public abstract void update(double deltaInSec);

    protected void suicide() {
        space.remove(this);
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(image, x, y);
    }
    public Image getImage() {
        return image;
    }

    public BoundingBox getBoundingBox() {
        return new BoundingBox(this.x, this.y, image.getWidth(), image.getHeight());
    }

    public void setSpace(Space space) {
        this.space = space;
    }


}
