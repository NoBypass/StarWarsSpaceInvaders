package ch.bbcag.game.scenes;

import ch.bbcag.common.Keyboard;
import ch.bbcag.common.Navigator;
import ch.bbcag.game.Space;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public abstract class Battle extends NavigatableScene {
    protected Navigator navigator;

    protected Space space = new Space();
    protected static Group rootNode;
    protected long lastTimeInNanoSec;
    protected Canvas canvas;
    protected GraphicsContext gc;
    protected int currentWave = 0;

    public Battle(Group rootNode) {
        super(rootNode);
        this.rootNode = rootNode;
        start();
    }

    public void start() {
        canvas = new Canvas(800, 600);
        rootNode.getChildren().add(canvas);
        gc = canvas.getGraphicsContext2D();
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                long deltaInNanoSec = now - lastTimeInNanoSec;
                double deltaInSec = deltaInNanoSec / 1000000000d;
                lastTimeInNanoSec = now;

                space.update(deltaInSec);
                space.draw(gc);
                update(deltaInSec);


            }
        }.start();

        this.setOnKeyPressed(Keyboard::onPress);
        this.setOnKeyReleased(Keyboard::onRelease);
    }
    protected abstract void update(double deltaInSec);
    public abstract void reStart();
}
