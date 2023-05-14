package ch.bbcag.game.objects;

import ch.bbcag.common.Global;
import ch.bbcag.common.Point;
import ch.bbcag.game.Space;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class CreditLabel extends GameObject {
    public CreditLabel(Space space, Point point) {
        super(space);
        x = point.getX();
        y = point.getY();
    }
    @Override
    public void draw(GraphicsContext gc){
        gc.setFill(Color.color(1,1,1));
        gc.setFont(new Font("Arial", 20));
        gc.fillText("Credits: "+Global.playerCredits,x,y+20, Double.MAX_VALUE);
    }

    @Override
    public void update(double deltaInSec) {

    }
}
