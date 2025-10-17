package io.github.philbone.javadocmd.config;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.Scanner;
import java.util.ResourceBundle;

/**
 * Servicio para gestionar la creación, validación y corrección de configuración
 */
public class ConfigurationService
{

    private final ResourceBundle messages;

    public ConfigurationService() {
        this.messages = ResourceBundle.getBundle("messages");
    }

    public ConfigurationService(ResourceBundle messages) {
        this.messages = messages;
    }

    /**
     * Crea una configuración con parámetros específicos (modo no-interactivo)
     */
    public Config createWithParameters(String sourcePath, String outputPath, String outFileName) {
        Config config = new Config();
        config.setSourcePath(sourcePath);
        config.setOutputPath(outputPath);
        if (outFileName != null) {
            config.setOutFileName(outFileName);
        }
        return config;
    }

    /**
     * Crea una configuración interactivamente (modo asistido)
     */
    public Config createInteractively(Scanner scanner) {
        Config config = new Config();

        System.out.println("\n💡 " + messages.getString("validate.message.creatingConfig"));

        // Solicitar sourcePath válido
        String sourcePath = getValidPathFromUser(
                scanner,
                "Source Path",
                config.getSourcePath(),
                "./src",
                true // debe existir
        );
        config.setSourcePath(sourcePath);

        // Solicitar outputPath válido
        String outputPath = getValidPathFromUser(
                scanner,
                "Output Path",
                config.getOutputPath(),
                "./docs",
                true // debe existir
        );
        config.setOutputPath(outputPath);

        return config;
    }

    /**
     * Valida si una configuración es válida
     */
    public boolean isValid(Config config) {
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
        if (parent != null && !Files.exists(parent) && !parent.toString().equals(".")) {
            return false;
        }

        return true;
    }

    /**
     * Obtiene una ruta válida del usuario de manera interactiva
     */
    public String getValidPathFromUser(Scanner scanner, String fieldName,
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
                    System.err.println("❌ " + String.format(messages.getString("validate.issue.path.notExists"), input));

                    // Ofrecer crear el directorio
                    System.out.print("¿Deseas crear este directorio y agregarlo como el " + fieldName + "? [s/N]: ");
                    String createResponse = scanner.nextLine().trim().toLowerCase();

                    if (createResponse.equals("s") || createResponse.equals("si")) {
                        try {
                            Files.createDirectories(path);
                            System.out.println("✅ " + String.format(messages.getString("validate.message.directoryCreated"), input));
                            return input;
                        } catch (IOException e) {
                            System.err.println("❌ " + String.format(messages.getString("validate.message.creationFailed"), e.getMessage()));
                            // Continuar el loop para pedir otra ruta
                        }
                    }
                    // Si no quiere crear, continuar el loop
                    continue;
                }

                if (!Files.isDirectory(path)) {
                    System.err.println("❌ " + String.format(messages.getString("validate.issue.path.notDirectory"), input));
                    continue;
                }
            } else {
                // Para output path, verificar que se pueda crear
                Path parent = path.getParent();
                if (parent != null && !Files.exists(parent) && !parent.toString().equals(".")) {
                    System.err.println("❌ " + String.format(messages.getString("validate.issue.parentNotExists"), parent));

                    // Ofrecer crear el directorio padre
                    System.out.print("¿Deseas crear el directorio padre? [s/N]: ");
                    String createResponse = scanner.nextLine().trim().toLowerCase();

                    if (createResponse.equals("s") || createResponse.equals("si")) {
                        try {
                            Files.createDirectories(parent);
                            System.out.println("✅ " + String.format(messages.getString("validate.message.parentCreated"), parent));
                            return input;
                        } catch (IOException e) {
                            System.err.println("❌ " + String.format(messages.getString("validate.message.creationFailed"), e.getMessage()));
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

    /**
     * Expande el directorio home del usuario (~)
     */
    private String expandHomeDirectory(String path) {
        if (path.startsWith("~")) {
            return path.replaceFirst("^~", System.getProperty("user.home"));
        }
        return path;
    }

    /**
     * Detecta si una configuración está usando valores por defecto
     */
    public boolean isUsingDefaultValues(Config config) {
        Config defaults = new Config();
        return config.getSourcePath().equals(defaults.getSourcePath())
                && config.getOutputPath().equals(defaults.getOutputPath());
    }
}
