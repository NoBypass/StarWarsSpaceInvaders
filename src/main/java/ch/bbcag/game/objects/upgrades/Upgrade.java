package ch.bbcag.game.objects.upgrades;

import ch.bbcag.game.Space;
import ch.bbcag.game.objects.GameObject;

import java.util.List;


public abstract class Upgrade extends GameObject {

    protected int cost;

    public Upgrade(Space space) {
        super(space);
    }
    public int getCost(){return cost;}
    abstract public List<String> getStats();


}
