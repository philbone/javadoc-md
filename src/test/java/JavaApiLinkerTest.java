
import io.github.philbone.javadocmd.exporter.JavaApiLinker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para {@link JavaApiLinker}.
 */
public class JavaApiLinkerTest {

    @Test
    void debeEnlazarTipoBasicoJava() {
        String result = JavaApiLinker.linkIfJavaType("List");
        assertTrue(result.contains("[List]("));
        assertTrue(result.contains("java/util/List.html"));
    }

    @Test
    void debeEnlazarTipoGenericoJava() {
        String result = JavaApiLinker.linkIfJavaType("List<String>");
        assertTrue(result.startsWith("[List]("));
        assertTrue(result.endsWith("<String>"));
    }

    @Test
    void debeEnlazarTipoConPaqueteCompleto() {
        String result = JavaApiLinker.linkIfJavaType("java.time.LocalDate");
        assertTrue(result.contains("[LocalDate]("));
        assertTrue(result.contains("java/time/LocalDate.html"));
    }

    @Test
    void noDebeEnlazarTiposCustom() {
        String result = JavaApiLinker.linkIfJavaType("MiClaseCustom");
        assertEquals("MiClaseCustom", result);
    }

    @Test
    void debeIgnorarEspaciosYRetornarVacio() {
        assertEquals("", JavaApiLinker.linkIfJavaType("   "));
        assertEquals("", JavaApiLinker.linkIfJavaType(null));
    }

    @Test
    void debeEnlazarMapYHashMap() {
        String map = JavaApiLinker.linkIfJavaType("Map");
        String hashMap = JavaApiLinker.linkIfJavaType("HashMap");

        assertTrue(map.contains("java/util/Map.html"));
        assertTrue(hashMap.contains("java/util/HashMap.html"));
    }

    @Test
    void debeEnlazarTiposDelPaqueteLang() {
        String string = JavaApiLinker.linkIfJavaType("String");
        String exception = JavaApiLinker.linkIfJavaType("Exception");

        assertTrue(string.contains("java/lang/String.html"));
        assertTrue(exception.contains("java/lang/Exception.html"));
    }

}