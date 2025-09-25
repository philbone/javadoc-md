package io.github.philbone.javadocmd.exporter;

import io.github.philbone.javadocmd.model.*;

public class MarkdownExporter implements DocExporter
{

    @Override
    public String export(DocPackage docPackage) {
        MarkdownBuilder builder = new MarkdownBuilder();

        // Encabezado principal
        builder.title("Documentación del paquete `" + docPackage.getName() + "`");

        // Recorrer clases
        for (DocClass docClass : docPackage.getClasses()) {
            builder.subtitle("Clase: " + docClass.getName());

            if (docClass.getDescription() != null && !docClass.getDescription().isEmpty()) {
                builder.paragraph(docClass.getDescription());
            }

            // Métodos
            if (!docClass.getMethods().isEmpty()) {
                builder.subtitle("Métodos");
                for (DocMethod method : docClass.getMethods()) {
                    String signature = method.getReturnType() + " " + method.getName()
                            + "(" + String.join(", ", method.getParameters()) + ")";
                    builder.listItem("`" + signature + "`");

                    if (method.getDescription() != null && !method.getDescription().isEmpty()) {
                        builder.paragraph(method.getDescription());
                    }
                }
            }
        }

        return builder.build();
    }
}
