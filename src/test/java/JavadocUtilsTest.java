import io.github.philbone.javadocmd.extractor.JavadocUtils;
import com.github.javaparser.ast.comments.JavadocComment;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

class JavadocUtilsTest {

    // ======== extractProjectTag ======== //

    @Test
    void extractProjectTag_debeExtraerNombreDelProyecto() {
        String source = """
                /**
                 * @project JavadocMd
                 * Esta clase genera documentación Markdown.
                 */
                """;
        JavadocComment comment = new JavadocComment(source);
        Optional<String> project = JavadocUtils.extractProjectTag(Optional.of(comment));

        assertTrue(project.isPresent());
        assertEquals("JavadocMd", project.get());
    }

    @Test
    void extractProjectTag_debeRetornarVacioSiNoHayProject() {
        String source = """
                /**
                 * Clase sin tag de proyecto.
                 */
                """;
        JavadocComment comment = new JavadocComment(source);
        Optional<String> project = JavadocUtils.extractProjectTag(Optional.of(comment));

        assertTrue(project.isEmpty());
    }

    // ======== extractFullDescription ======== //

    @Test
    void extractFullDescription_debeIncluirTextoYTags() {
        String source = """
                /**
                 * Clase encargada de procesar datos.
                 *
                 * @author Felipe
                 * @version 1.0
                 */
                """;
        JavadocComment comment = new JavadocComment(source);
        String result = JavadocUtils.extractFullDescription(Optional.of(comment));

        assertTrue(result.contains("Clase encargada de procesar datos."));
        assertTrue(result.contains("@author"));
        assertTrue(result.contains("@version"));
    }

    @Test
    void extractFullDescription_debeExcluirTagProject() {
        String source = """
                /**
                 * @project JavadocMd
                 * Este es el extractor de javadoc.
                 */
                """;
        JavadocComment comment = new JavadocComment(source);
        String result = JavadocUtils.extractFullDescription(Optional.of(comment));

        // Debe eliminar el tag @project
        assertFalse(result.contains("@project"));
        // Debe mantener el texto descriptivo
        assertEquals("Este es el extractor de javadoc.", result.trim());
    }

    @Test
    void extractFullDescription_debeManejarDescripcionSinTags() {
        String source = """
                /**
                 * Clase simple sin tags adicionales.
                 */
                """;
        JavadocComment comment = new JavadocComment(source);
        String result = JavadocUtils.extractFullDescription(Optional.of(comment));

        assertEquals("Clase simple sin tags adicionales.", result.trim());
    }
}
