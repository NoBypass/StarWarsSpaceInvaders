package ch.bbcag.game.objects.upgrades.projectiles;

import ch.bbcag.common.Global;
import ch.bbcag.common.Point;
import ch.bbcag.game.Space;
import ch.bbcag.game.objects.GameObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public class Projectile extends GameObject {
    private boolean friendly;
    private int damage;
    private int cost;
    private int speed;

    public Projectile(Space space, Point spawnPoint, boolean friendly, ProjectileType projectileType) {
        super(space);
        this.friendly = friendly;
        x = spawnPoint.getX();
        y = spawnPoint.getY();
        switch (projectileType) {
            case BLAST -> {
                cost = 8000;
                damage = 1250;
                speed = 800;
                image = new Image("graphics/projectiles/Blast.png");
            }
            case MISSILE -> {
                cost = 40000;
                damage = 17500;
                speed = 300;
                image = new Image("graphics/projectiles/Missile.png");
            }
            case ION_BLAST -> {
                cost = 80000;
                damage = 10000;
                speed = 600;
                image = new Image("graphics/projectiles/IonBlast.png");
            }
            case SHIP_BLAST -> {
                cost = 12000;
                damage = 2000;
                speed = 800;
                image = new Image("graphics/projectiles/ShipBlast.png");
            }
            case PROTON_BOMB -> {
                cost = 150000;
                damage = 25000;
                speed = 300;
                image = new Image("graphics/projectiles/ProtonBomb.png");
            }
        }
    }

    public boolean isFriendly() {
        return friendly;
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public void update(double deltaInSec) {
        double distanceToMove = deltaInSec * speed;
        if (friendly) {
            y -= distanceToMove;
        } else {
            y += distanceToMove;
        }
        if (y < 0-getBoundingBox().getHeight() || y > Global.WINDOW_HEIGHT+getBoundingBox().getHeight()){
            suicide();
        }
        for (Projectile projectile : space.getAllObjectsFromType(Projectile.class)) {
            if (this.getBoundingBox().intersects(projectile.getBoundingBox())) {
                if (projectile == this) return;
                space.remove(projectile);
                space.remove(this);
            }
        }
    }
    @Override
    public void draw(GraphicsContext gc) {
        if (friendly) gc.drawImage(image, x, y);
        else gc.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), x, y, image.getWidth(), -image.getHeight());
    }
}
