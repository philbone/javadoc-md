package io.github.philbone.javadocmd.exporter;

import io.github.philbone.javadocmd.model.DocClass;
import io.github.philbone.javadocmd.model.DocConstructor;
import io.github.philbone.javadocmd.model.DocException;
import io.github.philbone.javadocmd.model.DocField;
import io.github.philbone.javadocmd.model.DocMethod;
import io.github.philbone.javadocmd.model.DocPackage;
import io.github.philbone.javadocmd.model.DocParameter;
import io.github.philbone.javadocmd.model.Kind;

import java.util.List;

/**
 * Exportador que genera documentación en formato Markdown
 * a partir del modelo intermedio construido con
 * {@link io.github.philbone.javadocmd.extractor.JavadocExtractorVisitor}.
 *
 * <p>Renderiza:</p>
 * <ul>
 *     <li>Firma de la clase (visibilidad, static, tipo, nombre).</li>
 *     <li>Extensiones ({@code extends}) e implementaciones ({@code implements}).</li>
 *     <li>Descripción general de la clase.</li>
 *     <li>Campos, constructores y métodos con sus firmas y documentación Javadoc.</li>
 * </ul>
 */
public class MarkdownExporter implements DocExporter {

    @Override
    public String export(DocPackage docPackage) {
        MarkdownBuilder builder = new MarkdownBuilder();

        // Encabezado principal        
        builder.title("`" + docPackage.getName() + "`");

        // Recorrer clases / interfaces / enums / records
        for (DocClass docClass : docPackage.getClasses()) {
            String classVisibility = capitalize(docClass.getVisibility());

            String classSignature = classVisibility
                    + (docClass.isStatic() ? " static " : " ")
                    + formatKind(docClass.getKind())
                    + " <span style=\"color:#d2691e\">" + docClass.getName() + "</span>";

            builder.paragraph("---");
            builder.subtitle(classSignature.trim());

            // Extends
            if (docClass.getSuperClass() != null) {
                builder.paragraph("**extends** `" + docClass.getSuperClass() + "`");
            }

            // Implements
            if (!docClass.getInterfaces().isEmpty()) {
                List<String> interfaces = docClass.getInterfaces().stream()
                        .map(i -> "`" + i + "`")
                        .toList();
                builder.paragraph("**implements** " + String.join(", ", interfaces));
            }

            // Descripción
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

    private String capitalize(String s) {
        if (s == null || s.isEmpty()) return s;
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
}
