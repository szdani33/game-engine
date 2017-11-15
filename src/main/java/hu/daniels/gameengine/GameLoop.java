package hu.daniels.gameengine;

import org.lwjgl.opengl.Display;

import hu.daniels.gameengine.shader.StaticShaderProgram;

public class GameLoop {
    private static final float[] VERTICES = {
            -0.5f, 0.5f, 0f,
            -0.5f, -0.5f, 0f,
            0.5f, 0.5f, 0f,
            0.5f, -0.5f, 0f
    };

    private static final int[] INDICES = {
            0, 1, 2,
            2, 1, 3
    };

    private static Loader loader = new Loader();
    private static Renderer renderer = new Renderer();
    private static StaticShaderProgram staticShaderProgram;

    public static void main(String[] args) {
        DisplayManager.createDisplay();
        staticShaderProgram = new StaticShaderProgram();
        RawModel rawModel = loader.loadIntoVAO(VERTICES, INDICES);
        while (!Display.isCloseRequested()) {
            renderer.prepare();
            staticShaderProgram.start();
            renderer.render(rawModel);
            staticShaderProgram.stop();
            DisplayManager.updateDisplay();
        }
        staticShaderProgram.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();
    }
}
