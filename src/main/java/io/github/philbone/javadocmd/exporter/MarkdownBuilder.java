package io.github.philbone.javadocmd.exporter;

import io.github.philbone.javadocmd.model.DocClass;
import io.github.philbone.javadocmd.model.DocPackage;

public class MarkdownBuilder
{
    private StringBuilder outPrint = new StringBuilder();

    public MarkdownBuilder title(String text) {
        outPrint.append("# ").append(text).append("\n\n");
        return this;
    }

    public MarkdownBuilder subtitle(String text) {        
        outPrint.append("## ").append(text).append("\n\n");
        return this;
    }
    
    public MarkdownBuilder h3(String text) {        
        outPrint.append("### ").append(text).append("\n\n");
        return this;
    }
    
    public MarkdownBuilder h4(String text) {        
        outPrint.append("#### ").append(text).append("\n\n");
        return this;
    }

    public MarkdownBuilder paragraph(String text) {
        outPrint.append(text).append("\n\n");
        return this;
    }

    public MarkdownBuilder listItem(String text) {
        outPrint.append("- ").append(text).append("\n");
        return this;
    }
    
    public String build() {
        return outPrint.toString();
    }

    public void codeBlock(String content, String codeLang) {
        outPrint.append("```");
        if (codeLang != null && !codeLang.isBlank()) {
            outPrint.append(codeLang);
        }
        outPrint.append("\n")
                .append(content)
                .append("\n```")
                .append("\n");
    }
    
    public MarkdownBuilder blockquote(String text) {
        outPrint.append("> ").append(text.replace("\n", "\n> ")).append("\n\n");
        return this;
    }
    
    public MarkdownBuilder sub(String text){
        outPrint.append("<sub>").append(text).append("</sub>");
        return this;
    }
    
    /**
     * Inyecta una etiqueta arbitraria directamente en el flujo del Markdown.
     * <p>
     * Se utiliza principalmente como auxiliar para aplicar prefijos en las
     * siguientes líneas, por ejemplo:
     * <ul>
     * <li>{@code "> "} para iniciar un bloque de cita.</li>
     * <li>{@code "- "} para forzar un ítem de lista.</li>
     * <li>{@code ">> "} para una cita anidada.</li>
     * </ul>
     * </p>
     * <p>
     * Este método no agrega saltos de línea ni contenido adicional, únicamente
     * inserta el texto indicado de forma literal.
     * </p>
     *
     * @param tag la etiqueta o prefijo a inyectar (se añade tal cual al
     * buffer).
     * @return la instancia actual de {@code MarkdownBuilder}, para encadenar
     * llamadas.
     */
    public MarkdownBuilder tag(String tag) {
        outPrint.append(tag);
        return this;
    }

    public MarkdownBuilder toc(DocPackage docPackage) {
        // estos textos deben ser cargados desde la configuración de idioma de usuario(.langs/es-en.yml)
        subtitle("Resumen de Clases\n");
        outPrint.append("|#|CLASE|DESCRIPCIÓN|\n");
        outPrint.append("|---|---|---|\n");
        int i = 1;
        for (DocClass docClass : docPackage.getClasses()) {
            docClass.setIndexOrder(i);
            String item = docClass.getVisibility()
                    + (docClass.isStatic() ? " static " : " ")
                    + docClass.getKind().toString().toLowerCase()
                    + " " + docClass.getName();
            
            String itemUrl = "[" + item.replaceAll("_", " ") + "](#" + docClass.getIndexOrder() + "-" + item.replaceAll(" ", "-").replaceAll("_", "-").toLowerCase() + ")";
            String cleanDesc = sanitizeDescription(docClass.getDescription());
            outPrint.append("|**").append(docClass.getIndexOrder()).append("**").append("|").append(itemUrl).append("|").append(cleanDesc).append("\n");
            i++;
        }        
        return this;
    }
    
    private String sanitizeDescription(String raw) {
        if (raw == null || raw.isBlank()) {
            return "";
        }

        // 1. Quitar tags HTML
        String clean = raw.replaceAll("<[^>]+>", " ").replaceAll("\\s+", " ").trim();

        // 2. Cortar en primer punto final
        int dotIndex = clean.indexOf('.');
        if (dotIndex > 0) {
            clean = clean.substring(0, dotIndex + 1);
        }

        // 3. Cortar en límite de longitud
        if (clean.length() > 140) {
            clean = clean.substring(0, 140) + "...";
        }

        return clean;
    }

    public MarkdownBuilder insertAt(int index, String text) {
         outPrint.insert(index, text);
         return this;
    }

}
