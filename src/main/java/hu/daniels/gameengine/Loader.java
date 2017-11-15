package hu.daniels.gameengine;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

public class Loader {

    private List<Integer> vaos = new ArrayList<>();
    private List<Integer> vbos = new ArrayList<>();

    public RawModel loadIntoVAO(float[] positions, int[] indices) {
        int vaoId = createVAO();
        bindIndicesBuffer(indices);
        storeDataInAttributeList(0, positions);
        unbindVAO();
        return new RawModel(vaoId, indices.length);
    }

    public void cleanUp() {
        for (int vaoId : vaos) {
            GL30.glDeleteVertexArrays(vaoId);
        }
        for (int vboId : vbos) {
            GL15.glDeleteBuffers(vboId);
        }
    }

    private int createVAO() {
        int vaoId = GL30.glGenVertexArrays();
        vaos.add(vaoId);
        GL30.glBindVertexArray(vaoId);
        return vaoId;
    }

    private void storeDataInAttributeList(int attributeNumber, float[] data) {
        int vboId = GL15.glGenBuffers();
        vbos.add(vboId);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboId);
        FloatBuffer floatBuffer = createFloatBuffer(data);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, floatBuffer, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(attributeNumber, 3, GL11.GL_FLOAT, false, 0, 0);
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
