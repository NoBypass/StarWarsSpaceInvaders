package ch.bbcag.gui.shop;

import ch.bbcag.common.Global;
import ch.bbcag.game.Space;
import ch.bbcag.game.objects.upgrades.motors.Motor;
import ch.bbcag.game.objects.upgrades.motors.MotorType;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class MotorShop extends Shop {
    private final List<MotorType> items = new ArrayList<>();
    private int currentItem = 0;

    public MotorShop(List<MotorType> items) {
        this.items.addAll(items);
        update();
    }
    public MotorType getCurrentMotor() {
        if (Global.BOUGHT_MOTORS.contains(items.get(currentItem))) {
            return (items.get(currentItem));
        } else {
            return Global.BOUGHT_MOTORS.get(0);
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
        Motor item = new Motor(new Space(), items.get(currentItem));
        return item.getImage();
    }

    @Override
    protected List<String> getStats() {
        Motor item = new Motor(new Space(), items.get(currentItem));
        return item.getStats();
    }

    @Override
    protected int getCost() {
        Motor item = new Motor(new Space(), items.get(currentItem));
        return item.getCost();
    }

    @Override
    protected boolean isBought() {
        if (Global.BOUGHT_MOTORS.contains(items.get(currentItem))) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void buyItem() throws Exception {
        Motor item = new Motor(new Space(), items.get(currentItem));
        if (Global.playerCredits > item.getCost()) {
            Global.playerCredits -= item.getCost();
            Global.BOUGHT_MOTORS.add(items.get(currentItem));
        } else {
            throw new Exception("To less money");
        }
    }
}