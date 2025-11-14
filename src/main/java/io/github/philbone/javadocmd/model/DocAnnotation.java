package io.github.philbone.javadocmd.model;

import java.util.*;

/**
 * Representación ligera de una anotación Java extraída del AST.
 * Se guarda la información mínima necesaria para renderizar y para
 * posibles mejoras futuras (resolución de FQNs, etc).
 *
 * Diseñada para integrarse en DocClass, DocMethod, DocField, ...
 * (para la primera entrega se añadirá en DocClass y DocMethod).
 * 
 * @author Felipe M. <philbone@focused.cl>
 */
public class DocAnnotation
{
    private final String name; // nombre simple tal como aparece en el código, p.ej. "RequestMapping"
    private final String raw;  // representación completa tal cual AST.toString()
    private final Map<String, String> members; // pares clave -> valor-as-string (normal annotation)
    private final List<String> values; // para single-member o arrays sin clave
    private final boolean marker; // true si es MarkerAnnotationExpr (@Override)
    private final String fqName; // opcional, null si no se resolvió

    public DocAnnotation(String name, String raw, Map<String,String> members, List<String> values, boolean marker, String fqName) {
        this.name = name;
        this.raw = raw;
        this.members = members != null ? new LinkedHashMap<>(members) : new LinkedHashMap<>();
        this.values = values != null ? new ArrayList<>(values) : new ArrayList<>();
        this.marker = marker;
        this.fqName = fqName;
    }

    public DocAnnotation(String name, String raw) {
        this(name, raw, null, null, false, null);
    }

    public String getName() {
        return name;
    }

    public String getRaw() {
        return raw;
    }

    public Map<String, String> getMembers() {
        return Collections.unmodifiableMap(members);
    }

    public List<String> getValues() {
        return Collections.unmodifiableList(values);
    }

    public boolean isMarker() {
        return marker;
    }

    public Optional<String> getFqName() {
        return Optional.ofNullable(fqName);
    }

    /**
     * Render compacto (inline) por defecto, ej:
     * @Name
     * @Name("x")
     * @Name(a = "b", c = 1)
     */
    public String renderCompact() {
        if (marker) {
            return "@" + name;
        }
        if (!values.isEmpty() && members.isEmpty()) {
            if (values.size() == 1) {
                return "@" + name + "(" + values.get(0) + ")";
            } else {
                return "@" + name + "(" + String.join(", ", values) + ")";
            }
        }
        if (!members.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            sb.append("@").append(name).append("(");
            boolean first = true;
            for (Map.Entry<String,String> e : members.entrySet()) {
                if (!first) sb.append(", ");
                sb.append(e.getKey()).append(" = ").append(e.getValue());
                first = false;
            }
            sb.append(")");
            return sb.toString();
        }
        // fallback: raw
        return raw != null ? raw : "@" + name;
    }

    @Override
    public String toString() {
        return renderCompact();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DocAnnotation)) return false;
        DocAnnotation that = (DocAnnotation) o;
        return marker == that.marker &&
                Objects.equals(name, that.name) &&
                Objects.equals(raw, that.raw) &&
                Objects.equals(members, that.members) &&
                Objects.equals(values, that.values) &&
                Objects.equals(fqName, that.fqName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, raw, members, values, marker, fqName);
    }
}
