package io.github.philbone.javadocmd.exporter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utilidad para convertir nombres de tipos de Java en enlaces
 * a la documentación oficial de la API de Java SE.
 *
 * <p>Ejemplo:</p>
 * <pre>{@code
 * JavaApiLinker.linkIfJavaType("List<String>");
 * // → [List](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/List.html)<String>
 * }</pre>
 * @author Felipe M.
 */
public class JavaApiLinker
{

    /** Versión base de la documentación de Java. */
    private static final String BASE_URL = "https://docs.oracle.com/en/java/javase/17/docs/api/";

    /** Patrón para detectar tipos genéricos (por ejemplo, List<String>) */
    private static final Pattern GENERIC_PATTERN = Pattern.compile("^(\\w+(?:\\.\\w+)*)(<.*>)?$");

    /**
     * Si el tipo pertenece al paquete estándar de Java (java.* o javax.*),
     * devuelve un enlace Markdown al Javadoc oficial.
     * De lo contrario, devuelve el tipo original sin enlace.
     */
    public static String linkIfJavaType(String type) {
        if (type == null || type.isBlank()) {
            return "";
        }

        // Limpiar espacios innecesarios
        type = type.trim();

        // Extraer parte principal (sin genéricos)
        Matcher matcher = GENERIC_PATTERN.matcher(type);
        if (!matcher.find()) {
            return type;
        }

        String rawType = matcher.group(1);
        String generics = matcher.group(2) != null ? matcher.group(2) : "";

        // Resolver alias comunes (sin paquete)
        String fqcn = resolveToFQCN(rawType);

        if (fqcn == null) {
            return type; // no es un tipo conocido de la API estándar
        }

        String url = fqcnToUrl(fqcn);
        String simpleName = rawType.contains(".")
                ? rawType.substring(rawType.lastIndexOf('.') + 1)
                : rawType;

        return String.format("[%s](%s)%s", simpleName, url, generics);
    }

    /**
     * Convierte un nombre de clase totalmente calificado en URL al Javadoc.
     */
    private static String fqcnToUrl(String fqcn) {
        // Ejemplo: java.util.List -> https://docs.oracle.com/.../java.base/java/util/List.html
        String pkg = fqcn.substring(0, fqcn.lastIndexOf('.'));
        String simple = fqcn.substring(fqcn.lastIndexOf('.') + 1);
        String module = determineModule(pkg);

        return BASE_URL + module + "/" + pkg.replace('.', '/') + "/" + simple + ".html";
    }

    /**
     * Determina el módulo de Java donde reside un paquete.
     * Esto cubre los módulos más usados en Java SE 17.
     */
    private static String determineModule(String pkg) {
        if (pkg.startsWith("java.lang") || pkg.startsWith("java.util") ||
            pkg.startsWith("java.io") || pkg.startsWith("java.net") ||
            pkg.startsWith("java.time") || pkg.startsWith("java.math") ||
            pkg.startsWith("java.text")) {
            return "java.base";
        }
        if (pkg.startsWith("javax")) {
            return "java.desktop";
        }
        return "java.base"; // fallback
    }

    /**
     * Intenta mapear un tipo simple (como "List") a su nombre de clase completo.
     * Solo incluye clases comunes de la API estándar.
     */
    private static String resolveToFQCN(String type) {
        return switch (type) {
            case "String" -> "java.lang.String";
            case "Object" -> "java.lang.Object";
            case "List" -> "java.util.List";
            case "ArrayList" -> "java.util.ArrayList";
            case "LinkedList" -> "java.util.LinkedList";
            case "Set" -> "java.util.Set";
            case "HashSet" -> "java.util.HashSet";
            case "Map" -> "java.util.Map";
            case "HashMap" -> "java.util.HashMap";
            case "Optional" -> "java.util.Optional";
            case "Stream" -> "java.util.stream.Stream";
            case "Instant" -> "java.time.Instant";
            case "LocalDate" -> "java.time.LocalDate";
            case "LocalDateTime" -> "java.time.LocalDateTime";
            case "File" -> "java.io.File";
            case "IOException" -> "java.io.IOException";
            case "Throwable" -> "java.lang.Throwable";
            case "Exception" -> "java.lang.Exception";
            default -> {
                if (type.startsWith("java.") || type.startsWith("javax.")) {
                    yield type;
                }
                yield null; // No pertenece a Java SE
            }
        };
    }

}
