package io.github.philbone.javadocmd.model;

import java.util.ArrayList;
import java.util.List;

public class DocClass
{
    private String name;
    private String description;
    private List<DocMethod> methods = new ArrayList<>();

    public DocClass(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public List<DocMethod> getMethods() { return methods; }

    public void addMethod(DocMethod method) {
        methods.add(method);
    }
}
