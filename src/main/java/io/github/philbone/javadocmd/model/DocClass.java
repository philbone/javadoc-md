package io.github.philbone.javadocmd.model;

import java.util.ArrayList;
import java.util.List;

public class DocClass {
    private final String name;
    private final String description;
    private final Kind kind;
    private final String visibility;
    private final boolean isStatic;

    private final List<DocField> fields = new ArrayList<>();
    private final List<DocConstructor> constructors = new ArrayList<>();
    private final List<DocMethod> methods = new ArrayList<>();

    public DocClass(String name, String description, Kind kind, String visibility, boolean isStatic) {
        this.name = name;
        this.description = description;
        this.kind = kind;
        this.visibility = visibility;
        this.isStatic = isStatic;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public Kind getKind() { return kind; }
    public String getVisibility() { return visibility; }
    public boolean isStatic() { return isStatic; }

    public void addField(DocField field) { fields.add(field); }
    public List<DocField> getFields() { return fields; }

    public void addConstructor(DocConstructor constructor) { constructors.add(constructor); }
    public List<DocConstructor> getConstructors() { return constructors; }

    public void addMethod(DocMethod method) { methods.add(method); }
    public List<DocMethod> getMethods() { return methods; }
}
