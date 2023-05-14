package ch.bbcag.gui.shop;

import ch.bbcag.common.Global;
import ch.bbcag.game.Space;
import ch.bbcag.game.objects.upgrades.armors.Armor;
import ch.bbcag.game.objects.upgrades.armors.ArmorType;
import ch.bbcag.game.objects.upgrades.ships.Ship;
import ch.bbcag.game.objects.upgrades.ships.ShipType;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class ShipShop extends Shop {
    private final List<ShipType> items = new ArrayList<>();
    private int currentItem = 0;

    public ShipShop(List<ShipType> items) {
        this.items.addAll(items);
        update();
    }
    public ShipType getCurrentShip() {
        if (Global.BOUGHT_SHIPS.contains(items.get(currentItem))) {
            return (items.get(currentItem));
        } else {
            return Global.BOUGHT_SHIPS.get(0);
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
        Ship item = new Ship(new Space(), items.get(currentItem));
        return item.getTexture();
    }

    @Override
    protected List<String> getStats() {
        Ship item = new Ship(new Space(), items.get(currentItem));
        return item.getStats();
    }

    @Override
    protected int getCost() {
        Ship item = new Ship(new Space(), items.get(currentItem));
        return item.getCost();
    }

    @Override
    protected boolean isBought() {
        if (Global.BOUGHT_SHIPS.contains(items.get(currentItem))) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void buyItem() throws Exception {
        Ship item = new Ship(new Space(), items.get(currentItem));
        if (Global.playerCredits > item.getCost()) {
            Global.playerCredits -= item.getCost();
            Global.BOUGHT_SHIPS.add(items.get(currentItem));
        } else {
            throw new Exception("To less money");
        }
    }
}