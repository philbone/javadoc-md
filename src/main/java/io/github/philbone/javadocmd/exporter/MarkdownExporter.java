package io.github.philbone.javadocmd.exporter;

import io.github.philbone.javadocmd.model.*;

public class MarkdownExporter implements DocExporter {

    @Override
    public String export(DocPackage docPackage) {
        MarkdownBuilder builder = new MarkdownBuilder();

        // Encabezado principal        
        builder.title("`" + docPackage.getName() + "`");

        // Recorrer clases / interfaces / enums / records
        for (DocClass docClass : docPackage.getClasses()) {
            String classVisiblity = docClass.getVisibility().substring(0, 1).toUpperCase() + docClass.getVisibility().substring(1);
            String classSignature = classVisiblity
                    + (docClass.isStatic() ? " static " : " ") 
                    + formatKind(docClass.getKind()) 
                    + " <span style=\"color:#d2691e\">" + docClass.getName() + "</span>";
            
            builder.paragraph("---");
            builder.subtitle(classSignature.trim());

            if (docClass.getDescription() != null && !docClass.getDescription().isEmpty()) {
                builder.paragraph(docClass.getDescription());
            }

            // 📦 Campos
            if (!docClass.getFields().isEmpty()) {
                builder.subtitle("📦 Campos");
                for (DocField field : docClass.getFields()) {
                    String signature = field.getVisibility()
                            + (field.isStatic() ? " static" : "")
                            + " " + field.getType()
                            + " " + field.getName();
                    builder.listItem("#### `" + signature.trim() + "`");

                    if (field.getDescription() != null && !field.getDescription().isEmpty()) {
                        builder.paragraph(field.getDescription());
                    }
                }
            }

            // 🛠️ Constructores
            if (!docClass.getConstructors().isEmpty()) {
                builder.subtitle("🛠️ Constructores");
                for (DocConstructor constructor : docClass.getConstructors()) {
                    String signature = constructor.getVisibility()
                            + (constructor.isStatic() ? " static " : " ")
                            + constructor.getName()
                            + "(" + String.join(", ", constructor.getParameters()) + ")";
                    builder.listItem("#### `" + signature.trim() + "`");

                    if (constructor.getDescription() != null && !constructor.getDescription().isEmpty()) {
                        builder.paragraph(constructor.getDescription());
                    }

                    for (DocParameter param : constructor.getDocParameters()) {
                        builder.listItem("*@param* **" + param.getName() + "** " + param.getDescription());
                    }

                    for (DocException ex : constructor.getExceptions()) {
                        builder.listItem("*@throws* **" + ex.getName() + "** " + ex.getDescription());
                    }
                }
            }

            // 🧮 Métodos
            if (!docClass.getMethods().isEmpty()) {
                builder.subtitle("🧮 Métodos");
                for (DocMethod method : docClass.getMethods()) {
                    String signature = method.getVisibility()
                            + (method.isStatic() ? " static" : "")
                            + " " + method.getReturnType()
                            + " " + method.getName()
                            + "(" + String.join(", ", method.getParameters()) + ")";
                    builder.listItem("#### `" + signature.trim() + "`");

                    if (method.getDescription() != null && !method.getDescription().isEmpty()) {
                        builder.paragraph(method.getDescription());
                    }

                    for (DocParameter param : method.getDocParameters()) {
                        builder.listItem("*@param* **" + param.getName() + "** " + param.getDescription());
                    }

                    if (method.getReturnDescription() != null) {
                        builder.listItem("*@return* " + method.getReturnDescription());
                    }

                    for (DocException ex : method.getExceptions()) {
                        builder.listItem("*@throws* **" + ex.getName() + "** " + ex.getDescription());
                    }
                }
            }
        }        
        
        return builder.build();
    }

    private String formatKind(Kind kind) {
        return switch (kind) {
            case CLASS -> "Class";
            case ABSTRACT_CLASS -> "Abstract Class";
            case INTERFACE -> "Interface";
            case ENUM -> "Enum";
            case RECORD -> "Record";
        };
    }
}
