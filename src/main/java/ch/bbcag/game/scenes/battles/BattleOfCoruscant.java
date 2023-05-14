package ch.bbcag.game.scenes.battles;

import ch.bbcag.common.*;
import ch.bbcag.game.objects.Background;
import ch.bbcag.game.objects.CreditLabel;
import ch.bbcag.game.objects.GameObject;
import ch.bbcag.game.objects.spaceships.Enemy;
import ch.bbcag.game.objects.spaceships.EnemyType;
import ch.bbcag.game.objects.spaceships.HealthBar;
import ch.bbcag.game.objects.spaceships.Player;
import ch.bbcag.game.scenes.Battle;
import javafx.scene.Group;

public class BattleOfCoruscant extends Battle {
    private Player player = new Player(space, new Point(400, 400));

    public BattleOfCoruscant(Navigator navigator) {
        super(new Group());
        this.navigator = navigator;
        space.add(new Background(space, "graphics/backgrounds/background_game.png"));
        space.add(new CreditLabel(space, new Point(10,10)));

    }

    private int getEnemyCount(int value) {
        return Math.round(currentWave / value);
    }

    @Override
    protected void update(double deltaInSec) {
        if (!player.isAlive() && navigator.getCurentSceneType() == SceneType.BATTLE_OF_CORUSCANT) {
            navigator.navigateTo(SceneType.HOME_SCREEN);
            return;
        }

        for (GameObject spaceObject : space) {
            if (spaceObject instanceof Enemy) return;
        }
        currentWave++;
        Object[][] enemySchema = {
                {EnemyType.TIE_FIGHTER, 2},
                {EnemyType.TIE_DEFENDER, 3},
                {EnemyType.STAR_DESTROYER, 5},
        };

        //Spawning new Enemies
        for (Object[] i : enemySchema) {
            for (int j = 0; j < Math.round(currentWave / (int) i[1]); j++) {
                for (int k = 0; k < j; k++) {
                    space.add(new Enemy(space, new Point(Math.random() * Global.WINDOW_WITH, Math.random() * 50 + 20), 1, (EnemyType) i[0]));
                }
            }
        }
    }

    @Override
    public void reStart() {
        //Clean up
        Keyboard.releaseAll();
        space.remove(player);
        for (Enemy enemy : space.getAllObjectsFromType(Enemy.class)){
            space.remove(enemy);
        }
        for (HealthBar healthBar : space.getAllObjectsFromType(HealthBar.class)){
            space.remove(healthBar);
        }

        currentWave = 1;
        player = new Player(space, new Point(400, 400));
        space.add(player);
        space.add(new Enemy(space, new Point(300, 20), 1, EnemyType.TIE_FIGHTER));
    }
}
