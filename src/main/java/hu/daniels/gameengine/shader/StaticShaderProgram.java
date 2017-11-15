package hu.daniels.gameengine.shader;

public class StaticShaderProgram extends ShaderProgram {

    private static final String VERTEX_SHADER_FILE = "src/main/resources/vertexShader.txt";
    private static final String FRAGMENT_SHADER_FILE = "src/main/resources/fragmentShader.txt";

    public StaticShaderProgram() {
        super(VERTEX_SHADER_FILE, FRAGMENT_SHADER_FILE);
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
    }
}
