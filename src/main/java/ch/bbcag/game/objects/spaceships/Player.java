package ch.bbcag.game.objects.spaceships;

import ch.bbcag.common.Global;
import ch.bbcag.common.Keyboard;
import ch.bbcag.common.Point;
import ch.bbcag.game.Space;
import ch.bbcag.game.objects.upgrades.armors.Armor;
import ch.bbcag.game.objects.upgrades.motors.Motor;
import ch.bbcag.game.objects.upgrades.projectiles.Projectile;
import ch.bbcag.game.objects.upgrades.ships.Ship;
import ch.bbcag.game.objects.upgrades.weapons.Weapon;
import javafx.scene.input.KeyCode;


public class Player extends Spaceships {
    private Armor armor;
    private Motor motor;
    private Weapon weapon;
    private Ship ship;
    private HealthBar healthBar;

    public Player(Space space, Point point) {
        super(space, 1);
        x = point.getX();
        y = point.getY();
        armor = new Armor(space, Global.selectedArmor);
        motor = new Motor(space, Global.selectedMotor);
        weapon = new Weapon(space, Global.selectedWeapon, true);
        ship = new Ship(space, Global.selectedShip);
        this.image = ship.getTexture();
        healthBar = new HealthBar(space, new Point(getBoundingBox().getMaxY() + 10, getBoundingBox().getMinX()), armor.getHealth()+ship.getHP());
        space.add(healthBar);
    }

    @Override
    public void update(double deltaInSec) {
        double distanceToMove = deltaInSec * motor.getSpeed() / ship.getWeight();
        for (Projectile projectile : space.getAllObjectsFromType(Projectile.class)) {
            if (this.getBoundingBox().intersects(projectile.getBoundingBox()) && !projectile.isFriendly()) {
                armor.damage(projectile.getDamage(), motor, ship);
                space.remove(projectile);
            }
        }
        if (Keyboard.isPressed(KeyCode.LEFT) && x >= 0) {
            if (x < 0) x = 0;
            else x -= distanceToMove;
        }
        if (Keyboard.isPressed(KeyCode.RIGHT) && x <= Global.WINDOW_WITH) {
            if (x + getBoundingBox().getWidth() > Global.WINDOW_WITH)
                x = Global.WINDOW_WITH - getBoundingBox().getWidth();
            else x += distanceToMove;
        }
        if (Keyboard.isPressed(KeyCode.SPACE)) {
            weapon.shoot(new Point(x + image.getWidth() / 2, y));
        }
        healthBar.setPoint(new Point(getBoundingBox().getMinX(), getBoundingBox().getMaxY() + 10));
        healthBar.setCurrentHealth(armor.getHealth()+ship.getHP());
    }

    public void setSpace(Space space) {
        this.space = space;
        armor.setSpace(space);
        motor.setSpace(space);
        weapon.setSpace(space);
    }


    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public void setMotor(Motor motor) {
        this.motor = motor;
    }

    public Motor getMotor() {
        return motor;
    }

    public boolean isAlive() {
        if (armor.getHealth()+ship.getHP() < 0) {
            return false;
        } else {
            return true;
        }
    }
}
