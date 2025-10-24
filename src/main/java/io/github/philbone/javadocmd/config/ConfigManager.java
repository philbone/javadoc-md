package io.github.philbone.javadocmd.config;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

/**
 * Gestiona las rutas y directorios de configuración
 */
public class ConfigManager
{
    private static final String CONFIG_DIR = ".javadocmd";
    private static final String DEFAULT_CONFIG_FILE = "config.yml";
    private static final String LANGS_DIR = "langs";

    private final Path baseDirectory;

    public ConfigManager() {
        this(Paths.get(".").toAbsolutePath().normalize());
    }

    public ConfigManager(Path baseDirectory) {
        this.baseDirectory = baseDirectory;
    }

    public Path getConfigDir() {
        return baseDirectory.resolve(CONFIG_DIR);
    }

    public Path getConfigFilePath() {
        return getConfigDir().resolve(DEFAULT_CONFIG_FILE);
    }

    public Path getLangsDir() {
        return getConfigDir().resolve(LANGS_DIR);
    }

    public boolean configDirExists() {
        return Files.exists(getConfigDir());
    }

    public boolean configFileExists() {
        return Files.exists(getConfigFilePath());
    }

    public boolean langsDirExists() {
        return Files.exists(getLangsDir());
    }

    public void ensureConfigDir() throws IOException {
        Files.createDirectories(getConfigDir());
    }

    public void ensureLangsDir() throws IOException {
        Files.createDirectories(getLangsDir());
    }

    public Path getBaseDirectory() {
        return baseDirectory;
    }
}
