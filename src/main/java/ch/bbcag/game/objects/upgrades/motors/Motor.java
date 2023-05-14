package ch.bbcag.game.objects.upgrades.motors;

import ch.bbcag.game.Space;
import ch.bbcag.game.objects.upgrades.Upgrade;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Motor extends Upgrade {
    private int cost;
    private int speed;
    private int durability;
    private int defaultSpeed;
    private MotorType motorType;

    public Motor(Space space, MotorType motorType) {
        super(space);
        this.motorType = motorType;
        switch (motorType){
            case TIE -> {
                speed = 600;
                cost = 2500;
                durability = 600;
                image = new Image("graphics/motor.png");
            }
            case NABOO -> {
                speed = 1000;
                cost = 45000;
                durability = 500;
                image = new Image("graphics/motor.png");
            }
            case SPEEDER -> {
                speed = 350;
                cost = 75;
                durability = 300;
                image = new Image("graphics/motor.png");
            }
            case VENATOR -> {
                speed = 450;
                cost = 100000;
                durability = 2000;
                image = new Image("graphics/motor.png");
            }
            case INTERCEPTOR -> {
                speed = 350;
                cost = 18000;
                durability = 800;
                image = new Image("graphics/motor.png");

            }
        }
        defaultSpeed = speed;
    }

    public void damage(double hitDamage) {
        durability -= hitDamage/20;
        speed -= (int) Math.round(speed - speed/hitDamage*durability);
        if (speed < defaultSpeed/2) speed = defaultSpeed/2;
    }
    @Override
    public List<String> getStats() {
        List<String> stats = new ArrayList<>();
        stats.add("Name:\n   "+ motorType);
        stats.add("Speed:\n   " + speed);
        stats.add("Durability:\n   " + durability);
        return stats;
    }

    public int getSpeed() {
        return speed;
    }
    public int getDurability() {
        return durability;
    }

    @Override
    public void update(double deltaInSec) {

    }
}
