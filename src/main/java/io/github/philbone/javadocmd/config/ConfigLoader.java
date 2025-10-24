package io.github.philbone.javadocmd.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

/**
 * Esta clase se encarga de detectar el fichero de configuración
 * y cargar los datos si son encontrados.
 * De otra manera cargará los valores por defecto.
 * 
 * @author Felipe M. <philbone@focused.cl>
 */
public class ConfigLoader
{
    // Eliminamos DEFAULT_CONFIG_FILE de aquí

    private static ResourceBundle appMessages = ResourceBundle.getBundle("app_messages");

    /**
     * Carga la configuración usando ConfigManager para obtener la ruta por
     * defecto
     *
     * @return un objeto con los datos de configuración iniciales.
     */
    public static Config loadConfig() {
        ConfigManager configManager = new ConfigManager();
        return loadConfig(configManager.getConfigFilePath().toString());
    }

    /**
     * Carga la configuración desde un archivo específico
     *
     * @param filePath ruta del archivo de configuración
     * @return objeto Config con los datos cargados
     */
    public static Config loadConfig(String filePath) {
        Config defaultConfig = new Config();
        File yamlFile = new File(filePath);

        if (!yamlFile.exists()) {
            String outln = String.format(appMessages.getString("config.message.filePathNotFound"), filePath);
            System.out.println(outln);
            return defaultConfig;
        }

        try {
            ObjectMapper mapper = createObjectMapper();
            Config fileConfig = mapper.readValue(yamlFile, Config.class);
            System.out.println(appMessages.getString("config.message.configLoadedFrom") + ": " + filePath);
            return fileConfig;
        } catch (Exception e) {
            String outln = String.format(
                    appMessages.getString("config.error.usingDefault"),
                    filePath,
                    e.getMessage(),
                    appMessages.getString("config.message.usingDefault")
            );
            System.err.println(outln);
            return defaultConfig;
        }
    }

    /**
     * Guarda la configuración en un archivo YAML
     *
     * @param config objeto Config a guardar
     * @param filePath ruta del archivo destino
     * @throws IOException si ocurre error de escritura
     */
    public static void saveConfig(Config config, String filePath) throws IOException {
        ObjectMapper mapper = createObjectMapper();

        // Crear directorio padre si no existe
        Path path = Paths.get(filePath);
        Path parentDir = path.getParent();
        if (parentDir != null && !Files.exists(parentDir)) {
            Files.createDirectories(parentDir);
        }

        try (FileWriter writer = new FileWriter(filePath)) {
            mapper.writeValue(writer, config);
            System.out.println(appMessages.getString("config.message.saveSuccess") + ": " + filePath);
        }
    }

    /**
     * Guarda la configuración usando ConfigManager para obtener la ruta por
     * defecto
     *
     * @param config objeto Config a guardar
     * @throws IOException si ocurre error de escritura
     */
    public static void saveConfig(Config config) throws IOException {
        ConfigManager configManager = new ConfigManager();
        saveConfig(config, configManager.getConfigFilePath().toString());
    }

    /**
     * Crea una configuración por defecto y la guarda en un archivo
     *
     * @param filePath ruta del archivo destino
     * @return objeto Config creado
     * @throws IOException si ocurre error de escritura
     */
    public static Config createDefaultConfig(String filePath) throws IOException {
        Config defaultConfig = new Config();
        saveConfig(defaultConfig, filePath);
        return defaultConfig;
    }

    /**
     * Crea una configuración por defecto y la guarda en la ruta por defecto
     * usando ConfigManager
     *
     * @return objeto Config creado
     * @throws IOException si ocurre error de escritura
     */
    public static Config createDefaultConfig() throws IOException {
        ConfigManager configManager = new ConfigManager();
        return createDefaultConfig(configManager.getConfigFilePath().toString());
    }

    /**
     * Verifica si existe el archivo de configuración
     *
     * @param filePath ruta a verificar
     * @return true si el archivo existe
     */
    public static boolean configExists(String filePath) {
        return new File(filePath).exists();
    }

    /**
     * Verifica si existe el archivo de configuración por defecto usando
     * ConfigManager
     *
     * @return true si el archivo de configuración por defecto existe
     */
    public static boolean configExists() {
        ConfigManager configManager = new ConfigManager();
        return configExists(configManager.getConfigFilePath().toString());
    }

    /**
     * Crea un ObjectMapper configurado para YAML con formato legible
     *
     * @return ObjectMapper configurado
     */
    private static ObjectMapper createObjectMapper() {
        YAMLFactory yamlFactory = new YAMLFactory()
                .enable(YAMLGenerator.Feature.MINIMIZE_QUOTES)
                .enable(YAMLGenerator.Feature.INDENT_ARRAYS)
                .disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER);

        ObjectMapper mapper = new ObjectMapper(yamlFactory);
        return mapper;
    }
}
