package io.github.philbone.javadocmd.exporter;

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
}
