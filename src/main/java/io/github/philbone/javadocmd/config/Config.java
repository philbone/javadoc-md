package io.github.philbone.javadocmd.config;

/**
 * @project JavadocMD
 * @author Felipe M. <philbone@focused.cl>
 */
public class Config
{

    /**
     * Directorio de entrada donde se encuentran las clases a documentar. *
     */
    private String sourcePath;

    /**
     * Directorio base donde se escribirá la documentación generada.
     */
    private String outputPath;

    /**
     * Nombre del archivo de salida que contendrá la documentación en cada
     * paquete.
     */
    private String outFileName;

    /**
     * Bandera de depuración para imprimir trazas adicionales.
     */
    private boolean debugMode;
    
    /**
     * Bandera para definir el modo de exportación
     * false exportar un fichero por cada paquete
     * true exportar un fichero de forma global.
     */
    private boolean combinePackagesMode;

    // Constructor con valores por defecto
    public Config() {
        this.sourcePath = "/src";
        this.outputPath = "/doc";
        this.debugMode = false;
    }

    /**
     * Obtiene la ruta del código fuente a documentar.
     * @return el directorio donde se encuentran el código fuente.
     */
    public String getSourcePath() {
        return sourcePath;
    }
    
    /**
     * 
     * @param sourcePath 
     */
    public void setSourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
    }
    
    /**
     * 
     * @return 
     */
    public String getOutputPath() {
        return outputPath;
    }
    
    /**
     * 
     * @param outputPath 
     */
    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }
    
    /**
     * 
     * @return 
     */
    public String getOutFileName() {
        return outFileName;
    }
    
    /**
     * 
     * @param outFileName 
     */
    public void setOutFileName(String outFileName) {
        this.outFileName = outFileName;
    }
    
    /**
     * 
     * @return 
     */
    public boolean isDebugMode() {
        return debugMode;
    }
    
    /**
     * 
     * @param debugMode 
     */
    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }
    
    /**
     * 
     * @return
     */
    public boolean isCombinePackagesMode() {
        return combinePackagesMode;
    }
    
    /**
     * 
     * @param multiFileMode 
     */
    public void setCombinePackagesMode(boolean combinePackages) {
        this.combinePackagesMode = combinePackages;
    }
    
    
}
