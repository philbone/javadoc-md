package io.github.philbone.javadocmd.model;

import java.util.ArrayList;
import java.util.List;

public class DocMethod 
{
    private final String name;
    private final String returnType;
    private final List<String> parameters;
    private final String description;
    private final String visibility;
    private final boolean isStatic;
    private final boolean isVoid;
    private String returnDescription;
    private final List<DocParameter> docParameters = new ArrayList<>();
    private final List<DocException> exceptions = new ArrayList<>();

    public DocMethod(String name, String returnType, List<String> parameters, String description, String visibility, boolean isStatic, boolean isVoid) {
        this.name = name;
        this.returnType = returnType;
        this.parameters = parameters;
        this.description = description;
        this.visibility = visibility;
        this.isStatic = isStatic;
        this.isVoid = isVoid;
    }

    public String getName() { return name; }
    public String getReturnType() { return returnType; }
    public List<String> getParameters() { return parameters; }
    public String getDescription() { return description; }
    public String getVisibility() { return visibility; }
    public boolean isStatic() { return isStatic; }
    public boolean isVoid() { return isVoid; }

    public void setReturnDescription(String returnDescription) { this.returnDescription = returnDescription; }
    public String getReturnDescription() { return returnDescription; }

    public void addDocParameter(DocParameter param) { docParameters.add(param); }
    public List<DocParameter> getDocParameters() { return docParameters; }

    public void addException(DocException exception) { exceptions.add(exception); }
    public List<DocException> getExceptions() { return exceptions; }
}
