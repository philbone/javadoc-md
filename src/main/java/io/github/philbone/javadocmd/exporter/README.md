# JavadocMd

## io.github.philbone.javadocmd.exporter

## Resumen de Clases


|CLASE|DESCRIPCIÓN|
|---|---|
|[public interface DocExporter](#-public-interface-docexporter)|
|[public class MarkdownExporter](#-public-class-markdownexporter)|Exportador que genera documentación en formato Markdown a partir del modelo intermedio construido con {@link io.
|[public class MarkdownBuilder](#-public-class-markdownbuilder)|
|[public class JavaApiLinker](#-public-class-javaapilinker)|Utilidad para convertir nombres de tipos de Java en enlaces a la documentación oficial de la API de Java SE.
## 📗 Public Interface DocExporter

```java
public interface DocExporter
```
### 🧮 Métodos

- `package-private`[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `export(DocPackage docPackage)`
## 📘 Public Class MarkdownExporter

```java
public class MarkdownExporter
implements DocExporter
```
> **Descripción:**
> Exportador que genera documentación en formato Markdown
> a partir del modelo intermedio construido con
> {@link io.github.philbone.javadocmd.extractor.JavadocExtractorVisitor}.
> 
> <p>Renderiza:</p>
> <ul>
>     <li>Firma de la clase (visibilidad, static, tipo, nombre).</li>
>     <li>Extensiones ({@code extends}) e implementaciones ({@code implements}).</li>
>     <li>Descripción general de la clase.</li>
>     <li>Campos, constructores y métodos con sus firmas y documentación Javadoc.</li>
> </ul>

### 📦 Campos

- `private static` int `COLLAPSE_THRESHOLD`
> Número mínimo de clases dentro de un paquete para activar el modo colapsable.
> Si el paquete tiene más de este número, cada clase se renderiza dentro de un bloque `<details>`.

- `private` JavaApiLinker `apiLinker`
### 🧮 Métodos

- `public`[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `export(DocPackage docPackage)`
- `private`[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `formatCodeOrLink(String type)`
> Si el tipo tiene enlace conocido, devuelve el link Markdown. Si no, lo envuelve en `code`.

- `private`[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `formatKind(Kind kind)`
- `private`[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `capitalize(String s)`
- `private`[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `formatEmoji(Kind kind)`
## 📘 Public Class MarkdownBuilder

```java
public class MarkdownBuilder
```
### 📦 Campos

- `private` StringBuilder `sb`
### 🧮 Métodos

- `public`MarkdownBuilder `title(String text)`
- `public`MarkdownBuilder `subtitle(String text)`
- `public`MarkdownBuilder `h3(String text)`
- `public`MarkdownBuilder `h4(String text)`
- `public`MarkdownBuilder `paragraph(String text)`
- `public`MarkdownBuilder `listItem(String text)`
- `public`[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `build()`
- `public` **void** `codeBlock(String content, String codeLang)`
- `public`MarkdownBuilder `blockquote(String text)`
- `public`MarkdownBuilder `tag(String tag)`
> Inyecta una etiqueta arbitraria directamente en el flujo del Markdown.
> <p>
> Se utiliza principalmente como auxiliar para aplicar prefijos en las
> siguientes líneas, por ejemplo:
> <ul>
> <li>{@code "> "} para iniciar un bloque de cita.</li>
> <li>{@code "- "} para forzar un ítem de lista.</li>
> <li>{@code ">> "} para una cita anidada.</li>
> </ul>
> </p>
> <p>
> Este método no agrega saltos de línea ni contenido adicional, únicamente
> inserta el texto indicado de forma literal.
> </p>

> - *@param* **tag** la etiqueta o prefijo a inyectar (se añade tal cual al
buffer).
> - *@return* la instancia actual de {@code MarkdownBuilder}, para encadenar
llamadas.
- `public`MarkdownBuilder `toc(DocPackage docPackage)`
- `private`[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `sanitizeDescription(String raw)`
## 📘 Public Class JavaApiLinker

```java
public class JavaApiLinker
```
> **Descripción:**
> Utilidad para convertir nombres de tipos de Java en enlaces
> a la documentación oficial de la API de Java SE.
> 
> <p>Ejemplo:</p>
> <pre>{@code
> JavaApiLinker.linkIfJavaType("List<String>");
> // → [List](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/List.html)<String>
> }</pre>

### 📦 Campos

- `private static` [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `BASE_URL`
> Versión base de la documentación de Java.

- `private static` Pattern `GENERIC_PATTERN`
> Patrón para detectar tipos genéricos (por ejemplo, List<String>)

### 🧮 Métodos

- `public static`[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `linkIfJavaType(String type)`
> Si el tipo pertenece al paquete estándar de Java (java.* o javax.*),
> devuelve un enlace Markdown al Javadoc oficial.
> De lo contrario, devuelve el tipo original sin enlace.

- `private static`[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `fqcnToUrl(String fqcn)`
> Convierte un nombre de clase totalmente calificado en URL al Javadoc.

- `private static`[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `determineModule(String pkg)`
> Determina el módulo de Java donde reside un paquete.
> Esto cubre los módulos más usados en Java SE 17.

- `private static`[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html) `resolveToFQCN(String type)`
> Intenta mapear un tipo simple (como "List") a su nombre de clase completo.
> Solo incluye clases comunes de la API estándar.

