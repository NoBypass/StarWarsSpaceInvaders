package ch.bbcag.gui.shop;

import ch.bbcag.common.Global;
import ch.bbcag.game.Space;
import ch.bbcag.game.objects.upgrades.armors.Armor;
import ch.bbcag.game.objects.upgrades.armors.ArmorType;
import ch.bbcag.game.objects.upgrades.weapons.Weapon;
import ch.bbcag.game.objects.upgrades.weapons.WeaponType;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class ArmorShop extends Shop {
    private final List<ArmorType> items = new ArrayList<>();
    private int currentItem = 0;

    public ArmorShop(List<ArmorType> items) {
        this.items.addAll(items);
        update();
    }
    public ArmorType getCurrentArmor() {
        if (Global.BOUGHT_ARMOR.contains(items.get(currentItem))) {
            return (items.get(currentItem));
        } else {
            return Global.BOUGHT_ARMOR.get(0);
        }
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
        Armor item = new Armor(new Space(), items.get(currentItem));
        return item.getImage();
    }

    @Override
    protected List<String> getStats() {
        Armor item = new Armor(new Space(), items.get(currentItem));
        return item.getStats();
    }

    @Override
    protected int getCost() {
        Armor item = new Armor(new Space(), items.get(currentItem));
        return item.getCost();
    }

    @Override
    protected boolean isBought() {
        if (Global.BOUGHT_ARMOR.contains(items.get(currentItem))) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void buyItem() throws Exception {
        Armor item = new Armor(new Space(), items.get(currentItem));
        if (Global.playerCredits > item.getCost()) {
            Global.playerCredits -= item.getCost();
            Global.BOUGHT_ARMOR.add(items.get(currentItem));
        } else {
            throw new Exception("To less money");
        }
    }
}