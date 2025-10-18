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
        this.messages = ResourceBundle.getBundle("messages");
        this.configService = new ConfigurationService(messages);
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
                System.err.println("❌ El archivo de configuración ya existe: " + configFile);
                System.out.println("💡 Usa 'validate' para corregir o 'set' para modificar la configuración existente");
                return 1;
            }

            // Modo con parámetros completos
            if (sourcePath != null && outputPath != null) {
                return createWithParameters();
            } // Modo interactivo - delegar en ValidateCommand
            else if (interactive) {
                System.out.println("💡 Iniciando configuración interactiva...");
                ValidateCommand validate = new ValidateCommand();
                validate.setConfigFile(configFile);
                validate.setInteractive(true);
                return validate.call();
            } // Modo no interactivo sin parámetros suficientes
            else {
                System.err.println("❌ Parámetros insuficientes. Se requieren --sourcePath y --outputPath");
                System.out.println("💡 Ejecuta con --help para ver todas las opciones");
                return 1;
            }

        } catch (Exception e) {
            System.err.println("❌ Error creando configuración: " + e.getMessage());
            return 1;
        }
    }

    private Integer createWithParameters() {
        try {
            Config config = configService.createWithParameters(sourcePath, outputPath, outFileName);
            ConfigLoader.saveConfig(config, configFile);
            System.out.println("✅ Configuración creada con parámetros proporcionados");
            System.out.println("✅ Configuración guardada: " + configFile);
            System.out.println("  - Source: " + config.getSourcePath());
            System.out.println("  - Output: " + config.getOutputPath());
            if (config.getOutFileName() != null) {
                System.out.println("  - Output File: " + config.getOutFileName());
            }
            return 0;
        } catch (Exception e) {
            System.err.println("❌ Error creando configuración: " + e.getMessage());
            return 1;
        }
    }
}
