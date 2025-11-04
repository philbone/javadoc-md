package io.github.philbone.javadocmd.cli;

import io.github.philbone.javadocmd.config.Config;
import io.github.philbone.javadocmd.config.ConfigLoader;
import io.github.philbone.javadocmd.config.ConfigManager;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.ResourceBundle;
import java.util.concurrent.Callable;

@Command(
        name = "get",
        description = "${usage.get}",
        resourceBundle = "messages"
)
public class GetCommand implements Callable<Integer>
{
    private final ResourceBundle appMessages;

    public GetCommand() {
        this.appMessages = ResourceBundle.getBundle("app_messages");
    }

    @Option(
            names = {"-h", "--help"},
            usageHelp = true,
            descriptionKey = "get.help"
    )
    private boolean helpRequested;

    @Option(
            names = {"-q", "--quiet"},
            descriptionKey = "get.quiet"
    )
    private boolean quietMode = false;

    @Option(
            names = {"--configFile"},
            descriptionKey = "get.configFile",
            paramLabel = "CONFIG_FILE"
    )
    private String configFile; // ← Sin valor por defecto, lo manejaremos en el método

    @Parameters(
            index = "0",
            descriptionKey = "get.key",
            paramLabel = "KEY"
    )
    private String key;

    @Override
    public Integer call() {
        try {
            // ✅ Obtener la ruta real del archivo de configuración
            String actualConfigFile = getActualConfigFilePath();

            if (!quietMode) {
                System.out.println(appMessages.getString("message.get.loading"));
            }

            // Verificar si existe el archivo de configuración
            if (!ConfigLoader.configExists(actualConfigFile)) {
                System.err.println(appMessages.getString("message.get.noConfig"));
                if (!quietMode) {
                    System.out.println("  " + appMessages.getString("message.get.expectedPath") + ": " + actualConfigFile);
                }
                return 1;
            }

            // Cargar configuración
            Config config = ConfigLoader.loadConfig(actualConfigFile, quietMode);

            // Obtener y mostrar el valor
            String value = getConfigValue(config, key);
            if (value == null) {
                ResourceBundle messages = ResourceBundle.getBundle("messages");
                System.err.println(String.format(
                        messages.getString("get.error.invalidKey"),
                        key
                ));
                if (!quietMode) {
                    System.err.println(messages.getString("get.error.availableKeys"));
                }
                return 1;
            }

            System.out.println(key+ ": " + value);

            return 0;

        } catch (Exception e) {
            System.err.println(String.format(
                    appMessages.getString("message.get.loadError"),
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

    private String getConfigValue(Config config, String key) {
        switch (key.toLowerCase()) {
            case "sourcepath":
                return config.getSourcePath();
            case "outputpath":
                return config.getOutputPath();
            case "outfilename":
                return config.getOutFileName();
            case "combinepackagesmode":
                return String.valueOf(config.isCombinePackagesMode());
            case "includeprivate":
                return String.valueOf(config.isIncludePrivate());
            case "includeprotected":
                return String.valueOf(config.isIncludeProtected());
            case "includepublic":
                return String.valueOf(config.isIncludePublic());
            case "debugmode":
                return String.valueOf(config.isDebugMode());
            case "tableofcontent":
                return String.valueOf(config.isTableOfContent());
            case "printemptynotify":
                return String.valueOf(config.isPrintEmptyNotify());
            case "printclassindex":
                return String.valueOf(config.isPrintClassIndex());
            case "foresignclassindex":
                return config.getForeSignClassIndex();
            case "foresignclassindexondetails":
                return String.valueOf(config.isForeSignClassIndexOnDetails());
            case "foresignclassindexonsubtitle":
                return String.valueOf(config.isForeSignClassIndexOnSubtitle());
            case "markdownlanguage":
                return String.valueOf(config.getMarkdownLanguage());
            default:
                return null;
        }
    }

    /**
     * Permite configurar el archivo de configuración desde fuera
     */
    public void setConfigFile(String configFile) {
        this.configFile = configFile;
    }
}
