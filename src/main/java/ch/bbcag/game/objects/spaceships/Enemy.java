package ch.bbcag.game.objects.spaceships;

import ch.bbcag.common.Global;
import ch.bbcag.common.Point;
import ch.bbcag.game.Space;
import ch.bbcag.game.objects.upgrades.armors.Armor;
import ch.bbcag.game.objects.upgrades.armors.ArmorType;
import ch.bbcag.game.objects.upgrades.motors.Motor;
import ch.bbcag.game.objects.upgrades.motors.MotorType;
import ch.bbcag.game.objects.upgrades.projectiles.Projectile;
import ch.bbcag.game.objects.upgrades.ships.Ship;
import ch.bbcag.game.objects.upgrades.ships.ShipType;
import ch.bbcag.game.objects.upgrades.weapons.Weapon;
import ch.bbcag.game.objects.upgrades.weapons.WeaponType;
import javafx.scene.image.Image;

public class Enemy extends Spaceships {
    private Point point;
    private Weapon weapon;
    private Motor motor;
    private Armor armor;
    private Ship ship;
    private double shotRate;

    private enum DirectionEnum {
        LEFT,
        RIGHT
    }

    private DirectionEnum currentDirection = DirectionEnum.RIGHT;

    public Enemy(Space space, Point point, int difficultyLevel, EnemyType enemyType) {
        super(space, difficultyLevel);
        x = point.getX();
        y = point.getY();
        if (Math.random() < 0.5) currentDirection = DirectionEnum.LEFT;
        switch (enemyType) {
            case TIE_FIGHTER -> {
                image = new Image("graphics/ships/TieFighter.png");
                armor = new Armor(space, ArmorType.STEEL);
                motor = new Motor(space, MotorType.TIE);
                weapon = new Weapon(space, WeaponType.HANDHELD_BLASTER, false);
                ship = new Ship(space, ShipType.JEDI_STARFIGHTER);
                shotRate = 0.02;
            }
            case TIE_DEFENDER -> {
                image = new Image("graphics/ships/TieDefender.png");
                armor = new Armor(space, ArmorType.HARDENED_STEEL);
                motor = new Motor(space, MotorType.TIE);
                weapon = new Weapon(space, WeaponType.BLASTER, false);
                ship = new Ship(space, ShipType.NABOO_JTYPE);
                shotRate = 0.1;
            }
            case STAR_DESTROYER -> {
                image = new Image("graphics/ships/stardestroyer.png");
                armor = new Armor(space, ArmorType.HARDENED_STEEL);
                motor = new Motor(space, MotorType.VENATOR);
                weapon = new Weapon(space, WeaponType.MISSILE_GUN, false);
                ship = new Ship(space, ShipType.VENATOR);
                shotRate = 0.01;
            }
        }
    }

    protected void balance() {
        int[] values = {}; //TODO
        for (int i = 0; i < values.length; i++)
            values[i] = (int) Math.ceil(values[i] * Math.sqrt(this.difficultyLevel));
    }

    protected void damage(int hitDamage) {
        armor.damage(hitDamage, motor, ship);
        if (ship.getHP() < 0) {
            space.remove(this);
            Global.playerCredits += (motor.getCost() + armor.getCost() + ship.getCost() + weapon.getCost()) / 100;
        }
    }

    @Override
    public void update(double deltaInSec) {
        if (deltaInSec > 2) deltaInSec = 0.1;
        double distanceToMove = motor.getSpeed() * deltaInSec / ship.getWeight();
        if (currentDirection == DirectionEnum.RIGHT) x += distanceToMove;
        else x -= distanceToMove;

        if (x + getBoundingBox().getWidth() > Global.WINDOW_WITH) currentDirection = DirectionEnum.LEFT;
        else if (x < 0) currentDirection = DirectionEnum.RIGHT;

        if (Math.random() < shotRate) weapon.shoot(new Point(getBoundingBox().getCenterX(), getBoundingBox().getMaxY()));

        for (Projectile projectile : space.getAllObjectsFromType(Projectile.class)) {
            if (this.getBoundingBox().intersects(projectile.getBoundingBox()) && projectile.isFriendly()) {
                damage(projectile.getDamage());
                space.remove(projectile);
            }
        }
    }
}
