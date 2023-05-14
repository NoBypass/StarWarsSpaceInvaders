package ch.bbcag.common;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.List;

public class Keyboard {
    private static List<KeyCode> pressedKeys = new ArrayList<KeyCode>();

    public static void onPress(KeyEvent keyEvent) {
        if (pressedKeys.contains(keyEvent.getCode())) return;
        pressedKeys.add(keyEvent.getCode());
    }

    public static void onRelease(KeyEvent keyEvent) {
        pressedKeys.remove(keyEvent.getCode());
    }
    public static void releaseAll(){pressedKeys.clear();}

    public static Boolean isPressed(KeyCode key) {
        if (pressedKeys.contains(key)) return true;
        return false;
    }
}
