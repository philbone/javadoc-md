package io.github.philbone.javadocmd.exporter;

import io.github.philbone.javadocmd.model.DocPackage;

public class MarkdownExporter implements DocExporter
{

    @Override
    public String export(DocPackage docPackage) {
        MarkdownBuilder builder = new MarkdownBuilder();
        builder.title("Documentación del paquete `" + docPackage.getName() + "`");
        // TODO: recorrer clases y métodos
        return builder.build();
    }
}
