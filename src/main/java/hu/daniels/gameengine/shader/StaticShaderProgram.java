package hu.daniels.gameengine.shader;

import org.lwjgl.util.vector.Matrix4f;

import hu.daniels.gameengine.entity.Camera;
import hu.daniels.gameengine.util.MathUtils;

public class StaticShaderProgram extends ShaderProgram {
    private static final String SHADER_LOCATION = "src/main/resources/shaders/";
    private static final String VERTEX_SHADER_FILE = SHADER_LOCATION + "vertexShader.txt";
    private static final String FRAGMENT_SHADER_FILE = SHADER_LOCATION + "fragmentShader.txt";

    private int transformationMatrixLocation;
    private int projectionMatrixLocation;
    private int viewMatrixLocation;

    public StaticShaderProgram() {
        super(VERTEX_SHADER_FILE, FRAGMENT_SHADER_FILE);
    }

    @Override
    protected void getAllUniformLocations() {
        transformationMatrixLocation = super.getUniformLocation("transformationMatrix");
        projectionMatrixLocation = super.getUniformLocation("projectionMatrix");
        viewMatrixLocation = super.getUniformLocation("viewMatrix");
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoordinates");
    }

    public void loadTransformationMatrix(Matrix4f matrix) {
        super.loadMatrix(transformationMatrixLocation, matrix);
    }

    public void loadViewMatrix(Camera camera) {
        Matrix4f matrix = MathUtils.createViewMatrix(camera);
        super.loadMatrix(viewMatrixLocation, matrix);
    }

    public void loadProjectionMatrix(Matrix4f matrix) {
        super.loadMatrix(projectionMatrixLocation, matrix);
    }
}
