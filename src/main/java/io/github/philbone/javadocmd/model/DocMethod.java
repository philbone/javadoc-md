package io.github.philbone.javadocmd.model;

import java.util.List;

public class DocMethod
{
    private String name;
    private String description;
    private List<String> parameters;
    private String returnType;

    public DocMethod(String name, String description, List<String> parameters, String returnType) {
        this.name = name;
        this.description = description;
        this.parameters = parameters;
        this.returnType = returnType;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public List<String> getParameters() { return parameters; }
    public String getReturnType() { return returnType; }
}