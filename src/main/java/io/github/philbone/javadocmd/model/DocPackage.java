package io.github.philbone.javadocmd.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa un paquete de Java dentro del modelo intermedio de documentación.
 * <p>
 * Esta clase agrupa todas las {@link DocClass} (clases, interfaces, enums y records)
 * pertenecientes a un mismo paquete, junto con su nombre.
 * Es utilizada como unidad base por los exportadores para generar la documentación.
 * </p>
 *
 * <h2>Responsabilidades:</h2>
 * <ul>
 *   <li>Almacenar el nombre del paquete analizado.</li>
 *   <li>Contener la colección de clases, interfaces, enums y records del paquete.</li>
 *   <li>Proveer métodos para acceder y agregar clases al paquete.</li>
 * </ul>
 *
 * <h2>Uso típico:</h2>
 * Un {@code DocPackage} se crea durante la fase de extracción de Javadoc
 * y posteriormente es consumido por un {@code DocExporter} para generar la salida
 * (por ejemplo, en formato Markdown).
 *
 * <pre>{@code
 * DocPackage pkg = new DocPackage("io.github.philbone.javadocmd.exporter");
 * pkg.addClass(new DocClass("MarkdownExporter", "...", Kind.CLASS, "public", false));
 * }</pre>
 *
 * @project JavadocMd
 * @author Felipe M. philbone@focused.cl
 * 
 */
public class DocPackage {

    /** Nombre completo del paquete (ejemplo: {@code io.github.philbone.javadocmd.exporter}). */
    private final String name;

    /** Conjunto de clases, interfaces, enums y records pertenecientes al paquete. */
    private final List<DocClass> classes = new ArrayList<>();
    private String projectName;

    /**
     * Crea un nuevo descriptor de paquete.
     *
     * @param name nombre del paquete en notación estándar de Java.
     */
    public DocPackage(String name) {
        this.name = name;
    }

    /**
     * Obtiene el nombre del paquete.
     *
     * @return nombre completo del paquete.
     */
    public String getName() {
        return name;
    }

    /**
     * Devuelve la lista de clases, interfaces, enums y records que pertenecen al paquete.
     * <p>
     * La lista devuelta es la instancia interna; se recomienda usar
     * {@link #addClass(DocClass)} para agregar elementos en lugar de modificarla directamente.
     * </p>
     *
     * @return lista de clases del paquete.
     */
    public List<DocClass> getClasses() {
        return classes;
    }

    /**
     * Agrega una nueva clase, interfaz, enum o record al paquete.
     *
     * @param docClass instancia de {@link DocClass} a agregar.
     */
    public void addClass(DocClass docClass) {
        classes.add(docClass);
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectName() {
        return projectName;
    }
}
