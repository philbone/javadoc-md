package io.github.philbone.javadocmd.model;

import com.github.javaparser.javadoc.Javadoc;


/**
 * Representa una Etiqueta(Tag) en el modelo
 * 
 * 
 * 
 * @author <a target="_blank" href="https://github.com/philbone">Felipe M</a> <philbone@focused.cl>
 */
public class DocTag
{
    private String name;
    private String description;

    public DocTag(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }    
}
