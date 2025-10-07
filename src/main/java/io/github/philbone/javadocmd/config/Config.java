package io.github.philbone.javadocmd.config;

/**
 * @project JavadocMd
 * @author Felipe M.
 */
public class Config
{
    private String sourcePath = "/directorio_default_fuente/src";
    private String outputPath = "/directorio_default_salida/doc";

    // Constructor público (importante)
    public Config() {
        // Constructor vacío necesario para Jackson
    }

    public String getConfigSourcePath() {
        return sourcePath;
    }

    public void setConfigSourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
    }

    public String getConfigOutputPath() {
        return outputPath;
    }

    public void setConfigOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    @Override
    public String toString() {
        return String.format("Config{sourcePath='%s', outputPath='%s'}", sourcePath, outputPath);
    }
}
