package io.github.philbone.javadocmd.model;

/**
 * 
 * @author Felipe M. philbone@focused.cl
 */
public class DocParameter
{
    private String name;
    private String description;

    public DocParameter(String name, String description) {
        this.name = name;
        this.description = description;
    }
    
    public String getName() {
        return name;
    }
    public String getDescription() { return description; }
}