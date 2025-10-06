package io.github.philbone.javadocmd.extractor;

import com.github.javaparser.ast.comments.JavadocComment;
import com.github.javaparser.javadoc.Javadoc;

import java.util.List;
import java.util.Optional;

/**
 * Utilidades para trabajar con JavadocComment de JavaParser.
 * <p>
 * Provee métodos para:
 * <ul>
 *   <li>extraer una descripción "limpia" (heurística configurable),</li>
 *   <li>extraer la etiqueta @project (si existe),</li>
 *   <li>obtener una "descripción completa" que incluye los block tags (útil para debugging / fallback).</li>
 * </ul>
 */
public class JavadocUtils
{
    private static final List<String> TECHNICAL_TAGS = List.of(
            "author", "param", "return", "throws", "exception", "project"
    );

    // --- Helper: parse limpio (defensivo) ---
    private static Javadoc parseCleaning(JavadocComment comment) {
        String raw = comment.getContent();
        if (raw == null) raw = "";
        raw = raw.trim();
        // Si accidentalmente se pasó el bloque /** ... */ en bruto, limpiarlo
        if (raw.startsWith("/**")) {
            raw = raw.replaceAll("^/\\*\\*", "");
        }
        if (raw.endsWith("*/")) {
            raw = raw.replaceAll("\\*/$", "");
        }
        raw = raw.trim();
        return new JavadocComment(raw).parse();
    }

    /**
     * Extrae la descripción "preferida" desde un JavadocComment.
     * <p>
     *  - Si existe texto libre en la parte de descripción (antes de las etiquetas), lo devuelve.
     *  - Si no, intenta devolver el primer blockTag que no sea técnico
     *    (por ejemplo: @since, @note, etc., siempre que no esté en TECHNICAL_TAGS).
     *  - Si no encuentra nada, devuelve cadena vacía.
     */
    public static String extractDescription(Optional<JavadocComment> maybeComment) {
        if (maybeComment == null || maybeComment.isEmpty()) return "";

        Javadoc javadoc = parseCleaning(maybeComment.get());

        String mainDesc = javadoc.getDescription().toText().trim();
        if (!mainDesc.isEmpty()) return mainDesc;

        return javadoc.getBlockTags().stream()
                .filter(tag -> {
                    String tagName = tag.getTagName();
                    return tagName == null || !TECHNICAL_TAGS.contains(tagName.toLowerCase());
                })
                .map(tag -> tag.getContent().toText().trim())
                .filter(s -> !s.isEmpty())
                .findFirst()
                .orElse("");
    }

    /**
     * Extrae el contenido del primer tag {@code @project} (si existe).
     * Devuelve Optional.empty() si no está presente.
     */
    public static Optional<String> extractProjectTag(Optional<JavadocComment> maybeComment) {
        if (maybeComment == null || maybeComment.isEmpty()) return Optional.empty();

        Javadoc javadoc = parseCleaning(maybeComment.get());

        return javadoc.getBlockTags().stream()
                .filter(tag -> {
                    String tn = tag.getTagName();
                    return tn != null && tn.equalsIgnoreCase("project");
                })
                .map(tag -> tag.getContent().toText().trim())
                .filter(s -> !s.isEmpty())
                .findFirst();
    }

    /**
     * Devuelve la "descripción completa" del Javadoc, incluyendo los block tags
     * en forma textual, pero ignorando {@code @project}.
     */
    public static String extractFullDescription(Optional<JavadocComment> maybeComment) {
        if (maybeComment == null || maybeComment.isEmpty()) return "";

        Javadoc javadoc = parseCleaning(maybeComment.get());
        StringBuilder sb = new StringBuilder();

        String main = javadoc.getDescription().toText().trim();
        if (!main.isEmpty()) {
            sb.append(main);
        }

        javadoc.getBlockTags().stream()
                .filter(tag -> {
                    String name = tag.getTagName();
                    return name == null || !name.equalsIgnoreCase("project");
                })
                .forEach(tag -> {
                    if (sb.length() > 0) sb.append("\n");
                    String name = tag.getTagName();
                    if (name == null) name = "";
                    String content = tag.getContent().toText().trim();
                    sb.append("@").append(name);
                    if (!content.isEmpty()) {
                        sb.append(" ").append(content);
                    }
                });

        return sb.toString().trim();
    }
}
