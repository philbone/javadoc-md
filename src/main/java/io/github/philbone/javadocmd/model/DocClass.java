package io.github.philbone.javadocmd.model;

import java.util.ArrayList;
import java.util.List;

public class DocClass
{
    private final String name;
    private final String description;
    private final Kind kind;
    private final String visibility;
    private final boolean isStatic;

    private final List<DocField> fields = new ArrayList<>();
    private final List<DocMethod> methods = new ArrayList<>();
    private final List<DocConstructor> constructors = new ArrayList<>();

    // 🔹 Nueva info
    private String superClass;                 // nombre de la clase padre, si existe
    private final List<String> interfaces = new ArrayList<>(); // interfaces implementadas/extendidas

    public DocClass(String name, String description, Kind kind, String visibility, boolean isStatic) {
        this.name = name;
        this.description = description;
        this.kind = kind;
        this.visibility = visibility;
        this.isStatic = isStatic;
    }

    // --- Getters básicos ---
    public String getName() { return name; }
    public String getDescription() { return description; }
    public Kind getKind() { return kind; }
    public String getVisibility() { return visibility; }
    public boolean isStatic() { return isStatic; }

    public List<DocField> getFields() { return fields; }
    public List<DocMethod> getMethods() { return methods; }
    public List<DocConstructor> getConstructors() { return constructors; }

    // --- Manejo de campos, métodos y constructores ---
    public void addField(DocField field) { fields.add(field); }
    public void addMethod(DocMethod method) { methods.add(method); }
    public void addConstructor(DocConstructor constructor) { constructors.add(constructor); }

    // --- Extends / Implements ---
    public String getSuperClass() { return superClass; }
    public void setSuperClass(String superClass) { this.superClass = superClass; }

    public List<String> getInterfaces() { return interfaces; }
    public void addInterface(String iface) { this.interfaces.add(iface); }
}
