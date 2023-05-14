package ch.bbcag.game.objects.upgrades.ships;

import ch.bbcag.game.Space;
import ch.bbcag.game.objects.GameObject;
import ch.bbcag.game.objects.upgrades.Upgrade;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Ship extends Upgrade {
    private int weight;
    private int cost;
    private int baseHP;
    private Image texture;
    private ShipType shipType;

    public Ship(Space space, ShipType shipType) {
        super(space);
        this.shipType = shipType;
        switch(shipType) {
            case VENATOR -> {
                texture = new Image("graphics/ships/venator.png");
                cost = 200000;
                weight = 7;
                baseHP = 10000;
            }
            case NABOO_CRUISER -> {
                texture = new Image("graphics/ships/naboo_cruiser.png");
                cost = 7000;
                weight = 5;
                baseHP = 3000;
            }
            case NABOO_JTYPE -> {
                texture = new Image("graphics/ships/naboo_jtype.png");
                cost = 80000;
                weight = 3;
                baseHP = 3333;
            }
            case NABOO_FIGHTER -> {
                texture = new Image("graphics/ships/naboo_fighter.png");
                cost = 100000;
                weight = 1;
                baseHP = 2500;
            }
            case JEDI_INTERCEPTOR -> {
                texture = new Image("graphics/ships/jedi_interceptor.png");
                cost = 15000;
                weight = 4;
                baseHP = 5000;
            }
            case JEDI_STARFIGHTER -> {
                texture = new Image("graphics/ships/jedi_starfighter.png");
                cost = 50000;
                weight = 2;
                baseHP = 6500;
            }
        }
    }

    @Override
    public void update(double deltaInSec) {

    }

    public Image getTexture() {
        return texture;
    }

    public int getWeight() {
        return weight;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public List<String> getStats() {
        List<String> stats = new ArrayList<>();
        stats.add("Name:\n   "+ shipType);
        stats.add("Weight:\n   " + weight*weight+"t");
        stats.add("Health Points:\n   " + baseHP);
        return stats;
    }

    public int getBaseHP() {
        return baseHP;
    }

    public void damage(int hitDamage) {
        baseHP -= hitDamage;
    }

    public int getHP() {
        return baseHP;
    }
}
