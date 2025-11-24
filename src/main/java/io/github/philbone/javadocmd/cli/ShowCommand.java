package io.github.philbone.javadocmd.cli;

import io.github.philbone.javadocmd.config.Config;
import io.github.philbone.javadocmd.config.ConfigLoader;
import io.github.philbone.javadocmd.config.ConfigManager;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.ResourceBundle;
import java.util.concurrent.Callable;

@Command(
        name = "show",
        description = "${usage.show}",
        resourceBundle = "messages"
)
public class ShowCommand implements Callable<Integer>
{

    private final ResourceBundle appMessages;

    public ShowCommand() {
        this.appMessages = ResourceBundle.getBundle("app_messages");
    }

    @Option(
            names = {"-h", "--help"},
            usageHelp = true,
            descriptionKey = "show.help"
    )
    private boolean helpRequested;

    @Option(
            names = {"--configFile"},
            descriptionKey = "init.configFile", // Reutilizamos la misma descripción
            paramLabel = "CONFIG_FILE"
    )
    private String configFile; // ← Sin valor por defecto, lo manejaremos en el método
    
    @Option(
            names = {"--raw"},
            descriptionKey =  "${show.raw}",
            paramLabel = "BOOL_TYPE"
    )
    private boolean raw = false;

    @Override
    public Integer call() {
        try {
            // ✅ Obtener la ruta real del archivo de configuración
            String actualConfigFile = getActualConfigFilePath();

            System.out.println(appMessages.getString("message.show.loading"));

            // Verificar si existe el archivo de configuración
            if (!ConfigLoader.configExists(actualConfigFile)) {
                System.err.println(appMessages.getString("message.show.noConfig"));
                System.out.println("  "
                        + appMessages.getString("message.show.expectedPath")
                        + ": " + actualConfigFile);
                
                return 1;
            }

            // Cargar configuración
            Config config = ConfigLoader.loadConfig(actualConfigFile, false);

            if (raw){
                // Mostrar configuración raw
                showRawFile(actualConfigFile);
            } else {
                // Mostrar configuración formateada
                showConfiguration(config, actualConfigFile);
            }

            return 0;

        } catch (Exception e) {
            System.err.println(String.format(
                    appMessages.getString("message.show.loadError"),
                    e.getMessage()
            ));
            return 1;
        }
    }

    /**
     * Obtiene la ruta real del archivo de configuración - Si el usuario
     * proporcionó --configFile, usa esa ruta - Si no, usa la ruta por defecto
     * en .javadocmd/
     */
    private String getActualConfigFilePath() {
        if (configFile != null && !configFile.trim().isEmpty()) {
            return configFile; // Usuario proporcionó ruta específica
        }
        // Ruta por defecto en .javadocmd/
        return new ConfigManager().getConfigFilePath().toString();
    }

    private void showConfiguration(Config config, String configFilePath) {
        ResourceBundle messages = ResourceBundle.getBundle("messages");

        System.out.println("\n" + messages.getString("show.header"));
        System.out.println("═".repeat(100));
        
        // Mostrar la ruta del archivo de configuración
        System.out.println(String.format("               %-20s|%32s | %s", "USE", "KEY", "VALUE"));
        System.out.println("─".repeat(100));
        //System.out.println("-".repeat(50));
        printField(messages.getString("show.configFilePath"), "configFilePath", configFilePath);

        // Mostrar cada campo con formato consistente
        printField(messages.getString("show.sourcePath"), "sourcePath", config.getSourcePath());
        printField(messages.getString("show.outputPath"), "outputPath", config.getOutputPath());
        printField(messages.getString("show.outFileName"), "outFileName", config.getOutFileName());
        printField(messages.getString("show.combinePackagesMode"), "combinePackagesMode", config.isCombinePackagesMode());
        printField(messages.getString("show.includePrivate"), "includePrivate", config.isIncludePrivate());
        printField(messages.getString("show.includeProtected"), "includeProtected", config.isIncludeProtected());
        printField(messages.getString("show.includePublic"), "includePublic", config.isIncludePublic());
        printField(messages.getString("show.debugMode"), "debugMode", config.isDebugMode());
        printField(messages.getString("show.tableOfContent"), "tableOfContent", config.isTableOfContent());
        printField(messages.getString("show.printEmptyNotify"), "printEmptyNotify", config.isPrintEmptyNotify());
        printField(messages.getString("show.printClassIndex"), "printClassIndex", config.isPrintClassIndex());
        printField(messages.getString("show.foreSignClassIndex"), "foreSignClassIndex", 
                "\"" + config.getForeSignClassIndex() + "\"");
        printField(messages.getString("show.foreSignClassIndexOnDetails"), "foreSignClassIndexOnDetails",
                config.isForeSignClassIndexOnDetails());
        printField(messages.getString("show.foreSignClassIndexOnSubtitle"), "foreSignClassIndexOnSubtitle",
                config.isForeSignClassIndexOnSubtitle());
        printField(messages.getString("show.markdownLanguage"),"markdownLanguage", config.getMarkdownLanguage());
        printField(messages.getString("show.methodAnnotations"), "methodAnnotations", config.isMethodAnnotations());
                
        System.out.println("\n" + messages.getString("show.classTagsHeader"));
        System.out.println("═".repeat(100));
        
        printField(messages.getString("show.authorClassTag"), "authorClassTag", config.isAuthorClassTag());
        printField(messages.getString("show.deprecatedClassTag"), "deprecatedClassTag", config.isDeprecatedClassTag());
        printField(messages.getString("show.seeClassTag"), "seeClassTag", config.isSeeClassTag());
        printField(messages.getString("show.serialClassTag"), "serialClassTag", config.isSerialClassTag());
        printField(messages.getString("show.sinceClassTag"), "sinceClassTag", config.isSinceClassTag());
        printField(messages.getString("show.versionClassTag"), "versionClassTag", config.isVersionClassTag());

        System.out.println("═".repeat(100));
    }

    private void printField(String label, String key, Object value) {
        System.out.printf("  %-33s %33s: %s%n", label, key, value);
    }

    private void printField(String label, String key , boolean value) {
        String formattedValue = value ? "✅ true" : "❌ false";
        System.out.printf("  %-33s %33s: %s%n", label, key, formattedValue);
    }

    /**
     * Permite configurar el archivo de configuración desde fuera
     * @param configFile
     */
    public void setConfigFile(String configFile) {
        this.configFile = configFile;
    }

    private void showRawFile(String actualConfigFile) throws IOException {        
        ResourceBundle messages = ResourceBundle.getBundle("messages");

        System.out.println("\n" + messages.getString("show.header") + " (RAW)");
        System.out.println("═".repeat(50));
        
        {
            byte[] encoded = Files.readAllBytes(Paths.get(actualConfigFile));
            String out = new String(encoded);
            System.out.println(out);
        }        
    }
}
