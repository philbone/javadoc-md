package io.github.philbone.javadocmd.exporter;

import io.github.philbone.javadocmd.model.*;

public class MarkdownExporter implements DocExporter
{

    @Override
    public String export(DocPackage docPackage) {
        MarkdownBuilder builder = new MarkdownBuilder();

        // Encabezado principal
        builder.title("`" + docPackage.getName() + "`");

        // Recorrer clases / interfaces / enums / records
        for (DocClass docClass : docPackage.getClasses()) {
            builder.subtitle(capitalize(docClass.getKind()) + ": " + docClass.getName());

            if (docClass.getDescription() != null && !docClass.getDescription().isEmpty()) {
                builder.paragraph(docClass.getDescription());
            }

            // Métodos
            if (!docClass.getMethods().isEmpty()) {
                builder.subtitle("🧮 Métodos");
                for (DocMethod method : docClass.getMethods()) {
                    String signature = method.getReturnType() + " " + method.getName()
                            + "(" + String.join(", ", method.getParameters()) + ")";
                    builder.listItem("#### `" + signature + "`");

                    if (method.getDescription() != null && !method.getDescription().isEmpty()) {
                        builder.paragraph(method.getDescription());
                    }

                    // @param
                    for (DocParameter param : method.getDocParameters()) {
                        builder.listItem("*@param* **" + param.getName() + "** " + param.getDescription());
                    }

                    // @return
                    if (method.getReturnDescription() != null) {
                        builder.listItem("*@return* " + method.getReturnDescription());
                    }

                    // @throws
                    for (DocException ex : method.getExceptions()) {
                        builder.listItem("*@throws* **" + ex.getName() + "** " + ex.getDescription());
                    }
                }
            }
        }

        return builder.build();
    }

    private String capitalize(String s) {
        if (s == null || s.isEmpty()) return s;
        return s.substring(0,1).toUpperCase() + s.substring(1);
    }
}
