package app;

import javafx.scene.input.KeyCode;
import java.util.HashSet;
import java.util.Set;

public class InputHandler {

    private final Set<KeyCode> keysDown = new HashSet<>();
    private final Set<KeyCode> keysPressed = new HashSet<>();
    private final Set<KeyCode> keysReleased = new HashSet<>();

    String lastKey = "";

    public void keyPressed(KeyCode key) {
        if (!keysDown.contains(key)) {
            keysPressed.add(key);
        }
        keysDown.add(key);

        lastKey = key.toString();
    }

    public void keyReleased(KeyCode key) {
        keysDown.remove(key);
        keysReleased.add(key);
    }

    public boolean isKeyDown(KeyCode key) {
        return keysDown.contains(key);
    }

    public boolean isKeyPressed(KeyCode key) {
        return keysPressed.contains(key);
    }

    public boolean isKeyReleased(KeyCode key) {
        return keysReleased.contains(key);
    }

    public void endFrame() {
        keysPressed.clear();
        keysReleased.clear();
    }

    public String getLastKey() {
        return lastKey;
    }

    public void resetAll() {
        keysDown.clear();
        keysPressed.clear();
        keysReleased.clear();
        lastKey = "";
    }
}
