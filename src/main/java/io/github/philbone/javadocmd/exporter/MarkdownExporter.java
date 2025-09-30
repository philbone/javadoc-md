package io.github.philbone.javadocmd.exporter;

import io.github.philbone.javadocmd.model.*;

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
        boolean firstClass = true;

        // Encabezado principal        
        builder.title("`" + docPackage.getName() + "`");

        // Recorrer clases / interfaces / enums / records
        for (DocClass docClass : docPackage.getClasses()) {
            if (!firstClass) {
                builder.paragraph("---"); // separador entre clases
            }
            firstClass = false;

            String emoji = formatEmoji(docClass.getKind());
            String header = emoji + " " 
                    + capitalize(docClass.getVisibility())
                    + (docClass.isStatic() ? " static " : " ")
                    + formatKind(docClass.getKind())
                    + " " + docClass.getName();

            builder.subtitle(header.trim());

            // ========== Firma en bloque de código ==========
            StringBuilder signature = new StringBuilder();
            signature.append(docClass.getVisibility()).append(" ");
            if (docClass.isStatic()) {
                signature.append("static ");
            }
            signature.append(switch (docClass.getKind()) {
                case CLASS -> "class ";
                case ABSTRACT_CLASS -> "abstract class ";
                case INTERFACE -> "interface ";
                case ENUM -> "enum ";
                case RECORD -> "record ";
            });
            signature.append(docClass.getName());

            if (docClass.getSuperClass() != null) {
                signature.append("\nextends ").append(docClass.getSuperClass());
            }

            if (!docClass.getInterfaces().isEmpty()) {
                signature.append("\nimplements ")
                        .append(String.join(", ", docClass.getInterfaces()));
            }

            builder.codeBlock(signature.toString(), "java");

            // ========== Descripción ==========
            if (docClass.getDescription() != null && !docClass.getDescription().isEmpty()) {
                builder.blockquote("**Descripción**\n" + docClass.getDescription());
            }

            // 📦 Campos
            if (!docClass.getFields().isEmpty()) {
                builder.subtitle("📦 Campos");
                for (DocField field : docClass.getFields()) {
                    String signatureField = field.getVisibility()
                            + (field.isStatic() ? " static" : "")
                            + " " + field.getType()
                            + " " + field.getName();
                    builder.listItem("`" + signatureField.trim() + "`");
                    
                    if (field.getDescription() != null && !field.getDescription().isEmpty()) {
                        builder.blockquote(field.getDescription());
                    }                    
                }
            }

            // 🛠️ Constructores
            if (!docClass.getConstructors().isEmpty()) {
                builder.subtitle("🛠️ Constructores");
                for (DocConstructor constructor : docClass.getConstructors()) {
                    String signatureCons = constructor.getVisibility()
                            + (constructor.isStatic() ? " static " : " ")
                            + constructor.getName()
                            + "(" + String.join(", ", constructor.getParameters()) + ")";
                    builder.listItem("`" + signatureCons.trim() + "`");

                    if (constructor.getDescription() != null && !constructor.getDescription().isEmpty()) {
                        builder.blockquote("**Descripción**\n" + constructor.getDescription());
                    }
                                        
                    for (DocParameter param : constructor.getDocParameters()) {
                        builder.tag("> ");
                        builder.listItem("*@param* `" + param.getName() + "`" + param.getDescription());                        
                    }

                    for (DocException ex : constructor.getExceptions()) {
                        builder.tag("> ");
                        builder.listItem("*@throws* **" + ex.getName() + "** " + ex.getDescription());                        
                    }
                }
            }

            // 🧮 Métodos
            if (!docClass.getMethods().isEmpty()) {
                builder.subtitle("🧮 Métodos");
                for (DocMethod method : docClass.getMethods()) {
                    String signatureMeth = method.getVisibility()
                            + (method.isStatic() ? " static" : "")
                            + " " + method.getReturnType()
                            + " " + method.getName()
                            + "(" + String.join(", ", method.getParameters()) + ")";
                    builder.listItem("`" + signatureMeth.trim() + "`");

                    if (method.getDescription() != null && !method.getDescription().isEmpty()) {                        
                        builder.blockquote(method.getDescription());
                    }

                    for (DocParameter param : method.getDocParameters()) {
                        builder.tag("> ");
                        builder.listItem("*@param* **" + param.getName() + "** " + param.getDescription());
                    }

                    if (method.getReturnDescription() != null) {
                        builder.tag("> ");
                        builder.listItem("*@return* " + method.getReturnDescription());
                    }

                    for (DocException ex : method.getExceptions()) {                        
                        builder.tag("> ");
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

    private String formatEmoji(Kind kind) {
        return switch (kind) {
            case CLASS -> "📘";
            case ABSTRACT_CLASS -> "📕";
            case INTERFACE -> "📗";
            case ENUM -> "📙";
            case RECORD -> "📒";
        };
    }

}
