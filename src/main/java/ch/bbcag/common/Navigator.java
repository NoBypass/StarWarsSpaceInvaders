package ch.bbcag.common;

import ch.bbcag.game.scenes.NavigatableScene;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class Navigator {
    private Stage stage;
    private Map<SceneType, NavigatableScene> sceneMap = new HashMap<>();
    private SceneType curentScene;

    public Navigator(Stage stage){
        this.stage = stage;
    }
    public void registerScene(SceneType sceneType, NavigatableScene scene){
        sceneMap.put(sceneType,scene);
    }

    public void navigateTo(SceneType sceneType){
        sceneMap.get(sceneType).getStylesheets().add("/styles/style.css");
        stage.setScene(sceneMap.get(sceneType));
        curentScene = sceneType;
        stage.show();
    }

    public SceneType getCurentSceneType() {
        return curentScene;
    }
    public NavigatableScene getCurentScene() {
        return sceneMap.get(curentScene);
    }
}

