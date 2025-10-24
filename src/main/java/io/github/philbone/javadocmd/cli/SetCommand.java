package io.github.philbone.javadocmd.cli;

import io.github.philbone.javadocmd.config.Config;
import io.github.philbone.javadocmd.config.ConfigLoader;
import io.github.philbone.javadocmd.config.ConfigManager;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.ResourceBundle;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@Command(
        name = "set",
        description = "${usage.set}",
        resourceBundle = "messages"
)
public class SetCommand implements Callable<Integer>
{

    private final ResourceBundle appMessages;

    public SetCommand() {
        this.appMessages = ResourceBundle.getBundle("app_messages");
    }

    @Option(
            names = {"-h", "--help"},
            usageHelp = true,
            descriptionKey = "set.help"
    )
    private boolean helpRequested;

    // Opciones para cada campo de Config
    @Option(names = {"--sourcePath"}, descriptionKey = "set.sourcePath")
    private String sourcePath;

    @Option(names = {"--outputPath"}, descriptionKey = "set.outputPath")
    private String outputPath;

    @Option(names = {"--outFileName"}, descriptionKey = "set.outFileName")
    private String outFileName;

    @Option(names = {"--combinePackagesMode"}, descriptionKey = "set.combinePackagesMode", arity = "0..1")
    private Boolean combinePackagesMode;

    @Option(names = {"--includePrivate"}, descriptionKey = "set.includePrivate", arity = "0..1")
    private Boolean includePrivate;

    @Option(names = {"--includeProtected"}, descriptionKey = "set.includeProtected", arity = "0..1")
    private Boolean includeProtected;

    @Option(names = {"--includePublic"}, descriptionKey = "set.includePublic", arity = "0..1")
    private Boolean includePublic;

    @Option(names = {"--debugMode"}, descriptionKey = "set.debugMode", arity = "0..1")
    private Boolean debugMode;

    @Option(names = {"--tableOfContent"}, descriptionKey = "set.tableOfContent", arity = "0..1")
    private Boolean tableOfContent;

    @Option(names = {"--printEmptyNotify"}, descriptionKey = "set.printEmptyNotify", arity = "0..1")
    private Boolean printEmptyNotify;

    @Option(names = {"--printClassIndex"}, descriptionKey = "set.printClassIndex", arity = "0..1")
    private Boolean printClassIndex;

    @Option(names = {"--foreSignClassIndex"}, descriptionKey = "set.foreSignClassIndex")
    private String foreSignClassIndex;

    @Option(names = {"--foreSignClassIndexOnDetails"}, descriptionKey = "set.foreSignClassIndexOnDetails", arity = "0..1")
    private Boolean foreSignClassIndexOnDetails;

    @Option(names = {"--foreSignClassIndexOnSubtitle"}, descriptionKey = "set.foreSignClassIndexOnSubtitle", arity = "0..1")
    private Boolean foreSignClassIndexOnSubtitle;

    @Option(names = {"--markdownLanguage"}, descriptionKey = "set.markdownLanguage")
    private String markdownLanguage;

    @Option(
            names = {"--configFile"},
            descriptionKey = "set.configFile",
            paramLabel = "CONFIG_FILE"
    )
    private String configFile; // ← Sin valor por defecto, lo manejaremos en el método

    @Option(
            names = {"-f", "--force"},
            descriptionKey = "set.force"
    )
    private boolean forceCreate = false;

    @Override
    public Integer call() {
        try {
            // ✅ Obtener la ruta real del archivo de configuración
            String actualConfigFile = getActualConfigFilePath();

            Config config;
            boolean configExists = ConfigLoader.configExists(actualConfigFile);

            if (configExists) {
                System.out.println(appMessages.getString("message.set.loading"));
                config = ConfigLoader.loadConfig(actualConfigFile);
            } else if (forceCreate) {
                System.out.println(appMessages.getString("message.set.creating"));
                config = new Config();
            } else {
                System.err.println(appMessages.getString("message.set.noConfig"));
                System.out.println("  " + appMessages.getString("message.set.expectedPath") + ": " + actualConfigFile);
                System.out.println("  " + appMessages.getString("message.set.useForce"));
                return 1;
            }

            // Aplicar cambios
            List<String> changes = applyChanges(config);

            if (changes.isEmpty()) {
                System.out.println(appMessages.getString("message.set.noChanges"));
                return 0;
            }

            System.out.println(appMessages.getString("message.set.updating"));

            // Guardar configuración
            ConfigLoader.saveConfig(config, actualConfigFile);

            // Mostrar resumen
            showChangesSummary(changes, actualConfigFile);

            System.out.println(appMessages.getString("message.set.success"));

            return 0;

        } catch (Exception e) {
            System.err.println(String.format(
                    appMessages.getString("message.set.saveError"),
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

    private List<String> applyChanges(Config config) {
        List<String> changes = new ArrayList<>();
        ResourceBundle messages = ResourceBundle.getBundle("messages");

        if (sourcePath != null && !sourcePath.equals(config.getSourcePath())) {
            config.setSourcePath(sourcePath);
            changes.add(messages.getString("set.sourcePath") + ": " + sourcePath);
        }

        if (outputPath != null && !outputPath.equals(config.getOutputPath())) {
            config.setOutputPath(outputPath);
            changes.add(messages.getString("set.outputPath") + ": " + outputPath);
        }

        if (outFileName != null && !outFileName.equals(config.getOutFileName())) {
            config.setOutFileName(outFileName);
            changes.add(messages.getString("set.outFileName") + ": " + outFileName);
        }

        if (combinePackagesMode != null && combinePackagesMode != config.isCombinePackagesMode()) {
            config.setCombinePackagesMode(combinePackagesMode);
            changes.add(messages.getString("set.combinePackagesMode") + ": " + combinePackagesMode);
        }

        if (includePrivate != null && includePrivate != config.isIncludePrivate()) {
            config.setIncludePrivate(includePrivate);
            changes.add(messages.getString("set.includePrivate") + ": " + includePrivate);
        }

        if (includeProtected != null && includeProtected != config.isIncludeProtected()) {
            config.setIncludeProtected(includeProtected);
            changes.add(messages.getString("set.includeProtected") + ": " + includeProtected);
        }

        if (includePublic != null && includePublic != config.isIncludePublic()) {
            config.setIncludePublic(includePublic);
            changes.add(messages.getString("set.includePublic") + ": " + includePublic);
        }

        if (debugMode != null && debugMode != config.isDebugMode()) {
            config.setDebugMode(debugMode);
            changes.add(messages.getString("set.debugMode") + ": " + debugMode);
        }

        if (tableOfContent != null && tableOfContent != config.isTableOfContent()) {
            config.setTableOfContent(tableOfContent);
            changes.add(messages.getString("set.tableOfContent") + ": " + tableOfContent);
        }

        if (printEmptyNotify != null && printEmptyNotify != config.isPrintEmptyNotify()) {
            config.setPrintEmptyNotify(printEmptyNotify);
            changes.add(messages.getString("set.printEmptyNotify") + ": " + printEmptyNotify);
        }

        if (printClassIndex != null && printClassIndex != config.isPrintClassIndex()) {
            config.setPrintClassIndex(printClassIndex);
            changes.add(messages.getString("set.printClassIndex") + ": " + printClassIndex);
        }

        if (foreSignClassIndex != null && !foreSignClassIndex.equals(config.getForeSignClassIndex())) {
            config.setForeSignClassIndex(foreSignClassIndex);
            changes.add(messages.getString("set.foreSignClassIndex") + ": \"" + foreSignClassIndex + "\"");
        }

        if (foreSignClassIndexOnDetails != null && foreSignClassIndexOnDetails != config.isForeSignClassIndexOnDetails()) {
            config.setForeSignClassIndexOnDetails(foreSignClassIndexOnDetails);
            changes.add(messages.getString("set.foreSignClassIndexOnDetails") + ": " + foreSignClassIndexOnDetails);
        }

        if (foreSignClassIndexOnSubtitle != null && foreSignClassIndexOnSubtitle != config.isForeSignClassIndexOnSubtitle()) {
            config.setForeSignClassIndexOnSubtitle(foreSignClassIndexOnSubtitle);
            changes.add(messages.getString("set.foreSignClassIndexOnSubtitle") + ": " + foreSignClassIndexOnSubtitle);
        }

        if (markdownLanguage != null && !markdownLanguage.isBlank() && !markdownLanguage.equals(config.getMarkdownLanguage())) {
            config.setMarkdownLanguage(markdownLanguage);
            changes.add(messages.getString("set.markdownLanguage") + ": " + markdownLanguage);
        }

        return changes;
    }

    private void showChangesSummary(List<String> changes, String configFilePath) {
        System.out.println("\n" + appMessages.getString("message.set.summary"));
        System.out.println("  " + appMessages.getString("message.set.configFile") + ": " + configFilePath);
        for (String change : changes) {
            System.out.println("  • " + change);
        }
        System.out.println();
    }

    /**
     * Permite configurar el archivo de configuración desde fuera
     */
    public void setConfigFile(String configFile) {
        this.configFile = configFile;
    }
}
