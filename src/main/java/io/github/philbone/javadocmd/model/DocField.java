package io.github.philbone.javadocmd.model;

/**
 * Representa un campo (atributo) documentado dentro de una clase.
 */
public class DocField 
{
    private final String name;
    private final String type;
    private final String description;
    private final String visibility;
    private final boolean isStatic;

    public DocField(String name, String type, String description, String visibility, boolean isStatic) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.visibility = visibility;
        this.isStatic = isStatic;
    }

    public String getName() { return name; }
    public String getType() { return type; }
    public String getDescription() { return description; }
    public String getVisibility() { return visibility; }
    public boolean isStatic() { return isStatic; }
}
