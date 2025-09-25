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

import io.github.philbone.javadocmd.model.DocPackage;
import io.github.philbone.javadocmd.exporter.DocExporter;
import io.github.philbone.javadocmd.exporter.MarkdownExporter;

public class Javadocmd
{
    public static void main(String[] args) {
        generateDocs("", "");
    }

    public static void generateDocs(String sourcePath, String outputPath) {
        // TODO: Recorrer archivos, usar extractor, construir modelo
        DocPackage docPackage = new DocPackage("ejemplo"); // placeholder

        DocExporter exporter = new MarkdownExporter();
        String markdown = exporter.export(docPackage);

        // TODO: Guardar el markdown en outputPath/DOC.md
        System.out.println(markdown);
    }
}
