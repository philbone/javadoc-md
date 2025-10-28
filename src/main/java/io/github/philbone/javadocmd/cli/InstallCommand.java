package io.github.philbone.javadocmd.cli;

import io.github.philbone.javadocmd.config.ConfigurationService;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;

@Command(
        name = "install",
        aliases = {"instalar"},
        description = "${usage.install}",
        mixinStandardHelpOptions = true,
        resourceBundle = "messages"
)
public class InstallCommand implements Callable<Integer>
{
    private final ResourceBundle messages;
    private final ConfigurationService configService;

    public InstallCommand() {
        this.messages = ResourceBundle.getBundle("messages");
        this.configService = new ConfigurationService(messages);
    }
    
    @Option(
            names = {"--jar-path", "-p"},
            description = "Ruta donde está javadocmd-1.0.0.jar (por defecto: ~/.javadocmd)",
            defaultValue = "~/.javadocmd"
    )
    private String jarPath;

    @Option(
            names = {"--alias-name", "-a"},
            description = "Nombre del alias (por defecto: javadocmd)",
            defaultValue = "javadocmd"
    )
    private String aliasName;

    @Option(
            names = {"--force", "-f"},
            description = "Forzar creación aunque el alias ya exista"
    )
    private boolean force;

    @Override
    public Integer call() throws Exception {
        System.out.println("🚀 Configurando alias para JavaDocMd...");

        // Verificar si el alias ya existe
        if (!force && aliasExists(aliasName)) {
            System.err.println("❌ El alias '" + aliasName + "' ya existe en ~/.bashrc");
            System.err.println("   Usa --force para sobrescribir");
            return 1;
        }

        // Crear el alias
        if (createAlias()) {
            System.out.println("✅ Alias creado exitosamente: " + aliasName);
            System.out.println("📁 Ruta del JAR: " + jarPath + "/javadocmd-1.0.0.jar");
            System.out.println("\n📝 Para usar el alias ejecuta:");
            System.out.println("   source ~/.bashrc");
            System.out.println("   " + aliasName + " --help");
            return 0;
        } else {
            System.err.println("❌ Error al crear el alias");
            return 1;
        }
    }

    private boolean aliasExists(String aliasName) {
        try {
            String homeDir = System.getProperty("user.home");
            File bashrc = new File(homeDir + "/.bashrc");
            if (!bashrc.exists()) {
                return false;
            }

            // Buscar el alias en el archivo
            java.nio.file.Path path = java.nio.file.Paths.get(bashrc.getAbsolutePath());
            String content = new String(java.nio.file.Files.readAllBytes(path));
            return content.contains("alias " + aliasName + "=");
        } catch (Exception e) {
            return false;
        }
    }

    private boolean createAlias() {
        try {
            String homeDir = System.getProperty("user.home");
            File bashrc = new File(homeDir + "/.bashrc");

            FileWriter fw = new FileWriter(bashrc, true);
            fw.write("\n# Alias para JavaDocMd (creado automáticamente)\n");
            fw.write("alias " + aliasName + "='java -jar " + jarPath + "/javadocmd-1.0.0.jar'\n");
            fw.close();

            return true;
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            return false;
        }
    }

//    public static void main(String[] args) {
//        int exitCode = new CommandLine(new InstallCommand()).execute(args);
//        System.exit(exitCode);
//    }
}
