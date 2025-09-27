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

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import io.github.philbone.javadocmd.model.DocPackage;
import io.github.philbone.javadocmd.exporter.DocExporter;
import io.github.philbone.javadocmd.exporter.MarkdownExporter;
import io.github.philbone.javadocmd.extractor.JavadocExtractorVisitor;

import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

/**
 * Clase JavadocMd
 */
public abstract class JavadocMd
{
    private static String outFileName = "README.md";
    private static String sourcePath = "/home/felipe/Documentos/proyectos/Java/javadocmd/src/main/java/io/github/philbone/javadocmd/";
    private static String outputPath = "/home/felipe/Documentos/proyectos/Java/javadocmd/src/main/java/";
    
    /**
     * 
     * @param args argumentos
     */
    public static void main(String[] args) {
        
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Source Path:");
//        String sourcePath = sc.nextLine();
//        System.out.println("Output Path");
//        String outputPath = sc.nextLine();
        generateDocs(sourcePath, outputPath);
    }
    
    /**
     * 
     * @param sourcePath texto, el directorio donde está el proyecto a documentar
     * @param outputPath texto, el directorio donde será entregada la documentación de salida
     */
    public static void generateDocs(String sourcePath, String outputPath) {
        try {
            // 1. Mapear paquetes → DocPackage
            Map<String, DocPackage> packages = new HashMap<>();

            // 2. Recorrer todos los .java
            Files.walk(Paths.get(sourcePath))
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
            DocExporter exporter = new MarkdownExporter();
            for (DocPackage docPackage : packages.values()) {
                String markdown = exporter.export(docPackage);

                if (outputPath == null || outputPath.isEmpty()) {
                    // Mostrar en consola
                    System.out.println(markdown);
                } else {
                    // Guardar en archivo DOC.md dentro del outputPath
                    Path outDir = Paths.get(outputPath, docPackage.getName().replace('.', '/'));
                    Files.createDirectories(outDir);
                    Path outFile = outDir.resolve(outFileName);
                    Files.writeString(outFile, markdown);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}