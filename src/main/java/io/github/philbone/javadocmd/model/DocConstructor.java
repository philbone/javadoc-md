package io.github.philbone.javadocmd.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa un constructor documentado dentro de una clase.
 */
public class DocConstructor 
{
    private final String name;
    private final List<String> parameters;
    private final String description;
    private final String visibility;
    private final boolean isStatic; // casi siempre false, pero lo dejamos para consistencia
    private final List<DocParameter> docParameters = new ArrayList<>();
    private final List<DocException> exceptions = new ArrayList<>();

    public DocConstructor(String name, List<String> parameters, String description, String visibility, boolean isStatic) {
        this.name = name;
        this.parameters = parameters;
        this.description = description;
        this.visibility = visibility;
        this.isStatic = isStatic;
    }

    public String getName() { return name; }
    public List<String> getParameters() { return parameters; }
    public String getDescription() { return description; }
    public String getVisibility() { return visibility; }
    public boolean isStatic() { return isStatic; }

    public void addDocParameter(DocParameter param) { docParameters.add(param); }
    public List<DocParameter> getDocParameters() { return docParameters; }

    public void addException(DocException exception) { exceptions.add(exception); }
    public List<DocException> getExceptions() { return exceptions; }
}
