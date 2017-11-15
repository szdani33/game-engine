package hu.daniels.gameengine.shader;

public class StaticShaderProgram extends ShaderProgram {
    private static final String SHADER_LOCATION = "src/main/resources/shaders/";
    private static final String VERTEX_SHADER_FILE = SHADER_LOCATION + "vertexShader.txt";
    private static final String FRAGMENT_SHADER_FILE = SHADER_LOCATION + "fragmentShader.txt";

    public StaticShaderProgram() {
        super(VERTEX_SHADER_FILE, FRAGMENT_SHADER_FILE);
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoordinates");
    }
}
