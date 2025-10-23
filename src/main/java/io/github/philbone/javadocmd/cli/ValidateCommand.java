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
    
    private String shortPositive;
    private String longPositive;

    public ValidateCommand() {
        this.messages = ResourceBundle.getBundle("messages");
        this.configService = new ConfigurationService(messages);
        this.shortPositive = messages.getString("param.short.positive");
        this.longPositive = messages.getString("param.long.positive");
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
            names = {"-m", "--mute"},
            descriptionKey = "${init.muteMode}"
    )
    private boolean mute = false;

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
                    System.out.println( messages.getString("validate.message.validConfiguration") + ": " + configFile);
                    System.out.println( messages.getString("validate.message.source") + ": " + config.getSourcePath());
                    System.out.println( messages.getString("validate.message.output") + ": " + config.getOutputPath());
                    return 0;
                } // SI NO es válida, entonces verificar si está usando valores por defecto
                else if (configService.isUsingDefaultValues(config)) {
                    System.out.println( messages.getString("validate.message.corruptedConfiguration") );
                    if (interactive) {
                        config = fixConfigurationInteractively(config);
                    } else {
                        return 1;
                    }
                } // Configuración existe pero tiene problemas específicos
                else {
                    System.out.println( messages.getString("validate.message.invalidConfiguration") );
                    if (interactive) {
                        config = fixConfigurationInteractively(config);
                    } else {
                        return 1;
                    }
                }
            } else {
                // No existe configuración
                if(!mute) System.out.println( messages.getString("validate.message.noConfig") );
                if (interactive) {
                    config = fixConfigurationInteractively(null);
                } else {
                    return 1;
                }
            }

            // Si llegamos aquí, tenemos una configuración válida después de correcciones
            System.out.println( messages.getString("validate.message.validConfiguration") + ": " + configFile);
            System.out.println( messages.getString("validate.message.source") + ": " + config.getSourcePath());
            System.out.println( messages.getString("validate.message.output") + ": " + config.getOutputPath());

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
                "../src",
                true,
                mute
        );

        String outputPath = configService.getValidPathFromUser(
                scanner,
                "Output Path",
                config.getOutputPath(),
                "../docs",
                true,
                mute
        );

        config.setSourcePath(sourcePath);
        config.setOutputPath(outputPath);        

        // Guardar configuración PRIMERO
        try {
            ConfigLoader.saveConfig(config, configFile);
            System.out.println( messages.getString("init.message.configSaved") + ": " + configFile);
        } catch (Exception e) {
            System.err.println( messages.getString("validate.message.saveError") + ": " + e.getMessage());
            throw new RuntimeException(e);
        }

        // 2. ANEXAR: Configuración de internacionalización (NUEVO)
        setupInternationalization(scanner);

        return config;
    }
    
    private void setupInternationalization(Scanner scanner) {
        Path basePath = Paths.get(".");
        Path langsPath = basePath.resolve("langs");

        System.out.println("\n"+ messages.getString("validate.message.langConfig") );

        // Crear directorio langs si no existe
        if (!Files.exists(langsPath)) {
            String response = shortPositive;
            if(!mute){
                System.out.print( messages.getString("validate.message.ask.createDir") + ": ");
                response = scanner.nextLine().trim().toLowerCase();
            }  
            
            if (response.isEmpty() || response.equals(shortPositive) || response.equals(longPositive)) {
                try {
                    Files.createDirectories(langsPath);
                    System.out.println( messages.getString("validate.message.langDirCreated") + ": " + langsPath);

                    // Crear archivos de idioma después de crear el directorio
                    createDefaultLanguageFiles(langsPath, scanner);
                } catch (Exception e) {
                    System.err.println( messages.getString("validate.message.error.createDir") + ": " + e.getMessage());
                }
            } else {
                System.out.println( messages.getString("validate.message.skip.createDir") );
            }
        } else {
            // Directorio ya existe, verificar archivos
            System.out.println( messages.getString("validate.message.langDirExist") + ": " + langsPath);
            createDefaultLanguageFiles(langsPath, scanner);
        }
    }

    private void createDefaultLanguageFiles(Path langsPath, Scanner scanner) {
        Path esFile = langsPath.resolve("es.yml");
        Path enFile = langsPath.resolve("en.yml");

        boolean createdAny = false;

        // Español - solo crear si no existe
        if (!Files.exists(esFile)) {
            String response = shortPositive;
            if(!mute){
                System.out.print(messages.getString("validate.message.ask.createLangEs") + ": ");
                response = scanner.nextLine().trim().toLowerCase();
            }

            if (response.isEmpty() || response.equals(shortPositive) || response.equals(longPositive)) {
                try {
                    String esContent = getDefaultSpanishContent();
                    Files.writeString(esFile, esContent, java.nio.charset.StandardCharsets.UTF_8);
                    System.out.println( messages.getString("validate.message.langFileCreated") + ": " + esFile);
                    createdAny = true;
                } catch (Exception e) {
                    System.err.println( messages.getString("validate.message.error.createFile") + ": " + e.getMessage());
                }
            }
        } else {
            System.out.println( messages.getString("validate.message.langFileExist") + ": " + esFile);
        }

        // Inglés - solo crear si no existe
        if (!Files.exists(enFile)) {
            String response = shortPositive;
            if(!mute){
                System.out.print(messages.getString("validate.message.ask.createLangEn") + ": ");
                response = scanner.nextLine().trim().toLowerCase();
            }

            if (response.isEmpty() || response.equals(shortPositive) || response.equals(longPositive)) {
                try {
                    String enContent = getDefaultEnglishContent();
                    Files.writeString(enFile, enContent, java.nio.charset.StandardCharsets.UTF_8);
                    System.out.println( messages.getString("validate.message.langFileCreated") + ": " + enFile);
                    createdAny = true;
                } catch (Exception e) {
                    System.err.println( messages.getString("validate.message.error.createFile") + ": " + e.getMessage());
                }
            }
        } else {
            System.out.println( messages.getString("validate.message.langFileExist") + ": " + enFile);
        }

        if (createdAny) {
            System.out.println( messages.getString("validate.message.success") );
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
    
    public void setMuteMode(boolean mode){
        this.mute = mode;
    }
    
}
