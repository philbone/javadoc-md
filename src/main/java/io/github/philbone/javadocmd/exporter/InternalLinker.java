package io.github.philbone.javadocmd.exporter;

import java.util.*;

/**
 * InternalLinker: convierte nombres de tipo en enlaces internos a la
 * documentación generada por JavadocMd.
 *
 * - Soporta tipos simples y FQCNs.
 * - Normaliza genéricos y arrays: List<Config> -> Config
 * - Mapea simpleName -> fqn(s) para búsquedas rápidas.
 */
public class InternalLinker {

    private final Set<String> internalClasses; // puede ser FQCNs o simples, según cómo lo inicialices
    private final Map<String, List<String>> simpleToFqns;
    private final String extension;
    private final boolean debug;

    public InternalLinker(Set<String> internalClasses, String extension) {
        this(internalClasses, extension, false);
    }

    public InternalLinker(Set<String> internalClasses, String extension, boolean debug) {
        this.internalClasses = new HashSet<>();
        this.simpleToFqns = new HashMap<>();
        this.extension = extension.startsWith(".") ? extension : "." + extension;
        this.debug = debug;

        // Normalizar el set recibido y construir mapa simpleName -> fqn(s).
        for (String raw : internalClasses) {
            if (raw == null || raw.isBlank()) continue;
            String s = raw.trim();
            this.internalClasses.add(s); // mantengo la forma original (puede ser "Config" o "io.x.y.Config")

            String simple = extractSimpleName(s);
            simpleToFqns.computeIfAbsent(simple, k -> new ArrayList<>()).add(s);

            if (debug) {
                System.err.println(">> InternalLinker registered: [" + s + "] as simpleName [" + simple + "]");
            }
        }
    }

    /**
     * Devuelve un enlace Markdown si el tipo pertenece al proyecto JavadocMd.
     * Si no hay coincidencia, retorna null.
     */
    public String linkIfInternalType(String typeName) {
        if (typeName == null || typeName.isBlank()) {
            return null;
        }

        if (debug) {
            System.err.println(">> [linkIfInternalType] buscando coincidencia exacta para: " + typeName);
        }

        for (String internal : internalClasses) {
            if (internal.equals(typeName) || internal.endsWith("." + typeName)) {
                if (debug) {
                    System.err.println(">> [linkIfInternalType] MATCH encontrado: " + internal);
                }
                return buildLink(internal);
            }
        }

        if (debug) {
            System.err.println(">> [linkIfInternalType] sin coincidencia: " + typeName);
        }
        return null;
        // Nota técnica:
        // Se eliminó la versión anterior con normalización y mapas de búsqueda
        // porque en proyectos pequeños (como JavadocMd) bastaba con una coincidencia
        // simple o por sufijo. Además, la versión anterior causaba falsos negativos
        // cuando el InternalLinker se instanciaba por paquete.
    }

    private String normalizeTypeName(String raw) {
        String s = raw.trim();
        // eliminar genéricos complejos (usa reluctant)
        s = s.replaceAll("<.*?>", "");
        // eliminar arrays
        s = s.replace("[]", "");
        // eliminar espacios redundantes
        s = s.trim();
        return s;
    }

    private String extractSimpleName(String fqnOrSimple) {
        int idx = fqnOrSimple.lastIndexOf('.');
        if (idx >= 0 && idx < fqnOrSimple.length() - 1) {
            return fqnOrSimple.substring(idx + 1);
        } else {
            return fqnOrSimple;
        }
    }

    private String buildLink(String fqn) {
        // Si fqn contiene puntos -> asumimos FQCN y construimos ruta completa
        String path;
        if (fqn.contains(".")) {
            path = fqn.replace('.', '/') + extension;
        } else {
            // Si solo es simpleName, lo dejamos relativo al mismo directorio
            path = fqn + extension;
        }
        String simpleName = extractSimpleName(fqn);
        if (debug) System.err.println(">> buildLink: fqn=" + fqn + " -> path=" + path);
        // desactiva temporalmente la generación de enlaces internos
        //return String.format("[%s](%s)", simpleName, path);
        return String.format("**%s**", simpleName);
    }

    public int size() {
        return this.internalClasses.size();
    }
}
