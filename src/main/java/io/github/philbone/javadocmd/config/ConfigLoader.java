package io.github.philbone.javadocmd.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.io.File;

/**
 * Esta clase se encarga de detectar el fichero de configuración
 * y cargar los datos si son encontrados.
 * De otra manera cargará los valores por defecto.
 * 
 * @author Felipe M. <philbone@focused.cl>
 */
public class ConfigLoader
{
    
    /**
     * 
     * @return un objeto con los datos de configuracion iniciales.
     */
    public static Config loadConfig() {
        Config defaultConfig = new Config();

        File yamlFile = new File("config.yml");
        if (!yamlFile.exists()) {
            System.out.println("ℹ️  No se encontró config.yml, usando valores por defecto");
            return defaultConfig;
        }

        try {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            Config fileConfig = mapper.readValue(yamlFile, Config.class);
            System.out.println("✅ Configuración cargada desde config.yml");
            return fileConfig;
        } catch (Exception e) {
            System.err.println("❌ Error leyendo config.yml: " + e.getMessage() + ", usando valores por defecto");
            return defaultConfig;
        }
    }
}
