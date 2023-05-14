package ch.bbcag.game.objects.upgrades.weapons;

import ch.bbcag.common.Point;
import ch.bbcag.game.Space;
import ch.bbcag.game.objects.upgrades.Upgrade;
import ch.bbcag.game.objects.upgrades.projectiles.Projectile;
import ch.bbcag.game.objects.upgrades.projectiles.ProjectileType;
import javafx.scene.image.Image;


import java.util.ArrayList;

import java.util.List;


public class Weapon extends Upgrade {
    private final boolean friendly;
    private double lastShot = System.currentTimeMillis();
    private int cooldownInMS;
    private ProjectileType projectileType;
    private WeaponType weaponType;

    public Weapon(Space space, WeaponType weaponType, boolean friendly) {
        super(space);
        this.friendly = friendly;
        this.weaponType = weaponType;
        switch (weaponType) {
            case HANDHELD_BLASTER -> {
                cost = 50;
                cooldownInMS = 375;
                projectileType = ProjectileType.BLAST;
                image = new Image("graphics/weapons/handheld_blaster.png");
            }
            case BLASTER -> {
                cost = 7500;
                cooldownInMS = 250;
                projectileType = ProjectileType.SHIP_BLAST;
                image = new Image("graphics/weapons/not_done.png");
            }
            case AUTO_BLASTER -> {
                cost = 50000;
                cooldownInMS = 100;
                projectileType = ProjectileType.SHIP_BLAST;
                image = new Image("graphics/weapons/not_done.png");
            }
            case ION_CANNON -> {
                cost = 100000;
                cooldownInMS = 400;
                projectileType =  ProjectileType.ION_BLAST;
                image = new Image("graphics/weapons/not_done.png");
            }
            case CANNON -> {
                cost = 150000;
                cooldownInMS = 600;
                projectileType =  ProjectileType.MISSILE;
                projectileType = ProjectileType.PROTON_BOMB;
                image = new Image("graphics/weapons/not_done.png");
            }
            case MISSILE_GUN -> {
                cost = 150000;
                cooldownInMS = 450;
                projectileType = ProjectileType.MISSILE;
                image = new Image("graphics/weapons/not_done.png");
            }
        }
    }
    public void shoot(Point spawnPoint) {
        if (lastShot + cooldownInMS < System.currentTimeMillis()){
            lastShot = System.currentTimeMillis();
            spawnProjectile(spawnPoint);
        }
    }
    @Override
    public List<String> getStats() {
      List<String> stats = new ArrayList<>();
      stats.add("Name:\n   "+ weaponType);
        stats.add("Cooldoun in ms:\n   " + cooldownInMS);
        stats.add("Projectile:\n   " + projectileType);
        return stats;
    }
    protected void spawnProjectile(Point spawnPoint) {
        Projectile compatibleProjectile = new Projectile(space, new Point(-100, -100), friendly, projectileType);
        spawnPoint.setX(spawnPoint.getX() + compatibleProjectile.getImage().getWidth()/2);
        spawnPoint.setY(spawnPoint.getY() - compatibleProjectile.getImage().getHeight()/2);
        space.remove(compatibleProjectile);
        space.add(new Projectile(space, spawnPoint, friendly, projectileType));
    }

    @Override
    public void update(double deltaInSec) {

    }
}
