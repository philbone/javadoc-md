package io.github.philbone.javadocmd.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa la definición de una clase, interfaz, enum o record dentro del modelo intermedio de documentación.
 * <p>
 * Esta entidad encapsula la información esencial que puede obtenerse de una declaración de tipo en código fuente Java, incluyendo:
 * </p>
 * <ul>
 *   <li>Nombre, visibilidad y tipo (clase, interfaz, enum, record, abstracta).</li>
 *   <li>Descripción proveniente de la documentación Javadoc asociada.</li>
 *   <li>Lista de campos, métodos y constructores.</li>
 *   <li>Clase padre extendida y/o interfaces implementadas o extendidas.</li>
 *   <li>Indicador de si la clase es estática.</li>
 * </ul>
 * <p>
 * La información contenida en esta clase es utilizada por los exportadores (por ejemplo, {@code MarkdownExporter}) para generar documentación en distintos formatos.
 * </p>
 *
 * @author Felipe M. philbone@focused.cl
 * @see DocField
 * @see DocMethod
 * @see DocConstructor
 * @see Kind
 */
public class DocClass {

    /** Nombre simple de la clase, interfaz, enum o record. */
    private final String name;

    /** Descripción principal tomada del comentario Javadoc asociado. */
    private final String description;

    /** Tipo de elemento representado (clase, interfaz, enum, record, abstracta). */
    private final Kind kind;

    /** Nivel de visibilidad del tipo (public, protected, package-private, private). */
    private final String visibility;

    /** Indica si el tipo ha sido declarado como {@code static}. */
    private final boolean isStatic;

    /** Campos declarados dentro de la clase. */
    private final List<DocField> fields = new ArrayList<>();

    /** Métodos declarados dentro de la clase. */
    private final List<DocMethod> methods = new ArrayList<>();

    /** Constructores declarados dentro de la clase. */
    private final List<DocConstructor> constructors = new ArrayList<>();

    /** Nombre de la clase padre (superclase), si existe. */
    private String superClass;

    /** Interfaces implementadas (clases) o extendidas (interfaces). */
    private final List<String> interfaces = new ArrayList<>();

    /**
     * Crea una nueva representación de clase en el modelo intermedio.
     *
     * @param name        nombre simple de la clase
     * @param description descripción principal (desde Javadoc)
     * @param kind        tipo del elemento (clase, interfaz, enum, record)
     * @param visibility  nivel de visibilidad (public, protected, package-private, private)
     * @param isStatic    indica si la clase fue declarada como {@code static}
     */
    public DocClass(String name, String description, Kind kind, String visibility, boolean isStatic) {
        this.name = name;
        this.description = description;
        this.kind = kind;
        this.visibility = visibility;
        this.isStatic = isStatic;
    }

    // --- Getters básicos ---

    /** @return el nombre simple de la clase. */
    public String getName() { return name; }

    /** @return la descripción tomada del Javadoc. */
    public String getDescription() { return description; }

    /** @return el tipo de elemento representado. */
    public Kind getKind() { return kind; }

    /** @return la visibilidad del tipo (public, protected, etc.). */
    public String getVisibility() { return visibility; }

    /** @return {@code true} si la clase fue declarada como estática. */
    public boolean isStatic() { return isStatic; }

    /** @return lista inmutable de campos de la clase. */
    public List<DocField> getFields() { return fields; }

    /** @return lista inmutable de métodos de la clase. */
    public List<DocMethod> getMethods() { return methods; }

    /** @return lista inmutable de constructores de la clase. */
    public List<DocConstructor> getConstructors() { return constructors; }

    // --- Manejo de campos, métodos y constructores ---

    /**
     * Agrega un campo al modelo de la clase.
     *
     * @param field definición del campo
     */
    public void addField(DocField field) { fields.add(field); }

    /**
     * Agrega un método al modelo de la clase.
     *
     * @param method definición del método
     */
    public void addMethod(DocMethod method) { methods.add(method); }

    /**
     * Agrega un constructor al modelo de la clase.
     *
     * @param constructor definición del constructor
     */
    public void addConstructor(DocConstructor constructor) { constructors.add(constructor); }

    // --- Extends / Implements ---

    /**
     * @return el nombre de la superclase, o {@code null} si no tiene.
     */
    public String getSuperClass() { return superClass; }

    /**
     * Define la superclase de este tipo.
     *
     * @param superClass nombre de la clase padre
     */
    public void setSuperClass(String superClass) { this.superClass = superClass; }

    /**
     * @return lista de interfaces implementadas (clases) o extendidas (interfaces).
     */
    public List<String> getInterfaces() { return interfaces; }

    /**
     * Agrega una interfaz implementada o extendida.
     *
     * @param iface nombre de la interfaz
     */
    public void addInterface(String iface) { this.interfaces.add(iface); }
}
