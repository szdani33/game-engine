package hu.daniels.gameengine;

import org.lwjgl.opengl.Display;

import hu.daniels.gameengine.model.RawModel;
import hu.daniels.gameengine.model.TexturedModel;
import hu.daniels.gameengine.shader.StaticShaderProgram;
import hu.daniels.gameengine.texture.ModelTexture;

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

    private static final float[] TEXTURE_COORDINATES = {
            0, 0,
            0, 1,
            1, 0,
            1, 1
    };

    private static Loader loader = new Loader();
    private static Renderer renderer = new Renderer();
    private static StaticShaderProgram staticShaderProgram;

    public static void main(String[] args) {
        DisplayManager.createDisplay();
        staticShaderProgram = new StaticShaderProgram();

        RawModel rawModel = loader.loadIntoVAO(VERTICES, TEXTURE_COORDINATES, INDICES);
        ModelTexture modelTexture = new ModelTexture(loader.loadTexture("fcb.png"));
        TexturedModel texturedModel = new TexturedModel(rawModel, modelTexture);

        while (!Display.isCloseRequested()) {
            renderer.prepare();
            staticShaderProgram.start();
            renderer.render(texturedModel);
            staticShaderProgram.stop();
            DisplayManager.updateDisplay();
        }
        staticShaderProgram.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();
    }
}
