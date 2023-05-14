package ch.bbcag;


import ch.bbcag.common.Global;
import ch.bbcag.common.Navigator;
import ch.bbcag.common.SceneType;

import ch.bbcag.game.scenes.battles.BattleOfCoruscant;
import ch.bbcag.game.scenes.battles.BattleOfYavin;
import ch.bbcag.gui.HomeScreen;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("SISI");
        primaryStage.setWidth(Global.WINDOW_WITH);
        primaryStage.setHeight(Global.WINDOW_HEIGHT);
        primaryStage.setResizable(false);
        Navigator navigator = new Navigator(primaryStage);
        navigator.registerScene(SceneType.BATTLE_OF_CORUSCANT, new BattleOfCoruscant(navigator));
        navigator.registerScene(SceneType.BATTLE_OF_YAVIN, new BattleOfYavin(navigator));
        navigator.registerScene(SceneType.HOME_SCREEN, new HomeScreen(navigator));
        navigator.navigateTo(SceneType.HOME_SCREEN);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
