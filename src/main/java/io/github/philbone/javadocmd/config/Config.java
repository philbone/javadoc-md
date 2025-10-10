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
    
    private boolean includePrivate;
    private boolean includeProtected;
    private boolean includePublic;
    
    private boolean tableOfContent;
    
    private boolean printEmptyNotify;
    
    // Constructor con valores por defecto
    public Config() {
        this.sourcePath = "/src";
        this.outputPath = "/doc";
        //this.outFileName = "README.md";
        this.debugMode = false;
        this.combinePackagesMode = false;
        this.tableOfContent = true;
        this.printEmptyNotify = true;
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
     * @param combinePackages  
     */
    public void setCombinePackagesMode(boolean combinePackages) {
        this.combinePackagesMode = combinePackages;
    }

    public boolean isIncludePrivate() {
        return includePrivate;
    }

    public void setIncludePrivate(boolean includePrivate) {
        this.includePrivate = includePrivate;
    }

    public boolean isIncludeProtected() {
        return includeProtected;
    }

    public void setIncludeProtected(boolean includeProtected) {
        this.includeProtected = includeProtected;
    }

    public boolean isIncludePublic() {
        return includePublic;
    }

    public void setIncludePublic(boolean includePublic) {
        this.includePublic = includePublic;
    }

    public boolean isTableOfContent() {
        return tableOfContent;
    }

    public void setTableOfContent(boolean tableOfContent) {
        this.tableOfContent = tableOfContent;
    }

    public boolean isPrintEmptyNotify() {
        return printEmptyNotify;
    }

    public void setPrintEmptyNotify(boolean printEmptyNotify) {
        this.printEmptyNotify = printEmptyNotify;
    }
    
}
