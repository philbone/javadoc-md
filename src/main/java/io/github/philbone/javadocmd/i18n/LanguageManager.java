package io.github.philbone.javadocmd.i18n;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;

import java.io.File;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Gestiona la carga y acceso a textos traducidos para la documentación.
 * Usa archivos YAML externos ubicados en la carpeta "langs/".
 * 
 * Si no se encuentra el idioma solicitado, carga inglés por defecto.
 * Si falta alguna clave, devuelve la clave original como fallback.
 * 
 * Ejemplo de uso:
 * LanguageManager lang = new LanguageManager("es", Paths.get("."));
 * System.out.println(lang.get("classes")); // → "Clases"
 * 
 * @author 
 * Felipe M. <philbone@focused.cl> (inspirado en ConfigLoader)
 */
public class LanguageManager
{
    private static final String DEFAULT_LANG = "en";
    private static final String LANG_DIR = "langs";
    //private static String langCode;
    
    private final Map<String, String> labels;
    
    private ResourceBundle appMessages;

    public LanguageManager(String langCode, Path basePath) {
        this.appMessages = ResourceBundle.getBundle("app_messages");
        this.labels = loadLanguage(langCode, basePath);
    }

    @SuppressWarnings("unchecked")
    private Map<String, String> loadLanguage(String langCode, Path basePath) {
        ObjectMapper mapper = createObjectMapper();

        // Ruta del archivo de idioma (por ejemplo, langs/es.yml)
        Path langPath = basePath.resolve(LANG_DIR).resolve(langCode + ".yml");
        File langFile = langPath.toFile();

        if (!langFile.exists()) {
            System.err.println(appMessages.getString("message.lang.noFile") + ": " + langPath + ". " + appMessages.getString("message.lang.noFile") + ".");
            return loadDefaultLanguage(mapper, basePath);
        }

        try {
            Map<String, Object> yaml = mapper.readValue(langFile, Map.class);
            Map<String, String> labelsMap = (Map<String, String>) yaml.getOrDefault("labels", Collections.emptyMap());
            System.out.println( appMessages.getString("message.lang.loaded") + ": " + langCode);
            return labelsMap;
        } catch (Exception e) {
            System.err.println(appMessages.getString("message.lang.readError")+ " " + langPath + ": " + e.getMessage());
            return loadDefaultLanguage(mapper, basePath);
        }
    }

    @SuppressWarnings("unchecked")
    private Map<String, String> loadDefaultLanguage(ObjectMapper mapper, Path basePath) {
        Path defaultPath = basePath.resolve(LANG_DIR).resolve(DEFAULT_LANG + ".yml");
        File defaultFile = defaultPath.toFile();

        if (!defaultFile.exists()) {
            System.err.println( appMessages.getString("message.lang.noDefaultFile") + ": " + defaultPath );
            return Collections.emptyMap();
        }

        try {
            Map<String, Object> yaml = mapper.readValue(defaultFile, Map.class);
            return (Map<String, String>) yaml.getOrDefault("labels", Collections.emptyMap());
        } catch (Exception e) {
            System.err.println( appMessages.getString("message.lang.readErrorDefault") + ": " + e.getMessage());
            return Collections.emptyMap();
        }
    }

    private ObjectMapper createObjectMapper() {
        YAMLFactory yamlFactory = new YAMLFactory()
            .enable(YAMLGenerator.Feature.MINIMIZE_QUOTES)
            .enable(YAMLGenerator.Feature.INDENT_ARRAYS)
            .disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER);

        return new ObjectMapper(yamlFactory);
    }

    /**
     * Obtiene el texto traducido asociado a una clave.
     * Si no existe, devuelve la propia clave.
     */
    public String get(String key) {
        return labels.getOrDefault(key, key);
    }
}
