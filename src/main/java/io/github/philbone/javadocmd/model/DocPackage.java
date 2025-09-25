package io.github.philbone.javadocmd.model;

import java.util.ArrayList;
import java.util.List;

public class DocPackage
{
    private String name;
    private List<DocClass> classes = new ArrayList<>();

    public DocPackage(String name) {
        this.name = name;
    }

    public String getName() { return name; }
    public List<DocClass> getClasses() { return classes; }

    public void addClass(DocClass docClass) {
        classes.add(docClass);
    }
}
