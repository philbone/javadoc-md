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
                System.out.println("  " + appMessages.getString("message.show.expectedPath") + ": " + actualConfigFile);
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
        System.out.println("═".repeat(50));

        // Mostrar la ruta del archivo de configuración
        printField(messages.getString("show.configFilePath"), configFilePath);

        // Mostrar cada campo con formato consistente
        printField(messages.getString("show.sourcePath"), config.getSourcePath());
        printField(messages.getString("show.outputPath"), config.getOutputPath());
        printField(messages.getString("show.outFileName"), config.getOutFileName());
        printField(messages.getString("show.combinePackagesMode"), config.isCombinePackagesMode());
        printField(messages.getString("show.includePrivate"), config.isIncludePrivate());
        printField(messages.getString("show.includeProtected"), config.isIncludeProtected());
        printField(messages.getString("show.includePublic"), config.isIncludePublic());
        printField(messages.getString("show.debugMode"), config.isDebugMode());
        printField(messages.getString("show.tableOfContent"), config.isTableOfContent());
        printField(messages.getString("show.printEmptyNotify"), config.isPrintEmptyNotify());
        printField(messages.getString("show.printClassIndex"), config.isPrintClassIndex());
        printField(messages.getString("show.foreSignClassIndex"),
                "\"" + config.getForeSignClassIndex() + "\"");
        printField(messages.getString("show.foreSignClassIndexOnDetails"),
                config.isForeSignClassIndexOnDetails());
        printField(messages.getString("show.foreSignClassIndexOnSubtitle"),
                config.isForeSignClassIndexOnSubtitle());
        printField(messages.getString("show.markdownLanguage"), config.getMarkdownLanguage());

        System.out.println("═".repeat(50));
    }

    private void printField(String label, Object value) {
        System.out.printf("  %-35s : %s%n", label, value);
    }

    private void printField(String label, boolean value) {
        String formattedValue = value ? "✅ true" : "❌ false";
        System.out.printf("  %-35s : %s%n", label, formattedValue);
    }

    /**
     * Permite configurar el archivo de configuración desde fuera
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
