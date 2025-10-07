package io.github.philbone.javadocmd.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.io.File;

public class ConfigLoader {
    
    public static Config loadConfig() {
        Config defaultConfig = new Config();
        
        File configFile = new File("config.yml");
        if (!configFile.exists()) {
            System.out.println("ℹ️  No se encontró config.yml, usando valores por defecto");
            return defaultConfig;
        }
        
        try {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            Config fileConfig = mapper.readValue(configFile, Config.class);
            System.out.println("✅ Configuración cargada desde config.yml");
            return fileConfig;
        } catch (Exception e) {
            System.err.println("❌ Error leyendo config.yml: " + e.getMessage() + ", usando valores por defecto");
            return defaultConfig;
        }
    }
}