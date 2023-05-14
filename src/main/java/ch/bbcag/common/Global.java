package ch.bbcag.common;

import ch.bbcag.game.Space;
import ch.bbcag.game.objects.GameObject;
import ch.bbcag.game.objects.spaceships.Player;
import ch.bbcag.game.objects.upgrades.armors.ArmorType;
import ch.bbcag.game.objects.upgrades.motors.MotorType;
import ch.bbcag.game.objects.upgrades.ships.ShipType;
import ch.bbcag.game.objects.upgrades.weapons.WeaponType;
import ch.bbcag.game.objects.upgrades.weapons.Weapon;
import ch.bbcag.game.objects.upgrades.weapons.WeaponType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Global {
    public static final int WINDOW_WITH = 800;
    public static final int WINDOW_HEIGHT = 600;
    public static final List<WeaponType> BOUGHT_WEAPONS = new ArrayList<>(Arrays.asList(WeaponType.HANDHELD_BLASTER));
    public static final List<ArmorType> BOUGHT_ARMOR = new ArrayList<>(Arrays.asList(ArmorType.RECYCLED_MATERIAL));
    public static final List<ShipType> BOUGHT_SHIPS = new ArrayList<>(Arrays.asList(ShipType.NABOO_CRUISER));
    public static final List<MotorType> BOUGHT_MOTORS = new ArrayList<>(Arrays.asList(MotorType.SPEEDER));


    public static final List<GameObject> INVENTORY = new ArrayList<>();
    public static ArmorType selectedArmor = ArmorType.RECYCLED_MATERIAL;
    public static WeaponType selectedWeapon = WeaponType.MISSILE_GUN;
    public static MotorType selectedMotor = MotorType.VENATOR;
    public static ShipType selectedShip = ShipType.NABOO_FIGHTER;
    public static int currentWave = 1;
    public static final Player PLAYER_SHIP = new Player(new Space(), new Point(100, 400));
    public static int playerCredits = 0;
}
