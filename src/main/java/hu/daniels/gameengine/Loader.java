package hu.daniels.gameengine;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import hu.daniels.gameengine.model.RawModel;

public class Loader {
    private static final String TEXTURE_LOCATION = "src/main/resources/textures/";

    private List<Integer> vaos = new ArrayList<>();
    private List<Integer> vbos = new ArrayList<>();
    private List<Integer> textures = new ArrayList<>();

    public RawModel loadIntoVAO(float[] positions, float[] textureCoordinates, int[] indices) {
        int vaoId = createVAO();
        bindIndicesBuffer(indices);
        storeDataInVAO(0, 3, positions);
        storeDataInVAO(1, 2, textureCoordinates);
        unbindVAO();
        return new RawModel(vaoId, indices.length);
    }

    public int loadTexture(String fileName) {
        try {
            Texture texture = TextureLoader.getTexture("PNG", new FileInputStream(TEXTURE_LOCATION + fileName));
            int textureID = texture.getTextureID();
            textures.add(textureID);
            return textureID;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void cleanUp() {
        for (int vaoId : vaos) {
            GL30.glDeleteVertexArrays(vaoId);
        }
        for (int vboId : vbos) {
            GL15.glDeleteBuffers(vboId);
        }
        for (int textureId : textures) {
            GL11.glDeleteTextures(textureId);
        }
    }

    private int createVAO() {
        int vaoId = GL30.glGenVertexArrays();
        vaos.add(vaoId);
        GL30.glBindVertexArray(vaoId);
        return vaoId;
    }

    private void storeDataInVAO(int attributeIndex, int coordinateSize, float[] data) {
        int vboId = GL15.glGenBuffers();
        vbos.add(vboId);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboId);
        FloatBuffer floatBuffer = createFloatBuffer(data);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, floatBuffer, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(attributeIndex, coordinateSize, GL11.GL_FLOAT, false, 0, 0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
    }

    private void bindIndicesBuffer(int[] indices) {
        int vboId = GL15.glGenBuffers();
        vbos.add(vboId);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboId);
        IntBuffer intBuffer = createIntBuffer(indices);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, intBuffer, GL15.GL_STATIC_DRAW);

    }

    private void unbindVAO() {
        GL30.glBindVertexArray(0);
    }

    private IntBuffer createIntBuffer(int[] data) {
        IntBuffer intBuffer = BufferUtils.createIntBuffer(data.length);
        intBuffer.put(data);
        intBuffer.flip();
        return intBuffer;
    }

    private FloatBuffer createFloatBuffer(float[] data) {
        FloatBuffer floatBuffer = BufferUtils.createFloatBuffer(data.length);
        floatBuffer.put(data);
        floatBuffer.flip();
        return floatBuffer;
    }
}
