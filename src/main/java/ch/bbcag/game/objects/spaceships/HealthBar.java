package ch.bbcag.game.objects.spaceships;

import ch.bbcag.common.Point;
import ch.bbcag.game.Space;
import ch.bbcag.game.objects.GameObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class HealthBar extends GameObject {
    private Point point;
    private final int MAX_HEALTH;
    private int currentHealth;
    private final Image IMAGE_EMPTY = new Image("/graphics/health_bar_empty.png");
    public HealthBar(Space space, Point point, int maxHp) {
        super(space);
        this.point = point;
        this.MAX_HEALTH = maxHp;
        this.currentHealth = maxHp;
        image = new Image("/graphics/health_bar.png");
        x = point.getX(); y = point.getY();
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    @Override
    public void update(double deltaInSec) {
        x = point.getX(); y = point.getY();
        int width = (int) Math.round(IMAGE_EMPTY.getWidth()/(MAX_HEALTH-1)*(currentHealth-1));
        image = new Image("/graphics/health_bar.png", width, 4, false, true);
    }
    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(new Image("/graphics/health_bar_empty.png"),x,y);
        gc.drawImage(image, x, y);
    }
}
