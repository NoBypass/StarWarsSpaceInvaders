package ch.bbcag.game.objects.upgrades.armors;

import ch.bbcag.game.Space;
import ch.bbcag.game.objects.upgrades.Upgrade;
import ch.bbcag.game.objects.upgrades.motors.Motor;
import ch.bbcag.game.objects.upgrades.ships.Ship;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Armor extends Upgrade {
    private int durability;
    private int health;
    private int shieldHits;
    private ArmorType armorType;

    public Armor(Space space, ArmorType armorType) {
        super(space);
        this.armorType = armorType;
        switch (armorType){
            case STEEL -> {
                cost = 10000;
                health = 8000;
                shieldHits = 0;
                durability = 3000;
                image = new Image("graphics/weapons/not_done.png");

            }
            case RECYCLED_MATERIAL -> {
                cost = 2000;
                health = 2500;
                shieldHits = 0;
                durability = 1000;
                image = new Image("graphics/weapons/not_done.png");

            }
            case HARDENED_STEEL -> {
                cost = 35000;
                health = 15000;
                shieldHits = 3;
                durability = 5000;
                image = new Image("graphics/weapons/not_done.png");
            }
            case CHROME -> {
                cost = 75000;
                health = 20000;
                shieldHits = 4;
                durability = 10000;
                image = new Image("graphics/weapons/not_done.png");

            }
            case BESKAR -> {
                cost = 140000;
                health = 35000;
                shieldHits = 8;
                durability = 20000;
                image = new Image("graphics/weapons/not_done.png");

            }
        }
    }
    @Override
    public List<String> getStats() {
        List<String> stats = new ArrayList<>();
        stats.add("Name:\n   " +armorType);
        stats.add("Durability:\n   " + cost);
        stats.add("Health:\n   " + health);
        stats.add("Shield Hits:\n   "+ shieldHits);
        return stats;
    }

    public void damage(int hitDamage, Motor motor, Ship ship) {
        if (shieldHits > 0) {
            shieldHits -= 1;
            return;
        }
        durability = durability - hitDamage / 2;
        if (shieldHits == 0) motor.damage(hitDamage);
        if (durability < 0) durability = 0;
        if (ship.getBaseHP() > 0) {
            ship.damage(hitDamage);
            return;
        }
        health = health - hitDamage + durability/10;
    }

    public int getHealth() {
        return health;
    }

    public int getShieldHits() {
        return shieldHits;
    }

    @Override
    public void update(double deltaInSec) {

    }
}
