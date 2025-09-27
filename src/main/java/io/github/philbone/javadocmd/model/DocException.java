package io.github.philbone.javadocmd.model;

public class DocException
{
    private String name;
    private String description;

    public DocException(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
}
