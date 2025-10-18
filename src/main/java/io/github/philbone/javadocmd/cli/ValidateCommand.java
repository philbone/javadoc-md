package io.github.philbone.javadocmd.cli;

import io.github.philbone.javadocmd.config.Config;
import io.github.philbone.javadocmd.config.ConfigLoader;
import io.github.philbone.javadocmd.config.ConfigurationService;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

                // PRIMERO verificar si es válida
                if (configService.isValid(config)) {
                    // ✅ Configuración válida - mostrar éxito y salir
                    System.out.println("✅ Configuración válida: " + configFile);
                    System.out.println(" - Source: " + config.getSourcePath());
                    System.out.println(" - Output : " + config.getOutputPath());
                    return 0;
                } // SI NO es válida, entonces verificar si está usando valores por defecto
                else if (configService.isUsingDefaultValues(config)) {
                    System.out.println("❌ Configuración corrupta o vacía");
                    if (interactive) {
                        config = fixConfigurationInteractively(config);
                    } else {
                        return 1;
                    }
                } // Configuración existe pero tiene problemas específicos
                else {
                    System.out.println("❌ Configuración inválida - problemas detectados");
                    if (interactive) {
                        config = fixConfigurationInteractively(config);
                    } else {
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

            // Si llegamos aquí, tenemos una configuración válida después de correcciones
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

        // 1. CONFIGURACIÓN PRINCIPAL (ya existe)
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
                true
        );
        config.setOutputPath(outputPath);

        // Guardar configuración PRIMERO
        try {
            ConfigLoader.saveConfig(config, configFile);
            System.out.println("✅ Configuración guardada: " + configFile);
        } catch (Exception e) {
            System.err.println("❌ Error guardando configuración: " + e.getMessage());
            throw new RuntimeException(e);
        }

        // 2. ANEXAR: Configuración de internacionalización (NUEVO)
        setupInternationalization(scanner);

        return config;
    }
    
    private void setupInternationalization(Scanner scanner) {
        Path basePath = Paths.get(".");
        Path langsPath = basePath.resolve("langs");

        System.out.println("\n🌍 Configurando internacionalización...");

        // Crear directorio langs si no existe
        if (!Files.exists(langsPath)) {
            System.out.print("¿Crear directorio de idiomas (/langs)? [S/n]: ");
            String response = scanner.nextLine().trim().toLowerCase();

            if (response.isEmpty() || response.equals("s") || response.equals("si")) {
                try {
                    Files.createDirectories(langsPath);
                    System.out.println("✅ Directorio creado: " + langsPath);

                    // Crear archivos de idioma después de crear el directorio
                    createDefaultLanguageFiles(langsPath, scanner);
                } catch (Exception e) {
                    System.err.println("❌ Error creando directorio: " + e.getMessage());
                }
            } else {
                System.out.println("ℹ️  Saltando creación de directorio de idiomas");
            }
        } else {
            // Directorio ya existe, verificar archivos
            System.out.println("✅ Directorio ya existe: " + langsPath);
            createDefaultLanguageFiles(langsPath, scanner);
        }
    }

    private void createDefaultLanguageFiles(Path langsPath, Scanner scanner) {
        Path esFile = langsPath.resolve("es.yml");
        Path enFile = langsPath.resolve("en.yml");

        boolean createdAny = false;

        // Español - solo crear si no existe
        if (!Files.exists(esFile)) {
            System.out.print("¿Crear archivo de idioma español (es.yml)? [S/n]: ");
            String response = scanner.nextLine().trim().toLowerCase();

            if (response.isEmpty() || response.equals("s") || response.equals("si")) {
                try {
                    String esContent = getDefaultSpanishContent();
                    Files.writeString(esFile, esContent, java.nio.charset.StandardCharsets.UTF_8);
                    System.out.println("✅ Archivo creado: " + esFile);
                    createdAny = true;
                } catch (Exception e) {
                    System.err.println("❌ Error creando es.yml: " + e.getMessage());
                }
            }
        } else {
            System.out.println("✅ Archivo ya existe: " + esFile);
        }

        // Inglés - solo crear si no existe
        if (!Files.exists(enFile)) {
            System.out.print("¿Crear archivo de idioma inglés (en.yml)? [S/n]: ");
            String response = scanner.nextLine().trim().toLowerCase();

            if (response.isEmpty() || response.equals("s") || response.equals("si")) {
                try {
                    String enContent = getDefaultEnglishContent();
                    Files.writeString(enFile, enContent, java.nio.charset.StandardCharsets.UTF_8);
                    System.out.println("✅ Archivo creado: " + enFile);
                    createdAny = true;
                } catch (Exception e) {
                    System.err.println("❌ Error creando en.yml: " + e.getMessage());
                }
            }
        } else {
            System.out.println("✅ Archivo ya existe: " + enFile);
        }

        if (createdAny) {
            System.out.println("✅ Internacionalización configurada correctamente");
        }
    }

    private String getDefaultSpanishContent() {
        return """
        labels:
          classes: "Clases"
          methods: "Métodos" 
          fields: "Campos"
          indexTitle: "Tabla de Contenidos"
          packageTitle: "Paquete"
        """;
    }

    private String getDefaultEnglishContent() {
        return """
        labels:
          classes: "Classes"
          methods: "Methods"
          fields: "Fields" 
          indexTitle: "Table of Contents"
          packageTitle: "Package"
        """;
    }
    
    /**
     * Permite configurar el archivo de configuración desde fuera
     */
    public void setConfigFile(String configFile) {
        this.configFile = configFile;
    }

    /**
     * Permite configurar el modo interactivo desde fuera
     */
    public void setInteractive(boolean interactive) {
        this.interactive = interactive;
    }
    
}
