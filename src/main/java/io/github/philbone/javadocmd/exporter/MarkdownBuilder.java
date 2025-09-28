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
}
