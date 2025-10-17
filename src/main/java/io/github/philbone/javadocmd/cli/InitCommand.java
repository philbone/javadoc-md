package io.github.philbone.javadocmd.cli;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

@Command(
    name = "init",
    description = "${usage.init}",
    resourceBundle = "messages"
)
public class InitCommand implements Callable<Integer> {
    
    @Option(names = {"-h", "--help"}, usageHelp = true, descriptionKey = "init.help")
    private boolean helpRequested;

    @Option(names = {"--sourcePath"}, descriptionKey = "init.sourcePath", paramLabel = "SOURCE_PATH", required = true)
    private String sourcePath;

    @Option(names = {"--outputPath"}, descriptionKey = "init.outputPath", paramLabel = "OUTPUT_PATH", required = true)
    private String outputPath;
    
    @Override
    public Integer call() {
        System.out.println("Comando init ejecutado");
        return 0;
    }
}