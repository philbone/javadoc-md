package io.github.philbone.javadocmd.cli;

import io.github.philbone.javadocmd.config.Config;
import io.github.philbone.javadocmd.config.ConfigLoader;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.ResourceBundle;
import java.util.Locale;
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
    private String configFile = "config.yml";

    @Override
    public Integer call() {
        try {
            System.out.println(appMessages.getString("message.show.loading"));

            // Verificar si existe el archivo de configuración
            if (!ConfigLoader.configExists(configFile)) {
                System.err.println(appMessages.getString("message.show.noConfig"));
                return 1;
            }

            // Cargar configuración
            Config config = ConfigLoader.loadConfig(configFile);

            // Mostrar configuración formateada
            showConfiguration(config);

            return 0;

        } catch (Exception e) {
            System.err.println(String.format(
                    appMessages.getString("message.show.loadError"),
                    e.getMessage()
            ));
            return 1;
        }
    }

    private void showConfiguration(Config config) {
        ResourceBundle messages = ResourceBundle.getBundle("messages");

        System.out.println("\n" + messages.getString("show.header"));
        System.out.println("═".repeat(50));

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

        System.out.println("═".repeat(50));
    }

    private void printField(String label, Object value) {
        System.out.printf("  %-35s : %s%n", label, value);
    }

    private void printField(String label, boolean value) {
        String formattedValue = value ? "✅ true" : "❌ false";
        System.out.printf("  %-35s : %s%n", label, formattedValue);
    }
}
