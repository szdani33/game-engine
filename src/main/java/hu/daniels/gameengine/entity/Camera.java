package hu.daniels.gameengine.entity;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toRadians;

public class Camera {
    private static final float CAM_MOVEMENT_SPEED = 0.02f;
    private static final float CAM_ROTATION_SPEED = 0.5f;

    private Vector3f position = new Vector3f(0 ,0, 0);
    private float pitch;
    private float yaw;
    private float roll;

    public void move() {
        if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
            position.x -= CAM_MOVEMENT_SPEED * cos(toRadians(yaw));
            position.z -= CAM_MOVEMENT_SPEED * sin(toRadians(yaw));
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
            position.x += CAM_MOVEMENT_SPEED * cos(toRadians(yaw));
            position.z += CAM_MOVEMENT_SPEED * sin(toRadians(yaw));
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
            position.x += CAM_MOVEMENT_SPEED * sin(toRadians(yaw));
            position.y -= CAM_MOVEMENT_SPEED * sin(toRadians(pitch));
            position.z -= CAM_MOVEMENT_SPEED * cos(toRadians(yaw)) * cos(toRadians(pitch));
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
            position.x -= CAM_MOVEMENT_SPEED * sin(toRadians(yaw));
            position.y += CAM_MOVEMENT_SPEED * sin(toRadians(pitch));
            position.z += CAM_MOVEMENT_SPEED * cos(toRadians(yaw)) * cos(toRadians(pitch));
        }
//        if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
//            position.y += CAM_MOVEMENT_SPEED ;
//        }
//        if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
//            position.y -= CAM_MOVEMENT_SPEED ;
//        }
    }

    public void rotate() {
        if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD8)) {
            pitch -= CAM_ROTATION_SPEED;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD5)) {
            pitch += CAM_ROTATION_SPEED;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD4)) {
            yaw -= CAM_ROTATION_SPEED;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD6)) {
            yaw += CAM_ROTATION_SPEED;
        }
//        if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD7)) {
//            roll -= CAM_ROTATION_SPEED;
//        }
//        if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD9)) {
//            roll += CAM_ROTATION_SPEED;
//        }
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public float getPitch() {
        return pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public float getRoll() {
        return roll;
    }

    public void setRoll(float roll) {
        this.roll = roll;
    }
}
