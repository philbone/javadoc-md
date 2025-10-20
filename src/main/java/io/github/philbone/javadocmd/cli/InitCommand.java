package io.github.philbone.javadocmd.cli;

import io.github.philbone.javadocmd.config.Config;
import io.github.philbone.javadocmd.config.ConfigLoader;
import io.github.philbone.javadocmd.config.ConfigurationService;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.ResourceBundle;
import java.util.concurrent.Callable;

@Command(
        name = "init",
        description = "${usage.init}",
        resourceBundle = "messages"
)
public class InitCommand implements Callable<Integer>
{

    private final ResourceBundle messages;
    private final ConfigurationService configService;

    public InitCommand() {
        this.messages = getResourceBundle();
        this.configService = getConfigurationService(messages);
    }

    @Option(names = {"-h", "--help"}, usageHelp = true, descriptionKey = "init.help")
    private boolean helpRequested;

    @Option(names = {"--sourcePath"}, descriptionKey = "init.sourcePath", paramLabel = "SOURCE_PATH")
    private String sourcePath;

    @Option(names = {"--outputPath"}, descriptionKey = "init.outputPath", paramLabel = "OUTPUT_PATH")
    private String outputPath;

    @Option(names = {"--outFileName"}, descriptionKey = "init.outFileName", paramLabel = "OUTPUT_FILE")
    private String outFileName;

    @Option(
            names = {"-i", "--interactive"},
            description = "Modo interactivo si no se proporcionan todos los parámetros"
    )
    private boolean interactive = true;

    @Option(
            names = {"--configFile"},
            descriptionKey = "init.configFile",
            paramLabel = "CONFIG_FILE"
    )
    private String configFile = "config.yml";

    @Override
    public Integer call() {
        try {
            // Verificar si el archivo ya existe
            if (ConfigLoader.configExists(configFile)) {
                System.err.println(messages.getString("init.message.configFileAlreadyExist")  + ": " + configFile);
                System.out.println(messages.getString("init.message.configFileAlreadyExist.help"));
                return 1;
            }

            // Modo con parámetros completos
            if (sourcePath != null && outputPath != null) {
                return createWithParameters();
            } // Modo interactivo - delegar en ValidateCommand
            else if (interactive) {
                System.out.println( messages.getString("init.message.interactiveStart") );
                ValidateCommand validate = new ValidateCommand();
                validate.setConfigFile(configFile);
                validate.setInteractive(true);
                return validate.call();
            } // Modo no interactivo sin parámetros suficientes
            else {
                System.err.println( messages.getString("init.message.insufficientParameters") );
                System.out.println( messages.getString("usage.helpTip") );
                return 1;
            }

        } catch (Exception e) {
            System.err.println( messages.getString("init.message.createError") + ": " + e.getMessage());
            return 1;
        }
    }

    private Integer createWithParameters() {
        try {
            Config config = configService.createWithParameters(sourcePath, outputPath, outFileName);
            ConfigLoader.saveConfig(config, configFile);
            System.out.println( messages.getString("init.message.createdWithParameters") );
            System.out.println( messages.getString("init.message.configSaved") + ": " + configFile);
            System.out.println("  - Source: " + config.getSourcePath());
            System.out.println("  - Output: " + config.getOutputPath());
            if (config.getOutFileName() != null) {
                System.out.println("  - Output File: " + config.getOutFileName());
            }
            return 0;
        } catch (Exception e) {
            System.err.println( messages.getString("init.message.createError") + e.getMessage());
            return 1;
        }
    }
    
     // Métodos para testing - package private
    ResourceBundle getResourceBundle() {
        return ResourceBundle.getBundle("messages");
    }
    
     ConfigurationService getConfigurationService(ResourceBundle messages) {
        return new ConfigurationService(messages);
    }
}
