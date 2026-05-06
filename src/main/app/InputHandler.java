package app;

import javafx.scene.input.KeyCode;
import java.util.HashSet;
import java.util.Set;

/**
 * Handles input for the game. This class will keep track of which keys are
 * currently pressed, which keys were just pressed, and which keys were just
 * released. It will also keep track of the last key that was pressed, which can
 * be useful for certain game mechanics. The input handler will be updated every
 * frame, and will provide methods for checking the state of keys.
 */
public class InputHandler {

    private final Set<KeyCode> keysDown = new HashSet<>();
    private final Set<KeyCode> keysPressed = new HashSet<>();
    private final Set<KeyCode> keysReleased = new HashSet<>();

    String lastKey = "";

    /**
     * Called when a key is pressed.
     * 
     * @param key The key that was pressed.
     */
    public void keyPressed(KeyCode key) {
        if (!keysDown.contains(key)) {
            keysPressed.add(key);
        }
        keysDown.add(key);

        lastKey = key.toString();
    }

    /**
     * Called when a key is released.
     * 
     * @param key The key that was released.
     */
    public void keyReleased(KeyCode key) {
        keysDown.remove(key);
        keysReleased.add(key);
    }

    /**
     * Check if a key is currently down, was just pressed, or was just released.
     * 
     * @param key The key to check.
     * @return True if the key is in the specified state, false otherwise.
     */
    public boolean isKeyDown(KeyCode key) {
        return keysDown.contains(key);
    }

    /**
     * Check if a key was just pressed or just released.
     * 
     * @param key The key to check.
     * @return True if the key was just pressed or just released, false otherwise.
     */
    public boolean isKeyPressed(KeyCode key) {
        return keysPressed.contains(key);
    }

    /**
     * Check if a key was just released.
     * 
     * @param key The key to check.
     * @return True if the key was just released, false otherwise.
     */
    public boolean isKeyReleased(KeyCode key) {
        return keysReleased.contains(key);
    }

    /**
     * Called at the end of each frame to reset the state of keys that were just
     * pressed or released.
     */
    public void endFrame() {
        keysPressed.clear();
        keysReleased.clear();
    }

    /**
     * Get the last key that was pressed. This can be useful for certain game
     * mechanics, such as text input or command input.
     * 
     * @return The last key that was pressed, as a string.
     */
    public String getLastKey() {
        return lastKey;
    }

    /**
     * Reset all input states. This will clear all sets of keys and reset the
     * lastkey to an empty string. This can be useful when starting a new game or
     * returning to the main menu, to ensure that no keys are stuck in a pressed
     * state.
     */
    public void resetAll() {
        keysDown.clear();
        keysPressed.clear();
        keysReleased.clear();
        lastKey = "";
    }
}
