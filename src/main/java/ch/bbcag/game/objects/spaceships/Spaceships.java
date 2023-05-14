package ch.bbcag.game.objects.spaceships;

import ch.bbcag.game.Space;
import ch.bbcag.game.objects.GameObject;

public abstract class Spaceships extends GameObject {
    protected int difficultyLevel;
    public Spaceships(Space space, int difficultyLevel) {
        super(space);
        this.space = space;
        this.difficultyLevel = difficultyLevel;
    }
}
