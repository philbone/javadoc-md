package io.github.philbone.javadocmd.cli;

import io.github.philbone.javadocmd.config.Config;
import io.github.philbone.javadocmd.config.ConfigLoader;
import io.github.philbone.javadocmd.config.ConfigurationService;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.concurrent.Callable;

@Command(
        name = "validate",
        description = "${usage.validate}",
        resourceBundle = "messages"
)
public class ValidateCommand implements Callable<Integer>
{

    private final ResourceBundle messages;
    private final ConfigurationService configService;

    public ValidateCommand() {
        this.messages = ResourceBundle.getBundle("messages");
        this.configService = new ConfigurationService(messages);
    }

    @Option(
            names = {"-h", "--help"},
            usageHelp = true,
            descriptionKey = "validate.help"
    )
    private boolean helpRequested;

    @Option(
            names = {"-i", "--interactive"},
            descriptionKey = "validate.interactive"
    )
    private boolean interactive = true;

    @Option(
            names = {"--configFile"},
            descriptionKey = "validate.configFile",
            paramLabel = "CONFIG_FILE"
    )
    private String configFile = "config.yml";

    @Override
    public Integer call() {
        try {
            System.out.println(messages.getString("validate.message.loading"));

            Config config;
            boolean configExists = ConfigLoader.configExists(configFile);

            if (configExists) {
                // Cargar configuración existente
                config = ConfigLoader.loadConfig(configFile);

                // Verificar si está usando valores por defecto (archivo vacío/corrupto)
                if (configService.isUsingDefaultValues(config)) {
                    System.out.println("❌ Configuración corrupta o vacía");
                    if (interactive) {
                        config = fixConfigurationInteractively(config);
                    } else {
                        return 1;
                    }
                } // Validar configuración existente
                else if (!configService.isValid(config)) {
                    if (interactive) {
                        config = fixConfigurationInteractively(config);
                    } else {
                        System.err.println("❌ Configuración inválida");
                        return 1;
                    }
                }
            } else {
                // No existe configuración
                System.out.println("❌ No se encontró archivo de configuración");
                if (interactive) {
                    config = fixConfigurationInteractively(null);
                } else {
                    return 1;
                }
            }

            // Si llegamos aquí, tenemos una configuración válida
            System.out.println("✅ Configuración válida: " + configFile);
            System.out.println("  - Source: " + config.getSourcePath());
            System.out.println("  - Output: " + config.getOutputPath());

            return 0;

        } catch (Exception e) {
            System.err.println(String.format(
                    messages.getString("validate.message.loadError"),
                    e.getMessage()
            ));
            return 1;
        }
    }

    private Config fixConfigurationInteractively(Config existingConfig) {
        Scanner scanner = new Scanner(System.in);
        Config config = (existingConfig != null) ? existingConfig : new Config();

        System.out.println("\n💡 " + messages.getString("validate.message.creatingConfig"));

        // Usar ConfigurationService para obtener rutas válidas
        String sourcePath = configService.getValidPathFromUser(
                scanner,
                "Source Path",
                config.getSourcePath(),
                "./src",
                true
        );
        config.setSourcePath(sourcePath);

        String outputPath = configService.getValidPathFromUser(
                scanner,
                "Output Path",
                config.getOutputPath(),
                "./docs",
                false
        );
        config.setOutputPath(outputPath);

        // Guardar configuración corregida
        try {
            ConfigLoader.saveConfig(config, configFile);
            System.out.println("✅ Configuración guardada: " + configFile);
        } catch (Exception e) {
            System.err.println("❌ Error guardando configuración: " + e.getMessage());
            throw new RuntimeException(e);
        }

        return config;
    }
}
