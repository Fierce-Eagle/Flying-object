package com.company.engine.input;

import com.company.engine.EngineWindow;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorEnterCallback;
import org.lwjgl.glfw.GLFWCursorPosCallbackI;

public class Mouse {

    public static double mouseX, mouseY;
    public static boolean isEntered;

    private static boolean[] buttons = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST];


    public static boolean buttonDown(int buttonID) {
        return GLFW.glfwGetMouseButton(EngineWindow.getWindow().id, buttonID) == GLFW.GLFW_TRUE;
    }

    public static boolean buttonPressed(int buttonID) {
        return buttonDown(buttonID) && !buttons[buttonID];
    }

    public static boolean buttonReleased(int buttonID) {
        return !buttonDown(buttonID) && buttons[buttonID];
    }

    public static void handleMouseInput() {
        for (int i = 0; i < GLFW.GLFW_MOUSE_BUTTON_LAST; i++) {
            buttons[i] = buttonDown(i);
        }
    }

    public static void setMouseCallbacks(long id) {
        setCursorPosCallback(id);
        setCursorEnterCallback(id);
    }

    public static void setCursorPosCallback(long id) {
        GLFW.glfwSetCursorPosCallback(id, new GLFWCursorPosCallbackI() {
            @Override
            public void invoke(long l, double v, double v1) {
                mouseX = v;
                mouseY = v1;
            }
        });
    }

    public static void setCursorEnterCallback(long id) {
        GLFW.glfwSetCursorEnterCallback(id, new GLFWCursorEnterCallback() {
            @Override
            public void invoke(long l, boolean b) {
                isEntered = b;
            }
        });
    }
}
