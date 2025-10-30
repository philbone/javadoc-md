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
        name = "alias",
        aliases = {"make-alias"},
        description = "${usage.alias}",
        mixinStandardHelpOptions = true,
        resourceBundle = "messages"
)
public class AliasCommand implements Callable<Integer>
{
    private final ResourceBundle messages;
    private final ConfigurationService configService;

    public AliasCommand() {
        this.messages = ResourceBundle.getBundle("messages");
        this.configService = new ConfigurationService(messages);
    }
    
    @Option(
            names = {"--jar-path", "-p"},
            descriptionKey = "alias.jarPath",
            defaultValue = "~/.javadocmd"
    )
    private String jarPath;

    @Option(
            names = {"--alias-name", "-a"},
            descriptionKey = "alias.name",
            defaultValue = "javadocmd"
    )
    private String aliasName;

    @Option(
            names = {"--force", "-f"},
            descriptionKey = "alias.force"
    )
    private boolean force;

    @Override
    public Integer call() throws Exception {
        System.out.println(messages.getString("alias.message.config"));

        // Verificar si el alias ya existe
        if (!force && aliasExists(aliasName)) {            
            System.err.println( String.format(messages.getString("alias.message.alreadyExist"), aliasName) ); // ❌ El alias '" + aliasName + "' ya existe en ~/.bashrc
            System.err.println(messages.getString("alias.message.forceTip"));
            return 1;
        }

        // Crear el alias
        if (createAlias()) {
            System.out.println(messages.getString("alias.message.success") + ": " + aliasName);
            System.out.println( String.format(messages.getString("alias.message.jarRoute"), jarPath)); //Ruta del JAR: " + jarPath + "/javadocmd-1.0.0.jar
            System.out.println(messages.getString("alias.message.useTip"));
            System.out.println("   source ~/.bashrc");
            System.out.println("   " + aliasName + " --help");
            return 0;
        } else {
            System.err.println(messages.getString("alias.message.error"));
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
            fw.write(messages.getString("alias.message.wrout"));
            fw.write("alias " + aliasName + "='java -jar " + jarPath + "/javadocmd-1.0.0.jar'\n");
            fw.close();

            return true;
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            return false;
        }
    }

//    public static void main(String[] args) {
//        int exitCode = new CommandLine(new AliasCommand()).execute(args);
//        System.exit(exitCode);
//    }
}
