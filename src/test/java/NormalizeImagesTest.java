
import io.github.philbone.javadocmd.extractor.JavadocUtils;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author felipe
 */
public class NormalizeImagesTest
{
    
    @Test
    void normalizeImages_debeConvertirImgConAlt() {
        String input = "Diagrama general: <img src=\"img/diagram.png\" alt=\"Diagrama del sistema\">";
        String expected = "Diagrama general: ![Diagrama del sistema](img/diagram.png)";
        assertEquals(expected, JavadocUtils.normalizeImages(input));
    }

    @Test
    void normalizeImages_debeConvertirImgSinAlt() {
        String input = "Ejemplo: <img src=\"docs/example.png\">";
        String expected = "Ejemplo: ![](docs/example.png)";
        assertEquals(expected, JavadocUtils.normalizeImages(input));
    }

    @Test
    void normalizeImages_noDebeAlterarTextoSinImg() {
        String input = "Texto sin imágenes";
        assertEquals(input, JavadocUtils.normalizeImages(input));
    }

    @Test
    void normalizeImages_debeConvertirMultiplesImg() {
        String input = "Primera <img src=\"a.png\" alt=\"A\"> y segunda <img src=\"b.png\">";
        String expected = "Primera ![A](a.png) y segunda ![](b.png)";
        assertEquals(expected, JavadocUtils.normalizeImages(input));
    }
    
     @Test
    void normalizeImages_debeConvertirEtiquetasImgHttpYHttps() {
        // given
        String input = """
            Esta es una prueba con imágenes:
            <img src="http://example.com/uno.png" alt="Uno">
            <img src="https://cdn.example.com/dos.jpg">
            Texto final.
            """;

        // when
        String output = JavadocUtils.normalizeImages(input);

        // then
        assertTrue(output.contains("![Uno](http://example.com/uno.png)"),
                "Debe convertir la imagen con atributo alt en Markdown");
        assertTrue(output.contains("![](https://cdn.example.com/dos.jpg)"),
                "Debe convertir la imagen sin atributo alt correctamente");
        assertTrue(output.startsWith("Esta es una prueba"),
                "Debe conservar el texto anterior a las imágenes");
        assertTrue(output.endsWith("Texto final."),
                "Debe conservar el texto posterior a las imágenes");
    }
}
