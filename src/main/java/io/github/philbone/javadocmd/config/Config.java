package io.github.philbone.javadocmd.config;

public class Config
{
    private String sourcePath;
    private String outputPath;
    private String outFileName;
    private boolean debugMode;
    
    // Constructor con valores por defecto
    public Config() {
        this.sourcePath = "/src";
        this.outputPath = "/doc";
        this.debugMode = false;
    }
    
    // Getters y setters con los mismos nombres que el YAML
    public String getSourcePath() { return sourcePath; }
    public void setSourcePath(String sourcePath) { this.sourcePath = sourcePath; }
    
    public String getOutputPath() { return outputPath; }
    public void setOutputPath(String outputPath) { this.outputPath = outputPath; }

    public String getOutFileName() { return outFileName; }
    public void setOutFileName(String outFileName) { this.outFileName = outFileName; }

    public boolean isDebugMode() { return debugMode; }
    public void setDebugMode(boolean debugMode) { this.debugMode = debugMode; }
}
