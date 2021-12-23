package com.company.engine.input;

import com.company.engine.EngineWindow;
import org.lwjgl.glfw.GLFW;

public class Keyboard {

    private static boolean[] keys = new boolean[GLFW.GLFW_KEY_LAST];
    public static boolean keyDown(int keyID) {
        return GLFW.glfwGetKey(EngineWindow.getWindow().id, keyID) == GLFW.GLFW_TRUE;
    }

    public static boolean keyPressed(int keyID) {
        return keyDown(keyID) && !keys[keyID];
    }

    public static boolean keyReleased(int keyID) {
        return !keyDown(keyID) && keys[keyID];
    }

    public static void handleKeyboardInput() {
        for (int i = 0; i < GLFW.GLFW_KEY_LAST; i++) {
            keys[i] = keyDown(i);
        }
    }
}
