package io.github.philbone.javadocmd.exporter;

import io.github.philbone.javadocmd.model.DocClass;
import io.github.philbone.javadocmd.model.DocPackage;

public class MarkdownBuilder
{
    private StringBuilder sb = new StringBuilder();

    public MarkdownBuilder title(String text) {
        sb.append("# ").append(text).append("\n\n");
        return this;
    }

    public MarkdownBuilder subtitle(String text) {        
        sb.append("## ").append(text).append("\n\n");
        return this;
    }
    
    public MarkdownBuilder h3(String text) {        
        sb.append("### ").append(text).append("\n\n");
        return this;
    }

    public MarkdownBuilder paragraph(String text) {
        sb.append(text).append("\n\n");
        return this;
    }

    public MarkdownBuilder listItem(String text) {
        sb.append("- ").append(text).append("\n");
        return this;
    }
    
    public String build() {
        return sb.toString();
    }

    public void codeBlock(String content, String codeLang) {
        sb.append("```");
        if (codeLang != null && !codeLang.isBlank()) {
            sb.append(codeLang);
        }
        sb.append("\n")
                .append(content)
                .append("\n```")
                .append("\n");
    }
    
    public MarkdownBuilder blockquote(String text) {
        sb.append("> ").append(text.replace("\n", "\n> ")).append("\n\n");
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
        sb.append(tag);
        return this;
    }

    void toc(DocPackage docPackage) {
        for (DocClass docClass : docPackage.getClasses()) {            
            String item = docClass.getVisibility()
                    + (docClass.isStatic() ? " static " : " ")
                    + docClass.getKind().toString().toLowerCase()
                    + " " + docClass.getName();
            
            String itemUrl = "[" + item + "](#-" + item.replaceAll(" ", "-").toLowerCase() + ")";            
            this.listItem(itemUrl);
        }
    }
}
