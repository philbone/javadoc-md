/**
 *       d8b                                 888                                           888
 *       Y8P                                 888                                           888
 *                                           888                                           888
 *      8888  8888b.  888  888  8888b.   .d88888  .d88b.   .d8888b      88888b.d88b.   .d88888
 *      "888     "88b 888  888     "88b d88" 888 d88""88b d88P"         888 "888 "88b d88" 888
 *       888 .d888888 Y88  88P .d888888 888  888 888  888 888           888  888  888 888  888
 *       888 888  888  Y8bd8P  888  888 Y88b 888 Y88..88P Y88b.         888  888  888 Y88b 888
 *       888 "Y888888   Y88P   "Y888888  "Y88888  "Y88P"   "Y8888P      888  888  888  "Y88888
 *       888
 *      d88P
 *    888P"
 */
package io.github.philbone.javadocmd;

import com.github.javaparser.ParserConfiguration;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import io.github.philbone.javadocmd.cli.JavadocmdCLI;
import io.github.philbone.javadocmd.config.Config;
import io.github.philbone.javadocmd.config.ConfigLoader;
import io.github.philbone.javadocmd.model.DocPackage;
import io.github.philbone.javadocmd.exporter.DocExporter;
import io.github.philbone.javadocmd.exporter.InternalLinker;
import io.github.philbone.javadocmd.exporter.MarkdownExporter;
import io.github.philbone.javadocmd.extractor.JavadocExtractorVisitor;
import io.github.philbone.javadocmd.model.DocClass;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Punto de entrada principal del programa <b>javadoc-md</b>.
 * <p>
 * Esta clase se encarga de:
 * <ul>
 * <li>Recorrer los archivos fuente de un proyecto Java.</li>
 * <li>Analizar sus clases, interfaces, enums y records.</li>
 * <li>Extraer la documentación Javadoc mediante
 * {@link JavadocExtractorVisitor}.</li>
 * <li>Generar documentación en formato Markdown usando un
 * {@link DocExporter}.</li>
 * </ul>
 *
 * <p>
 * Actualmente soporta la exportación de documentación hacia un archivo
 * <code>README.md</code> por cada paquete encontrado en el proyecto.</p>
 * 
 * @author Felipe M
 * @project JavadocMD
 */
public abstract class JavadocMd
{
    /**
     * Contador global de ejecuciones del generador de documentación.
     */
    private static int executionCount = 0;    

    /**
     * Constructor protegido por defecto.
     * <p>
     * Inicializa valores de configuración básicos.
     *
     * @throws IllegalStateException si la configuración inicial es inválida.
     */
    protected JavadocMd() throws IllegalStateException {
        executionCount++;
    }

    /**
     * Método principal que inicia el proceso de generación de documentación.
     *
     * @param args argumentos opcionales (no utilizados actualmente). Se planea
     * en futuras versiones aceptar <code>sourcePath</code> y
     * <code>outputPath</code> como parámetros desde consola.
     */
    public static void main(String[] args) {
        
        // Si hay argumentos CLI, usar el sistema de comandos
        if (args.length > 0) {
            JavadocmdCLI.main(args);
            return;
        }
        
        // Forzar Java 21
        forceJavaLevel(ParserConfiguration.LanguageLevel.JAVA_21);
        
        // carga la configuración externa 
        Config config = ConfigLoader.loadConfig();
        
        // Generar documentación
        if (config.isCombinePackagesMode()) { // true genera una documentación unificada
            generateCombinedDocs(config);
        } else { // false genera documentación por cada paquete
            //generatePackageDocs(config.getSourcePath(), config.getOutputPath(), config.getOutFileName());
            generatePackageDocs(config);
        }
    }

    /**
     * Genera la documentación en formato Markdown a partir del código fuente de
     * un proyecto Java.
     * <p>
     * El proceso sigue los siguientes pasos:
     * <ol>
     * <li>Recorrer todos los archivos <code>.java</code> en el directorio de
     * entrada.</li>
     * <li>Analizar cada archivo con {@link StaticJavaParser}.</li>
     * <li>Obtener el nombre del paquete y crear un objeto {@link DocPackage}
     * asociado.</li>
     * <li>Visitar cada clase, método y comentario con
     * {@link JavadocExtractorVisitor}.</li>
     * <li>Exportar la documentación usando un {@link MarkdownExporter}.</li>
     * </ol>
     *
     * @param sourcePath ruta del directorio que contiene los archivos fuente de
     * Java a documentar.
     * @param outputPath ruta del directorio donde se guardará la documentación
     * generada. Si es <code>null</code> o vacío, la documentación se imprime en
     * consola.
     */
    //public static void generatePackageDocs(String sourcePath, String outputPath, String outFileName, Config config) {
    public static void generatePackageDocs(Config config) {
        try {
            // 1. Mapear paquetes → DocPackage
            Map<String, DocPackage> packages = new HashMap<>();

            // 2. Recorrer todos los .java
            Files.walk( Paths.get( config.getSourcePath() ) )
                    .filter(p -> p.toString().endsWith(".java"))
                    .forEach(p -> {
                        try {
                            File file = p.toFile();
                            CompilationUnit cu = StaticJavaParser.parse(file);

                            // 3. Obtener nombre del paquete
                            String packageName = cu.getPackageDeclaration()
                                    .map(pd -> pd.getName().asString())
                                    .orElse("default");

                            // 4. Obtener/crear DocPackage
                            DocPackage docPackage = packages.computeIfAbsent(
                                    packageName,
                                    DocPackage::new
                            );

                            // 5. Extraer info con Visitor
                            JavadocExtractorVisitor visitor = new JavadocExtractorVisitor();
                            visitor.visit(cu, docPackage);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });

            // 6. Exportar cada paquete
             // Reunir todas las clases del proyecto
            Set<String> allClasses = packages.values().stream()
                    .flatMap(pkg -> pkg.getClasses().stream())
                    .map(DocClass::getName)
                    .collect(Collectors.toSet());
            // Crear linker global
            InternalLinker internalLinker = new InternalLinker(allClasses, ".md");
            DocExporter exporter = new MarkdownExporter(config, internalLinker);
            for (DocPackage docPackage : packages.values()) {
                String markdown = exporter.export(docPackage);
                if ( config.getOutputPath() == null || config.getOutputPath().isEmpty() ) {
                    // Mostrar en consola
                    System.out.println(markdown);
                } else {
                    // Guardar en archivo README.md dentro del outputPath
                    Path outDir = Paths.get( config.getOutputPath(), docPackage.getName().replace('.', '/'));
                    Files.createDirectories(outDir);
                    //Path outFile = outDir.resolve(outFileName);
                    Path outFile = outDir.resolve( config.getOutFileName() );
                    Files.writeString(outFile, markdown);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generateCombinedDocs(Config config) {
        try {
            // 1. Mapear paquetes → DocPackage
            Map<String, DocPackage> packages = new HashMap<>();

            // 2. Recorrer todos los .java
            Files.walk( Paths.get( config.getSourcePath() ) )
                    .filter(p -> p.toString().endsWith(".java"))
                    .forEach(p -> {
                        try {
                            File file = p.toFile();
                            CompilationUnit cu = StaticJavaParser.parse(file);

                            // 3. Obtener nombre del paquete
                            String packageName = cu.getPackageDeclaration()
                                    .map(pd -> pd.getName().asString())
                                    .orElse("default");

                            // 4. Obtener/crear DocPackage
                            DocPackage docPackage = packages.computeIfAbsent(
                                    packageName,
                                    DocPackage::new
                            );

                            // 5. Extraer info con Visitor
                            JavadocExtractorVisitor visitor = new JavadocExtractorVisitor();
                            visitor.visit(cu, docPackage);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });

            // 6. Exportar todos los paquetes en un solo archivo unificado
            // Reunir todas las clases del proyecto
            Set<String> allClasses = packages.values().stream()
                    .flatMap(pkg -> pkg.getClasses().stream())
                    .map(DocClass::getName)
                    .collect(Collectors.toSet());
            // Crear linker global
            InternalLinker internalLinker = new InternalLinker(allClasses, ".md");
            DocExporter exporter = new MarkdownExporter(config, internalLinker);
            StringBuilder combined = new StringBuilder();

            for (DocPackage docPackage : packages.values()) {
                String markdown = exporter.export(docPackage);
                combined.append(markdown).append("\n\n---\n\n");
            }

            if ( config.getOutputPath() == null || config.getOutputPath().isEmpty() ) {
                System.out.println(combined.toString());
            } else {
                Path outDir = Paths.get( config.getOutputPath() );
                Files.createDirectories(outDir);
                Path outFile = outDir.resolve( config.getOutFileName() );
                Files.writeString(outFile, combined.toString());
                System.out.println("✅ Documentación combinada generada en: " + outFile);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void forceJavaLevel(ParserConfiguration.LanguageLevel languageLevel) {        
        ParserConfiguration config = new ParserConfiguration();
        config.setLanguageLevel(languageLevel);
        StaticJavaParser.setConfiguration(config);
    }
}
