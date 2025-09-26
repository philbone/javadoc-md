package io.github.philbone.javadocmd.model;

import java.util.ArrayList;
import java.util.List;

public class DocClass
{
    private String name;
    private String description;
    private String kind; // "class" o "interface"
    private List<DocMethod> methods = new ArrayList<>();

    public DocClass(String name, String description, String kind) {
        this.name = name;
        this.description = description;
        this.kind = kind;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getKind() { return kind; }
    public List<DocMethod> getMethods() { return methods; }

    public void addMethod(DocMethod method) {
        methods.add(method);
    }
}
