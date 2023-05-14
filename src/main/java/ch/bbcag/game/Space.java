package ch.bbcag.game;

import ch.bbcag.game.objects.GameObject;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Space extends CopyOnWriteArrayList<GameObject> {
    public <T> List<T> getAllObjectsFromType(Class<T> classToFind) {
        List<T> resultList = new ArrayList<T>();
        for (Object o : this) {
            if (classToFind.equals(o.getClass())) resultList.add((T) o);
        }
        return resultList;
    }
    public void draw(GraphicsContext gc) {
        for (GameObject object : this){
            object.draw(gc);
        }
    }
    public void update(double deltaInSec){
        for (GameObject object : this){
            object.update(deltaInSec);
        }
    }

}
