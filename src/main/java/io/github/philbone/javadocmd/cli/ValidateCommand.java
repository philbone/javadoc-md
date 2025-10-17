package io.github.philbone.javadocmd.cli;

import io.github.philbone.javadocmd.config.Config;
import io.github.philbone.javadocmd.config.ConfigLoader;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

@Command(
        name = "validate",
        description = "${usage.validate}",
        resourceBundle = "messages"
)
public class ValidateCommand implements Callable<Integer>
{

    private final ResourceBundle messages;

    public ValidateCommand() {
        this.messages = ResourceBundle.getBundle("messages");
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
                // Intentar cargar configuración existente
                config = ConfigLoader.loadConfig(configFile);

                // Verificar si está usando valores por defecto (archivo vacío/corrupto)
                if (isUsingDefaultValues(config)) {
                    System.out.println("❌ Configuración corrupta o vacía");
                    config = createOrFixConfigurationInteractively(null);
                } else {
                    // Validar si las rutas existen
                    if (!isConfigurationValid(config)) {
                        config = createOrFixConfigurationInteractively(config);
                    }
                }
            } else {
                // No existe configuración
                System.out.println("❌ No se encontró archivo de configuración");
                config = createOrFixConfigurationInteractively(null);
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

    private Config createOrFixConfigurationInteractively(Config existingConfig) {
        Scanner scanner = new Scanner(System.in);
        Config config = (existingConfig != null) ? existingConfig : new Config();

        System.out.println("\n💡 Configuración requerida:");

        // Solicitar sourcePath válido - usar ./src en lugar de /src
        String sourcePath = getValidPathFromUser(
                scanner,
                "Source Path",
                config.getSourcePath(),
                "./src", // ← CAMBIAR de "/src" a "./src"
                true // debe existir
        );
        config.setSourcePath(sourcePath);

        // Solicitar outputPath válido - usar ./docs en lugar de /doc  
        String outputPath = getValidPathFromUser(
                scanner,
                "Output Path",
                config.getOutputPath(),
                "./docs", // ← CAMBIAR de "/doc" a "./docs"
                true // no necesita existir (se puede crear)
        );
        config.setOutputPath(outputPath);

        // Guardar configuración
        try {
            ConfigLoader.saveConfig(config, configFile);
            System.out.println("✅ Configuración guardada: " + configFile);
        } catch (Exception e) {
            System.err.println("❌ Error guardando configuración: " + e.getMessage());
            throw new RuntimeException(e);
        }

        return config;
    }

    private String getValidPathFromUser(Scanner scanner, String fieldName,
            String currentValue, String defaultValue,
            boolean mustExist) {
        String prompt = String.format("%s (%s): ", fieldName,
                (currentValue != null && !currentValue.trim().isEmpty()) ? currentValue : defaultValue);

        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            // Usar default si no se ingresa nada
            if (input.isEmpty()) {
                input = (currentValue != null && !currentValue.trim().isEmpty())
                        ? currentValue : defaultValue;
            }

            // Expandir ~ si está presente
            if (input.startsWith("~")) {
                input = expandHomeDirectory(input);
            }

            // Validar ruta
            Path path = Paths.get(input);

            if (mustExist) {
                if (!Files.exists(path)) {
                    System.err.println("❌ La ruta no existe: " + input);

                    // Ofrecer crear el directorio
                    System.out.print("¿Deseas crear este directorio y agregarlo como el " + fieldName + "? [s/N]: ");
                    String createResponse = scanner.nextLine().trim().toLowerCase();

                    if (createResponse.equals("s") || createResponse.equals("si")) {
                        try {
                            Files.createDirectories(path);
                            System.out.println("✅ Directorio creado: " + input);
                            return input;
                        } catch (IOException e) {
                            System.err.println("❌ No se pudo crear el directorio: " + e.getMessage());
                            // Continuar el loop para pedir otra ruta
                        }
                    }
                    // Si no quiere crear, continuar el loop
                    continue;
                }

                if (!Files.isDirectory(path)) {
                    System.err.println("❌ La ruta no es un directorio: " + input);
                    continue;
                }
            } else {
                // Para output path, verificar que se pueda crear
                Path parent = path.getParent();
                if (parent != null && !Files.exists(parent) && !parent.toString().equals(".")) {
                    System.err.println("❌ El directorio padre no existe: " + parent);

                    // Ofrecer crear el directorio padre
                    System.out.print("¿Deseas crear el directorio padre? [s/N]: ");
                    String createResponse = scanner.nextLine().trim().toLowerCase();

                    if (createResponse.equals("s") || createResponse.equals("si")) {
                        try {
                            Files.createDirectories(parent);
                            System.out.println("✅ Directorio padre creado: " + parent);
                            return input;
                        } catch (IOException e) {
                            System.err.println("❌ No se pudo crear el directorio padre: " + e.getMessage());
                            continue;
                        }
                    }
                    // Si no quiere crear, continuar el loop
                    continue;
                }
            }

            return input;
        }
    }

    private String expandHomeDirectory(String path) {
        if (path.startsWith("~")) {
            return path.replaceFirst("^~", System.getProperty("user.home"));
        }
        return path;
    }

    private boolean isUsingDefaultValues(Config config) {
        Config defaults = new Config();
        return config.getSourcePath().equals(defaults.getSourcePath())
                && config.getOutputPath().equals(defaults.getOutputPath());
    }

    private boolean isConfigurationValid(Config config) {
        // Validar sourcePath existe y es directorio
        if (config.getSourcePath() == null || config.getSourcePath().trim().isEmpty()) {
            return false;
        }
        Path sourcePath = Paths.get(config.getSourcePath());
        if (!Files.exists(sourcePath) || !Files.isDirectory(sourcePath)) {
            return false;
        }

        // Validar outputPath (puede no existir, pero su directorio padre debe existir)
        if (config.getOutputPath() == null || config.getOutputPath().trim().isEmpty()) {
            return false;
        }
        Path outputPath = Paths.get(config.getOutputPath());
        Path parent = outputPath.getParent();
        if (parent != null && !Files.exists(parent)) {
            return false;
        }

        return true;
    }
}
