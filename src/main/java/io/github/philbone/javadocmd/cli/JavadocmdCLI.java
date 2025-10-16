package io.github.philbone.javadocmd.cli;

import java.util.Locale;
import java.util.ResourceBundle;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.util.concurrent.Callable;

@Command(
        name = "javadocmd",
        mixinStandardHelpOptions = true,
        version = "1.0",
        description = "JavaDoc to Markdown documentation tool",
        resourceBundle = "messages",
        subcommands = {
            InitCommand.class
        }
)
public class JavadocmdCLI implements Callable<Integer>
{

    @Override
    public Integer call() {
        // Si no se especifica subcomando, mostrar ayuda
        CommandLine.usage(this, System.out);
        return 0;
    }

    public static void main(String[] args) {

        String lang = System.getProperty("user.language", "en");
        Locale locale = Locale.of(lang);  // Java 19+
        ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);

        int exitCode = new CommandLine(new JavadocmdCLI())
                .setResourceBundle(bundle)
                .setExecutionStrategy(new CommandLine.RunLast())
                .execute(args);
        System.exit(exitCode);

    }

}
