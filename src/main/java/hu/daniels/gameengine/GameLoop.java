package hu.daniels.gameengine;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import hu.daniels.gameengine.entity.Entity;
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

    public static void main(String[] args) {
        DisplayManager.createDisplay();
        Loader loader = new Loader();
        StaticShaderProgram staticShaderProgram = new StaticShaderProgram();
        Renderer renderer = new Renderer(staticShaderProgram);

        RawModel rawModel = loader.loadIntoVAO(VERTICES, TEXTURE_COORDINATES, INDICES);
        ModelTexture modelTexture = new ModelTexture(loader.loadTexture("fcb.png"));
        TexturedModel texturedModel = new TexturedModel(rawModel, modelTexture);
        Entity entity = new Entity(texturedModel, new Vector3f(0, 0, -1), 0, 0, 0, 1);

        while (!Display.isCloseRequested()) {
            entity.increasePosition(0, 0, -0.02f);
            entity.increaseRotation(0, 1, 1);
            renderer.prepare();
            staticShaderProgram.start();
            renderer.render(entity, staticShaderProgram);
            staticShaderProgram.stop();
            DisplayManager.updateDisplay();
        }
        staticShaderProgram.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();
    }
}
