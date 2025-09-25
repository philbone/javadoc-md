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

/**
 * Clase Javadocmd
 */
public class Javadocmd
{

    /**
     * método estático main
     *
     * @param args texto
     * @return void
     */
    public static void main(String[] args) {
        generateDocs("/home/felipe/Documentos/proyectos/Java/javadocmd/src/main/java/io/github/philbone/javadocmd/", "");
    }

    /**
     * método estático generateDocs
     *
     * @param sourcePath texto
     * @param outputPath texto
     * @return void
     */
    public static void generateDocs(String sourcePath, String outputPath) {
        try {
            // Parsear un archivo específico por ahora
            File file = new File(sourcePath + "Javadocmd.java");
            CompilationUnit cu = StaticJavaParser.parse(file);

            DocPackage docPackage = new DocPackage("com.ejemplo.utils");

            JavadocExtractorVisitor visitor = new JavadocExtractorVisitor();
            visitor.visit(cu, docPackage);

            DocExporter exporter = new MarkdownExporter();
            String markdown = exporter.export(docPackage);

            System.out.println(markdown);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
