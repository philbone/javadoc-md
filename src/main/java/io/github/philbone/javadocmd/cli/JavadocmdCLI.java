/**
 *    d8b                                 888                                          888
 *    Y8P                                 888                                          888
 *                                        888                                          888
 *   8888  8888b.  888  888  8888b.   .d88888  .d88b.   .d8888b     88888b.d88b.   .d88888
 *   "888     "88b 888  888     "88b d88" 888 d88""88b d88P"        888 "888 "88b d88" 888
 *    888 .d888888 Y88  88P .d888888 888  888 888  888 888          888  888  888 888  888
 *    888 888  888  Y8bd8P  888  888 Y88b 888 Y88..88P Y88b.    d8b 888  888  888 Y88b 888
 *    888 "Y888888   Y88P   "Y888888  "Y88888  "Y88P"   "Y8888P Y8P 888  888  888  "Y88888
 *    888
 *   d88P
 * 888P"
 *                            .d8888b.  888      8888888
 *                           d88P  Y88b 888        888
 *                           888    888 888        888
 *                           888        888        888
 *                           888        888        888
 *                           888    888 888        888
 *                           Y88b  d88P 888        888
 *                            "Y8888P"  88888888 8888888
 */
package io.github.philbone.javadocmd.cli;

import java.util.Locale;
import java.util.ResourceBundle;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.util.concurrent.Callable;

@Command(
        name = "javadocmd",
        mixinStandardHelpOptions = true,
        version = "1.0.0",
        description = "${description.app}",
        resourceBundle = "messages",
        subcommands = {
            InitCommand.class,
            ShowCommand.class,
            GetCommand.class,
            SetCommand.class,
            ValidateCommand.class,
            AliasCommand.class
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
