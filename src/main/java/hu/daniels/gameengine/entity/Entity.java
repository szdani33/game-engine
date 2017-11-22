package hu.daniels.gameengine.entity;

import org.lwjgl.util.vector.Vector3f;

import hu.daniels.gameengine.model.TexturedModel;

public class Entity {

    private TexturedModel model;
    private Vector3f position;
    private float rotX;
    private float rotY;
    private float rotZ;
    private float scale;

    public Entity(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
        this.model = model;
        this.position = position;
        this.rotX = rotX;
        this.rotY = rotY;
        this.rotZ = rotZ;
        this.scale = scale;
    }

    public void increasePosition(float deltaX, float deltaY, float deltaZ) {
        position.x += deltaX;
        position.y += deltaY;
        position.z += deltaZ;
    }

    public void increaseRotation(float deltaX, float deltaY, float deltaZ) {
        rotX += deltaX;
        rotY += deltaY;
        rotZ += deltaZ;
    }

    public TexturedModel getModel() {
        return model;
    }

    public void setModel(TexturedModel model) {
        this.model = model;
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public float getRotX() {
        return rotX;
    }

    public void setRotX(float rotX) {
        this.rotX = rotX;
    }

    public float getRotY() {
        return rotY;
    }

    public void setRotY(float rotY) {
        this.rotY = rotY;
    }

    public float getRotZ() {
        return rotZ;
    }

    public void setRotZ(float rotZ) {
        this.rotZ = rotZ;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }
}
