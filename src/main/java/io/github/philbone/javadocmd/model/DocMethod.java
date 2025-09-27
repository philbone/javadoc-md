package io.github.philbone.javadocmd.model;

import java.util.ArrayList;
import java.util.List;

public class DocMethod
{
    private String name;
    private String returnType;
    private List<String> parameters = new ArrayList<>(); // tipos de parámetros
    private String description;

    private List<DocParameter> docParameters = new ArrayList<>();
    private String returnDescription;
    private List<DocException> exceptions = new ArrayList<>();

    public DocMethod(String name, String returnType, List<String> parameters, String description) {
        this.name = name;
        this.returnType = returnType;
        this.parameters = parameters;
        this.description = description;
    }

    public String getName() { return name; }
    public String getReturnType() { return returnType; }
    public List<String> getParameters() { return parameters; }
    public String getDescription() { return description; }

    public List<DocParameter> getDocParameters() { return docParameters; }
    public void addDocParameter(DocParameter param) { docParameters.add(param); }

    public String getReturnDescription() { return returnDescription; }
    public void setReturnDescription(String returnDescription) {
        this.returnDescription = returnDescription;
    }

    public List<DocException> getExceptions() { return exceptions; }
    public void addException(DocException exception) { exceptions.add(exception); }
}
