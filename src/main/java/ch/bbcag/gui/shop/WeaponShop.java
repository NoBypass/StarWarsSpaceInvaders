package ch.bbcag.gui.shop;

import ch.bbcag.common.Global;
import ch.bbcag.game.Space;
import ch.bbcag.game.objects.upgrades.armors.Armor;
import ch.bbcag.game.objects.upgrades.armors.ArmorType;
import ch.bbcag.game.objects.upgrades.weapons.Weapon;
import ch.bbcag.game.objects.upgrades.weapons.WeaponType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class WeaponShop extends Shop {
    private final List<WeaponType> items = new ArrayList<>();
    private int currentItem = 0;

    public WeaponShop(List<WeaponType> items) {
        this.items.addAll(items);
        update();
    }


    @Override
    protected void btnNext() {
        currentItem++;
        if (currentItem >= items.size()) {
            currentItem = 0;
        }
    }

    @Override
    protected void btnPrevious() {
        currentItem--;
        if (currentItem < 0) {
            currentItem = items.size() - 1;
        }
    }

    @Override
    protected Image getImage() {
        Weapon item = new Weapon(new Space(), items.get(currentItem), true);
        return item.getImage();
    }

    @Override
    protected List<String> getStats() {
        Weapon item = new Weapon(new Space(), items.get(currentItem), true);
        return item.getStats();
    }

    @Override
    protected int getCost() {
        Weapon item = new Weapon(new Space(), items.get(currentItem), true);
        return item.getCost();
    }

    @Override
    protected boolean isBought() {
        if (Global.BOUGHT_WEAPONS.contains(items.get(currentItem))) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void buyItem() throws Exception {
        Weapon item = new Weapon(new Space(), items.get(currentItem), true);
        if (Global.playerCredits > item.getCost()) {
            Global.playerCredits -= item.getCost();
            Global.BOUGHT_WEAPONS.add(items.get(currentItem));
        } else {
            throw new Exception("To less money");
        }
    }

    public WeaponType getCurrentWeapon() {
        if (Global.BOUGHT_WEAPONS.contains(items.get(currentItem))) {
            return (items.get(currentItem));
        } else {
            return Global.BOUGHT_WEAPONS.get(0);
        }
    }
}